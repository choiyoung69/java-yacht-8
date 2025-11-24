package app.domain.environment.current;

public class CurrentConfigFactory {

    public static CurrentConfig fromLevel(int level) {
        double t = (level - 1) / 9.0;

        // 강조류 threshold : 3.5 -> 2.5
        double strongThreshold = 3.5 - (2.5 * t);

        // 강조류 지속 시간 기준 : 5 -> 2
        int strongDurationThreshold = (int) Math.round(5 - (3 * t));

        // 1 ~ 2
        double naturalVariability = 1.0 + t;

        return new CurrentConfig(
                strongThreshold,
                strongDurationThreshold,
                naturalVariability
        );
    }
}
