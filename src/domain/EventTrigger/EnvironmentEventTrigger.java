package domain.EventTrigger;

import domain.environment.Environment;
import domain.event.EventManager;
import java.util.Random;

public interface EnvironmentEventTrigger {
    void apply(Environment environment, EventManager eventManager, Random random);
}
