package domain.environment;

import domain.environment.wind.Wind;
import domain.event.EventManager;
import domain.event.EventType;
import java.util.Random;

public class Environment {
    private final Wind wind;
    private final EventManager eventManager;
    private final Random random = new Random();

    public Environment(Wind wind, EventManager eventManager) {
        this.wind = wind;
        this.eventManager = eventManager;
    }

    public void update() {
        wind.updateNatural(random);
    }

    public void collectEnvironmentEvents() {
    }

    public Wind wind() {
        return wind;
    }
}
