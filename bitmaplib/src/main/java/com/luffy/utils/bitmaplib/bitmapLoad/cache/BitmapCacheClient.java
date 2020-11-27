package com.luffy.utils.bitmaplib.bitmapLoad.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 图片加载客户端
 */
public class BitmapCacheClient {

    private BitmapCacheMemory mBitmapCacheMemory;//内存缓存工具类
    private BitmapCacheLocal mBitmapCacheLocal;//本地缓存工具类
    private BitmapCacheNet mBitmapCacheNet;//网络缓存工具类

    private BitmapCacheClient(Context context) {
        mBitmapCacheMemory = new BitmapCacheMemory();
        mBitmapCacheLocal = new BitmapCacheLocal(context.getApplicationContext());
        mBitmapCacheNet = new BitmapCacheNet(mBitmapCacheLocal, mBitmapCacheMemory);
    }

    public static BitmapCacheClient getInstance(Context context) {
        return new BitmapUtilsHolder(context).instance;
    }

    private static class BitmapUtilsHolder {
        private static BitmapCacheClient instance;

        public BitmapUtilsHolder(Context context) {
            instance = new BitmapCacheClient(context);
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
        bitmap = mBitmapCacheLocal.getBitmapCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            //写内存缓存
            mBitmapCacheMemory.setBitmapCache(url, bitmap);
            return;
        }

        // 3,从网络加载
        mBitmapCacheNet.getBitmapFromNet(imageView, url);

    }
}
