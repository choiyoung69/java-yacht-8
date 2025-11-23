package domain.environment.wind;

import domain.environment.EnvironmentEventTrigger;
import domain.environment.Environment;
import domain.event.environment.EnvironmentEvent;
import domain.event.environment.EnvironmentEventType;
import java.util.Random;
import service.EventManager;

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
