package domain.environment;

import domain.environment.wind.Wind;
import domain.event.EventManager;
import domain.event.EventType;

public class Environment {
    private final Wind wind;
    private final EventManager eventManager;

    public Environment(Wind wind, EventManager eventManager) {
        this.wind = wind;
        this.eventManager = eventManager;
    }

    public void update() {
        wind.updateNatural();
    }

    public void collectEnvironmentEvents() {
        if (wind.isSpeedJump()) {
            eventManager.registerEvent(
                    EventType.WIND_SPEED_SPIKE,
                    "🌬 바람이 갑자기 강해졌습니다! (변화량: %.1f m/s)".formatted(wind.lastDeltaSpeed())
            );
        }

        if (wind.isDirectionJump()) {
            eventManager.registerEvent(
                    EventType.WIND_DIRECTION_SHIFT,
                    "💨 풍향이 크게 바뀌었습니다! (변화량: %.1f°)".formatted(wind.lastDeltaDirection())
            );
        }
    }
}
