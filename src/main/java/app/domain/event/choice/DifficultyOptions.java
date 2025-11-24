package app.domain.event.choice;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DifficultyOptions {
    @JsonProperty("EASY")
    private List<EventOption> easy;

    @JsonProperty("MEDIUM")
    private List<EventOption> medium;

    @JsonProperty("HARD")
    private List<EventOption> hard;

    public List<EventOption> get(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> easy;
            case MEDIUM -> medium;
            case HARD -> hard;
        };
    }
}
