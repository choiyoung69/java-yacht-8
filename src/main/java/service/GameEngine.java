package service;

import domain.environment.Environment;
import domain.environment.wind.WindEventTrigger;
import domain.environment.wind.WindRandomEventTrigger;
import domain.event.choice.EventOption;
import domain.event.choice.EventOptionSelector;
import domain.event.choice.EventPackage;
import domain.event.choice.EventPackageRepository;
import domain.event.environment.EnvironmentEvent;
import domain.event.environment.EnvironmentEventType;
import domain.yacht.Yacht;
import domain.yacht.YachtInternalEventTrigger;
import dto.TickResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {

    private final Random random = new Random();

    public TickResult runNaturalPhase(
            Environment environment,
            Yacht yacht,
            EventManager eventManager,
            WindEventTrigger naturalTrigger,
            EventPackageRepository repo,
            EventOptionSelector selector,
            int level
    ) {

        environment.updateNaturalAll(random);

        naturalTrigger.apply(environment, eventManager, random);

        EnvironmentEvent event = eventManager.pickNatural();
        eventManager.clear();

        if (event == null) {
            yacht.update();
            return TickResult.none();
        }

        EventPackage pkg = repo.find(event.type());
        List<EventOption> options = selector.selectOptions(pkg, level, random);

        return TickResult.natural(event.type(), pkg.description(), options);
    }

    public void applyNaturalChoice(Yacht yacht, EventOption option) {
        option.apply(yacht);
    }

    public TickResult runRandomPhase(
            Environment environment,
            Yacht yacht,
            EventManager eventManager,
            WindRandomEventTrigger randomTrigger,
            EventPackageRepository repo,
            EventOptionSelector selector,
            int level
    ) {

        randomTrigger.apply(environment, eventManager, random);

        EnvironmentEvent event = eventManager.pickRandom();
        eventManager.clear();

        if (event == null) {
            yacht.update();
            return TickResult.none();
        }

        EventPackage pkg = repo.find(event.type());
        List<EventOption> options = selector.selectOptions(pkg, level, random);

        return TickResult.random(event.type(), pkg.description(), options);
    }

    public void applyRandomChoice(Yacht yacht, EventOption option) {
        option.apply(yacht);
    }

    public List<TickResult> runInternalPhase(
            Yacht yacht,
            YachtInternalEventTrigger internalTrigger,
            EventPackageRepository repo,
            EventOptionSelector selector,
            int level
    ) {
        List<EnvironmentEventType> types = internalTrigger.apply(yacht);
        List<TickResult> results = new ArrayList<>();

        if (types == null || types.isEmpty()) {
            results.add(TickResult.none());
            return results;
        }

        for (EnvironmentEventType type : types) {
            if (type == EnvironmentEventType.YACHT_CAPSIZE ||
                    type == EnvironmentEventType.YACHT_DEAD_STOP) {
                results.add(TickResult.gameOver(type));
                return results;
            }

            EventPackage pkg = repo.find(type);
            List<EventOption> options = selector.selectOptions(pkg, level, random);

            results.add(TickResult.internal(type, pkg.description(), options));
        }

        return results;
    }

    public void applyInternalChoice(Yacht yacht, EventOption option) {
        option.apply(yacht);
    }

    public boolean isFinished(Yacht yacht) {
        return yacht.hasFinished();
    }
}