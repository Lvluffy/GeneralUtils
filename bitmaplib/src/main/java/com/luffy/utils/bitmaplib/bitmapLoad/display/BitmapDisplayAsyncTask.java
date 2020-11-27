package com.luffy.utils.bitmaplib.bitmapLoad.display;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.BitmapDownloadUtils;

/**
 * Created by lvlufei on 2020-11-27
 *
 * @name 图片显示方式-异步任务
 */
public class BitmapDisplayAsyncTask implements IBitmapDisplayMode {

    @Override
    public void display(final ImageView imageView, final String url) {
        new BitmapTask().execute(imageView, url);
    }

    private class BitmapTask extends AsyncTask<Object, Integer, Bitmap> {

        private ImageView imageView;
        private String url;

        /**
         * 1.预加载（运行在主线程）
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 2.正在加载（运行在子线程）
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];
            imageView.setTag(url);
            return BitmapDownloadUtils.getInstance().download(url);
        }

        /**
         * 3.进度更新（运行在主线程）
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 4.加载结束（运行在主线程）
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
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
