package com.swlibs.common.system;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    public static class Algorithm {
        public static final String MD5 = "HmacMD5";
        public static final String SHA512 = "HmacSHA512";
        public static final String SHA256 = "HmacSHA256";
        public static final String SHA1 = "HmacSHA1";
    }
    //private static final String ALGORITHM = "AES";
    //private static final byte[] keyValue = "325".getBytes();

    /*public static String encrypt(String value) {
        return value+keyValue;
    }

    public static String decrypt(String value) {
        return value.substring(-1*keyValue.length);
    }*/

    public static String getHmac(String key, String message) {
        return getHmac(Algorithm.SHA256, key, message);
    }

    public static String getHmac(String algorithm, String key, String message) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
            mac.init(secret_key);
            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            byte[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] hexHmac = new byte[rawHmac.length * 2];
            for ( int j = 0; j < rawHmac.length; j++ ) {
                int v = rawHmac[j] & 0xFF;
                hexHmac[j * 2] = hexArray[v >>> 4];
                hexHmac[j * 2 + 1] = hexArray[v & 0x0F];
            }
            //byte[] bytes = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            //byte[] bytes = Base64.encode(fin, Base64.NO_WRAP);
            //
            return new String(hexHmac, StandardCharsets.US_ASCII);
        } catch (InvalidKeyException e) {
            throw new RuntimeException();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public static String getMd5(String value) {
        String result = null;
        try {
            byte[] bytesOfMessage = value.getBytes("utf-8");
            MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.reset();
            md.update(bytesOfMessage);
            result = new BigInteger(1,md.digest()).toString(16);
            //дополняем нулями до 32 символов, в случае необходимости
            while(result.length() < 32 ){
                result = "0" + result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
