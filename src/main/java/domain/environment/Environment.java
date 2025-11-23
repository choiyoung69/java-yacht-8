package domain.environment;

import domain.environment.wind.Wind;
import java.util.Random;

public class Environment {
    private final Wind wind;

    private Environment(Wind wind) {
        this.wind = wind;
    }

    public static Environment defaultEnvironment(Wind wind) {
        return new Environment(wind);
    }

    public void updateNaturalAll(Random random) {
        wind.updateNatural(random);
    }

    public Wind wind() {
        return wind;
    }
}
