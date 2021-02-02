package com.luffy.utils.bitmaplib.bitmapLoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheFile;
import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheMemory;
import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheNet;
import com.luffy.utils.bitmaplib.bitmapLoad.download.BitmapDownloadFactory;
import com.luffy.utils.bitmaplib.bitmapLoad.download.BitmapDownloadMode;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 图片加载客户端
 */
public class BitmapLoadClient {

    private BitmapCacheMemory mBitmapCacheMemory;//内存缓存
    private BitmapCacheFile mBitmapCacheFile;//文件缓存
    private BitmapCacheNet mBitmapCacheNet;//网络缓存

    private BitmapLoadClient(Context context) {
        mBitmapCacheMemory = new BitmapCacheMemory();
        mBitmapCacheFile = new BitmapCacheFile(context.getApplicationContext());
        mBitmapCacheNet = BitmapDownloadFactory.getInstance()
                .setBitmapCacheMemory(mBitmapCacheMemory)
                .setBitmapCacheFile(mBitmapCacheFile)
                .setBitmapDownloadMode(BitmapDownloadMode.EXECUTOR)
                .build();
    }

    public static BitmapLoadClient getInstance(Context context) {
        return new BitmapUtilsHolder(context).instance;
    }

    private static class BitmapUtilsHolder {
        private static BitmapLoadClient instance;

        public BitmapUtilsHolder(Context context) {
            instance = new BitmapLoadClient(context);
        }
    }

    public void display(ImageView imageView, String url) {
        display(imageView, url, 0);
    }

    /**
     * @param imageView 要展示加载图片的ImageView
     * @param url       加载图片的链接
     * @param resId     默认图片的资源id
     */
    public void display(ImageView imageView, String url, int resId) {
        // 设置默认图片
        imageView.setImageResource(resId);

        Bitmap bitmap;
        // 1,从内存加载
        bitmap = mBitmapCacheMemory.getBitmapCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        // 2,从本地加载
        bitmap = mBitmapCacheFile.getBitmapCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            //写内存缓存
            mBitmapCacheMemory.setBitmapCache(url, bitmap);
            return;
        }

        // 3,从网络加载
        mBitmapCacheNet.download(imageView, url);

    }
}
