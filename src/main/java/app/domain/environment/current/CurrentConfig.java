package app.domain.environment.current;

public record CurrentConfig(
    double strongThreshold,
    int strongDurationThreshold,
    double naturalVariability
) {}
