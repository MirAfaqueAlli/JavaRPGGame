package game.characters;

public abstract class Character {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int mana;
    protected int maxMana;
    protected int experience;
    protected int level;
    public double attackPower;
    public double defense;
    
    // Constructor
    public Character(String name, int maxHealth, int maxMana, double attackPower, double defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.experience = 0;
        this.level = 1;
        this.attackPower = attackPower;
        this.defense = defense;
    }
    
    // Encapsulation - Protected setter methods
    public void takeDamage(double damage) {
        double reducedDamage = damage - (defense * 0.5);
        if (reducedDamage < 0) reducedDamage = 0;
        this.health -= (int) reducedDamage;
        if (this.health < 0) this.health = 0;
    }
    
    public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }
    
    protected void restoreMana(int amount) {
        this.mana += amount;
        if (this.mana > this.maxMana) {
            this.mana = this.maxMana;
        }
    }
    
    protected boolean consumeMana(int amount) {
        if (this.mana >= amount) {
            this.mana -= amount;
            return true;
        }
        return false;
    }
    
    // Polymorphism - Abstract methods
    public abstract int attack();
    public abstract void defend();
    public abstract String getCharacterInfo();
    
    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public int getExperience() { return experience; }
    public int getLevel() { return level; }
    public boolean isAlive() { return health > 0; }
    
    // Display status
    public void displayStatus() {
        System.out.println("\n" + name + " | HP: " + health + "/" + maxHealth + 
                         " | Mana: " + mana + "/" + maxMana + " | Level: " + level);
        System.out.println("Experience: " + experience);
    }
}