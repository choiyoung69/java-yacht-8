package dto;

import domain.event.choice.EventOption;
import domain.event.environment.EnvironmentEventType;
import java.util.List;

public class TickResult {

    public enum Phase {
        NONE,       // 이벤트 없음
        NATURAL,    // 자연 이벤트 발생
        RANDOM,     // 랜덤 이벤트 발생
        INTERNAL,   // 요트 내부 이벤트 발생
        GAME_OVER   // 게임 오버
    }

    private final Phase phase;                 // 지금 어떤 phase인지
    private final EnvironmentEventType type;   // 이벤트 종류
    private final String description;          // 이벤트 설명
    private final List<EventOption> options;   // 선택지 목록

    // 생성자는 private
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

    public Phase phase() {
        return phase;
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
}
