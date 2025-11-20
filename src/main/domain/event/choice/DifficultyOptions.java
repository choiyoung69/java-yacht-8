package main.domain.event.choice;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DifficultyOptions {
    //json과 일치시키는 이름
    private List<EventOption> EASY;
    private List<EventOption> MEDIUM;
    private List<EventOption> HARD;

    private Map<Difficulty, List<EventOption>> difficultyTable;

    public void init() {
        difficultyTable = new EnumMap<>(Difficulty.class);
        difficultyTable.put(Difficulty.EASY, EASY);
        difficultyTable.put(Difficulty.MEDIUM, MEDIUM);
        difficultyTable.put(Difficulty.HARD, HARD);
    }

    public List<EventOption> get(Difficulty difficulty) {
        return difficultyTable.get(difficulty);
    }
}
