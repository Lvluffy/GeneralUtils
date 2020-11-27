package com.luffy.utils.bitmaplib.bitmapLoad.display;

import android.widget.ImageView;

/**
 * Created by lvlufei on 2020-11-27
 *
 * @name 图片显示方式
 */
public interface IBitmapDisplayMode {
    /**
     * 显示图片
     *
     * @param imageView
     * @param url
     */
    void display(ImageView imageView, String url);
}
