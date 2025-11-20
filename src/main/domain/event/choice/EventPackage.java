package main.domain.event.choice;

import main.domain.event.environment.EnvironmentEventType;

public class EventPackage {
    private EnvironmentEventType type;
    private String description;
    private DifficultyOptions correct;
    private DifficultyOptions wrong;

    public EnvironmentEventType type() {
        return type;
    }

    public String description() {
        return description;
    }
}
