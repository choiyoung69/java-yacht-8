package app.service;

import app.domain.environment.Environment;
import app.domain.environment.EnvironmentEventTrigger;
import app.domain.environment.wind.WindEventTrigger;
import app.domain.environment.wind.WindRandomEventTrigger;
import app.domain.event.choice.EventOption;
import app.domain.event.choice.EventOptionSelector;
import app.domain.event.choice.EventPackage;
import app.domain.event.choice.EventPackageRepository;
import app.domain.event.environment.EnvironmentEvent;
import app.domain.event.environment.EnvironmentEventType;
import app.domain.yacht.Yacht;
import app.domain.yacht.YachtInternalEventTrigger;
import app.dto.TickResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {

    private final Random random = new Random();

    public TickResult runNaturalPhase(
            Environment environment,
            EventManager eventManager,
            EnvironmentEventTrigger naturalTrigger,
            EventPackageRepository repo,
            EventOptionSelector selector,
            int level
    ) {
        environment.updateNaturalAll(random);
        naturalTrigger.apply(environment, eventManager, random);

        EnvironmentEvent event = eventManager.pickNatural();
        eventManager.clear();

        if (event == null) {
            return TickResult.none();
        }

        EventPackage pkg = repo.find(event.type());
        List<EventOption> options = selector.selectOptions(pkg, level, random);

        return TickResult.natural(event.type(), pkg.getDescription(), options);
    }

    public void applyNaturalChoice(Yacht yacht, EventOption option) {
        option.apply(yacht);
    }

    public TickResult runRandomPhase(
            Environment environment,
            EventManager eventManager,
            EnvironmentEventTrigger randomTrigger,
            EventPackageRepository repo,
            EventOptionSelector selector,
            int level
    ) {

        randomTrigger.apply(environment, eventManager, random);

        EnvironmentEvent event = eventManager.pickRandom();
        eventManager.clear();

        if (event == null) {
            return TickResult.none();
        }

        EventPackage pkg = repo.find(event.type());
        List<EventOption> options = selector.selectOptions(pkg, level, random);

        return TickResult.random(event.type(), pkg.getDescription(), options);
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

            results.add(TickResult.internal(type, pkg.getDescription(), options));
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