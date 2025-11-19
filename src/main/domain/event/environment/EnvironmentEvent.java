package main.domain.event.environment;

public class EnvironmentEvent {
    private final EnvironmentEventType environmentEventType;

    public EnvironmentEvent(EnvironmentEventType environmentEventType) {
        this.environmentEventType = environmentEventType;
    }

    public EnvironmentEventType type() {
        return environmentEventType;
    }
}
