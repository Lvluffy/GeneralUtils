package com.luffy.utils.bitmaplib.bitmapLoad.display;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.BitmapDownloadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lvlufei on 2020-11-27
 *
 * @name 图片显示方式-线程池
 */
public class BitmapDisplayExecutor implements IBitmapDisplayMode {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);// 同时最多启动1个线程

    private BitmapDisplayExecutor() {

    }

    public static BitmapDisplayExecutor getInstance() {
        return LoadImageExecutorServiceHolder.instance;
    }

    private static class LoadImageExecutorServiceHolder {
        private static final BitmapDisplayExecutor instance = new BitmapDisplayExecutor();
    }

    @Override
    public void display(final ImageView imageView, final String url) {
        final Handler handler = new Handler();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap[] bitmap = {BitmapDownloadUtils.getInstance().download(url)};
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
