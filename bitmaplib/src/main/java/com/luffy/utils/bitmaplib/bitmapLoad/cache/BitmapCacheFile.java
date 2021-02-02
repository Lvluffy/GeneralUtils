package com.luffy.utils.bitmaplib.bitmapLoad.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 文件缓存
 */
public class BitmapCacheFile implements IBitmapCache {

    private String cachePath;

    public BitmapCacheFile(Context context) {
        cachePath = getExternalCachePath(context);
    }

    @Override
    public void setBitmapCache(String url, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            // 创建文件目录
            File dir = new File(cachePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 创建文件
            File imageFile = new File(dir, md5(url) + ".png");
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }
            // 写文件
            fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Bitmap getBitmapCache(String url) {
        FileInputStream fileInputStream = null;
        try {
            File cacheFile = new File(cachePath, md5(url) + ".png");
            if (cacheFile.exists()) {
                fileInputStream = new FileInputStream(cacheFile);
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void clearBitmapCache() {

    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private String toHexString(byte[] bytes) {
        if (bytes == null) return "";
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(hexDigits[(b >> 4) & 0x0F]);
            hex.append(hexDigits[b & 0x0F]);
        }
        return hex.toString();
    }

    private String md5(String string) {
        byte[] encodeBytes;
        try {
            encodeBytes = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException neverHappened) {
            throw new RuntimeException(neverHappened);
        } catch (UnsupportedEncodingException neverHappened) {
            throw new RuntimeException(neverHappened);
        }
        return toHexString(encodeBytes);
    }

    /**
     * 获取外部缓存路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/cache
     */
    private String getExternalCachePath(Context context) {
        return context.getExternalCacheDir().getPath() + File.separator + "image";
    }
}

