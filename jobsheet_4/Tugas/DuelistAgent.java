public class DuelistAgent extends Agent {
    private String specialAbility;

    DuelistAgent(String agent, int health, int armor, String specialAbility) {
        super(agent, health, armor);
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

    public void dash() {
    
        System.out.println(getName() + " uses Dash!");
    }
    
}
