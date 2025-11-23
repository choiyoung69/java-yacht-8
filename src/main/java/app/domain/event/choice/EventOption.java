package app.domain.event.choice;

import app.domain.yacht.Yacht;

public class EventOption {
    private final String message;
    private final int power;
    private final int stability;

    public EventOption(String message, int power, int stability) {
        this.message = message;
        this.power = power;
        this.stability = stability;
    }

    public void apply(Yacht yacht) {
        yacht.changePower(power);
        yacht.changeStability(stability);
    }

    public String message() {
        return message;
    }

    public int power() {
        return power;
    }

    public int stability() {
        return stability;
    }
}
