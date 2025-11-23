package domain.yacht;

import domain.event.environment.EnvironmentEventType;
import java.util.ArrayList;
import java.util.List;

public class YachtInternalEventTrigger {

    public List<EnvironmentEventType> apply(Yacht yacht) {
        List<EnvironmentEventType> list = new ArrayList<>();

        if (yacht.stability() <= 0) {
            list.add(EnvironmentEventType.YACHT_CAPSIZE);
            return list; // 즉시 게임오버 → 다른 것은 무시
        }

        if (yacht.power() <= 0) {
            list.add(EnvironmentEventType.YACHT_DEAD_STOP);
            return list; // 즉시 게임오버
        }

        if (yacht.stability() < 20) {
            list.add(EnvironmentEventType.YACHT_STABILITY_LOW);
        }

        if (yacht.power() < 10) {
            list.add(EnvironmentEventType.YACHT_SPEED_LOW);
        }

        return list;
    }
}
