package domain.environment.wind;

import domain.event.environment.EnvironmentEventType;

public enum WindSpeedChange {
    NONE(null),
    WEAK_TO_NORMAL(EnvironmentEventType.WIND_WEAK_TO_NORMAL),
    NORMAL_TO_STRONG(EnvironmentEventType.WIND_NORMAL_TO_STRONG),
    STRONG_TO_NORMAL(EnvironmentEventType.WIND_STRONG_TO_NORMAL),
    NORMAL_TO_WEAK(EnvironmentEventType.WIND_NORMAL_TO_WEAK);

    private final EnvironmentEventType eventType;

    WindSpeedChange(EnvironmentEventType eventType) {
        this.eventType = eventType;
    }

    public EnvironmentEventType toEventType() {
        return eventType;
    }
}
