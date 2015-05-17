/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import java.util.Scanner;
import static javarsa.JavaRSA.key;
import javarsa.Timer;
import javarsa.functions.Base64;
import javarsa.functions.Decrypt;

/**
 * Handles user interaction with decrypting a message.
 *
 * @author vrsaari
 */
public class DecryptCmd implements Command {

    private final Scanner scanner;

    /**
     *
     * @param scanner
     */
    public DecryptCmd(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        String ret;
        System.out.print("Decrypt message: ");
        String encrypted = scanner.nextLine();
        ret = Decrypt.decrypt(key, Base64.decode(encrypted));
        System.out.println(ret + "\n" + "Time taken : " + Timer.getTimeInMilliseconds() + " ms");
    }

}
