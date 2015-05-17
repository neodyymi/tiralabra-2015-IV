/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import java.io.File;
import java.util.Scanner;
import static javarsa.JavaRSA.key;

/**
 * Handles user interaction with exporting private or public key to file.
 *
 * @author vrsaari
 */
public class ExportCmd implements Command {

    private final Scanner scanner;

    /**
     *
     * @param scanner
     */
    public ExportCmd(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println("What do you want to export? (private, public)");
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "private":
                System.out.println("Filename to use:");
                cmd = scanner.nextLine();
                javarsa.io.WriteFile.writePrivate(new File(cmd), key);
                break;
            case "public":
                System.out.println("Filename to use:");
                cmd = scanner.nextLine();
                javarsa.io.WriteFile.writePublic(new File(cmd), key);
                break;
            default:
                break;
        }
    }

}
