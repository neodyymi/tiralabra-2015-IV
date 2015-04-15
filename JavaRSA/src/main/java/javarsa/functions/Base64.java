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
public class Base64 {

    public static final BigInteger BASE = BigInteger.valueOf(64);
    private final static String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    /**
     * Encodes BigInteger into base64 encoded String
     * @param input BigInteger to be encoded
     * @return encoded base64 String
     */
    public static String encode(BigInteger input) {

        if (input.compareTo(BigInteger.ZERO) == -1) {
            throw new IllegalArgumentException("Input must not be negative");
        }

        StringBuilder encoded = new StringBuilder();

        while (input.compareTo(BigInteger.ZERO) == 1) {
            BigInteger[] divAndMod = input.divideAndRemainder(BASE);
            input = divAndMod[0];
            int digit = divAndMod[1].intValue();
            encoded.insert(0, base64chars.charAt(digit));
        }

        if (encoded.length() == 0) {
            encoded.insert(0, base64chars.charAt(0));
        }

        return encoded.toString();
    }

    /**
     * Decodes base64 string to BigInteger
     * @param input base64 encoded String
     * @return decoded BigInteger
     */
    public static BigInteger decode(final String input) {

        if (input.length() == 0) {
            throw new IllegalArgumentException("Input must not be empty");
        }

        BigInteger decoded = BigInteger.ZERO;
        for (int index = 0; index < input.length(); index++) {
            int digit = base64chars.indexOf(input.charAt(input.length() - index - 1));
            decoded = decoded.add(BigInteger.valueOf(digit).multiply(BASE.pow(index)));
        }

        return decoded;
    }
}
