package app.domain.environment.current;

import app.domain.environment.Environment;
import app.domain.environment.EnvironmentEventTrigger;
import app.domain.event.environment.EnvironmentEvent;
import app.domain.event.environment.EnvironmentEventType;
import app.service.EventManager;
import java.util.Random;

public class CurrentEventTrigger implements EnvironmentEventTrigger {
    @Override
    public void apply(Environment environment, EventManager eventManager, Random random) {
        Current current = environment.current();

        if (current.shouldTriggerStrongEvent()) {
            eventManager.register(new EnvironmentEvent(EnvironmentEventType.CURRENT_STRONG_CONTINUE));
        }
    }
}
