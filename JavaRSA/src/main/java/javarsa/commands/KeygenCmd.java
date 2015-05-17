/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import java.util.Scanner;
import static javarsa.JavaRSA.key;
import javarsa.functions.Keygen;

/**
 * Handles user interaction with generating private-public keypairs.
 *
 * @author vrsaari
 */
public class KeygenCmd implements Command {

    private final Scanner scanner;

    public KeygenCmd(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() throws NumberFormatException {
        System.out.println("Select key size(128,256,512,1024...)");
        int size = Integer.parseInt(scanner.nextLine());
        key = new Keygen(size);
        key.keygen();
        System.out.println(key);
    }
}
