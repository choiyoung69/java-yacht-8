package app.domain.environment;

import app.domain.environment.current.Current;
import app.domain.environment.wind.Wind;
import java.util.Random;

public class Environment {
    private final Wind wind;
    private final Current current;

    public Environment(Wind wind, Current current) {
        this.wind = wind;
        this.current = current;
    }

    public static Environment defaultEnvironment(Wind wind, Current current) {
        return new Environment(wind, current);
    }

    public void updateNaturalAll(Random random) {
        wind.updateNatural(random);
        current.updateNatural(random);
    }

    public Wind wind() {
        return wind;
    }

    public Current current() {
        return current;
    }
}
