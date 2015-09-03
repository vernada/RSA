package com.insolid.rsaencr;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final SecureRandom random = new SecureRandom();

    private BigInteger modulus = null;
    private BigInteger phi = null;
    private BigInteger publickey = null;
    private BigInteger privatekey = null;

    public RSA(final int bits) {
        BigInteger primeOne = new BigInteger(bits/2, 100, random);
        BigInteger primeTwo = new BigInteger(bits/2, 100, random);
        modulus = primeOne.multiply(primeTwo);
        phi = primeOne.subtract(ONE).multiply(primeTwo.subtract(ONE));
        publickey = new BigInteger(12, 100, random);
        privatekey = publickey.modInverse(phi);
    }

    public RSA() {
        this(256);
    }

    public final BigInteger encrypt(BigInteger message) {
        return message.modPow(publickey, modulus);
    }

    public final BigInteger decrypt(BigInteger message) {
        return message.modPow(privatekey, modulus);
    }
}
