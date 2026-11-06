import java.util.ArrayList;

// 第五頁修改日誌表現：執行後連同歷史記錄輸出的完整程式碼。
public class MissionLog {

    public static void main(String[] args) {
        int[] script = {1, 1, 2};
        runMission(script, 20, 0);
    }

    // 擴展測試入口，script 陣列、初始 energy 0..20、samples 數量
    static void runMission(int[] script, int energy, int samples) {
        int actionCount = 0;
        boolean running = true;
        ArrayList<String> history = new ArrayList<>();

        // 先判斷狀態，若已到達終點或本體已損壞，不需執行第一筆指令。
        for (int i = 0; i < script.length && running && energy > 0 && samples < 3; i++) {
            int command = script[i];
            String result = "";
            switch (command) {
                case 1:
                    if (energy >= 6) {
                        energy -= 6;
                        samples += 2;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 2:
                    if (energy >= 2) {
                        energy -= 2;
                        samples++;
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "NOT_ENOUGH_ENERGY";
                    }
                    break;
                case 3:
                    if (energy < 20) {
                        energy += 5;
                        if (energy > 20) {
                            energy = 20;
                        }
                        actionCount++;
                        result = "OK";
                    } else {
                        result = "ALREADY_FULL";
                    }
                    break;
                case 0:
                    running = false;
                    result = "RETURN";
                    break;
                default:
                    result = "UNKNOWN_COMMAND";
                    break;
            }

            // 記錄歷程並列印結果
            history.add("cmd=" + command + ", result=" + result + ", energy=" + energy + ", samples=" + samples);
            System.out.println("cmd=" + command + " -> " + result + " | energy=" + energy + ", samples=" + samples);
        }

        System.out.println("--- 執行完成 ---");
        System.out.println("總動作數: " + actionCount);
        System.out.println("最終電量: " + energy + ", 最終樣本數: " + samples);
        System.out.println("歷史記錄: " + history);
    }
}