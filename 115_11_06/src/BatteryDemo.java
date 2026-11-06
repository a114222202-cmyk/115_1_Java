public class BatteryDemo {

    public static void main(String[] args) {
        RoverBattery low = new RoverBattery(5);
        low.explore();
        System.out.println("explore, energy=" + low.getEnergy());

        low.charge();
        System.out.println("charge, energy=" + low.getEnergy());

        low.explore();
        System.out.println("explore, energy=" + low.getEnergy());

        RoverBattery edge = new RoverBattery(19);
        edge.charge();
        System.out.println("cap, energy=" + edge.getEnergy());

        edge.charge();
        System.out.println("full, energy=" + edge.getEnergy());
    }
}
