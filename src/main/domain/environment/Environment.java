package main.domain.environment;

import main.domain.environment.wind.Wind;
import java.util.Random;

public class Environment {
    private final Wind wind;
    private final Random random = new Random();

    public Environment(Wind wind) {
        this.wind = wind;
    }

    public void updateNaturalAll() {
        wind.updateNatural(random);
    }

    public Wind wind() {
        return wind;
    }
}
