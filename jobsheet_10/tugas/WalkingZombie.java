package tugas;
public class WalkingZombie extends Zombie {

    public WalkingZombie(int health, int level) {
        this.health = health;
        this.level = level;
    }

    @Override
    public void heal() {
        int percent = 0;
        switch (level) {
            case 1: percent = 20; break;
            case 2: percent = 30; break;
            case 3: percent = 40; break;
        }
        health += health * percent / 100;
    }

    @Override
    public void destroyed() {
        health -= health * 20 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Walking Zombie Data =" + System.lineSeparator() +
                "Health = " + health + System.lineSeparator() +
                "Level = " + level;
    }
}
