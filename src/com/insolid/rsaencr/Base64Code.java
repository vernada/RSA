package com.insolid.rsaencr;

import java.util.Base64;
import java.math.BigInteger;

/**
*Converting message to/from Base64 String/BigInteger
*@author insolid
*@version 1.0 September 4, 2015
*/
public final class Base64Code {
    /**
    *Encoding message(BigInteger) to a Base64 String
    *@param message is a BigInteger encrypted via RSA cryptosystem
    *@return a Base64 encoded String
    */
    public static String encode(BigInteger message) {
        return Base64.getEncoder().encodeToString(message.toByteArray());
    }

    /**
    *Decoding Base64 message to a BigInteger
    *@param message is an encoded Base64 String
    *@return a decoded BigInteger
    */
    public static BigInteger decode(String message) {
        return new BigInteger(Base64.getDecoder().decode(message));
    }
}
