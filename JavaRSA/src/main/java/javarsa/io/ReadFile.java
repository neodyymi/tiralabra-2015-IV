/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javarsa.functions.Base64;
import javarsa.functions.Keygen;

/**
 * File importing functions.
 *
 * @author vrsaari
 */
public class ReadFile {

    /**
     * Fetches private key from file to new Keygen
     *
     * @param file
     * @return Keygen with key fetched
     */
    public static Keygen fetchPrivate(String file) {
        Keygen keygen = new Keygen(128);
        return fetchPrivate(file, keygen);
    }

    /**
     * Fetches private key from file to Keygen given as parameter
     *
     * @param file file to read from
     * @param keygen Keygen to insert key into
     * @return map with key information.
     */
    public static Keygen fetchPrivate(String file, Keygen keygen) {
        Scanner lukija = openFile(file);
        if (lukija != null) {
            if (lukija.nextLine().equals(javarsa.JavaRSA.PRIVATE_FILE_BEGIN)) {
                StringBuilder str = new StringBuilder();
                String rivi = lukija.nextLine();
                while (!rivi.equals(javarsa.JavaRSA.PRIVATE_FILE_END)) {
                    str.append(rivi);
                    rivi = lukija.nextLine();
                }
                keygen.setPrivateKey(Base64.decode(str.toString()));
            }

            lukija.close();
        }

        return keygen;
    }

    /**
     * Fetches public key from file to new Keygen
     *
     * @param file
     * @return Keygen with key inside
     */
    public static Keygen fetchPublic(String file) {
        Keygen keygen = new Keygen(128);
        return fetchPublic(file, keygen);
    }

    /**
     * Fetches public key from file to given Keygen
     *
     * @param file file to use
     * @param keygen Keygen to insert keys to
     * @return Keygen containing public key
     */
    public static Keygen fetchPublic(String file, Keygen keygen) {
        Scanner lukija = openFile(file);
        if (lukija != null) {
            lukija.useDelimiter(" ");
            keygen.setPublicKey(BigInteger.valueOf(Integer.parseInt(lukija.next())));
            lukija.useDelimiter("\\Z");
            String modulus = lukija.next();
            modulus = modulus.replace(" ", "");
            keygen.setModulus(Base64.decode(modulus));
            lukija.close();
        }

        return keygen;
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
