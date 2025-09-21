public class ControllerAgent extends Agent {
    private String specialAbility;

    ControllerAgent(String name, int health, int armor, String specialAbility) {
        super(name, health, armor);
        this.specialAbility = specialAbility;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Special Ability: " + this.specialAbility);
    }

    public void smoke() {
    
        System.out.println(getName() + " uses Smoke!");
    }
    
}