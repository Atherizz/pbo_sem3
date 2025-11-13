package tugas;
public class JumpingZombie extends Zombie {

    public JumpingZombie(int health, int level) {
        this.health = health;
        this.level = level;
    }

    @Override
    public void heal() {
        int percent = 0;
        switch (level) {
            case 1: percent = 30; break;
            case 2: percent = 40; break;
            case 3: percent = 50; break;
        }
        health += health * percent / 100;
    }

    @Override
    public void destroyed() {
        health -= health * 10 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Jumping Zombie Data =" + System.lineSeparator() +
                "Health = " + health + System.lineSeparator() +
                "Level = " + level;
    }
}
