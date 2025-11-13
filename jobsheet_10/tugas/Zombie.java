package tugas;
public class Zombie implements Destroyable {
    protected int health;
    protected int level;

     void heal() {
        
    }

    @Override
    public void destroyed() {

    }

    public String getZombieInfo() {
        return "Zombie Data =" + System.lineSeparator() +
                "Health = " + health + System.lineSeparator() +
                "Level = " + level;
    }
}
