/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javarsa.functions.Base64;
import javarsa.functions.Keygen;

/**
 * File output functions.
 *
 * @author vrsaari
 */
public class WriteFile {

    /**
     * Writes private key information to file
     *
     * @param file where to write
     * @param key what to write
     */
    public static void writePrivate(File file, Keygen key) {
        FileWriter fw = openFile(file);
        if (fileError(fw)) {
            return;
        }
        String pKey = Base64.encode(key.getPrivateKey());
        try {
            fw.write(javarsa.JavaRSA.PRIVATE_FILE_BEGIN + "\n");
            for (int i = 0; i < pKey.length(); i += 64) {
                int loppu = i + 64;
                if (loppu > pKey.length()) {
                    loppu = pKey.length();
                }
                fw.write(pKey.substring(i, loppu));
                fw.write("\n");
            }
            fw.write("\n" + javarsa.JavaRSA.PRIVATE_FILE_END);
            fw.close();
            System.out.println(pKey + "\nWritten to " + file);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes public key information to file
     *
     * @param file where to write
     * @param key what to write
     */
    public static void writePublic(File file, Keygen key) {
        FileWriter fw = openFile(file);
        if (fileError(fw)) {
            return;
        }
        try {
            fw.write(key.getPublicKey() + " " + Base64.encode(key.getModulus()));
            fw.close();
            System.out.println(key.getPublicKey() + " " + Base64.encode(key.getModulus()) + "\nWritten to " + file);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean fileError(FileWriter fw) {
        if (fw == null) {
            System.out.println("File error");
            return true;
        }
        return false;
    }

    /**
     * Write message to file
     *
     * @param file where to write
     * @param message what to write
     */
    public static void writeMessage(File file, String message) {
        FileWriter fw = openFile(file);
        if (fileError(fw)) {
            return;
        }
        try {
            fw.write(message);
            fw.close();
            System.out.println(message + "\nWritten to" + file);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * one file for usage
     *
     * @param file file to use
     * @return
     */
    private static FileWriter openFile(File file) {
        FileWriter kirjoittaja = null;
        try {
            kirjoittaja = new FileWriter(file);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kirjoittaja;
    }
}
