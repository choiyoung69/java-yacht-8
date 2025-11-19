package main.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import main.domain.event.environment.EnvironmentEvent;
import main.domain.event.environment.EventCategory;

public class EventManager {
    private final Map<EventCategory, List<EnvironmentEvent>> eventsByCategory =
            new EnumMap<>(EventCategory.class);

    public EventManager() {
        for (EventCategory category : EventCategory.values()) {
            eventsByCategory.put(category, new ArrayList<>());
        }
    }

    public void register(EnvironmentEvent event) {
        eventsByCategory.get(event.type().category()).add(event);
    }

    public EnvironmentEvent pickOne(EventCategory category) {
        return eventsByCategory.get(category).stream()
                .max(Comparator.comparingInt(e -> e.type().priority()))
                .orElse(null);
    }

    public void clear() {
        eventsByCategory.values().forEach(List::clear);
    }
}
