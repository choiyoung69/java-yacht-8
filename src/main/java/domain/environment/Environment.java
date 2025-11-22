package domain.environment;

import domain.environment.wind.Wind;
import java.util.Random;

public class Environment {
    private final Wind wind;

    public Environment(Wind wind) {
        this.wind = wind;
    }

    public void updateNaturalAll(Random random) {
        wind.updateNatural(random);
    }

    public Wind wind() {
        return wind;
    }
}
