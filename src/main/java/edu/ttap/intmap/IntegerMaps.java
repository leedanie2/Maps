package edu.ttap.intmap;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
 
/**
 * Counting and reporting character frequencies in text files
 */
public class IntegerMaps {
 
    /**
     * Counts occurences of each letter in the file at the given path
     * and prints the counts in alphabetical order
     *
     * @param path the path to the text file
     * @throws FileNotFoundException if the file does not exist
     */
    public static void reportCounts(String path) throws FileNotFoundException {
        int[] arr = new int[26];
        String text = textify(path);
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (c >= 'a' && c <= 'z') {
                arr[c - 'a']++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
 
    /**
     * Use the LetterCounter method to write reportCounts.
     * Count occurences of each character in the file and prints the
     * number of occurence along the character
     *
     * @param path the path to the text file
     * @throws FileNotFoundException if the file does not exist
     */
    public static void reportCountsRewrite(String path) throws FileNotFoundException {
        LetterCounter newCounter = new LetterCounter();
        String text = textify(path);
 
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (newCounter.hasKey(c)) {
                newCounter.put(c, newCounter.get(c) + 1);
            } else {
                newCounter.put(c, 1);
            }
        }
 
        for (int i = 0; i < 256; i++) {
            if (newCounter.lst[i] != null) {
                for (Pair<Character, Integer> p : newCounter.lst[i]) {
                    System.out.println(p.fst + " : " + p.snd + " ");
                }
            }
        }
    }
 
    /**
     * Scan a text file and return its content as a single String
     *
     * @param textfile the path to the text file
     * @return the full contents of the file as a String
     * @throws FileNotFoundException if the file does not exist
     */
    public static String textify(String textfile) throws FileNotFoundException {
        String text = "";
        Scanner scan = new Scanner(new File(textfile));
        while (scan.hasNextLine()) {
            text = text + scan.nextLine();
        }
        scan.close();
        return text;
    }
 
    /**
     * Counts and prints all unique characters in the file
     * after that return the total number of unique characters
     *
     * @param path the path to the text file
     * @return the number of distinct characters found in the file
     * @throws FileNotFoundException if the file does not exist
     */
    public static int countChars(String path) throws FileNotFoundException {
        Set<Character> ans = new TreeSet<>();
        String text = textify(path);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            ans.add(c);
        }
        for (char c : ans) {
            System.out.print(c + ":" + (int) c + " ");
        }
        return ans.size();
    }
 
    /**
     * Accepts a file path as the first argument
     * prints the frequency of every character
     *
     * @param args command-line arguments
     * @throws FileNotFoundException if the file does not exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        String path = args[0];
        countChars(path);
    }
}
 
/**
 * Stores two related values
 * 
 * @param <K> the type of the first element
 * @param <V> the type of the second element
 */
class Pair<K, V> {

    public K fst;

    public V snd;
 
    public Pair(K fst, V snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
 
/**
 * A hash map from characters to integers
 */
class LetterCounter {
 
    ArrayList<Pair<Character, Integer>>[] lst;
 
    /**
     * Constructs a LetterCounter with 256 empty buckets.
     */
    public LetterCounter() {
        this.lst = new ArrayList[256];
    }
 
    /**
     * Returns true if the given character has been inserted into this map.
     *
     * @param ch the character to look up
     * @return true if ch is present, false otherwise
     */
    public boolean hasKey(char ch) {
        int i = ch % 256;
        if (lst[i] == null) {
            return false;
        }
        for (Pair<Character, Integer> p : lst[i]) {
            if (p.fst.equals(ch)) {
                return true;
            }
        }
        return false;
    }
 
    /**
     * Inserts or updates the count for the given character.
     *
     * @param ch the character key
     * @param v  the integer value to associate with ch
     */
    public void put(char ch, int v) {
        int i = ch % 256;
        if (lst[i] == null) {
            lst[i] = new ArrayList<>();
        }
        for (Pair<Character, Integer> p : lst[i]) {
            if (p.fst.equals(ch)) {
                p.snd = v; // overwrite pre-existing value
                return;
            }
        }
        lst[i].add(new Pair<>(ch, v));
    }
 
    /**
     * Returns the count associated with the given character
     *
     * @param ch the character to look up
     * @return the integer value associated with ch
     * @throws IllegalArgumentException if ch is not present in the map
     */
    public int get(char ch) {
        int i = ch % 256;
        if (lst[i] != null) {
            for (Pair<Character, Integer> p : lst[i]) {
                if (p.fst.equals(ch)) {
                    return p.snd;
                }
            }
        }
        throw new IllegalArgumentException();
    }
}