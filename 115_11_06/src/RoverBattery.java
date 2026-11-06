public class RoverBattery {
    private int energy;

    public RoverBattery(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void explore() {
        if (energy >= 5) {
            energy -= 5;
        }
    }

    // 補上這個充電方法
    public void charge() {
        if (energy < 20) {
            energy += 5;
            if (energy > 20) {
                energy = 20;
            }
        }
    }
}