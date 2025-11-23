package app;

import app.controller.GameController;
import app.domain.environment.wind.WindEventTrigger;
import app.domain.environment.wind.WindRandomEventTrigger;
import app.domain.event.choice.EventOptionSelector;
import app.domain.event.choice.EventPackageRepository;
import app.domain.yacht.YachtInternalEventTrigger;
import app.service.EventManager;
import app.service.GameEngine;
import app.view.InputView;
import app.view.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameEngine engine = new GameEngine();
        EventManager eventManager = new EventManager();
        EventOptionSelector selector = new EventOptionSelector();

        EventPackageRepository repo =
                new EventPackageRepository("src/main/resources/events");

        WindEventTrigger naturalTrigger = new WindEventTrigger();
        WindRandomEventTrigger randomTrigger = new WindRandomEventTrigger();
        YachtInternalEventTrigger internalTrigger = new YachtInternalEventTrigger();

        GameController controller = new GameController(
                inputView,
                outputView,
                engine,
                eventManager,
                selector,
                repo,
                naturalTrigger,
                randomTrigger,
                internalTrigger
        );

        controller.startGame();
    }
}