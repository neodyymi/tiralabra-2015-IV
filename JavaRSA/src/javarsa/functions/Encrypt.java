/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.math.BigInteger;

/**
 *
 * @author vrsaari
 */
public class Encrypt {

    /**
     * Encrypts
     *
     * @param keygen Key Generator to be used as the supply for the keys for the encryption
     * @param message Message to be encrypted
     * @return Encrypted material
     */
    public static String encrypt(Keygen keygen, String message) {
        byte[] bytes = message.getBytes();
        BigInteger bigInt = new BigInteger(bytes);
        bigInt = bigInt.modPow(keygen.getPublicKey(), keygen.getModulus());
        return bigInt.toString(16);
    }
}
