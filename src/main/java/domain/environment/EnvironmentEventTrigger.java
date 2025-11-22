package domain.environment;

import java.util.Random;
import service.EventManager;

public interface EnvironmentEventTrigger {
    void apply(Environment environment, EventManager eventManager, Random random);
}
