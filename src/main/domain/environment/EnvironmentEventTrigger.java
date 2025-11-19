package main.domain.environment;

import main.service.EventManager;
import java.util.Random;

public interface EnvironmentEventTrigger {
    void apply(Environment environment, EventManager eventManager);
}
