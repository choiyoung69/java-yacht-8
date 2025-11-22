package service;

import domain.event.environment.EnvironmentEvent;
import domain.event.environment.EventCategory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private final Map<EventCategory, List<EnvironmentEvent>> events =
            new EnumMap<>(EventCategory.class);

    public EventManager() {
        for (EventCategory c : EventCategory.values()) {
            events.put(c, new ArrayList<>());
        }
    }

    public void register(EnvironmentEvent event) {
        events.get(event.type().category()).add(event);
    }

    public List<EnvironmentEvent> pickForDisplay() {
        List<EnvironmentEvent> result = new ArrayList<>();

        EnvironmentEvent n = pickNatural();
        EnvironmentEvent r = pickRandom();

        if (n != null) result.add(n);
        if (r != null) result.add(r);

        return result;
    }

    public EnvironmentEvent pickNatural() {
        return pickOne(EventCategory.NATURAL);
    }

    public EnvironmentEvent pickRandom() {
        return pickOne(EventCategory.RANDOM);
    }

    private EnvironmentEvent pickOne(EventCategory category) {
        List<EnvironmentEvent> list = events.get(category);

        return list.stream()
                .max(Comparator.comparingInt(EnvironmentEvent::priority))
                .orElse(null);
    }

    public void clear() {
        events.values().forEach(List::clear);
    }
}
