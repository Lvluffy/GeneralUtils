package com.luffy.utils.bitmaplib.bitmapLoad.cache;

import android.graphics.Bitmap;

import com.luffy.utils.bitmaplib.bitmapLoad.download.IBitmapDownload;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 网络缓存
 */
public abstract class BitmapCacheNet implements IBitmapCache, IBitmapDownload {

    BitmapCacheMemory mBitmapCacheMemory;
    BitmapCacheFile mBitmapCacheFile;

    public BitmapCacheNet setBitmapCacheMemory(BitmapCacheMemory mBitmapCacheMemory) {
        this.mBitmapCacheMemory = mBitmapCacheMemory;
        return this;
    }

    public BitmapCacheNet setBitmapCacheFile(BitmapCacheFile mBitmapCacheFile) {
        this.mBitmapCacheFile = mBitmapCacheFile;
        return this;
    }

    @Override
    public void setBitmapCache(String url, Bitmap bitmap) {
        // 写文件缓存
        if (mBitmapCacheFile != null) {
            mBitmapCacheFile.setBitmapCache(url, bitmap);
        }
        // 写内存缓存
        if (mBitmapCacheMemory != null) {
            mBitmapCacheMemory.setBitmapCache(url, bitmap);
        }
    }

    @Override
    public Bitmap getBitmapCache(String url) {
        return null;
    }

    @Override
    public void clearBitmapCache() {

    }
}
