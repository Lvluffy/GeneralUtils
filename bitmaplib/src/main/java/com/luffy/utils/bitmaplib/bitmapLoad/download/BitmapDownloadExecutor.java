package com.luffy.utils.bitmaplib.bitmapLoad.download;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheNet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 网络缓存
 */
public class BitmapDownloadExecutor extends BitmapCacheNet {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);// 同时最多启动1个线程

    @Override
    public void download(final ImageView imageView, final String url) {
        final Handler handler = new Handler();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap[] bitmap = {BitmapDownloadUtils.getInstance().download(url)};
                // 设置缓存
                setBitmapCache(url, bitmap[0]);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap[0]);
                        bitmap[0] = null;
                    }
                });
            }
        });
    }
}
