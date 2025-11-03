package game.characters;

public class Enemy extends Character {
    private String type;
    private int experienceReward;
    private int goldReward;
    
    // Constructor - Overloaded
    public Enemy(String name, String type, int health, double attackPower, double defense, 
                 int expReward, int goldReward) {
        super(name, health, 0, attackPower, defense);
        this.type = type;
        this.experienceReward = expReward;
        this.goldReward = goldReward;
    }
    
    // Polymorphism - Override attack method
    @Override
    public int attack() {
        int baseDamage = (int) attackPower;
        int randomDamage = (int) (Math.random() * 8);
        int totalDamage = baseDamage + randomDamage;
        
        System.out.println(name + " attacks for " + totalDamage + " damage!");
        return totalDamage;
    }
    
    // Polymorphism - Override defend method
    @Override
    public void defend() {
        System.out.println(name + " braces for impact!");
        defense += 2;
    }
    
    // Polymorphism - Override getCharacterInfo
    @Override
    public String getCharacterInfo() {
        return "\n=== ENEMY INFO ===\n" +
               "Name: " + name + "\n" +
               "Type: " + type + "\n" +
               "Health: " + health + "/" + maxHealth + "\n" +
               "Attack Power: " + attackPower + "\n" +
               "Defense: " + defense + "\n" +
               "Rewards: " + experienceReward + " XP, " + goldReward + " Gold";
    }
    
    // Getters
    public String getType() { return type; }
    public int getExperienceReward() { return experienceReward; }
    public int getGoldReward() { return goldReward; }
}