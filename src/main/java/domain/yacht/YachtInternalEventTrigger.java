package domain.yacht;

import domain.event.environment.EnvironmentEventType;

public class YachtInternalEventTrigger {

    public EnvironmentEventType apply(Yacht yacht) {
        if (yacht.stability() <= 0) {
            return EnvironmentEventType.YACHT_CAPSIZE;
        }

        if (yacht.power() <= 0) {
            return EnvironmentEventType.YACHT_DEAD_STOP;
        }

        // 경고 이벤트
        if (yacht.stability() < 20) {
            return EnvironmentEventType.YACHT_STABILITY_LOW;
        }

        if (yacht.power() < 10) {
            return EnvironmentEventType.YACHT_SPEED_LOW;
        }

        return null;
    }
}
