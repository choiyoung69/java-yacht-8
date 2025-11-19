package main.domain.environment.wind;

import java.util.Random;

public class Wind {
    //초기 풍향, 방향 고정
    private static final double INITIAL_WIND_SPEED = 10.0;
    private static final double INITIAL_WIND_DIRECTION = 0.0;
    public static final double WEAK_MAX = 5.0; //약풍 기준점
    public static final double STRONG_MIN = 15.0; //강풍 기준점

    private double speed;
    private double direction;
    private WindSpeedChange windSpeedChange;
    private final WindConfig config;

    public Wind(double speed, double direction, WindSpeedChange windSpeedChange, WindConfig config) {
        this.speed = speed;
        this.direction = direction;
        this.windSpeedChange = windSpeedChange;
        this.config = config;
    }

    public static Wind initalizeWind(WindConfig windConfig) {
        return new Wind(INITIAL_WIND_SPEED, INITIAL_WIND_DIRECTION, WindSpeedChange.NONE, windConfig);
    }

    //시간(s)에 따른 자연변화
    public void updateNatural(Random random) {
        WindPhase before = calculatePhase(this.speed);

        double dSpeed = noise(random) * config.speedVariability();
        double dDirection = noise(random) * config.directionVariability();

        this.speed = Math.max(0, this.speed + dSpeed);
        this.direction = clampDirection(this.direction + dDirection);

        WindPhase after = calculatePhase(this.speed);

        this.windSpeedChange = computeSpeedChange(before, after);
    }

    //-0.5 ~ 0.5 값을 생성
    private double noise(Random random) {
        return random.nextDouble() - 0.5;
    }

    private double clampDirection(double deg) {
        if (deg < -45) return -45;
        if (deg > 45) return 45;
        return deg;
    }


    private WindPhase calculatePhase(double speed) {
        if (speed <= WEAK_MAX) return WindPhase.WEAK;
        if (speed >= STRONG_MIN) return WindPhase.STRONG;
        return WindPhase.NORMAL;
    }

    private WindSpeedChange computeSpeedChange(WindPhase before, WindPhase after) {
        if (before == after) return WindSpeedChange.NONE;
        if (before == WindPhase.WEAK && after == WindPhase.NORMAL) return WindSpeedChange.WEAK_TO_NORMAL;
        if (before == WindPhase.NORMAL && after == WindPhase.STRONG) return WindSpeedChange.NORMAL_TO_STRONG;
        if (before == WindPhase.STRONG && after == WindPhase.NORMAL) return WindSpeedChange.STRONG_TO_NORMAL;
        if (before == WindPhase.NORMAL && after == WindPhase.WEAK) return WindSpeedChange.NORMAL_TO_WEAK;

        return WindSpeedChange.NONE;
    }
}
