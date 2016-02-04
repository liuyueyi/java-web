package com.mogu.hui.biz.tools;

import com.mogu.hui.biz.monitor.annotation.RT;

import java.io.UnsupportedEncodingException;

/**
 * Emoj表情转换工具，将emoj表情转为utf-8编码格式
 * Created by yihui on 16/1/6.
 */
public class EmojTools {
    /**
     * uft8mb4_编码（针对ios的表情符号）
     *
     * @param input
     * @return string
     */
    @RT
    public static String encode(String input) {
        if (input.isEmpty()) {
            return "";
        }

        int utf8_2byte = 0xC0; /*1100 0000*/
        int utf8_2byte_bmask = 0xE0; /*1110 0000*/

        int utf8_3byte = 0xE0; /*1110 0000*/
        int utf8_3byte_bmask = 0XF0; /*1111 0000*/

        int utf8_4byte = 0xF0; /*1111 0000*/
        int utf8_4byte_bmask = 0xF8; /*1111 1000*/

        String sanitized = "";
        byte[] buff = input.getBytes();
        int len = buff.length;
        for (int i = 0; i < len; ++i) {
            int mbyte = buff[i];
            if ((mbyte & utf8_2byte_bmask) == utf8_2byte) {
                byte[] tmp = new byte[]{buff[i], buff[++i]};
                String str = new String(tmp);
                sanitized += str;
            } else if ((mbyte & utf8_3byte_bmask) == utf8_3byte) {
                byte[] tmp = new byte[]{buff[i], buff[++i], buff[++i]};
                String str = new String(tmp);
                sanitized += str;
            } else if ((mbyte & utf8_4byte_bmask) == utf8_4byte) {
                byte[] tmp = new byte[]{buff[i], buff[++i], buff[++i], buff[++i]};
                String str = new String(tmp);
                sanitized += string2Unicode(str);
            } else {
                byte[] tmp = new byte[]{buff[i]};
                String str = new String(tmp);
                sanitized += str;
            }
        }

        return sanitized;
    }

    @RT
    public static String decode(String input) {
        return unicode2String(input);
    }

    /**
     * unicode 转字符串
     */
    private static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");
        if (hex.length > 0) {
            string.append(hex[0]);
        }

        for (int i = 1; i < hex.length; i++) {
            if (hex[i].length() < 4) {
                // 可能被截断了，不再继续解析了
                break;
            }
            String str = hex[i].substring(0, 4);
            // 转换出每一个代码点
            int data = Integer.parseInt(str, 16);
            // 追加成string
            string.append((char) data);
            // 追加剩下的非表情字符
            string.append(hex[i].substring(4));
        }

        try {
            byte[] utf8Bytes = string.toString().getBytes("UTF-8");

            return new String(utf8Bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 字符串转换unicode
     */
    private static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
}
