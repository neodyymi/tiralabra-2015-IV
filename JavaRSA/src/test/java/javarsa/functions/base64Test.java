/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.functions;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author vrsaari
 */
public class base64Test {

    @Before
    public void setUp() {

    }
    
    @Test
    public void canEncodeToBase64() {
        String message = "jotain";
        String encoded = Base64.encode(new BigInteger(message.getBytes()));
        assertTrue("encoded exists", message.length() < encoded.length());
    }
    
    @Test
    public void canDecodeBase64EncodedText() {
        String message = "jotain";
        String encoded = Base64.encode(new BigInteger(message.getBytes()));
        String decoded = new String(Base64.decode(encoded).toByteArray());
        assertEquals(message, decoded);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void messageToEncodeMustNotBeEmpty() {
        String message = "";
        String encoded = Base64.encode(new BigInteger(message.getBytes()));
    }
    
    @Test
    public void messageContainsOnlyA() {
        String message = "A";
        String encoded = Base64.encode(new BigInteger(message.getBytes()));
        String decoded = new String(Base64.decode(encoded).toByteArray());
        assertEquals(message, decoded);
    }
}
