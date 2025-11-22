package domain.event.environment;

public enum EnvironmentEventType {
    // Wind 자연 풍속 이벤트
    WIND_WEAK_TO_NORMAL(EventCategory.NATURAL, EventSource.WIND, 40),
    WIND_NORMAL_TO_STRONG(EventCategory.NATURAL, EventSource.WIND, 40),
    WIND_STRONG_TO_NORMAL(EventCategory.NATURAL, EventSource.WIND, 40),
    WIND_NORMAL_TO_WEAK(EventCategory.NATURAL, EventSource.WIND, 40),

    // Wind 자연 풍향 이벤트
    WIND_DIRECTION_UNDER_THRESHOLD(EventCategory.NATURAL, EventSource.WIND, 50),
    WIND_DIRECTION_OVER_THRESHOLD(EventCategory.NATURAL, EventSource.WIND, 50),

    // Wind 랜덤 이벤트
    WIND_GUST(EventCategory.RANDOM, EventSource.WIND, 80),
    WIND_LULL(EventCategory.RANDOM, EventSource.WIND, 30),
    WIND_TURBULENCE(EventCategory.RANDOM, EventSource.WIND, 80),
    WIND_RANDOM_SHIFT(EventCategory.RANDOM, EventSource.WIND, 60),

    YACHT_STABILITY_LOW(EventCategory.INTERNAL, EventSource.YACHT, 100),
    YACHT_SPEED_LOW(EventCategory.INTERNAL, EventSource.YACHT, 100),
    YACHT_CAPSIZE(EventCategory.INTERNAL, EventSource.YACHT, 1000),
    YACHT_DEAD_STOP(EventCategory.INTERNAL, EventSource.YACHT, 900);

    private final EventCategory category;
    private final EventSource source;
    private final int priority;

    EnvironmentEventType(EventCategory category, EventSource source, int priority) {
        this.category = category;
        this.source = source;
        this.priority = priority;
    }

    public EventCategory category() {
        return category;
    }

    public EventSource source() {
        return source;
    }

    public int priority() {
        return priority;
    }
}
