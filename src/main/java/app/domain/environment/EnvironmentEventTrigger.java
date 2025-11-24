package app.domain.environment;

import java.util.Random;
import app.service.EventManager;

public interface EnvironmentEventTrigger {
    void apply(Environment environment, EventManager eventManager, Random random);
}
