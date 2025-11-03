package game.locations;

import java.util.ArrayList;
import java.util.List;
import game.characters.Enemy;

public abstract class Location {
    protected String name;
    protected String description;
    protected List<Enemy> enemies;
    
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.enemies = new ArrayList<>();
    }
    
    public abstract void displayLocation();
    public abstract Enemy getRandomEnemy();
    
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Enemy> getEnemies() { return enemies; }
}