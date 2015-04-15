/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa;

import java.math.BigInteger;
import java.util.Scanner;
import javarsa.functions.Decrypt;
import javarsa.functions.Encrypt;
import javarsa.functions.Help;
import javarsa.functions.Keygen;
import javarsa.functions.Base64;

/**
 *
 * @author vrsaari
 */
public class JavaRSA {

    /**
     * Main accepts parameters that make the program perform different
     * functions.
     * To-do: Ability to use external keys.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Keygen key = null;
        BigInteger bigInt;
        String ret;
        Help.help();
        String cmd = scanner.nextLine();
        while (!cmd.equals("quit")) {
            switch (cmd) {
                case "keygen":
                    System.out.println("Select key size(128,256,512,1024...)");
                    int size = Integer.parseInt(scanner.nextLine());
                    key = new Keygen(size);
                    key.keygen();
                    System.out.println(key);
                    break;
                case "encrypt":
                    System.out.print("Encrypt message: ");
                    String message = scanner.nextLine();
                    bigInt = Encrypt.encrypt(key, message);
                    System.out.println(Base64.encode(bigInt));
                    break;
                case "decrypt":
                    System.out.print("Decrypt message: ");
                    String encrypted = scanner.nextLine();
                    ret = Decrypt.decrypt(key, Base64.decode(encrypted));
                    System.out.println(ret);
                    break;
                case "help":
                    if (args.length > 1) {
                        Help.help();
                    } else {
                        Help.help();
                    }   break;
                default:
                    Help.help();
                    break;
            }
            Help.help();
            cmd = scanner.nextLine();
        }
    }
}
