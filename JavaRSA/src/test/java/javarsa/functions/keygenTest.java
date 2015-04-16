/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import javarsa.functions.Keygen;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.matchers.JUnitMatchers;

/**
 *
 * @author vrsaari
 */
public class keygenTest {

    Keygen keygen;

    @Before
    public void setUp() {
        keygen = new Keygen(128);
    }

    @Test
    public void keyGenIsCreated() {
        keygen.keygen();
        assertEquals(new BigInteger("65537"), keygen.getPublicKey());
    }

    @Test
    public void keysAreDifferent() {
        keygen.keygen();
        BigInteger firstPrivate = keygen.getPrivateKey();
        BigInteger firstModulus = keygen.getModulus();
        BigInteger firstPublic = keygen.getPublicKey();
        keygen.keygen();
        BigInteger otherPrivate = keygen.getPrivateKey();
        BigInteger otherModulus = keygen.getModulus();
        BigInteger otherPublic = keygen.getPublicKey();

        assertNotSame(firstPrivate, otherPrivate);
        assertNotSame(firstModulus, otherModulus);
        assertEquals(firstPublic, otherPublic);
    }

    @Test
    public void keysAreCorrectlyDisplayedAsString() {
        keygen.keygen();
        assertThat("Modulus", keygen.toString(), JUnitMatchers.containsString("Modulus : "));
        assertThat("Private", keygen.toString(), JUnitMatchers.containsString("Private : "));
        assertThat("Public", keygen.toString(), JUnitMatchers.containsString("Public : "));
        assertThat("Base64", keygen.toString(), JUnitMatchers.containsString("in Base64 : "));
        assertThat("hexadecimal", keygen.toString(), JUnitMatchers.containsString("in hexadecimal : "));

    }
}
