package edu.ttap.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    
    public static ArrayList<Monster> MonsterList(String path) throws FileNotFoundException {
        ArrayList<Monster> monsters = new ArrayList<>();
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] arr = line.split("\t");
            String name = arr[0];
            String type = arr[1];
            int level = Integer.parseInt(arr[2]);
            String TreasureClass = arr[3];
            monsters.add(new Monster(name, type, level, TreasureClass));
        }
        scan.close();
        return monsters;
    }

    public static Monster randomMonster(ArrayList<Monster> monsters) {
        Random rand = new Random();
        int index = rand.nextInt(monsters.size());
        return monsters.get(index);
    }

    public static HashMap<String, TreasureClass> TreasureHash(String path) throws FileNotFoundException {
        HashMap<String, TreasureClass> treasureHash = new HashMap<>();
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNextLine()) {
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

    public static String getTreasureClass(String treasureClass, HashMap<String, TreasureClass> treasureHash) {
        if (!treasureHash.containsKey(treasureClass)) {
            return treasureClass;
        }
        TreasureClass tc = treasureHash.get(treasureClass);
        String[] items = {tc.item1, tc.item2, tc.item3};
        Random rand = new Random();            
        String picked = items[rand.nextInt(items.length)];
        return getTreasureClass(picked, treasureHash);
    }

    public static HashMap<String, Armor> ArmorHash(String path) throws FileNotFoundException {
        HashMap<String, Armor> armorHash = new HashMap<>();
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNextLine()) {
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

    public static String getStat(String armor, HashMap<String, Armor> armorHash) {
        Armor piece = armorHash.get(armor);
        Random rand = new Random();
        int defense = piece.minac + rand.nextInt(piece.maxac - piece.minac + 1);
        return "Defense: " + defense;
    }

    public static ArrayList<Affix> AffixList(String path) throws FileNotFoundException {
        ArrayList<Affix> affix= new ArrayList<>();
        Scanner scan = new Scanner(new File(path));
        while(scan.hasNextLine()) {
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

    public static Affix getAffix(ArrayList<Affix> affixes) {
        Random rand = new Random();
        return affixes.get(rand.nextInt(affixes.size()));
    }

    public static boolean has() {
        Random rand = new Random();
        if (rand.nextInt(2) == 0) { // 0 or 1
            return true;
        }
        return false;
    }

    public static int getAffixStat(Affix affix) {
        Random rand = new Random();
        return affix.mod1min + rand.nextInt(affix.mod1max - affix.mod1min + 1);
    }

    /*
    public static String generateAffix(Affix affix) {
        
    }
    */

    public static void main(String[] args) throws FileNotFoundException {
        String datatype = args[0]; // data/large or data/small
        ArrayList<Monster> monsterList = MonsterList(datatype + "/monstats.txt");
        HashMap<String, TreasureClass> treasureHash = TreasureHash(datatype + "/TreasureClassEx.txt");
        HashMap<String, Armor> armorHash = ArmorHash(datatype + "/armor.txt");
        ArrayList<Affix> prefixes = AffixList(datatype + "/MagicPrefix.txt");
        ArrayList<Affix> suffixes = AffixList(datatype + "/MagicSuffix.txt");

        String continue_var = "y";
        while (continue_var.equals("y")) {
            Monster monster = randomMonster(monsterList);
            String item = getTreasureClass(monster.TreasureClass, treasureHash);

            System.out.println("Fighting" + monster.Class);
            System.out.println("You have slain " + monster.Class);
            System.out.println(monster.Class + " dropped:");
            
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
            Scanner answer = new Scanner(System.in);
            continue_var = answer.toString();

        }
    }
}

class Monster {
    String Class;
    String type;  
    int level;
    String TreasureClass;

    public Monster(String Class, String type, int level, String TreasureClass) {
        this.Class = Class;
        this.type = type;
        this.level = level;
        this.TreasureClass = TreasureClass;
    }

}

class TreasureClass {
    String treasureClass;
    String item1;
    String item2;
    String item3;
    
    public TreasureClass(String TreasureClass, String item1, String item2, String item3) {
        this.treasureClass = treasureClass;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
}

class Armor {
    String name;
    int minac;
    int maxac;

    public Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }
}

class Affix {
    String name;
    String mod1code;
    int mod1min;
    int mod1max;

    public Affix(String name, String mod1code, int mod1min, int mod1max) {
        this.name = name;
        this.mod1code = mod1code;
        this.mod1min = mod1min;
        this.mod1max = mod1max;
    }
}