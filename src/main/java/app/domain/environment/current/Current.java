package app.domain.environment.current;

import java.util.Random;

public class Current {
    private static final double DEFAULT_CURRENT_SPEED = 1.0;
    private static final int DEFAULT_STRONG_DURATION = 0;
    private static final int MIN_SPEED = 0;
    private static final int MAX_SPEED = 5;

    private double currentSpeed;
    private int strongDuration;

    private final CurrentConfig config;

    private Current(double currentSpeed, int strongDuration, CurrentConfig config) {
        this.currentSpeed = currentSpeed;
        this.strongDuration = strongDuration;
        this.config = config;
    }

    public static Current initalizeCurrent(CurrentConfig currentConfig) {
        return new Current(DEFAULT_CURRENT_SPEED, DEFAULT_STRONG_DURATION, currentConfig);
    }

    public void updateNatural(Random random) {
        double delta = noise(random) * config.naturalVariability();
        currentSpeed += delta;
        currentSpeed = Math.max(MIN_SPEED, Math.min(MAX_SPEED, currentSpeed));

        updateStrongDuration();
    }

    //-0.5 ~ 0.5 값을 생성
    private double noise(Random random) {
        return random.nextDouble() - 0.5;
    }

    private void updateStrongDuration() {
        if (currentSpeed >= config.strongThreshold()) {
            strongDuration++;
        } else {
            strongDuration = 0;
        }
    }

    public boolean shouldTriggerStrongEvent() {
        return strongDuration >= config.strongDurationThreshold();
    }
}
