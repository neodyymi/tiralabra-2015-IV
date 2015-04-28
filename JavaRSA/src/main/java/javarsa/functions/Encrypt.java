/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.math.BigInteger;
import javarsa.Timer;
import javax.crypto.IllegalBlockSizeException;

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
     * @return Encrypted material as BigInteger
     */
    public static BigInteger encrypt(Keygen keygen, String message) throws IllegalBlockSizeException {
        Timer.setStartTime();
        byte[] bytes = message.getBytes();
        BigInteger bigInt = new BigInteger(bytes);
        System.out.println(bigInt.bitLength() +" >< "+ keygen.getModulus().bitLength());
        if(bigInt.bitLength() > keygen.getModulus().bitLength()) {
            System.out.println("Error: Message too long! (Message bitsize larger than modulus.)");
            throw new IllegalBlockSizeException("Message too long! (Message bitsize larger than modulus.)");
        }
        bigInt = bigInt.modPow(keygen.getPublicKey(), keygen.getModulus());
        Timer.stopTime();
        return bigInt;
    }
}
