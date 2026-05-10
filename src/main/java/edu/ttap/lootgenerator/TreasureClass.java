package edu.ttap.lootgenerator;

/**
 * Store a treasure class, which maps the treasure class to three possible
 * item that can be randomly chosen
 */
public class TreasureClass {

    String treasureClass;

    String item1;

    String item2;

    String item3;

    /**
     * Constructs a new TreasureClass with the given name and three items
     *
     * @param treasureClass the name of this treasure class
     * @param item1         the first possible item
     * @param item2         the second possible item
     * @param item3         the third possible item
     */
    public TreasureClass(String treasureClass, String item1, String item2, String item3) {
        this.treasureClass = treasureClass;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
}
