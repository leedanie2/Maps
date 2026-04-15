package edu.ttap.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A substitution cipher is a simple encryption scheme that associates each
 * letter of the alphabet with a different letter.
 */
public class SubstitutionCipher {
    /**
     * Creates a substitution cipher by reading a mapping of characters from the given
     * file. Each mapping of the file should be of the form "a b", where 'a' is mapped to
     * 'b' in the cipher. We require 
     * @param filename the name of the file containing the mapping
     * @return the cipher as a mapping between characters
     */
    public static Map<Character, Character> createCipher(String filename) throws IOException {
        ArrayList<AssociationList.Pair> lst = new ArrayList<>();
        AssociationList<Character, Character> map = new AssociationList(lst);
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            char key = scan.next().charAt(0);
            char value = scan.next().charAt(0);
            map.put(key, value);
        }
        scan.close();
        return map;
    }

    /**
     * Determines whether the given mapping is a valid substitution cipher. A cipher is
     * valid if (a) it maps every letter of the alphabet (a–z) and (b) it is a bijection,
     * i.e., no two letters map to the same letter (so that we can roundtrip encode/decode
     * a message without loss of fidelity).
     * @param cipher
     * @return true iff the given mapping is a valid substitution cipher
     */
    public static boolean isValidCipher(Map<Character, Character> cipher) {
        for (char c = 'a'; c <= 'z'; c++) {
            if (!cipher.containsKey(c) && !cipher.containsValue(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given a valid substitution cipher, produces the inverse mapping, which
     * can be used to decode the encoded massage. For example, if the cipher
     * maps 'a' to 'b', then the inverse mapping should map 'b' to 'a'.
     * @param cipher the cipher to invert
     * @return the inverse mapping of the given cipher
     */
    public static Map<Character, Character> invertCipher(Map<Character, Character> cipher) {
        ArrayList<AssociationList.Pair> lst = new ArrayList<>();
        Map<Character, Character> inverse = new AssociationList(lst);
        for (char key : cipher.keySet()) {
            inverse.put(cipher.get(key), key);
        }
        return inverse;
    }

    /**
     * Translates the given string using the provided mapping.
     * @param s the string to translate
     * @param mapping the mapping to use
     * @return the translated string
     */
    public static String translate(String s, Map<Character, Character> mapping) {
        String mapped = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                mapped = mapped + " ";
            } else {
                mapped = mapped + mapping.get(c);
            }
        }
        return mapped;
    }

    /**
    * Reads a text file and returns its contents as a single String.
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
     * The main driver for the substitution cipher program.
     * @param args the driver's command-line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println(
                "Usage: java SubstitutionCipher <encode|decode> <cipherfile> <filename>");
            System.exit(1);
        }
        String mode = args[0];
        String cipher = args[1];
        String textfile = args[2];

        String output;
        Map<Character, Character> map = createCipher(cipher);
        String text = textify(textfile);

        if (mode.equals("encrypt")) {
            output = translate(text, map);
        } else if (mode.equals("decrypt")) {
            map = invertCipher(map);
            output = translate(text, map);
        } else {
            throw new IOException("First argument must be encrypt or decrypt!");
        }
        System.out.println(output);
    }
}