package tugas;
public class Barrier implements Destroyable {
    private int strength;

    public Barrier(int strength) {
        this.strength = strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void destroy() {
        strength -= 9;
        if(strength < 0) strength = 0;
    }

    @Override
    public void destroyed() {
        destroy();
    }

    public String getBarrierInfo() {
        return "Barrier Strength = " + strength;
    }
}
