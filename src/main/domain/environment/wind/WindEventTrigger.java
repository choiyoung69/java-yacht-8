package main.domain.environment.wind;

import main.domain.environment.EnvironmentEventTrigger;
import main.domain.environment.Environment;
import main.domain.event.environment.EnvironmentEvent;
import main.domain.event.environment.EnvironmentEventType;
import main.service.EventManager;
import java.util.Random;

public class WindEventTrigger implements EnvironmentEventTrigger {
    @Override
    public void apply(Environment environment, EventManager eventManager) {
        Wind wind = environment.wind();

        //강풍 -> 중풍, 중풍 -> 약풍, 약풍 -> 중풍, 중풍 -> 강풍 변화 체크
        EnvironmentEventType speedEvent = wind.speedNaturalEventType();
        if (speedEvent != null) {
            eventManager.register(new EnvironmentEvent(speedEvent));
        }

        // 2. 풍향 임계치 자연 이벤트
        if (wind.isDirectionUnderThreshold()) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.WIND_DIRECTION_UNDER_THRESHOLD));
        }

        if (wind.isDirectionOverThreshold()) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.WIND_DIRECTION_OVER_THRESHOLD));
        }
    }
}
