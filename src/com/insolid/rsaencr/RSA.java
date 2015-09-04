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
    *@return BigInteger of encrypted message.
    */
    public BigInteger encrypt(String message) {
        BigInteger biMessage = new BigInteger(message.getBytes());
        return biMessage.modPow(publickey, modulus);
    }

    /**
    *Decrypting an encoded BigInteger message.
    *@param message encrypted in BigInteger to decrypt
    *@return a string of decrypted message
    */
    public String decrypt(BigInteger message) {
        message = message.modPow(privatekey, modulus);
        return new String(message.toByteArray());
    }

    public BigInteger getPublickey() { return publickey; }
    public BigInteger getModulus() { return modulus; }
}
