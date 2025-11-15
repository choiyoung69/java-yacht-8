package domain.environment.wind;

import java.util.Random;

public class Wind {
    //초기 풍향, 방향 고정
    private static final double INITIAL_WIND_SPEED = 10.0;
    private static final double INITIAL_WIND_DIRECTION = 0.0;

    private double speed;
    private double direction;
    private final WindConfig config;
    private final Random random = new Random();

    public Wind(double speed, double direction, WindConfig config) {
        this.speed = speed;
        this.direction = direction;
        this.config = config;
    }

    public static Wind initalizeWind(WindConfig windConfig) {
        return new Wind(INITIAL_WIND_SPEED, INITIAL_WIND_DIRECTION, windConfig);
    }
}
