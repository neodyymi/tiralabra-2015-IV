/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javarsa.functions.Decrypt;
import javarsa.functions.Encrypt;
import javarsa.functions.Keygen;
import javax.crypto.IllegalBlockSizeException;

/**
 *
 * @author vrsaari
 */
public class Comparison implements Command {

    private final Scanner scanner;

    public Comparison(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        Keygen keygen;
        String message = "testiviesti";
        BigInteger encrypted = null;
        String decrypted;
        double[] keyTime = new double[6];
        double[] encryptTime = new double[6];
        double[] decryptTime = new double[6];

        System.out.println("This comparison will do timed runs for keys with bitsize 128-4096\n"
                + "How many iterations do you want to do? (One iteration takes a few seconds.)");
        final int iterations = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < 6; i++) {
            keyTime[i] = 0;
            encryptTime[i] = 0;
            decryptTime[i] = 0;
            for (int j = 0; j < iterations; j++) {
                keygen = new Keygen((int) Math.pow(2, i + 7));
                javarsa.Timer.setStartTime();
                keygen.keygen();
                keyTime[i] += javarsa.Timer.getTimeInMilliseconds();
                javarsa.Timer.setStartTime();
                try {
                    encrypted = Encrypt.encrypt(keygen, message);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(Comparison.class.getName()).log(Level.SEVERE, null, ex);
                }
                encryptTime[i] += javarsa.Timer.getTimeInMilliseconds();

                javarsa.Timer.setStartTime();
                decrypted = Decrypt.decrypt(keygen, encrypted);
                decryptTime[i] += javarsa.Timer.getTimeInMilliseconds();
                if (!decrypted.equals(message)) {
                    System.out.println("Erroneous decryption");
                }
                System.out.println(Math.pow(2, i + 7) + "-bit key " + (j + 1) + "/" + iterations + " done.");
            }
        }
        for (int i = 0; i < keyTime.length; i++) {
            System.out.println("Key with bitsize " + (Math.pow(2, i + 7)) + " took an average of\n"
                    + ((double) Math.round(100 * (keyTime[i] / iterations)) / 100) + "ms to generate key,\n"
                    + keyTime[i] + "ms to generate key,\n"
                    + ((double) Math.round(100 * (encryptTime[i] / iterations)) / 100) + "ms to encrypt message,\n"
                    + ((double) Math.round(100 * (decryptTime[i] / iterations)) / 100) + "ms to decrypt message.\n");

        }

    }

}
