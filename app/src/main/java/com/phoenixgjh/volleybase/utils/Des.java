package com.phoenixgjh.volleybase.utils;

import com.phoenixgjh.volleybase.app.AppConfig;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Phoenix on 2016/7/18.
 */
public class Des {
    public static final int keyMd5BeginNum = 0;
    public static final int keyMd5Num = 8;
    public static final String pwdKey = "LMvcOs";

    public static String encrypt(String value) throws Exception {
        String keyMd5 = MD5Util.MD5(Des.pwdKey);
        keyMd5 = keyMd5.substring(0, 8);
        String jiami = java.net.URLEncoder.encode(value, "utf-8").toLowerCase();
        String pwd = Des.toHexString(Des.encrypt(jiami, keyMd5)).toUpperCase();
        return pwd;
    }

    public static String decode(String value) throws Exception {
        String keyMd5 = MD5Util.MD5(Des.pwdKey);
        keyMd5 = keyMd5.substring(0, 8);
        String pwd = java.net.URLDecoder.decode(Des.decrypt(value, keyMd5),
                "utf-8");
        return pwd;
    }

    /**
     * @param message
     * @param key
     * @return
     * @throws Exception
     * @author /解密数据
     */
    public static String decrypt(String message, String key) throws Exception {
        byte[] bytesrc = convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    public static byte[] encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        return cipher.doFinal(message.getBytes("UTF-8"));
    }


    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(MD5Util.MD5(Des.pwdKey));
//        String key = "F7A71510";
//        String value = "123";
//        String jiami = java.net.URLEncoder.encode(value, "utf-8").toLowerCase();
//        System.out.println("加密数据:" + jiami);
//        String a = toHexString(encrypt(jiami, key)).toUpperCase();
//        System.out.println("加密后的数据为:" + a);
//        String b = java.net.URLDecoder.decode(decrypt(a, key), "utf-8");
//        System.out.println("解密后的数据:" + b);
//    }

    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public static String getString(String value) {
        String jiami = null;
        String md5 = MD5Util.MD5(AppConfig.whereiskey);
        String temp = md5.substring(0, 8).toUpperCase();
        try {
            jiami = java.net.URLEncoder.encode(value, "utf-8").toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String a = Des.toHexString(Des.encrypt(jiami, temp)).toUpperCase();
//            System.out.println("加密后的数据为:" + a);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
