package main.domain.EventTrigger;

import main.domain.environment.Environment;
import main.domain.event.EventManager;
import java.util.Random;

public interface EnvironmentEventTrigger {
    void apply(Environment environment, EventManager eventManager, Random random);
}
