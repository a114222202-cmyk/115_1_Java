// 契約與教師版相同；佔位值只是讓骨架能啟動，不代表完成需求。
public class MissionStep {
    private int command;
    private int energy;
    private String result;

    // TODO 1: 以 this 將三個參數保存到目前物件的三個欄位。
    public MissionStep(int command, int energy, String result) {
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    // TODO 2: 三個 getter 應讀取對應欄位，取代佔位回傳值。
    public int getCommand() {
        return this.command;
    }

    public int getEnergy() {
        return this.energy;
    }

    public String getResult() {
        return this.result;
    }

    // TODO 3: 以內容比較判斷本物件的 result 是否為 OK。
    public boolean isSuccessful() {
        return "OK".equals(this.result);
    }
}
