package domain.yacht;

public class Yacht {
    private static final double FINISH_DISTANCE = 100.0;
    private double stability;
    private double power;
    private double progress;

    public void changeStability(int delta) {
        this.stability += delta;
    }

    public void changePower(int delta) {
        this.power += delta;
    }

    public double stability() {
        return stability;
    }

    public double power() {
        return power;
    }

    public double progress() {
        return progress;
    }

    public void update() {
        progress += Math.max(0, power * 0.5);
        if (progress > FINISH_DISTANCE) progress = FINISH_DISTANCE;
    }

    public boolean hasFinished() {
        return progress >= FINISH_DISTANCE;
    }
}
