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
        if(args.length != 2) return;

        RSA rsa = RSA.getInstance(Integer.parseInt(args[0]));
        String message = args[1];
        String encryptedMessage = rsa.encrypt(message);
        String decryptedMessage = rsa.decrypt(encryptedMessage);

        System.out.println(message + "\n" + encryptedMessage + "\n" + decryptedMessage);
    }
}
