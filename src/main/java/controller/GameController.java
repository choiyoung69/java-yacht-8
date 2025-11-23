package controller;

import domain.environment.Environment;
import domain.environment.wind.Wind;
import domain.environment.wind.WindConfigFactory;
import domain.environment.wind.WindEventTrigger;
import domain.environment.wind.WindRandomEventTrigger;
import domain.event.choice.EventOptionSelector;
import domain.event.choice.EventPackageRepository;
import domain.yacht.Yacht;
import domain.yacht.YachtInternalEventTrigger;
import dto.TickResult;
import java.util.List;
import service.EventManager;
import service.GameEngine;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    private final GameEngine engine;
    private final EventManager eventManager;
    private final EventOptionSelector selector;
    private final EventPackageRepository eventRepository;

    private final WindEventTrigger naturalTrigger;
    private final WindRandomEventTrigger randomTrigger;
    private final YachtInternalEventTrigger internalTrigger;

    public GameController(
            InputView inputView,
            OutputView outputView,
            GameEngine engine,
            EventManager eventManager,
            EventOptionSelector selector,
            EventPackageRepository eventRepository,
            WindEventTrigger naturalTrigger,
            WindRandomEventTrigger randomTrigger,
            YachtInternalEventTrigger internalTrigger
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.engine = engine;
        this.eventManager = eventManager;
        this.selector = selector;
        this.eventRepository = eventRepository;
        this.naturalTrigger = naturalTrigger;
        this.randomTrigger = randomTrigger;
        this.internalTrigger = internalTrigger;
    }

    public void startGame() {
        outputView.printWelcome();
        outputView.printTutorial();

        outputView.printSelectDifficulty();
        int level = inputView.readDifficultyLevel();
        outputView.printDifficultyConfirm(level);

        Wind wind = Wind.initalizeWind(WindConfigFactory.fromLevel(level));
        Environment env = Environment.defaultEnvironment(wind);

        Yacht yacht = Yacht.defaultYacht();

        while (true) {
            outputView.printYachtStatus(yacht);

            TickResult natural = engine.runNaturalPhase(
                    env, yacht, eventManager, naturalTrigger,
                    eventRepository, selector, level
            );
            if (natural.isEvent()) {
                outputView.printNaturalEvent(natural);
                int choice = inputView.readOptionSelection(natural.size());
                engine.applyNaturalChoice(yacht, natural.options().get(choice));

                outputView.printApplyResult(natural.options().get(choice));
            }

            TickResult random = engine.runRandomPhase(
                    env, yacht, eventManager, randomTrigger,
                    eventRepository, selector, level
            );
            if (random.isEvent()) {
                outputView.printRandomEvent(random);
                int choice = inputView.readOptionSelection(random.size());
                engine.applyRandomChoice(yacht, random.options().get(choice));
                outputView.printApplyResult(random.options().get(choice));
            }

            List<TickResult> internals = engine.runInternalPhase(
                    yacht, internalTrigger, eventRepository, selector, level
            );

            for (TickResult internal : internals) {
                if (internal.isGameOver()) {
                    outputView.printGameOver(internal.type());
                    return;
                }

                if (internal.isEvent()) {
                    outputView.printInternalEvent(internal);
                    int choice = inputView.readOptionSelection(internal.size());
                    engine.applyInternalChoice(yacht, internal.options().get(choice));
                    outputView.printApplyResult(internal.options().get(choice));
                }
            }

            yacht.update();

            if (engine.isFinished(yacht)) {
                outputView.printGameClear();
                outputView.printExit();
                return;
            }
        }
    }
}
