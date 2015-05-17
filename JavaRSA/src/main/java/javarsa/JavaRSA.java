/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javarsa.functions.Keygen;
import javarsa.commands.*;

/**
 *
 * @author vrsaari
 */
public class JavaRSA {

    public static final String PRIVATE_FILE_BEGIN = "-----BEGIN RSA PRIVATE KEY-----";
    public static final String PRIVATE_FILE_END = "-----END RSA PRIVATE KEY-----";
    public static final String PUBLIC_FILE_BEGIN = "-----BEGIN RSA PUBLIC KEY-----";
    public static final String PUBLIC_FILE_END = "-----END RSA PUBLIC KEY-----";
    public static Keygen key;

    /**
     * Main accepts parameters that make the program perform different
     * functions.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = init(scanner);
        key = null;
        commands.get("help").run();
        String cmd = scanner.nextLine();
        while (!cmd.equals("quit")) {
            if (commands.containsKey(cmd)) {
                commands.get(cmd).run();
            }
            commands.get("help").run();
            cmd = scanner.nextLine();
        }
    }

    public static Map init(Scanner scanner) {
        Map<String, Command> commands;
        commands = new HashMap();
        commands.put("encrypt", new EncryptCmd(scanner));
        commands.put("decrypt", new DecryptCmd(scanner));
        commands.put("help", new HelpCmd());
        commands.put("keygen", new KeygenCmd(scanner));
        commands.put("import", new ImportCmd(scanner));
        commands.put("export", new ExportCmd(scanner));
        commands.put("comparison", new Comparison(scanner));
        return commands;
    }
}
