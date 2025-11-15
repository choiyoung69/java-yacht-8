package domain.environment.wind;

public record WindConfig (
    double speedVariability, //풍속 변화량, 게임 레벨에 따라 변동성이 커짐
    double directionVariability, //

    double gustChance,  //돌풍 발생 확률
    double lullChance,  //약풍 발생 확률
    double shiftChance, //풍향 급변 확률
    double turbulenceChance //난류 발생 확률
) { }
