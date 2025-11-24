package app.domain.environment.wind;

import app.domain.environment.EnvironmentEventTrigger;
import app.domain.environment.Environment;
import app.domain.event.environment.EnvironmentEvent;
import app.domain.event.environment.EnvironmentEventType;
import java.util.Random;
import app.service.EventManager;

public class WindRandomEventTrigger implements EnvironmentEventTrigger {
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
