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
    public static void writePrivate(String file, HashMap<String, String> key) {
        FileWriter fw = openFile(file);
        String pKey = key.get("key");
        try {
            fw.write(javarsa.JavaRSA.PRIVATE_FILE_BEGIN + "\n");
            fw.write(pKey);
            fw.write(javarsa.JavaRSA.PRIVATE_FILE_END + "\n");
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
    public static void writePublic(String file, String key) {
        FileWriter fw = openFile(file);
        try {
            fw.write(key);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write message to file
     *
     * @param file where to write
     * @param message what to write
     */
    public static void writeMessage(String file, String message) {
        FileWriter fw = openFile(file);
        try {
            fw.write(message);
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
    private static FileWriter openFile(String file) {
        FileWriter kirjoittaja = null;
        File f = new File(file);
        try {
            kirjoittaja = new FileWriter(f);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found exception: " + ex);
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kirjoittaja;
    }
}
