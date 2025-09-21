public class Agent {
    private String name;
    private int health;
    private int armor;
    private int totalHealth;
    private int baseHealth;
    private int baseArmor;
    private Weapon weapon;

    Agent(String name, int health, int armor) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.totalHealth = health + armor;
        this.baseHealth = health;
        this.baseArmor = armor;
    }

    private void respawn() {
        this.health = baseHealth;
        this.armor = baseArmor;
        this.totalHealth = this.health + this.armor;
        System.out.println(this.name + " has respawned with full HP: " + totalHealth);
    }

    public boolean shoot(Agent enemy, int hitCount) {
        boolean isDied = false;

        if (this.weapon == null) {
            System.out.println(this.name + " has no weapon to shoot.");
        }

        int totalDamage = 0;

        for (int i = 0; i < hitCount; i++) {
            if (this.weapon.getAmmo() > 0 && enemy.getTotalHealth() > 0) {
                int damage = this.weapon.getDamage();
                enemy.takeDamage(this.weapon.getDamage());
                this.weapon.oneShoot();
                totalDamage += damage;
                System.out.println(this.name + " shoots " + enemy.getName() +
                        " with " + this.weapon.getName() +
                        " (damage: " + damage + ")");
                System.out.println("=> " + enemy.getName() +
                        " totalHealth: " + enemy.getTotalHealth());

                if (enemy.getTotalHealth() <= 0) {
                    System.out.println(enemy.getName() + " already Died");
                    isDied = true;
                    enemy.respawn();
                    System.out.println(enemy.getName() + " respawned with HP: " + enemy.getTotalHealth());
                    break;
                }
            } else {
                System.out.println(this.name + " has no ammo, reload needed!");
                break;
            }
        }
        System.out.println("SUMMARY " + this.name + " attacked " +
                enemy.getName() + " for " + totalDamage + " damage.");
                
        return isDied;

    }

    private void takeDamage(int damage) {
        this.totalHealth -= damage;
        if (this.totalHealth < 0) {
            this.totalHealth = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void info() {
        System.out.println("Agent: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Armor: " + this.armor);
        if (this.weapon != null) {
            System.out.println("Weapon: " + this.weapon.getName());
            System.out.println("Damage: " + this.weapon.getDamage());
            System.out.println("Ammo: " + this.weapon.getAmmo());
        } else {
            System.out.println("Weapon: None");
        }
    }

}