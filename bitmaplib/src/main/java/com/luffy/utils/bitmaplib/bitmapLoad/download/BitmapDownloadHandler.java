package com.luffy.utils.bitmaplib.bitmapLoad.download;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.cache.BitmapCacheNet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2020-11-25
 *
 * @name 网络缓存
 */
public class BitmapDownloadHandler extends BitmapCacheNet {

    private ImageHandler imageHandler = new ImageHandler();

    @Override
    public void download(final ImageView imageView, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Object> obj = new ArrayList<>();
                    Bitmap bitmap = BitmapDownloadUtils.getInstance().download(url);
                    // 设置缓存
                    setBitmapCache(url, bitmap);
                    obj.add(imageView);
                    obj.add(bitmap);
                    Message msg = new Message();
                    msg.obj = obj;
                    imageHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class ImageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.obj instanceof List) {
                List<Object> obj = (List<Object>) msg.obj;
                if (obj != null && !obj.isEmpty()) {
                    ImageView imageView = (ImageView) obj.get(0);
                    Bitmap bitmap = (Bitmap) obj.get(1);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
