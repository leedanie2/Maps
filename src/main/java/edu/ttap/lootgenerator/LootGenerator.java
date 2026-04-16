package edu.ttap.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Generates randomized loot drops for monsters
 */
public class LootGenerator {

    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    /**
     * Reads a data file and returns a list of Monster
     *
     * @param path the path to the monster stats file
     * @return an link ArrayList of Monster parsed from the file
     * @throws FileNotFoundException if no file exists at the given path
     */
    public static ArrayList<Monster> monsterList(String path) throws FileNotFoundException {
        ArrayList<Monster> monsters = new ArrayList<>();
        Scanner scan = new Scanner(new File(path));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] arr = line.split("\t");
            String name = arr[0];
            String type = arr[1];
            int level = Integer.parseInt(arr[2]);
            String treasureClass = arr[3];
            monsters.add(new Monster(name, type, level, treasureClass));
        }
        scan.close();
        return monsters;
    }

    /**
     * Returns a randomly selected Monster from the given list.
     *
     * @param monsters the list of Monsters to choose from
     * @return a randomly chosen Monster
     */
    public static Monster randomMonster(ArrayList<Monster> monsters) {
        Random rand = new Random();
        int index = rand.nextInt(monsters.size());
        return monsters.get(index);
    }

    /**
     * Reads a treasure class file and returns a map of treasure class names to
     * TreasureClass objects.
     *
     * @param path the path to the treasure class file
     * @return a HashMap mapping treasure class to TreasureClass
     * @throws FileNotFoundException if no file exists at the given path
     */
    public static HashMap<String, TreasureClass> treasureHash(String path)
        throws FileNotFoundException {
        HashMap<String, TreasureClass> treasureHash = new HashMap<>();
        Scanner scan = new Scanner(new File(path));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] arr = line.split("\t");
            String name = arr[0];
            String item1 = arr[1];
            String item2 = arr[2];
            String item3 = arr[3];
            treasureHash.put(name, new TreasureClass(name, item1, item2, item3));
        }
        scan.close();
        return treasureHash;
    }

    /**
     * find the item that may be dropped by the monster
     *
     * @param treasureClass the treasure class name to resolve
     * @param treasureHash  the map of treasure class names to TreasureClass
     * @return the name of the resolved base item
     */
    public static String getTreasureClass(
        String treasureClass, HashMap<String, TreasureClass> treasureHash) {
        if (!treasureHash.containsKey(treasureClass)) {
            return treasureClass;
        }
        TreasureClass tc = treasureHash.get(treasureClass);
        String[] items = {tc.item1, tc.item2, tc.item3};
        Random rand = new Random();
        String picked = items[rand.nextInt(items.length)];
        return getTreasureClass(picked, treasureHash);
    }

    /**
     * Reads a file and returns a map of armor names to Armor objects.
     *
     * @param path the path to the armor file
     * @return a HashMap mapping armor names to Armor
     * @throws FileNotFoundException if no file exists at the given path
     */
    public static HashMap<String, Armor> armorHash(String path) throws FileNotFoundException {
        HashMap<String, Armor> armorHash = new HashMap<>();
        Scanner scan = new Scanner(new File(path));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] arr = line.split("\t");
            String name = arr[0];
            int minac = Integer.parseInt(arr[1]);
            int maxac = Integer.parseInt(arr[2]);
            armorHash.put(name, new Armor(name, minac, maxac));
        }
        scan.close();
        return armorHash;
    }

    /**
     * Pick a random defense value for the given armor piece and return it as
     * a string
     *
     * @param armor     the name of the armor piece to look up
     * @param armorHash the map of armor names to Armor
     * @return a string in the format "Defense: <value>"
     */
    public static String getStat(String armor, HashMap<String, Armor> armorHash) {
        Armor piece = armorHash.get(armor);
        Random rand = new Random();
        int defense = piece.minac + rand.nextInt(piece.maxac - piece.minac + 1);
        return "Defense: " + defense;
    }

    /**
     * Reads an affix data file and returns a list of Affix
     *
     * @param path the path to the affix file
     * @return an ArrayList of Affix parsed from the file
     * @throws FileNotFoundException if no file exists at the given path
     */
    public static ArrayList<Affix> affixList(String path) throws FileNotFoundException {
        ArrayList<Affix> affix = new ArrayList<>();
        Scanner scan = new Scanner(new File(path));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] arr = line.split("\t");
            String name = arr[0];
            String mod1code = arr[1];
            int mod1min = Integer.parseInt(arr[2]);
            int mod1max = Integer.parseInt(arr[3]);
            affix.add(new Affix(name, mod1code, mod1min, mod1max));
        }
        scan.close();
        return affix;
    }

    /**
     * Returns a randomly selected Affix from the given list.
     *
     * @param affixes the list of affixes to choose from
     * @return a randomly chosen Affix
     */
    public static Affix getAffix(ArrayList<Affix> affixes) {
        Random rand = new Random();
        return affixes.get(rand.nextInt(affixes.size()));
    }

    /**
     * Returns true with 50%
     *
     * @return true or false with equal probability
     */
    public static boolean has() {
        Random rand = new Random();
        return rand.nextInt(2) == 0;
    }

    /**
     * Pick a random value for the given affix within its max&min range.
     *
     * @param affix the affix whose stat range to roll
     * @return a random integer between min and max, inclusive
     */
    public static int getAffixStat(Affix affix) {
        Random rand = new Random();
        return affix.mod1min + rand.nextInt(affix.mod1max - affix.mod1min + 1);
    }

    /**
     * Start the loot generator simulation.
     *
     * @param args command-line arguments
     * @throws FileNotFoundException if any required data file is missing from the given directory
     */
    public static void main(String[] args) throws FileNotFoundException {
        String datatype = args[0]; // data/large or data/small
        ArrayList<Monster> monsterList = monsterList(datatype + "/monstats.txt");
        HashMap<String, TreasureClass> treasureHash
            = treasureHash(datatype + "/TreasureClassEx.txt");
        HashMap<String, Armor> armorHash = armorHash(datatype + "/armor.txt");
        ArrayList<Affix> prefixes = affixList(datatype + "/MagicPrefix.txt");
        ArrayList<Affix> suffixes = affixList(datatype + "/MagicSuffix.txt");

        String continueVar = "y";
        Scanner answer = new Scanner(System.in);
        while (continueVar.equals("y")) {
            Monster monster = randomMonster(monsterList);
            String item = getTreasureClass(monster.treasureClass, treasureHash);

            System.out.println("Fighting " + monster.name);
            System.out.println("You have slain " + monster.name);
            System.out.println(monster.name + " dropped:");

            Affix prefix = getAffix(prefixes);
            Affix suffix = getAffix(suffixes);
            boolean haspre = has();
            boolean hassuff = has();

            if (haspre) {
                System.out.print(prefix.name + " ");
            }
            System.out.print(item);
            if (hassuff) {
                System.out.print(" " + suffix.name);
            }
            System.out.print("\n");

            System.out.println(getStat(item, armorHash));
            if (haspre) {
                System.out.println(getAffixStat(prefix) + " " + prefix.mod1code);
            }
            if (hassuff) {
                System.out.println(getAffixStat(suffix) + " " + suffix.mod1code);
            }

            System.out.println("Fight again [y/n]?");
            continueVar = answer.nextLine();
        }
        answer.close();
    }
}

/**
 * Stores basic info about a monster
 */
class Monster {

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

/**
 * Store a treasure class, which maps the treasure class to three possible
 * item that can be randomly chosen
 */
class TreasureClass {

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

/**
 * Store an armor piece with a name and a defense range.
 */
class Armor {

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

/**
 * Store a magic affix that can be added to an item
 */
class Affix {

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