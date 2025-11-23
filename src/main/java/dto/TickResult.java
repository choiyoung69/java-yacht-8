package dto;

import domain.event.choice.EventOption;
import domain.event.environment.EnvironmentEventType;
import java.util.List;

public class TickResult {

    public enum Phase {
        NONE,
        NATURAL,
        RANDOM,
        INTERNAL,
        GAME_OVER
    }

    private final Phase phase;
    private final EnvironmentEventType type;
    private final String description;
    private final List<EventOption> options;

    private TickResult(Phase phase,
                       EnvironmentEventType type,
                       String description,
                       List<EventOption> options) {
        this.phase = phase;
        this.type = type;
        this.description = description;
        this.options = options;
    }

    public static TickResult none() {
        return new TickResult(Phase.NONE, null, null, null);
    }

    public static TickResult natural(EnvironmentEventType type, String desc, List<EventOption> opts) {
        return new TickResult(Phase.NATURAL, type, desc, opts);
    }

    public static TickResult random(EnvironmentEventType type, String desc, List<EventOption> opts) {
        return new TickResult(Phase.RANDOM, type, desc, opts);
    }

    public static TickResult internal(EnvironmentEventType type, String desc, List<EventOption> opts) {
        return new TickResult(Phase.INTERNAL, type, desc, opts);
    }

    public static TickResult gameOver(EnvironmentEventType type) {
        return new TickResult(Phase.GAME_OVER, type, null, null);
    }

    public EnvironmentEventType type() {
        return type;
    }

    public String description() {
        return description;
    }

    public List<EventOption> options() {
        return options;
    }

    public int size() {
        return options.size();
    }

    public boolean isEvent() {
        return phase == Phase.NATURAL ||
                phase == Phase.RANDOM ||
                phase == Phase.INTERNAL;
    }

    public boolean isNone() {
        return phase == Phase.NONE;
    }

    public boolean isGameOver() {
        return phase == Phase.GAME_OVER;
    }
}
