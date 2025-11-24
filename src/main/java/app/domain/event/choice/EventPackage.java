package app.domain.event.choice;

import app.domain.event.environment.EnvironmentEventType;

public class EventPackage {
    private EnvironmentEventType type;
    private String description;
    private DifficultyOptions correct;
    private DifficultyOptions wrong;

    public EnvironmentEventType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public DifficultyOptions getCorrect() {
        return correct;
    }

    public DifficultyOptions getWrong() {
        return wrong;
    }
}
