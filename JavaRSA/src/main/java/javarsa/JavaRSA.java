/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javarsa.functions.Decrypt;
import javarsa.functions.Encrypt;
import javarsa.functions.Help;
import javarsa.functions.Keygen;
import javarsa.functions.Base64;
import javax.crypto.IllegalBlockSizeException;

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
     * functions. To-do: Ability to use external keys.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        key = null;
        BigInteger bigInt;
        String ret;
        Help.help();
        String cmd = scanner.nextLine();
        while (!cmd.equals("quit")) {
            switch (cmd) {
                case "keygen":
                    key = keygenCmd(scanner, key);
                    break;
                case "encrypt":
                    encryptCmd(scanner, key);
                    break;
                case "decrypt":
                    decryptCmd(scanner, key);
                    break;
                case "import":
                    key = importCmd(scanner, key);
                    break;
                case "export":
                    exportCmd(scanner, key);
                    break;
                case "show":
                    System.out.println(key.toString());
                    break;
                case "help":
                    Help.help();
                    break;
                default:
                    Help.help();
                    break;
            }
//            Help.help();
            cmd = scanner.nextLine();
        }
    }

    private static void decryptCmd(Scanner scanner, Keygen key) {
        String ret;
        System.out.print("Decrypt message: ");
        String encrypted = scanner.nextLine();
        ret = Decrypt.decrypt(key, Base64.decode(encrypted));
        System.out.println(ret + "\n" + "Time taken : " + Timer.getTimeInMilliseconds() + " ms");
    }

    private static void encryptCmd(Scanner scanner, Keygen key) {
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

    private static Keygen keygenCmd(Scanner scanner, Keygen key) throws NumberFormatException {
        System.out.println("Select key size(128,256,512,1024...)");
        int size = Integer.parseInt(scanner.nextLine());
        key = new Keygen(size);
        key.keygen();
        System.out.println(key);
        return key;
    }

    private static Keygen importCmd(Scanner scanner, Keygen key) {
//        System.out.println("What is the bitsize of the key to be imported?");
//        int size = Integer.parseInt(scanner.nextLine());
        System.out.println("What do you want to import? (private, public)");
        String cmd = scanner.nextLine();
//        Keygen key = new Keygen(size);
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
        return key;
    }

    private static void exportCmd(Scanner scanner, Keygen key) {
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
