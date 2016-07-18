package com.phoenixgjh.volleybase.utils;

import java.security.MessageDigest;

/**
 * Created by Phoenix on 2016/7/18.
 */
public class MD5Util {
    public static final String generateMD5String(String string) {
        String result = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(string.getBytes("UTF8"));
            byte[] s = m.digest();

            for (int i = 0; i < s.length; i++)
                result = result +
                        Integer.toHexString(0xFF & s[i] | 0xFFFFFF00)
                                .substring(6);
        } catch (Exception e) {
            result = null;
        }

        return result;
    }

    public static final String MD5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] strTemp = s.getBytes("UTF8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(byte0 & 0xF)];
            }
            return new String(str);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    public static String MD52(String s) {
        try {
            String str = s;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            byte[] hash = digest.digest();
            StringBuffer result = new StringBuffer(hash.length * 2);
            for (int i = 0; i < hash.length; i++) {
                if ((hash[i] & 0xFF) < 16) {
                    result.append("0");
                }
                result.append(Long.toString(hash[i] & 0xFF, 16));
            }
            return result.toString();
        } catch (Exception localException) {
        }
        return null;
    }


    //  md5 接口加密方法
    public static String encrypt(String str) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = str.getBytes("utf-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char c[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                c[k++] = hexDigits[byte0 >>> 4 & 0xf];
                c[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(c);
        } catch (Exception e) {
            return null;
        }
    }
}
