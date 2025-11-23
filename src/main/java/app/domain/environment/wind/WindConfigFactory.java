package app.domain.environment.wind;

public class WindConfigFactory {

    public static WindConfig fromLevel(int level) {
        double t = (level - 1) / 9.0;

        double speedMax = 2.0 + (2.0 * t);
        double dirMax   = 3.0 + (2.0 * t);

        double speedVar = speedMax * 2.0;
        double dirVar   = dirMax * 2.0;

        double directionThreshold = 20.0;

        double randomChance = 0.01 + (0.09 * t);

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

