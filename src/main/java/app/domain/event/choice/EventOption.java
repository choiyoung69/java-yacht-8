package app.domain.event.choice;

import app.domain.yacht.Yacht;

public class EventOption {
    private String text;
    private int stability;
    private int power;

    public EventOption() {}

    public EventOption(String text, int stability, int power) {
        this.text = text;
        this.stability = stability;
        this.power = power;
    }

    public void apply(Yacht yacht) {
        yacht.changePower(power);
        yacht.changeStability(stability);
    }

    public String getText() {
        return text;
    }

    public int getPower() {
        return power;
    }

    public int getStability() {
        return stability;
    }
}
