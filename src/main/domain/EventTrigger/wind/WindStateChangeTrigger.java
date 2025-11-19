package main.domain.EventTrigger.wind;

import main.domain.EventTrigger.EnvironmentEventTrigger;
import main.domain.environment.Environment;
import main.domain.environment.wind.Wind;
import main.domain.event.EventManager;
import main.domain.event.EventType;
import java.util.Random;

public class WindStateChangeTrigger implements EnvironmentEventTrigger {
    public static final String SPEED_SPIKE =  "🌬 바람이 갑자기 강해졌습니다! (변화량: %.1f m/s)";
    public static final String DIRECTION_SHIFT = "💨 풍향이 크게 바뀌었습니다! (변화량: %.1f°)";

    @Override
    public void apply(Environment environment, EventManager eventManager, Random random) {
        Wind wind = environment.wind();

        if (wind.isSpeedJump()) {
            eventManager.registerEvent(
                    EventType.WIND_SPEED_SPIKE,
                    SPEED_SPIKE.formatted(wind.getLastDeltaSpeed())
            );
        }

        if (wind.isDirectionJump()) {
            eventManager.registerEvent(
                    EventType.WIND_DIRECTION_SHIFT,
                    DIRECTION_SHIFT.formatted(wind.getLastDeltaDirection())
            );
        }
    }
}
