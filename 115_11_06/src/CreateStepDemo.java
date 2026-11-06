public class CreateStepDemo {
    public static void main(String[] args) {
        StepCard first = new StepCard(1, 14);
        StepCard second = new StepCard(2, 12);
        System.out.println("first=" + first.getCommand() + "/" + first.getEnergy());
        System.out.println("second=" + second.getCommand() + "/" + second.getEnergy());
        System.out.println("same object=" + (first == second));
        
        // 測試修改電量（使用 setter 方法）
        first.setEnergy(18);
        System.out.println("修改後的 first energy=" + first.getEnergy());

        // 測試輸入不合理的數值（會觸發提示訊息）
        first.setEnergy(99);
    }
}