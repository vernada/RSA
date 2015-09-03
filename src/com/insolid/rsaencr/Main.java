package com.insolid.rsaencr;

import java.math.BigInteger;

/**
*This is a simulation of RSA Cryptosystem
*@author insolid
*@version 1.0 September 3, 2015
*/
public class Main {
    /**
    *Initializing a RSA object and BigInteger to encrypt and decrypt a message
    *@param args {bit length of RSA key, message}
    */
    public static void main(String[] args) {
        int length = args.length;
        if(length != 2) return;

        RSA rsa = new RSA(Integer.valueOf(args[0]));
        BigInteger message = BigInteger.valueOf(Integer.valueOf(args[1]));
        BigInteger encrypted = rsa.encrypt(message);
        BigInteger decrypted = rsa.decrypt(encrypted);

        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
