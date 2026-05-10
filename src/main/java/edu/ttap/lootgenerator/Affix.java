package edu.ttap.lootgenerator;

/**
 * Store a magic affix that can be added to an item
 */
public class Affix {
    
    String name;

    String mod1code;

    int mod1min;

    int mod1max;

    /**
     * Constructs a new Afffix with the given name, modifier, and value range.
     *
     * @param name     the display name of the affix
     * @param mod1code the modifier code for the stat this affix affects
     * @param mod1min  the minimum rollable value for this modifier
     * @param mod1max  the maximum rollable value for this modifier
     */
    public Affix(String name, String mod1code, int mod1min, int mod1max) {
        this.name = name;
        this.mod1code = mod1code;
        this.mod1min = mod1min;
        this.mod1max = mod1max;
    }
}
