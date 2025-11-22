package domain.yacht;

public class Yacht {
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
}
