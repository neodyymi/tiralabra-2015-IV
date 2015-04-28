/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.math.BigInteger;
import javarsa.Timer;

/**
 *
 * @author vrsaari
 */
public class Decrypt {

    /**
     * Decrypts
     *
     * @param keygen Key Generator to be used as the supply for the keys for the encryption
     * @param encrypted Encrypted message to be decrypted
     * @return decrypted material
     */
    public static String decrypt(Keygen keygen, BigInteger encrypted) {
        Timer.setStartTime();
        byte[] bytes;
//        bytes = encrypted.getBytes();
        BigInteger bigInt;
//        bigInt = new BigInteger(bytes);
        
        bigInt = encrypted.modPow(keygen.getPrivateKey(), keygen.getModulus());
        bytes = bigInt.toByteArray();
        String message = new String(bytes);
        Timer.stopTime();
        return message;
    }
}
