package domain.environment;

import domain.environment.wind.Wind;

public class Environment {
    private final Wind wind;

    public Environment(Wind wind) {
        this.wind = wind;
    }

    public void update() {
        wind.updateNatural();
    }
}
