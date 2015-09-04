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

        RSA rsa = RSA.getInstance(Integer.parseInt(args[0]));
        String message = args[1];
        BigInteger encrypted = rsa.encrypt(message);
        String encodedMessage = Base64Code.encode(encrypted);
        BigInteger decodedMessage = Base64Code.decode(encodedMessage);
        String decrypted = rsa.decrypt(decodedMessage);

        System.out.println(message + "\n" + encrypted + "\n" + encodedMessage + "\n" + decodedMessage + "\n" + decrypted);
    }
}
