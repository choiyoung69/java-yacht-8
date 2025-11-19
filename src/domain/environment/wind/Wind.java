package domain.environment.wind;

import java.util.Random;

public class Wind {
    //초기 풍향, 방향 고정
    private static final double INITIAL_WIND_SPEED = 5.0;
    private static final double INITIAL_WIND_DIRECTION = 0.0;

    private double speed;
    private double direction;

    private double lastDeltaSpeed;      // 속도 변화량
    private double lastDeltaDirection;  // 방향 변화량

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
    public void updateNatural(Random random) {
        double dSpeed = noise(random) * config.speedVariability();
        double dDirection = noise(random) * config.directionVariability();

        this.lastDeltaSpeed = dSpeed;
        this.lastDeltaDirection = dDirection;

        this.speed += Math.max(0, speed + dSpeed);
        this.direction = normalize(this.direction + dDirection);
    }

    //-0.5 ~ 0.5 값을 생성
    private double noise(Random random) {
        return random.nextDouble() - 0.5;
    }

    private static double normalize(double rawAngle) {
        double angle = rawAngle % 360;
        if (angle < 0) {
            return angle + 360;
        }
        return angle;
    }

    public boolean isSpeedJump() {
        return Math.abs(lastDeltaSpeed) >= config.speedThreshold();
    }

    public boolean isDirectionJump() {
        return Math.abs(lastDeltaDirection) >= config.directionThreshold();
    }

    public boolean isGustTriggered(Random random) {
        return random.nextDouble() < config.gustChance();
    }

    public boolean isLullTriggered(Random random) {
        return random.nextDouble() < config.lullChance();
    }

    public boolean isShiftTriggered(Random random) {
        return random.nextDouble() < config.shiftChance();
    }

    public boolean isTurbulenceTriggered(Random random) {
        return random.nextDouble() < config.turbulenceChance();
    }

    public double getLastDeltaSpeed() {
        return lastDeltaSpeed;
    }

    public double getLastDeltaDirection() {
        return lastDeltaDirection;
    }
}
