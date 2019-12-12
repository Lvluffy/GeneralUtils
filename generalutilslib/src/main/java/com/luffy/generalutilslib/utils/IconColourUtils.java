package com.luffy.generalutilslib.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

/**
 * Created by lvlufei on 2019/2/19
 *
 * @desc 图标着色-辅助工具
 */
public class IconColourUtils {
    private IconColourUtils() {
    }

    public static IconColourUtils getInstance() {
        return IconColourUtilsHelper.mIconColourUtils;
    }

    private static class IconColourUtilsHelper {
        private static final IconColourUtils mIconColourUtils;

        static {
            mIconColourUtils = new IconColourUtils();
        }
    }

    /**
     * 设置-ImageView图标颜色
     *
     * @param view       ImageView
     * @param colorResId 颜色值
     */
    public void setImageViewColor(ImageView view, int colorResId) {
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        ColorStateList colorStateList = ColorStateList.valueOf(view.getResources().getColor(colorResId));
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }
}
