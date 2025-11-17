package domain.EventTrigger.wind;

import domain.EventTrigger.EnvironmentEventTrigger;
import domain.environment.Environment;
import domain.environment.wind.Wind;
import domain.event.EventManager;
import domain.event.EventType;
import java.util.Random;

public class WindEventTrigger implements EnvironmentEventTrigger {
    public static final String GUST = "🌪 갑작스러운 돌풍이 몰아칩니다!";
    public static final String LULL = "🌫 바람이 약해져 배가 느려질 수 있습니다.";
    public static final String SHIFT = "💨 풍향이 예기치 못하게 변하려 합니다!";
    public static final String TURBULENCE = "💥 난류로 인해 풍향과 풍속이 불안정해집니다!";

    @Override
    public void apply(Environment environment, EventManager eventManager, Random random) {
        Wind wind = environment.wind();

        if (wind.isGustTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_GUST,
                    GUST
            );
        }

        if (wind.isLullTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_LULL,
                    LULL
            );
        }

        if (wind.isShiftTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_RANDOM_SHIFT,
                    SHIFT
            );
        }

        if (wind.isTurbulenceTriggered(random)) {
            eventManager.registerEvent(
                    EventType.WIND_TURBULENCE,
                    TURBULENCE
            );
        }
    }
}
