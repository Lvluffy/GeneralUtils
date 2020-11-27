package com.luffy.utils.bitmaplib.bitmapLoad.display;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.luffy.utils.bitmaplib.bitmapLoad.BitmapDownloadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2020-11-27
 *
 * @name 图片显示方式-Handler
 */
public class BitmapDisplayHandler implements IBitmapDisplayMode {

    private ImageHandler imageHandler = new ImageHandler();

    private BitmapDisplayHandler() {

    }

    public static BitmapDisplayHandler getInstance() {
        return LoadImageHandlerHolder.instance;
    }

    private static class LoadImageHandlerHolder {
        private static final BitmapDisplayHandler instance = new BitmapDisplayHandler();
    }

    @Override
    public void display(final ImageView imageView, final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Object> obj = new ArrayList<>();
                    Bitmap bitmap = BitmapDownloadUtils.getInstance().download(urlStr);
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
