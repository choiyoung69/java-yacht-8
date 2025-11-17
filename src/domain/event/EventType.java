package domain.event;

public enum EventType {
    // 풍속 임계값 초과
    WIND_SPEED_SPIKE,
    // 풍향 임계값 초과
    WIND_DIRECTION_SHIFT,

    WIND_GUST,
    WIND_LULL,
    WIND_RANDOM_SHIFT,
    WIND_TURBULENCE
}
