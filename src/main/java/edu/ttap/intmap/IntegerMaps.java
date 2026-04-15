package edu.ttap.intmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class IntegerMaps {

    public static void reportCounts(String path) throws FileNotFoundException {
        int[] arr = new int[26]; // 26 letters in the alphabet, index corresponds to alphabetical order
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
     *  Use the LetterCounter method to write reportCounts
     */
    
    public static void reportCountsRewrite(String path) throws FileNotFoundException {
        LetterCounter newCounter = new LetterCounter();
        String text = textify(path);

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if(newCounter.hasKey(c)) {
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
    
    public static String textify(String textfile) throws FileNotFoundException {
        String text = "";
        Scanner scan = new Scanner(new File(textfile));
        while (scan.hasNextLine()) {
            text = text + scan.nextLine();
        }
        scan.close();
        return text;
    }

    public static int countChars(String path) throws FileNotFoundException {
        Set<Character> ans = new TreeSet<>();
        String text = textify(path);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            ans.add(c);
        }
        for (char c : ans) {
            System.out.print(c + ":" + (int)c + " ");
        }
        return ans.size();
    }

    

    public static void main(String args[]) throws FileNotFoundException {
        String path = args[0];
        countChars(path);
    }
}

class Pair<K, V> {        
    public K fst;
    public V snd;
    public Pair(K fst, V snd) { 
        this.fst = fst;
        this.snd = snd;
    }
}

class LetterCounter {

    ArrayList<Pair<Character, Integer>>[] lst; // each pair contains character, int

    public LetterCounter() {
        this.lst = new ArrayList[256];
    }

    public boolean hasKey(char ch) {
        int i = ch % 256;
        if (lst[i] == null) {
            return false;
        } 
        for (Pair p : lst[i]) {
            if (p.fst.equals(ch)) {
                return true;
            }
        }
        return false;
    }

    public void put(char ch, int v) {
        int i = ch % 26;
        if (lst[i] == null) { // nothing in here yet
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

    public int get(char ch) {
        int i = ch % 26;
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