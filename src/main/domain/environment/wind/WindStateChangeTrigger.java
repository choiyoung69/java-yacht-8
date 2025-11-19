package main.domain.environment.wind;

import main.domain.environment.EnvironmentEventTrigger;
import main.domain.environment.Environment;
import main.domain.event.environment.EnvironmentEvent;
import main.domain.event.environment.EnvironmentEventType;
import main.service.EventManager;
import main.domain.event.choice.EventType;
import java.util.Random;

public class WindStateChangeTrigger implements EnvironmentEventTrigger {
    @Override
    public void apply(Environment environment, EventManager eventManager, Random random) {
        Wind wind = environment.wind();

        if (wind.shouldTriggerGust(random)) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.WIND_GUST));
        }

        if (wind.shouldTriggerLull(random)) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.WIND_LULL));
        }

        if (wind.shouldTriggerTurbulence(random)) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.WIND_TURBULENCE));
        }

        if (wind.shouldTriggerRandomShift(random)) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.WIND_RANDOM_SHIFT));
        }
    }
}
