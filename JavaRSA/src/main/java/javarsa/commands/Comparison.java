/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import java.math.BigInteger;
import java.util.Random;
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
        System.out.println("What do you want to compare?\n"
                + "\"1\" different size keys, or\n"
                + "\"2\" different message sizes.\n"
                + "(Choose 1 or 2)");
        String cmd = scanner.nextLine();
        if (cmd.contains("1")) {
            keySizeComparison();
        } else if (cmd.contains("2")) {
            messageSizeComparison();
        }

    }

    private void keySizeComparison() throws NumberFormatException {
        Keygen keygen;
        String message = "testiviesti";
        BigInteger encrypted = null;
        String decrypted;
        double[] keyTime = new double[6];
        double[] encryptTime = new double[6];
        double[] decryptTime = new double[6];

        System.out.println("This comparison will do timed runs for keys with bitsize 128-4096\n"
                + "How many iterations do you want to do? (One iteration takes a few seconds.)");
        String cmd = scanner.nextLine();
        if (cmd.equals("")) {
            return;
        }
        final int iterations = Integer.parseInt(cmd);

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

    private void messageSizeComparison() {
        System.out.println("Select key size(128,256,512,1024...)");
        String cmd = scanner.nextLine();
        if (cmd.equals("")) {
            return;
        }
        int keySize = Integer.parseInt(cmd);
        Keygen keygen = new Keygen(keySize);
        keygen.keygen();
        double[] encryptTime = new double[6];
        double[] decryptTime = new double[6];
        String[] messages = new String[6];
        int[] messageBitLength = new int[6];
        BigInteger encrypted = null;
        String decrypted;
        System.out.println("This comparison will do timed runs encrypting 5 different length random messages.\n"
                + "How many iterations do you want to do? (Depending on bitsize, one iteration will take up to several seconds.");
        cmd = scanner.nextLine();
        if (cmd.equals("")) {
            return;
        }
        int iterations = Integer.parseInt(cmd);
        for (int i = 0; i < 5; i++) {
            BigInteger message = new BigInteger((keySize - 1) / (6 - i), new Random());
            messages[i] = message.toString(16);
            messageBitLength[i] = message.bitLength();
            for (int j = 0; j < iterations; j++) {
                javarsa.Timer.setStartTime();
                try {
                    encrypted = Encrypt.encrypt(keygen, messages[i]);
                } catch (IllegalBlockSizeException ex) {
                    System.out.println("Message size was too large");
                }
                encryptTime[i] += javarsa.Timer.getTimeInMilliseconds();

                javarsa.Timer.setStartTime();
                decrypted = Decrypt.decrypt(keygen, encrypted);
                if (!decrypted.equals(messages[i])) {
                    System.out.println("Erroneous decryption");
                }
                decryptTime[i] += javarsa.Timer.getTimeInMilliseconds();
                System.out.println("Iteration " + ((j + 1) + (i * iterations)) + "/" + (5 * iterations) + " done.");
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Message with bitsize " + messageBitLength[i] + " took an average of\n"
                    + ((double) Math.round(100 * (encryptTime[i] / iterations)) / 100) + "ms to encrypt message,\n"
                    + ((double) Math.round(100 * (decryptTime[i] / iterations)) / 100) + "ms to decrypt message.\n");
        }

    }

}
