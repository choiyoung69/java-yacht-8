package app.domain.environment.current;

import app.domain.environment.Environment;
import app.domain.environment.EnvironmentEventTrigger;
import app.domain.event.environment.EnvironmentEvent;
import app.domain.event.environment.EnvironmentEventType;
import app.service.EventManager;
import java.util.Random;

public class CurrentPeriodicTrigger implements EnvironmentEventTrigger {

    private int counter = 0;

    @Override
    public void apply(Environment environment, EventManager eventManager, Random random) {
        Current current = environment.current();

        counter++;

        if (current.isPeriodicReady(counter)) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.CURRENT_DIRECTION_SHIFT_PERIODIC));
            counter = 0;
        }
    }
}
