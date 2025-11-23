package app.domain.environment.wind;

public class WindConfigFactory {

    public static WindConfig fromLevel(int level) {
        double t = (level - 1) / 9.0;
        double speedMax = 4.0 + (6.0 * t);
        double directionMax   = 6.0 + (6.0 * t);

        double speedVar = speedMax * 2.0;
        double dirVar   = directionMax * 2.0;

        double directionThreshold = 10.0;

        double randomChance = 0.05 + (0.20 * t);

        return new WindConfig(
                speedVar,
                dirVar,
                directionThreshold,
                randomChance,
                randomChance,
                randomChance,
                randomChance
        );
    }
}

