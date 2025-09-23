public class Weapon {
    private String name;
    private int damage;
    private int ammo;
    private int baseAmmo;

    Weapon(String name, int damage, int ammo) {
        this.name = name;
        this.damage = damage;
        this.ammo = ammo;
        this.baseAmmo = ammo;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmmo() {
        return ammo;
    }

    public void reload() {
        this.ammo = baseAmmo;
    }

    public void oneShoot() {
        this.ammo -= 1;
    }

    public String info()
    {
        String info = "";  
        info += "Weapon: " + this.name + "\n";
        info += "Damage: " + this.damage + "\n";
        info += "Ammo: " + this.ammo + "\n";
        return info;
    }


}
