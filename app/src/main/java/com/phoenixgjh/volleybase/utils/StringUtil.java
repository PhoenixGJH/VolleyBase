package com.phoenixgjh.volleybase.utils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Phoenix on 2016/7/18.
 */
public class StringUtil {
    /**
     * 获取MD5值
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
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

    /**
     * 输入的内容是否是手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        Pattern pattern = Pattern.compile("1[3-9][0-9]{9}");
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     * 输入的内容是否是密码
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        Pattern pattern = Pattern.compile("[0-9a-zA-Z_]{6,15}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 字符串替换
     *
     * @param from   被替换部分
     * @param to     替换后的部分
     * @param source 替换目标
     * @return 替换后的字符串
     */
    public static String replace(String from, String to, String source) {
        if (source == null || from == null || to == null)
            return null;
        StringBuffer bf = new StringBuffer("");
        int index = -1;
        while ((index = source.indexOf(from)) != -1) {
            bf.append(source.substring(0, index) + to);
            source = source.substring(index + from.length());
            index = source.indexOf(from);
        }
        bf.append(source);
        return bf.toString();
    }
}
