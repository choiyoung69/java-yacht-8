package domain.event;

import domain.yacht.Yacht;

public class EventEffect {
    private final int stabilityChange;
    private final int powerChange;

    public EventEffect(int stabilityChange, int powerChange) {
        this.stabilityChange = stabilityChange;
        this.powerChange = powerChange;
    }

    public void apply(Yacht yacht) {
        yacht.changeStability(stabilityChange);
        yacht.changePower(powerChange);
    }
}
