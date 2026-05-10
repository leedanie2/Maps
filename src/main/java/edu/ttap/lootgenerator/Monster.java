package edu.ttap.lootgenerator;

/**
 * Stores basic info about a monster
 */
public class Monster {

    String name;

    String type;

    int level;

    String treasureClass;

    /**
     * Constructs a new Monster with the given characteristic
     *
     * @param name          the display name of the monster
     * @param type          the category or species of the monster
     * @param level         the level of the monster
     * @param treasureClass the designed treasureclass of that monster
     */
    public Monster(String name, String type, int level, String treasureClass) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }
}