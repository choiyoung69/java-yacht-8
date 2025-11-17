package domain.EventTrigger.wind;

import domain.EventTrigger.EnvironmentEventTrigger;
import domain.environment.Environment;
import domain.environment.wind.Wind;
import domain.event.EventManager;
import domain.event.EventType;
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
