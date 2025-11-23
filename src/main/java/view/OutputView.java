package view;

import domain.event.choice.EventOption;
import domain.event.environment.EnvironmentEventType;
import domain.yacht.Yacht;
import dto.TickResult;

import java.util.List;

public class OutputView {

    public void printWelcome() {
        System.out.println("=======================================");
        System.out.println("⛵  요트 항해 시뮬레이터에 오신 것을 환영합니다!");
        System.out.println("=======================================");
    }

    public void printTutorial() {
        System.out.println();
        System.out.println("📘 **튜토리얼 안내**");
        System.out.println(" - 게임은 총 여러 Tick(턴)으로 진행됩니다.");
        System.out.println(" - 각 턴에서 자연 이벤트, 랜덤 이벤트, 내부 이벤트가 발생할 수 있습니다.");
        System.out.println(" - 이벤트가 발생하면 선택지를 고르고 요트의 안정도·속도에 영향을 줍니다.");
        System.out.println(" - 안정도가 0 이하 → 전복 ❌");
        System.out.println(" - 속도가 0 이하 → 정지 ❌");
        System.out.println(" - 거리를 모두 이동하면 게임 클리어! 🎉");
        System.out.println("---------------------------------------");
    }

    public void printSelectDifficulty() {
        System.out.println("============ 난이도 선택 ============");
        System.out.println(" 레벨을 선택하세요 (1 ~ 10)");
        System.out.println();
        System.out.println(" 1~3   → EASY   (입문자용)");
        System.out.println(" 4~6   → MEDIUM (기본 난이도)");
        System.out.println(" 7~10  → HARD   (상급자용)");
        System.out.println("=====================================");
        System.out.print("레벨 입력 >>> ");
    }

    public void printDifficultyConfirm(int level) {
        System.out.println();
        System.out.println("🔥 선택된 레벨: " );
        System.out.println("---------------------------------------");
    }

    public void printTickHeader(int tick) {
        System.out.println();
        System.out.println("========== 🕒 Tick " + tick + " ==========");
    }

    public void printYachtStatus(Yacht yacht) {
        System.out.println("⚓ 요트 상태");
        System.out.println(" - 안정도: " + yacht.stability());
        System.out.println(" - 속도: " + yacht.power());
        System.out.println(" - 진행도: " + yacht.progress() + "%");
        System.out.println("---------------------------------------");
    }

    public void printNaturalEvent(TickResult result) {
        System.out.println("🌿 [자연 이벤트 발생]");
        printEventDetail(result);
    }

    public void printRandomEvent(TickResult result) {
        System.out.println("🎲 [랜덤 이벤트 발생]");
        printEventDetail(result);
    }

    public void printInternalEvent(TickResult result) {
        System.out.println("⚠️  [요트 내부 이벤트]");
        printEventDetail(result);
    }

    private void printEventDetail(TickResult result) {
        System.out.println(" 이벤트: " + result.type().name());
        System.out.println(" 설명: " + result.description());
        System.out.println("----- 선택지를 골라주세요 -----");

        List<EventOption> options = result.options();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + options.get(i).message());
        }
        System.out.print("입력 >>> ");
    }

    public void printApplyResult(EventOption option) {
        System.out.println();
        System.out.println("🔧 선택한 행동: " + option.message());
        System.out.println(" → 안정도 변화: " + option.stability());
        System.out.println(" → 속도 변화: " + option.power());
        System.out.println("---------------------------------------");
    }

    public void printGameClear() {
        System.out.println();
        System.out.println("🏁=================================================");
        System.out.println("🎉  축하합니다! 목적지에 성공적으로 도착했습니다! 🎉");
        System.out.println("=================================================🏁");
    }

    public void printGameOver(EnvironmentEventType reason) {
        System.out.println();
        System.out.println("💀================= GAME OVER =================💀");

        switch (reason) {
            case YACHT_CAPSIZE ->
                    System.out.println("⚠️ 요트가 전복되었습니다!");
            case YACHT_DEAD_STOP ->
                    System.out.println("⚠️ 요트가 멈춰버렸습니다!");
            default ->
                    System.out.println("⚠️ 치명적인 문제가 발생했습니다!");
        }

        System.out.println("===============================================");
    }

    public void printExit() {
        System.out.println();
        System.out.println("👋 게임을 종료합니다. 다음 항해에서 만나요!");
    }
}
