/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa;

import javarsa.functions.Decrypt;
import javarsa.functions.Encrypt;
import javarsa.functions.Help;
import javarsa.functions.Keygen;

/**
 *
 * @author vrsaari
 */
public class JavaRSA {

    /**
     * Main accepts parameters that make the program perform different
     * functions.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args[0].equals("keygen")) {
            Keygen.keygen();
        } else if (args[0].equals("encrypt")) {
            Encrypt.encrypt();
        } else if (args[0].equals("decrypt")) {
            Decrypt.decrypt();
        } else if (args[0].equals("help")) {
            if (args.length > 1) {
                Help.help();
            } else {
                Help.help();
            }
        } else {
            Help.help();
        }
    }

}
