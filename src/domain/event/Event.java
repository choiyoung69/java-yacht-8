package domain.event;

public class Event {
    private final EventType eventType;
    private final String message;

    private Event(EventType eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public static create(EventType, String message) {
        return new Event(EventType, message);
    }
}
