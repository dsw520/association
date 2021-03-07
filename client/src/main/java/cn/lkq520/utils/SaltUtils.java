package cn.lkq520.utils;

import java.util.Random;

/**
 * TODO
 *
 * @author Luo
 * @version 1.0
 * @date 2020/11/28 16:55
 */
public class SaltUtils {

    public static String getSalt(int len) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(8));
    }

}
