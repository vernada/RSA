package com.insolid.rsaencr;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
*Generating public key and modulus with RSA algorithm
*Singleton object.
*@author insolid
*@version 1.0 September 3, 2015
*/
public final class RSA {
    private static final Object lock = new Object();
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final SecureRandom random = new SecureRandom();
    private static RSA rsa;

    private final BigInteger modulus;
    private final BigInteger phi;
    private final BigInteger publickey;
    private final BigInteger privatekey;

    /**
    *Generating RSA with given a specific bit number
    *@param bits of key
    */
    private RSA(final int bits) {
        BigInteger primeOne = new BigInteger(bits/2, 100, random);
        BigInteger primeTwo = new BigInteger(bits/2, 100, random);
        modulus = primeOne.multiply(primeTwo);
        phi = primeOne.subtract(ONE).multiply(primeTwo.subtract(ONE));
        publickey = new BigInteger(12, 100, random);
        privatekey = publickey.modInverse(phi);
    }

    /**
    *Static factory of RSA object, it is singleton.
    *@param bits of key
    *@return a RSA object which is singleton
    */
    public static RSA getInstance(final int bits) {
        if(rsa != null) return rsa;
        synchronized(lock) {
            if(rsa != null) return rsa;
            rsa = new RSA(bits);
            return rsa;
        }
    }

    /**
    *Encrypting a string message.
    *@param message string to encrypt
    *@return String of encrypted message in Base64.
    */
    public String encrypt(String message) {
        BigInteger biMessage = new BigInteger(message.getBytes());
        biMessage = biMessage.modPow(publickey, modulus);
        return Base64Code.encode(biMessage);
    }

    /**
    *Decrypting an encoded BigInteger message.
    *@param message is an encrypted string in Base64 to decrypt
    *@return a string of decrypted message
    */
    public String decrypt(String message) {
        BigInteger biMessage = Base64Code.decode(message);
        biMessage = biMessage.modPow(privatekey, modulus);
        return new String(biMessage.toByteArray());
    }

    public BigInteger getPublickey() { return publickey; }
    public BigInteger getModulus() { return modulus; }
}
