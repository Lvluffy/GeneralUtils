package com.luffy.utils.securitylib;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by lvlufei on 2020-08-19
 *
 * @name AES加解密（可逆对称算法）-辅助工具
 */
public class AESUtils {

    private static final String TRANSFORMATION = "AES/ECB/PKCS5PADDING";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String ALGORITHM = "AES";

    private AESUtils() {

    }

    public static AESUtils getInstance() {
        return AESUtilsHolder.instance;
    }

    private static class AESUtilsHolder {
        private static final AESUtils instance = new AESUtils();

    }

    /**
     * 加密
     *
     * @param value
     * @param key
     * @return
     */
    public String encrypt(String value, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 创建Key对象
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), ALGORITHM);
        // 用密码初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        // 获取加密后的数据
        byte[] encrypted = cipher.doFinal(value.getBytes());
        // 进行Base64加密
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    /**
     * 解密
     *
     * @param encrypted
     * @param key
     * @return
     */
    public String decrypt(String encrypted, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Base64解密
        byte[] byteSrc = Base64.decode(encrypted, Base64.DEFAULT);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 创建Key对象
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), ALGORITHM);
        // 用密码初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        // 获取解密后的数据
        byte[] original = cipher.doFinal(byteSrc);
        return new String(original);
    }
}
