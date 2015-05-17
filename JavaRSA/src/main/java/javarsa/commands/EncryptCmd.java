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
import javarsa.JavaRSA;
import static javarsa.JavaRSA.key;
import javarsa.Timer;
import javarsa.functions.Base64;
import javarsa.functions.Encrypt;
import javax.crypto.IllegalBlockSizeException;

/**
 * Handles user interaction with encrypting a message.
 *
 * @author vrsaari
 */
public class EncryptCmd implements Command {

    private final Scanner scanner;

    /**
     * @param scanner
     */
    public EncryptCmd(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        BigInteger bigInt;
        System.out.print("Encrypt message: ");
        String message = scanner.nextLine();
        try {
            bigInt = Encrypt.encrypt(key, message);
        } catch (IllegalBlockSizeException ex) {
            bigInt = null;
            Logger.getLogger(JavaRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Base64.encode(bigInt) + "\n" + "Time taken : " + Timer.getTimeInMilliseconds() + " ms");
    }
}
