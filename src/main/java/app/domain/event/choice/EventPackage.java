package app.domain.event.choice;

import app.domain.event.environment.EnvironmentEventType;

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

    public DifficultyOptions correct() {
        return correct;
    }

    public DifficultyOptions wrong() {
        return wrong;
    }
}
