/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RSA key generator
 * @author vrsaari
 * 
 * to-do: Actually generate everything without using BigInteger methods like BigInteger.modInverse.
 */
public class Keygen {

    private BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger modulus;

    // random large prime numbers
    private BigInteger p, q;

    // Phi n
    private BigInteger m;

    // bitsize of key
    private final int size;

    private static SecureRandom r = new SecureRandom();

    /**
     *
     * @param size Length of key
     */
    public Keygen(int size) {
        this.size = size;
    }

    /**
     * Starts function to create a new key.
     * Generates keys required for RSA encryption and decryption.
     *
     */
    public void keygen() {
        do {
            p = BigInteger.probablePrime(size / 2, r);
            q = BigInteger.probablePrime(size / 2, r);
            modulus = p.multiply(q);
            m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            publicKey = new BigInteger("65537"); //2^16+1
        } while (m.gcd(publicKey).intValue() > 1);

        privateKey = publicKey.modInverse(m);
    }

    /**
     * returns modulus
     *
     * @return
     */
    public BigInteger getModulus() {
        return modulus;
    }

    /**
     * return private key
     *
     * @return
     */
    public BigInteger getPrivateKey() {
        return privateKey;
    }

    /**
     * returns public key
     *
     * @return
     */
    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }
    
    

    @Override
    public String toString() {
        return ""
                + "Modulus : " + this.modulus + "\n"
                + "in Base64 : " + Base64.encode(this.modulus) + "\n"
                + "in hexadecimal : " + this.modulus.toString(16) + "\n"
                + "Private : " + this.privateKey + "\n"
                + "in Base64 : " + Base64.encode(this.privateKey) + "\n"
                + "in hexadecimal : " + this.privateKey.toString(16) + "\n"
                + "Public : " + this.publicKey;
    }

}
