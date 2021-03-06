package com.luffy.utils.bitmaplib;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

/**
 * Created by lvlufei on 2019/2/19
 *
 * @name 图标着色-辅助工具
 */
public class IconColourUtils {
    /**
     * 设置-ImageView图标颜色
     *
     * @param view       ImageView
     * @param colorResId 颜色值
     */
    public static void setImageViewColor(ImageView view, int colorResId) {
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        ColorStateList colorStateList = ColorStateList.valueOf(view.getResources().getColor(colorResId));
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }
}
