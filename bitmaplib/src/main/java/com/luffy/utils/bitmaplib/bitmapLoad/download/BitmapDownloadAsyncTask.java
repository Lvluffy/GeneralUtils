package com.luffy.utils.bitmaplib.bitmapLoad.download;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheNet;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 网络缓存
 */
public class BitmapDownloadAsyncTask extends BitmapCacheNet {

    @Override
    public void download(ImageView imageView, String url) {
        new BitmapTask().execute(imageView, url);
    }

    private class BitmapTask extends AsyncTask<Object, Integer, Bitmap> {

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
            Bitmap bitmap = BitmapDownloadUtils.getInstance().download(url);
            // 设置缓存
            setBitmapCache(url, bitmap);
            return bitmap;
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
                imageView.setImageBitmap(result);
            }
        }
    }
}
