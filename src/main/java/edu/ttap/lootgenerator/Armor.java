package edu.ttap.lootgenerator;

/**
 * Store an armor piece with a name and a defense range.
 */
public class Armor {
    
    String name;

    int minac;

    int maxac;

    /**
     * Constructs a new Armor with the given name and defense range.
     *
     * @param name  the name of the armor piece
     * @param minac the minimum defense value
     * @param maxac the maximum defense value
     */
    public Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }
}
