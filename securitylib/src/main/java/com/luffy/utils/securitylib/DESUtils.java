package com.luffy.utils.securitylib;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by lvlufei on 2020-08-19
 *
 * @name DES加解密（可逆对称算法）-辅助工具
 */
public class DESUtils {

    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";
    private static final String CHARSET_NAME = "UTF-8";
    private static final String ALGORITHM = "DES";

    private DESUtils() {

    }

    public static DESUtils getInstance() {
        return DESUtilsHolder.instance;
    }

    private static class DESUtilsHolder {
        private static final DESUtils instance = new DESUtils();
    }

    /**
     * 加密
     *
     * @param message
     * @param key     长度必须是8位
     */
    public String encrypt(String message, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(CHARSET_NAME));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 实例化IvParameterSpec对象
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET_NAME));
        // 用密码初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] results = cipher.doFinal(message.getBytes(CHARSET_NAME));
        // Base64加密
        return Base64Utils.getInstance().encodeBase64NoWrap(results);
    }

    /**
     * 解密
     *
     * @param message
     * @param key     长度必须是8位
     * @return
     */
    public String decrypt(String message, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        // Base64解密
        byte[] bytesrc = Base64Utils.getInstance().decodeBase64Bytes(message);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(CHARSET_NAME));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 实例化IvParameterSpec对象
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(CHARSET_NAME));
        // 用密码初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        // 获取解密后的数据
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }


}
