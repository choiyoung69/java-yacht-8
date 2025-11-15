package domain.environment.wind;

import java.util.Random;

public class Wind {
    //초기 풍향, 방향 고정
    private static final double INITIAL_WIND_SPEED = 10.0;
    private static final double INITIAL_WIND_DIRECTION = 0.0;

    private double speed;
    private double direction;
    private final WindConfig config;

    public Wind(double speed, double direction, WindConfig config) {
        this.speed = speed;
        this.direction = direction;
        this.config = config;
    }

    public static Wind initalizeWind(WindConfig windConfig) {
        return new Wind(INITIAL_WIND_SPEED, INITIAL_WIND_DIRECTION, windConfig);
    }


    //시간(s)에 따른 자연변화
    public void updateNatural(double rawSpeedNoise, double rawDirectionNoise) {
        double deltaSpeed = rawSpeedNoise * config.speedVariability();
        double deltaDirection = rawDirectionNoise * config.directionVariability();

        this.speed += deltaSpeed;
        this.direction = normalize(this.direction + deltaDirection);
    }

    private static double normalize(double angle) {
        double a = angle % 360;
        return (a < 0) ? a + 360 : a;
    }
}
