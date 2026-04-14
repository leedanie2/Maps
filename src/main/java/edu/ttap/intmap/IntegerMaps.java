package edu.ttap.intmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.ttap.maps.AssociationList.Pair;

public class IntegerMaps {

    /**
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        HashSet<K> hash = new HashSet();
        for (Pair p : lst) {
            hash.add(p.fst);
        }
        return hash;
    }

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

    /* Use the LetterCounter method to write reportCounts
     */
    public static void reportCountsRewrite(String path) throws FileNotFoundException {
        LetterCounter newCounter = new LetterCounter();
        String text = textify(path);

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if(newCounter.hasKey(c)) {
                newCounter.put(c, newCounter.get(c)+1);
            } else {
                newCounter.put(c,1);
            }
        }
        System.out.println(Arrays.toString(newCounter));
    }

    public static int countChars(String path) throws FileNotFoundException {
        Set<Character> ans = new TreeSet<>();
        String text = textify(path);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            ans.add(c);
        }
        for (char c : ans) {
            System.out.println(c + ":" + c + " ");
        }
        return ans.size();
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

    public static void main(String args[]) throws FileNotFoundException {
        String path = args[0];
        reportCounts(path);
    }
}


class LetterCounter {
    public Pair[] arr;

    public LetterCounter() {
        this.arr = new Pair[26];
    }

    public boolean hasKey(char ch) {
        int index = ch % 26;
        for (int i = index; ; i = (i + 1) % 26) {
        if (arr[i] == null) {
            return false;
        } else if (arr[i].fst.equals(ch)) {
            return true;
        } if(((i + 1) % 26) == index) break; //Stop searching after checking once.
    }
        return false;
    }

    public void put(char ch, int v) {
        int index = ch % 26;
        for(int i=index; ; i=(i+1)%26) {
            if (arr[i]==null) {
                arr[i] = new Pair(ch, v);
                break;
            } else if (arr[i].fst.equals(ch)) {
                arr[i].snd = v;
                break;
            }
        }
    }

    public int get(char ch) {
        int index = ch % 26;
        for (int i = index; ; i = (i + 1) % 26) {
        if(arr[i] == null) {
            throw new IllegalArgumentException;
        } else if (arr[i].fst.equals(ch)) {
            return arr[i].snd;
        } if(((i + 1) % 26) == index) break; //Stop searching after checking once.
        }    
    } 
}