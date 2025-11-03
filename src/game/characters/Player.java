package game.characters;

import java.util.ArrayList;
import java.util.List;
import game.items.Item;
import game.items.Weapon;

public class Player extends Character {
    private List<Item> inventory;
    private Weapon currentWeapon;
    private int skillPoints;
    
    // Constructor
    public Player(String name) {
        super(name, 100, 50, 15.0, 5.0);
        this.inventory = new ArrayList<>();
        this.skillPoints = 0;
        this.currentWeapon = new Weapon("Iron Sword", 10, "A basic iron sword");
        inventory.add(currentWeapon);
    }
    
    // Polymorphism - Override attack method
    @Override
    public int attack() {
        int damage = (int) (attackPower + currentWeapon.getDamage());
        // Add random variance
        damage += (int) (Math.random() * 5);
        return damage;
    }
    
    // Polymorphism - Override defend method
    @Override
    public void defend() {
        System.out.println(name + " takes a defensive stance!");
        defense += 3;
    }
    
    // Polymorphism - Override getCharacterInfo
    @Override
    public String getCharacterInfo() {
        return "\n=== PLAYER INFO ===\n" +
               "Name: " + name + "\n" +
               "Level: " + level + "\n" +
               "Health: " + health + "/" + maxHealth + "\n" +
               "Mana: " + mana + "/" + maxMana + "\n" +
               "Weapon: " + currentWeapon.getName() + "\n" +
               "Skill Points: " + skillPoints;
    }
    
    // Encapsulation - Inventory management
    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("✓ " + item.getName() + " added to inventory!");
    }
    
    public void removeItem(Item item) {
        inventory.remove(item);
    }
    
    public void displayInventory() {
        System.out.println("\n=== INVENTORY ===");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty!");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getName() + 
                             " - " + inventory.get(i).getDescription());
        }
    }
    
    // Cast spell method
    public int castSpell(String spellName) {
        if (spellName.equalsIgnoreCase("fireball")) {
            if (consumeMana(30)) {
                int damage = (int) (30 + Math.random() * 20);
                System.out.println(name + " casts Fireball!");
                return damage;
            }
        } else if (spellName.equalsIgnoreCase("lightning")) {
            if (consumeMana(40)) {
                int damage = (int) (40 + Math.random() * 25);
                System.out.println(name + " casts Lightning Strike!");
                return damage;
            }
        }
        System.out.println("Not enough mana!");
        return 0;
    }
    
    // Leveling system
    public void gainExperience(int exp) {
        experience += exp;
        System.out.println("✓ Gained " + exp + " experience points!");
        
        if (experience >= 100 * level) {
            levelUp();
        }
    }
    
    private void levelUp() {
        level++;
        maxHealth += 20;
        health = maxHealth;
        maxMana += 15;
        mana = maxMana;
        attackPower += 5;
        defense += 2;
        skillPoints += 2;
        
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        LEVEL UP! Level: " + level + "      ║");
        System.out.println("║  Stats increased! Skill Points: " + skillPoints + "  ║");
        System.out.println("╚════════════════════════════════════╝");
    }
    
    // Getters
    public List<Item> getInventory() { return inventory; }
    public Weapon getCurrentWeapon() { return currentWeapon; }
    public void setCurrentWeapon(Weapon weapon) { 
        this.currentWeapon = weapon; 
        System.out.println("Equipped: " + weapon.getName());
    }
}
