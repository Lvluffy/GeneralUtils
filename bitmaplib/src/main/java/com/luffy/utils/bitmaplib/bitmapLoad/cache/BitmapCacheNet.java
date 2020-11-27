package com.luffy.utils.bitmaplib.bitmapLoad.cache;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.BitmapDownloadUtils;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 网络缓存
 */
public class BitmapCacheNet implements IBitmapCache {

    private BitmapCacheLocal mBitmapCacheLocal;
    private BitmapCacheMemory mBitmapCacheMemory;

    public BitmapCacheNet(BitmapCacheLocal bitmapCacheLocal, BitmapCacheMemory bitmapCacheMemory) {
        this.mBitmapCacheLocal = bitmapCacheLocal;
        this.mBitmapCacheMemory = bitmapCacheMemory;
    }

    // 从网络加载图片
    public void getBitmapFromNet(ImageView imageView, String url) {
        new BitmapTask().execute(imageView, url);
    }

    @Override
    public void setBitmapCache(String url, Bitmap bitmap) {

    }

    @Override
    public Bitmap getBitmapCache(String url) {
        return null;
    }

    @Override
    public void clearBitmapCache() {

    }

    class BitmapTask extends AsyncTask<Object, Integer, Bitmap> {

        private ImageView imageView;
        private String url;

        /**
         * 1.预加载 ，运行在主线程
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 2.正在加载(核心方法)，运行在子线程
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];
            imageView.setTag(url);
            return BitmapDownloadUtils.getInstance().download(url);
        }

        /**
         * 3.进度更新（如果下载文件），运行在主线程
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 4.加载结束，运行在主线程
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            if (result == null) {
                return;
            }
            String url = (String) imageView.getTag();
            if (url != null && url.equals(this.url)) {
                // 从网络加载图片
                imageView.setImageBitmap(result);
                // 写本地缓存
                mBitmapCacheLocal.setBitmapCache(url, result);
                //写内存缓存
                mBitmapCacheMemory.setBitmapCache(url, result);
            }
        }
    }
}
