package game.items;

public class Weapon extends Item {
    private int damage;
    
    public Weapon(String name, int damage, String description) {
        super(name, description, damage * 10);
        this.damage = damage;
    }
    
    @Override
    public void use() {
        System.out.println("You wield " + name + "!");
    }
    
    @Override
    public String getItemInfo() {
        return name + " (Damage: " + damage + ") - " + description;
    }
    
    public int getDamage() { return damage; }
}