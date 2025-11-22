package domain.yacht;

import domain.environment.EnvironmentEventTrigger;
import domain.event.environment.EnvironmentEvent;
import domain.event.environment.EnvironmentEventType;
import service.EventManager;

public class YachtInternalEventTrigger {

    public void apply(Yacht yacht, EventManager manager) {
        if (yacht.stability() <= 0) {
            manager.register(new EnvironmentEvent(EnvironmentEventType.YACHT_CAPSIZE));
            return;
        }

        if (yacht.power() <= 0) {
            manager.register(new EnvironmentEvent(EnvironmentEventType.YACHT_DEAD_STOP));
            return;
        }

        // 경고 이벤트
        if (yacht.stability() < 20) {
            manager.register(new EnvironmentEvent(EnvironmentEventType.YACHT_STABILITY_LOW));
        }

        if (yacht.power() < 10) {
            manager.register(new EnvironmentEvent(EnvironmentEventType.YACHT_SPEED_LOW));
        }
    }
}
