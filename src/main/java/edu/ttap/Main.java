package edu.ttap;

import java.io.IOException;

import edu.ttap.intmap.IntegerMaps;

/**
 * The driver for our lab on lists.
 */
public class Main {
    /**
     * The main entry point for the program.
     * @param args the command-line arguments
     */
    public void main(String[] args) throws IOException {
        /*String[] enc = new String[]{"encrypt", "example-cipher.txt", "wikipedia.txt"};
        String[] dec = new String[]{"decrypt", "example-cipher.txt", "wikipedia-encrypted-example.txt"};
        SubstitutionCipher.main(dec);
        */
       String[] arr = new String[]{"tolstoy.txt"};
       IntegerMaps.main(arr);
    }
}
