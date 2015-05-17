/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import java.util.Scanner;
import javarsa.functions.Keygen;
import static javarsa.JavaRSA.key;

/**
 * Handles user interaction with importing private or public key from file.
 *
 * @author vrsaari
 */
public class ImportCmd implements Command {

    private final Scanner scanner;

    /**
     *
     * @param scanner
     */
    public ImportCmd(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("What do you want to import? (private, public)");
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "private":
                System.out.println("Filename to use:");
                cmd = scanner.nextLine();
                if (key == null) {
                    key = javarsa.io.ReadFile.fetchPrivate(cmd);
                } else {
                    key = javarsa.io.ReadFile.fetchPrivate(cmd, key);
                }
                break;
            case "public":
                System.out.println("Filename to use:");
                cmd = scanner.nextLine();
                if (key == null) {
                    key = javarsa.io.ReadFile.fetchPublic(cmd);
                } else {
                    key = javarsa.io.ReadFile.fetchPublic(cmd, key);
                }
                break;
            default:
                break;
        }
    }
}
