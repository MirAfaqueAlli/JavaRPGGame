package game.engine;

import java.util.Scanner;
import game.characters.Player;
import game.characters.Enemy;
import game.locations.Location;
import game.locations.Room;
import game.locations.BossRoom;
import game.items.Weapon;
import game.items.HealthPotion;

public class Game {
    private Player player;
    private Location currentLocation;
    private Location[] locations;
    private boolean isRunning;
    private int gold;
    
    public Game() {
        initializeGame();
    }
    
    private void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your adventurer's name: ");
        String playerName = scanner.nextLine();
        this.player = new Player(playerName);
        this.gold = 0;
        this.isRunning = true;
        
        createLocations();
        currentLocation = locations[0];
    }
    
    private void createLocations() {
        // Dark Cave - Easy
        Room darkCave = new Room("Dark Cave", "A mysterious and dark cavern filled with echoes", 1);
        darkCave.addEnemy(new Enemy("Goblin", "Creature", 30, 8.0, 2.0, 50, 25));
        darkCave.addEnemy(new Enemy("Skeleton Warrior", "Undead", 40, 10.0, 3.0, 75, 35));
        darkCave.addEnemy(new Enemy("Dark Bat", "Beast", 20, 6.0, 1.0, 40, 15));
        
        // Enchanted Forest - Medium
        Room enchantedForest = new Room("Enchanted Forest", "A mystical forest with glowing trees", 2);
        enchantedForest.addEnemy(new Enemy("Forest Troll", "Creature", 50, 12.0, 4.0, 100, 50));
        enchantedForest.addEnemy(new Enemy("Corrupted Elf", "Humanoid", 45, 11.0, 3.5, 90, 40));
        enchantedForest.addEnemy(new Enemy("Giant Spider", "Beast", 35, 10.0, 2.5, 80, 30));
        
        // Ancient Temple - Hard
        Room ancientTemple = new Room("Ancient Temple", "An ancient temple covered in mysterious runes", 3);
        ancientTemple.addEnemy(new Enemy("Stone Golem", "Construct", 60, 14.0, 6.0, 150, 75));
        ancientTemple.addEnemy(new Enemy("Phantom Knight", "Undead", 55, 13.0, 5.0, 130, 60));
        ancientTemple.addEnemy(new Enemy("Temple Guardian", "Construct", 50, 12.0, 5.5, 120, 55));
        
        // Dragon's Lair - Boss Room
        Enemy dragonBoss = new Enemy("Ancient Dragon", "Boss", 200, 20.0, 8.0, 500, 300);
        BossRoom dragonsLair = new BossRoom("Dragon's Lair", "The lair of an ancient and powerful dragon", dragonBoss);
        
        locations = new Location[] { darkCave, enchantedForest, ancientTemple, dragonsLair };
    }
    
    public void start() {
        displayWelcome();
        Scanner scanner = new Scanner(System.in);
        
        while (isRunning) {
            displayMainMenu();
            String choice = scanner.nextLine().toLowerCase();
            
            switch(choice) {
                case "1":
                    exploreLocation();
                    break;
                case "2":
                    currentLocation.displayLocation();
                    break;
                case "3":
                    player.displayInventory();
                    break;
                case "4":
                    player.displayStatus();
                    break;
                case "5":
                    travelToLocation(scanner);
                    break;
                case "6":
                    displayGameInfo();
                    break;
                case "7":
                    endGame();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
    
    private void displayWelcome() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║        🎮 WELCOME TO THE MYSTICAL ADVENTURE QUEST! 🎮       ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Embark on an epic journey through dangerous dungeons       ║");
        System.out.println("║  Battle fierce monsters, collect treasure, and level up!    ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println(player.getCharacterInfo());
    }
    
    private void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        MAIN MENU - SELECT ACTION    ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1. Explore (Battle Random Enemy)   ║");
        System.out.println("║ 2. View Current Location           ║");
        System.out.println("║ 3. Check Inventory                 ║");
        System.out.println("║ 4. Check Character Stats           ║");
        System.out.println("║ 5. Travel to Another Location      ║");
        System.out.println("║ 6. Help & Info                     ║");
        System.out.println("║ 7. Quit Game                       ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Enter choice (1-7): ");
    }
    
    private void exploreLocation() {
        if (!player.isAlive()) {
            System.out.println("You are dead! Game over.");
            isRunning = false;
            return;
        }
        
        Enemy enemy = currentLocation.getRandomEnemy();
        if (enemy == null) {
            System.out.println("No enemies found in this location!");
            return;
        }
        
        Enemy battleEnemy = new Enemy(enemy.getName(), enemy.getType(), enemy.getMaxHealth(), 
                                     enemy.attackPower, enemy.defense, 
                                     enemy.getExperienceReward(), enemy.getGoldReward());
        
        startBattle(battleEnemy);
    }
    
    private void startBattle(Enemy enemy) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      ⚔️  BATTLE ENCOUNTERED!  ⚔️     ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println(enemy.getCharacterInfo());
        
        Scanner scanner = new Scanner(System.in);
        boolean battleRunning = true;
        
        while (battleRunning && player.isAlive() && enemy.isAlive()) {
            displayBattleMenu(enemy);
            String action = scanner.nextLine().toLowerCase();
            
            switch(action) {
                case "1": // Attack
                    performPlayerAttack(enemy);
                    break;
                case "2": // Defend
                    player.defend();
                    break;
                case "3": // Cast Spell
                    castSpellInBattle(enemy, scanner);
                    break;
                case "4": // Use Potion
                    useItemInBattle(scanner);
                    break;
                case "5": // Run Away
                    if (Math.random() > 0.4) {
                        System.out.println("You successfully escaped!");
                        battleRunning = false;
                    } else {
                        System.out.println("Failed to escape!");
                    }
                    break;
                default:
                    System.out.println("Invalid action!");
                    continue;
            }
            
            if (enemy.isAlive()) {
                int enemyDamage = enemy.attack();
                player.takeDamage(enemyDamage);
                player.displayStatus();
            }
            
            if (!enemy.isAlive()) {
                battleWon(enemy);
                battleRunning = false;
            } else if (!player.isAlive()) {
                battleLost();
                battleRunning = false;
            }
        }
    }
    
    private void displayBattleMenu(Enemy enemy) {
        System.out.println("\n⚔️  Battle Options  ⚔️");
        System.out.println("Enemy: " + enemy.getName() + " [HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth() + "]");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Cast Spell");
        System.out.println("4. Use Item");
        System.out.println("5. Run Away");
        System.out.print("Choose action: ");
    }
    
    private void performPlayerAttack(Enemy enemy) {
        int damage = player.attack();
        enemy.takeDamage(damage);
        System.out.println(player.getName() + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }
    
    private void castSpellInBattle(Enemy enemy, Scanner scanner) {
        System.out.println("\nAvailable Spells:");
        System.out.println("1. Fireball (30 Mana, ~30-50 damage)");
        System.out.println("2. Lightning Strike (40 Mana, ~40-65 damage)");
        System.out.print("Choose spell (1-2): ");
        
        String spellChoice = scanner.nextLine();
        int damage = 0;
        
        if (spellChoice.equals("1")) {
            damage = player.castSpell("fireball");
        } else if (spellChoice.equals("2")) {
            damage = player.castSpell("lightning");
        } else {
            System.out.println("Invalid spell choice!");
            return;
        }
        
        if (damage > 0) {
            enemy.takeDamage(damage);
        }
    }
    
    private void useItemInBattle(Scanner scanner) {
        if (player.getInventory().isEmpty()) {
            System.out.println("No items in inventory!");
            return;
        }
        
        player.displayInventory();
        System.out.print("Choose item number to use: ");
        try {
            int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (itemIndex >= 0 && itemIndex < player.getInventory().size()) {
                var item = player.getInventory().get(itemIndex);
                if (item instanceof HealthPotion) {
                    HealthPotion potion = (HealthPotion) item;
                    player.heal(potion.getHealAmount());
                    player.removeItem(item);
                    System.out.println("You used " + item.getName());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
    
    private void battleWon(Enemy enemy) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     🎉 VICTORY! 🎉                 ║");
        System.out.println("╚════════════════════════════════════╝");
        
        int exp = enemy.getExperienceReward();
        int goldReward = enemy.getGoldReward();
        
        player.gainExperience(exp);
        gold += goldReward;
        System.out.println("✓ Gold earned: " + goldReward);
        System.out.println("Total Gold: " + gold);
        
        if (Math.random() > 0.6) {
            Weapon newWeapon = new Weapon(enemy.getName() + "'s Blade", 15, "A powerful weapon from " + enemy.getName());
            player.addItem(newWeapon);
        }
    }
    
    private void battleLost() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     💀 YOU HAVE BEEN DEFEATED! 💀   ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Final Stats - Level: " + player.getLevel() + ", Experience: " + player.getExperience());
        isRunning = false;
    }
    
    private void travelToLocation(Scanner scanner) {
        System.out.println("\nAvailable Locations:");
        for (int i = 0; i < locations.length; i++) {
            System.out.println((i + 1) + ". " + locations[i].getName());
        }
        System.out.print("Choose location: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            if (choice >= 0 && choice < locations.length) {
                currentLocation = locations[choice];
                currentLocation.displayLocation();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
    
    private void displayGameInfo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║          GAME INFORMATION          ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ Your goal is to defeat the Ancient ║");
        System.out.println("║ Dragon and save the realm!         ║");
        System.out.println("║                                    ║");
        System.out.println("║ • Battle monsters to gain XP       ║");
        System.out.println("║ • Collect gold and weapons         ║");
        System.out.println("║ • Level up to become stronger      ║");
        System.out.println("║ • Defeat the boss in Dragon's Lair ║");
        System.out.println("║                                    ║");
        System.out.println("║ Current Location: " + currentLocation.getName());
        System.out.println("║ Total Gold: " + gold);
        System.out.println("╚════════════════════════════════════╝");
    }
    
    private void endGame() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        THANKS FOR PLAYING!         ║");
        System.out.println("║                                    ║");
        System.out.println("║  Final Stats:                      ║");
        System.out.println("║  Level: " + player.getLevel());
        System.out.println("║  Experience: " + player.getExperience());
        System.out.println("║  Gold: " + gold);
        System.out.println("║  Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("╚════════════════════════════════════╝");
        isRunning = false;
    }
}