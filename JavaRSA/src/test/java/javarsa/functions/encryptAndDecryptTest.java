/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.math.BigInteger;
import javax.crypto.IllegalBlockSizeException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author vrsaari
 */
public class encryptAndDecryptTest {

    Keygen keygen;
    String message;
    BigInteger encrypted;



    @Before
    public void setUp() {
        keygen = new Keygen(128);
        keygen.keygen();
        message = "Testiviesti";
    }

    @Test
    public void encryptionReturnsValidBigInt() throws IllegalBlockSizeException {
        encrypted = Encrypt.encrypt(keygen, message);
        assertEquals(BigInteger.class, encrypted.getClass());
    }

    @Test
    public void encryptedMessageCanBeDecrypted() throws IllegalBlockSizeException {
        encrypted = Encrypt.encrypt(keygen, message);
        assertEquals(message, Decrypt.decrypt(keygen, encrypted));
    }

    @Test(expected = IllegalBlockSizeException.class)
    public void exceptionThrownIfMessageTooLong() throws IllegalBlockSizeException {
        message = "aivan liian pitkä viesti 128bittiseen";
        Encrypt.encrypt(keygen, message);
    }
    
    @Test
    public void messageCanBeDecryptedAfterEncryption() throws IllegalBlockSizeException {
        message = "lyhyt";
        BigInteger encrypted = Encrypt.encrypt(keygen, message);
        String decrypted = Decrypt.decrypt(keygen, encrypted);
        assertEquals(message, decrypted);
    }

}
