package com.luffy.utils.securitylib;

import android.util.Base64;

/**
 * Created by lvlufei on 2020-08-19
 *
 * @name Base64加解密（可逆算法加密）-辅助工具
 */
public class Base64Utils {

    /**
     * 加密
     *
     * @param string
     * @return
     */
    public static String encodeBase64NoWrap(String string) {
        return encodeBase64NoWrap(string.getBytes());
    }

    /**
     * 加密
     *
     * @param bytes
     * @return
     */
    public static String encodeBase64NoWrap(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * 解密
     *
     * @param string
     * @return
     */
    public static String decodeBase64(String string) {
        return new String(decodeBase64Bytes(string));
    }

    /**
     * 解密
     *
     * @param string
     * @return
     */
    public static byte[] decodeBase64Bytes(String string) {
        return Base64.decode(string, Base64.DEFAULT);
    }

}
