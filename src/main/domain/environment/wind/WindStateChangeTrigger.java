package main.domain.environment.wind;

import main.domain.environment.EnvironmentEventTrigger;
import main.domain.environment.Environment;
import main.service.EventManager;
import main.domain.event.choice.EventType;
import java.util.Random;

public class WindStateChangeTrigger implements EnvironmentEventTrigger {
    @Override
    public void apply(Environment environment, EventManager eventManager) {
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
