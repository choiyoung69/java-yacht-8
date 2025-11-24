package app.controller;

import app.domain.environment.Environment;
import app.domain.environment.current.Current;
import app.domain.environment.wind.Wind;
import app.domain.environment.wind.WindConfigFactory;
import app.domain.environment.wind.WindEventTrigger;
import app.domain.environment.wind.WindRandomEventTrigger;
import app.domain.event.choice.EventOptionSelector;
import app.domain.event.choice.EventPackageRepository;
import app.domain.yacht.Yacht;
import app.domain.yacht.YachtInternalEventTrigger;
import app.dto.TickResult;
import java.util.List;
import app.service.EventManager;
import app.service.GameEngine;
import app.view.InputView;
import app.view.OutputView;

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
        inputView.pauseForNextTurn();
        outputView.printTutorial();
        inputView.pauseForNextTurn();

        int level = inputLevel();

        Wind wind = Wind.initalizeWind(WindConfigFactory.fromLevel(level));
        Current current = Current.initalizeCurrent(null)
        Environment env = Environment.defaultEnvironment(wind, current);
        Yacht yacht = Yacht.defaultYacht();

        int turn = 1;

        while (true) {
            outputView.printTurnHeader(turn);
            outputView.printYachtStatus(yacht);

            progressNaturalEvent(env, level, yacht);
            progressRandomEvent(env, level, yacht);
            if (progressYachtEvent(yacht, level)) {
                return;
            }

            yacht.update();

            if (isGameClear(yacht)) {
                return;
            }

            inputView.pauseForNextTurn();
            turn++;
        }
    }

    private boolean isGameClear(Yacht yacht) {
        if (engine.isFinished(yacht)) {
            outputView.printGameClear();
            outputView.printExit();
            return true;
        }
        return false;
    }

    private boolean progressYachtEvent(Yacht yacht, int level) {
        List<TickResult> internals = engine.runInternalPhase(
                yacht, internalTrigger, eventRepository, selector, level
        );

        for (TickResult internal : internals) {
            if (internal.isGameOver()) {
                outputView.printGameOver(internal.type());
                return true;
            }

            if (internal.isEvent()) {
                outputView.printInternalEvent(internal);
                int choice = inputView.readOptionSelection(internal.size());
                engine.applyInternalChoice(yacht, internal.options().get(choice));
                outputView.printApplyResult(internal.options().get(choice));
            }
        }
        return false;
    }

    private void progressRandomEvent(Environment env, int level, Yacht yacht) {
        TickResult random = engine.runRandomPhase(
                env, eventManager, randomTrigger,
                eventRepository, selector, level
        );
        if (random.isEvent()) {
            outputView.printRandomEvent(random);
            int choice = inputView.readOptionSelection(random.size());
            engine.applyRandomChoice(yacht, random.options().get(choice));
            outputView.printApplyResult(random.options().get(choice));
        }
    }

    private void progressNaturalEvent(Environment env, int level, Yacht yacht) {
        TickResult natural = engine.runNaturalPhase(
                env, eventManager, naturalTrigger,
                eventRepository, selector, level
        );

        if (natural.isEvent()) {
            outputView.printNaturalEvent(natural);
            int choice = inputView.readOptionSelection(natural.size());
            engine.applyNaturalChoice(yacht, natural.options().get(choice));

            outputView.printApplyResult(natural.options().get(choice));
        }
    }

    private int inputLevel() {
        outputView.printSelectDifficulty();
        int level = inputView.readDifficultyLevel();
        outputView.printDifficultyConfirm(level);
        return level;
    }
}
