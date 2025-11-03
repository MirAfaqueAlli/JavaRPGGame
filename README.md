# Java RPG Game

A text-based Role-Playing Game (RPG) implemented in Java that features turn-based combat, character progression, and inventory management.

## Features

- Turn-based combat system
- Character leveling and progression
- Inventory management
- Multiple enemy types
- Combat abilities and spells
- Gold and experience rewards
- Equipment system

## Project Structure

```
JavaRPGGame/
├── src/
│   ├── game/
│   │   ├── characters/
│   │   │   ├── Character.java
│   │   │   ├── Player.java
│   │   │   └── Enemy.java
│   │   ├── engine/
│   │   │   └── Game.java
│   │   ├── items/
│   │   │   ├── Item.java
│   │   │   └── Weapon.java
│   │   └── Main.java
└── README.md
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Command line terminal or IDE (like VS Code)

### Running the Game

1. Clone the repository:
```bash
git clone https://github.com/MirAfaqueAlli/JavaRPGGame.git
```

2. Navigate to the project directory:
```bash
cd JavaRPGGame
```

3. Compile the source files:
```bash
javac src/game/*.java src/game/characters/*.java src/game/items/*.java src/game/locations/*.java src/game/engine/*.java
```

4. Run the game:
```bash
java -cp src game.Main
```

## Gameplay

1. Enter your character's name when prompted
2. Navigate through menus using number keys
3. Battle enemies and gain experience
4. Level up and become stronger
5. Collect items and gold
6. Manage your inventory and equipment

## Game Controls

- Enter numbers (1-7) to select menu options
- Follow on-screen prompts for actions
- During battle:
  - 1: Attack
  - 2: Defend
  - 3: Cast Spell
  - 4: Use Item
  - 5: Run Away

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Author

- **Mir Afaque Alli** - [GitHub Profile](https://github.com/MirAfaqueAlli)

## Acknowledgments

- Inspired by classic RPG games
- Built as a Java programming exercise
