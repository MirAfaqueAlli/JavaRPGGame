package game.items;

public class HealthPotion extends Item {
    private int healAmount;
    
    public HealthPotion(String name, int healAmount, String description) {
        super(name, description, 50);
        this.healAmount = healAmount;
    }
    
    @Override
    public void use() {
        System.out.println("You drink " + name + " and restore " + healAmount + " health!");
    }
    
    @Override
    public String getItemInfo() {
        return name + " (Restores: " + healAmount + " HP) - " + description;
    }
    
    public int getHealAmount() { return healAmount; }
}