package main.domain.event;

import java.util.LinkedList;
import java.util.Queue;

public class EventManager {
    private final Queue<Event> queue = new LinkedList<>();

    public void registerEvent(EventType eventType, String message, EventOptions eventOptions) {
        queue.add(Event.create(eventType, message, eventOptions));
    }

    public Event poll() {
        return queue.poll();
    }

    public boolean hasEvent() {
        return !queue.isEmpty();
    }
}
