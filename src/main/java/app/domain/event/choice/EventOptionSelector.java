package app.domain.event.choice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EventOptionSelector {

    private static final int MAX_OPTIONS = 5;

    public List<EventOption> selectOptions(EventPackage pkg, int level, Random random) {

        //난이도 그룹 결정
        List<Difficulty> availableDifficulties = selectDifficultyGroup(level);

        //정답 옵션 1개 선택
        EventOption correct = pickOneCorrect(pkg, availableDifficulties, random);

        //같은 난이도 그룹에서 오답들 불러오기
        List<EventOption> wrongOptions = pickWrongOptions(pkg, availableDifficulties, correct);

        //최종 합치기 (정답 + 오답)
        List<EventOption> finalOptions = new ArrayList<>();
        finalOptions.add(correct);

        //오답을 최대 4개만 추가
        int toAdd = Math.min(wrongOptions.size(), MAX_OPTIONS - 1);
        for (int i = 0; i < toAdd; i++) {
            finalOptions.add(wrongOptions.get(i));
        }

        //셔플해서 순서 랜덤
        Collections.shuffle(finalOptions);

        return finalOptions;
    }

    private List<Difficulty> selectDifficultyGroup(int level) {
        List<Difficulty> list = new ArrayList<>();
        if (level <= 3) {
            list.add(Difficulty.EASY);
        } else if (level <= 6) {
            list.add(Difficulty.EASY);
            list.add(Difficulty.MEDIUM);
        } else {
            list.add(Difficulty.EASY);
            list.add(Difficulty.MEDIUM);
            list.add(Difficulty.HARD);
        }
        return list;
    }

    private EventOption pickOneCorrect(EventPackage pkg, List<Difficulty> diffs, Random random) {
        List<EventOption> pool = new ArrayList<>();

        for (Difficulty d : diffs) {
            pool.addAll(pkg.correct().get(d));
        }

        if (pool.isEmpty()) {
            throw new IllegalStateException("정답 옵션이 없습니다: " + pkg.type());
        }

        return pool.get(random.nextInt(pool.size()));
    }

    private List<EventOption> pickWrongOptions(EventPackage pkg, List<Difficulty> diffs, EventOption correct) {
        List<EventOption> pool = new ArrayList<>();

        for (Difficulty d : diffs) {
            pool.addAll(pkg.wrong().get(d));
        }
        Collections.shuffle(pool);

        return pool;
    }
}

