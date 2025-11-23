package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int readDifficultyLevel() {
        while (true) {
            System.out.print("레벨 입력 >>> ");
            String input = scanner.nextLine().trim();

            try {
                int level = Integer.parseInt(input);

                if (level < 1 || level > 10) {
                    System.out.println("❗ 1~10 사이의 숫자를 입력하세요.");
                    continue;
                }
                return level;

            } catch (NumberFormatException e) {
                System.out.println("❗ 숫자만 입력해주세요.");
            }
        }
    }

    public int readOptionSelection(int optionCount) {
        while (true) {
            System.out.print("선택 >>> ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice < 1 || choice > optionCount) {
                    System.out.println("❗ 1~" + optionCount + " 중에서 고르세요.");
                    continue;
                }

                return choice - 1; // index 변환

            } catch (NumberFormatException e) {
                System.out.println("❗ 숫자만 입력해주세요.");
            }
        }
    }

    public boolean askRestart() {
        while (true) {
            System.out.print("다시 시작하시겠습니까? (y/n) >>> ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "y", "yes" -> {
                    return true;
                }
                case "n", "no" -> {
                    return false;
                }
                default -> {
                    System.out.println("❗ y 또는 n으로 입력하세요.");
                }
            }
        }
    }
}

