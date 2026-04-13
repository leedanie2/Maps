package edu.ttap.intmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class IntegerMaps {
    
    public static void reportCounts(String path) throws FileNotFoundException {
        int[] arr = new int[26]; // 26 letters in the alphabet, index corresponds to alphabetical order
        String text = textify(path);
        for(int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if(c >= 'a' && c <= 'z') {
                arr[c - 'a'] ++;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static int countChars(String path) {
        
    }

    public static String textify(String textfile) throws FileNotFoundException {
        String text = "";
        Scanner scan = new Scanner(new File(textfile));
        while(scan.hasNextLine()) {
            text = text + scan.nextLine();
        }
        scan.close();
        return text;
    }

    public static void main (String args[]) throws FileNotFoundException {
        String path = args[0];
        reportCounts(path);
    }
}
