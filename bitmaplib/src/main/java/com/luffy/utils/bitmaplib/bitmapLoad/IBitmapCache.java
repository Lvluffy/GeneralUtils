package com.luffy.utils.bitmaplib.bitmapLoad;

import android.graphics.Bitmap;

/**
 * Created by lvlufei on 2020-11-26
 *
 * @name 图片缓存
 */
public interface IBitmapCache {

    /**
     * 设置缓存
     *
     * @param url
     * @param bitmap
     */
    void setBitmapCache(String url, Bitmap bitmap);

    /**
     * 获取缓存
     *
     * @param url
     * @return
     */
    Bitmap getBitmapCache(String url);

    /**
     * 清理图片缓存
     */
    void clearBitmapCache();
}
