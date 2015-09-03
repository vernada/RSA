package com.insolid.rsaencr;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA();

        BigInteger message = BigInteger.valueOf(1111);
        BigInteger encrypted = rsa.encrypt(message);
        BigInteger decrypted = rsa.decrypt(encrypted);

        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
