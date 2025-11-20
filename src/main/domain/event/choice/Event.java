package main.domain.event.choice;

public class Event {
    private final EventType eventType;
    private final String message;
    private final DifficultyOptions eventOptions;

    public Event(EventType eventType, String message, DifficultyOptions eventOptions) {
        this.eventType = eventType;
        this.message = message;
        this.eventOptions = eventOptions;
    }

    public static Event create(EventType eventType, String message, DifficultyOptions eventOptions) {
        return new Event(eventType, message, eventOptions);
    }

    public void resolve(EventOption selected) {
        selected.effect();
    }
}
