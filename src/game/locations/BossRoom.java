package game.locations;

import game.characters.Enemy;

public class BossRoom extends Location {
    private Enemy bossEnemy;
    private boolean bossDefeated;
    
    public BossRoom(String name, String description, Enemy bossEnemy) {
        super(name, description);
        this.bossEnemy = bossEnemy;
        this.bossDefeated = false;
    }
    
    @Override
    public void displayLocation() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║   🏰 BOSS CHAMBER: " + name);
        System.out.println("║ " + description);
        System.out.println("║   ⚔️  Boss: " + bossEnemy.getName());
        System.out.println("╚════════════════════════════════════╝");
    }
    
    @Override
    public Enemy getRandomEnemy() {
        return bossEnemy;
    }
    
    public boolean isBossDefeated() { return bossDefeated; }
    public void setBossDefeated(boolean defeated) { 
        this.bossDefeated = defeated;
        if (defeated) {
            System.out.println("\n🎉 BOSS DEFEATED! The dungeon is now peaceful!");
        }
    }
}