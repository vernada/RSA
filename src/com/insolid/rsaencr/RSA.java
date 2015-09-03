package com.insolid.rsaencr;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RSA {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final SecureRandom random = new SecureRandom();

    private final BigInteger modulus;
    private final BigInteger phi;
    private final BigInteger publickey;
    private final BigInteger privatekey;

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

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publickey, modulus);
    }

    public BigInteger decrypt(BigInteger message) {
        return message.modPow(privatekey, modulus);
    }

    public BigInteger getPublickey() { return publickey; }
    public BigInteger getModulus() { return modulus; }
}
