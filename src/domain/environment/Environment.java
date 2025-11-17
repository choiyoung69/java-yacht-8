package domain.environment;

import domain.environment.wind.Wind;
import domain.event.EventManager;
import domain.event.EventType;
import java.util.Random;

public class Environment {
    private final Wind wind;
    private final EventManager eventManager;
    private final Random random = new Random();

    public Environment(Wind wind, EventManager eventManager) {
        this.wind = wind;
        this.eventManager = eventManager;
    }

    public void update() {
        wind.updateNatural(random);
    }

    public void collectEnvironmentEvents() {
        if (wind.isSpeedJump()) {
            eventManager.registerEvent(
                    EventType.WIND_SPEED_SPIKE,
                    "🌬 바람이 갑자기 강해졌습니다! (변화량: %.1f m/s)".formatted(wind.getLastDeltaSpeed())
            );
        }

        if (wind.isDirectionJump()) {
            eventManager.registerEvent(
                    EventType.WIND_DIRECTION_SHIFT,
                    "💨 풍향이 크게 바뀌었습니다! (변화량: %.1f°)".formatted(wind.getLastDeltaDirection())
            );
        }
    }

    private void triggerRandomEvents() {
        if (wind.isGustTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_GUST,
                    "🌪 갑작스러운 돌풍이 몰아칩니다!"
            );
        }

        if (wind.isLullTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_LULL,
                    "🌫 바람이 약해져 배가 느려질 수 있습니다."
            );
        }

        if (wind.isShiftTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_RANDOM_SHIFT,
                    "💨 풍향이 예기치 못하게 변하려 합니다!"
            );
        }

        if (wind.isTurbulenceTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_TURBULENCE,
                    "💥 난류로 인해 풍향과 풍속이 불안정해집니다!"
            );
        }
    }
}
