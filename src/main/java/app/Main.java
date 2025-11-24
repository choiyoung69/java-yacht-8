package app;

import app.controller.GameController;
import app.domain.environment.EnvironmentEventTrigger;
import app.domain.environment.current.CurrentEventTrigger;
import app.domain.environment.current.CurrentPeriodicTrigger;
import app.domain.environment.wind.WindEventTrigger;
import app.domain.environment.wind.WindRandomEventTrigger;
import app.domain.event.choice.EventOptionSelector;
import app.domain.event.choice.EventPackageRepository;
import app.domain.yacht.YachtInternalEventTrigger;
import app.service.EventManager;
import app.service.GameEngine;
import app.view.InputView;
import app.view.OutputView;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameEngine engine = new GameEngine();
        EventManager eventManager = new EventManager();
        EventOptionSelector selector = new EventOptionSelector();

        EventPackageRepository repo =
                new EventPackageRepository("src/main/resources/events");

        List<EnvironmentEventTrigger> naturalTriggerList = List.of(new WindEventTrigger(), new CurrentEventTrigger());
        List<EnvironmentEventTrigger> randomTriggerList = List.of(new WindRandomEventTrigger(), new CurrentPeriodicTrigger());
        YachtInternalEventTrigger internalTrigger = new YachtInternalEventTrigger();

        GameController controller = new GameController(
                inputView,
                outputView,
                engine,
                eventManager,
                selector,
                repo,
                naturalTriggerList,
                randomTriggerList,
                internalTrigger
        );

        controller.startGame();
    }
}