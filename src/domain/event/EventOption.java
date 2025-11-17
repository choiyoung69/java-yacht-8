package domain.event;

public class EventOption {
    private final String message;
    private final Boolean correct;
    private final EventEffect eventEffect;

    public EventOption(String message, boolean correct, EventEffect effect) {
        this.message = message;
        this.correct = correct;
        this.effect = effect;
    }

    public EventEffect effect() {
        return effect;
    }
}
