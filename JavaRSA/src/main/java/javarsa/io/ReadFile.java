/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File importing functions.
 *
 * @author vrsaari
 */
public class ReadFile {

    /**
     * Fetches private key from file
     *
     * @param file
     * @return map with key information.
     */
    public static HashMap<String, String> fetchPrivate(String file) {
        HashMap<String, String> ret = new HashMap<>();
        Scanner lukija = openFile(file);
        if (lukija != null) {
            if(lukija.nextLine().equals(javarsa.JavaRSA.PRIVATE_FILE_BEGIN)) {
                StringBuilder str = new StringBuilder();
                String rivi = lukija.nextLine();
                while(!rivi.equals(javarsa.JavaRSA.PRIVATE_FILE_END)) {
                    str.append(rivi);
                    rivi = lukija.nextLine();
                }
                ret.put("key", str.toString());
            }
            
            lukija.close();
        }

        return ret;
    }

    /**
     * Fetches public key from file
     *
     * @param file
     * @return String containing public key
     */
    public static String fetchPublic(String file) {
        String ret = "";
        Scanner lukija = openFile(file);
        if (lukija != null) {
            lukija.useDelimiter("\\Z");
            ret = lukija.next();
            lukija.close();
        }
        
        return ret;
    }

    /**
     * Fetches message to be encrypted from file
     *
     * @param file
     * @return String containing message to be encrypted
     */
    public static String fetchMessage(String file) {
        String ret = "";
        Scanner lukija = openFile(file);
        if (lukija != null) {
            lukija.useDelimiter("\\Z");
            ret = lukija.next();
            lukija.close();
        }

        return ret;
    }

    private static Scanner openFile(String file) {
        Scanner lukija = null;
        File f = new File(file);
        try {
            lukija = new Scanner(f);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found exception: " + ex);
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lukija;
    }

}
