package domain.event.choice;

public class EventOption {
    private final String message;
    private final int power;
    private final int stability;

    public EventOption(String message, int power, int stability) {
        this.message = message;
        this.power = power;
        this.stability = stability;
    }
}
