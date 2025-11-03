package game.locations;

import game.characters.Enemy;

public class Room extends Location {
    private int difficulty;
    
    public Room(String name, String description, int difficulty) {
        super(name, description);
        this.difficulty = difficulty;
    }
    
    @Override
    public void displayLocation() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        📍 " + name);
        System.out.println("║ " + description);
        System.out.println("║ Difficulty: " + getDifficultyLevel());
        System.out.println("╚════════════════════════════════════╝");
    }
    
    @Override
    public Enemy getRandomEnemy() {
        if (enemies.isEmpty()) return null;
        int randomIndex = (int) (Math.random() * enemies.size());
        return enemies.get(randomIndex);
    }
    
    private String getDifficultyLevel() {
        switch(difficulty) {
            case 1: return "Easy ⭐";
            case 2: return "Medium ⭐⭐";
            case 3: return "Hard ⭐⭐⭐";
            case 4: return "Legendary ⭐⭐⭐⭐";
            default: return "Unknown";
        }
    }
    
    public int getDifficulty() { return difficulty; }
}