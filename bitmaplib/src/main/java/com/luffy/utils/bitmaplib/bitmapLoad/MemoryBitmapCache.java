package com.luffy.utils.bitmaplib.bitmapLoad;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 内存缓存
 */
public class MemoryBitmapCache implements IBitmapCache {

    private HashMap<String, SoftReference<Bitmap>> hash;

    @Override
    public void setBitmapCache(String url, Bitmap bitmap) {
        if (hash == null) {
            hash = new HashMap<>();
        }
        SoftReference<Bitmap> soft = new SoftReference<>(bitmap);
        hash.put(url, soft);
    }

    @Override
    public Bitmap getBitmapCache(String url) {
        if (hash == null) {
            return null;
        }
        Bitmap bitmap = null;
        if (hash.containsKey(url)) {
            SoftReference<Bitmap> soft = hash.get(url);
            bitmap = soft.get();
        }
        return bitmap;
    }

    @Override
    public void clearBitmapCache() {
        if (hash != null) {
            hash.clear();
        }
    }
}
