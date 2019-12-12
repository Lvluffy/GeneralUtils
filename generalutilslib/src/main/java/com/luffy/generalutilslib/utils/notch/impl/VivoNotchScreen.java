package com.luffy.generalutilslib.utils.notch.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * 测试之后发现vivo并不需要适配，因为vivo没有将显示区域绘制到耳朵区的API
 */
@TargetApi(Build.VERSION_CODES.O)
public class VivoNotchScreen implements INotchScreen {

    @Override
    public boolean hasNotch(Context context) {
        boolean value = false;
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            Method hideMethod = cls.getMethod("isFtFeatureSupport", int.class);
            Object object = cls.newInstance();
            value = (boolean) hideMethod.invoke(object, 0x00000020);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public void getNotchWidthHeight(Context context, NotchSizeCallback callback) {
        int[] notchWidthHeight = {getNotchWidth(context), getNotchHeight(context)};
        callback.onResult(notchWidthHeight);
    }

    /**
     * vivo的适配文档中就告诉是27dp，未告知如何动态获取
     *
     * @param context 上下文
     * @return 刘海屏高度
     */
    private static int getNotchHeight(Context context) {
        float density = getDensity(context);
        return (int) (27 * density);
    }

    /**
     * vivo的适配文档中就告诉是100dp，未告知如何动态获取
     *
     * @param context 是否有效
     * @return 刘海屏宽度
     */
    private static int getNotchWidth(Context context) {
        float density = getDensity(context);
        return (int) (100 * density);
    }

    /**
     * 获取屏幕密度
     *
     * @param context 上下文
     * @return 屏幕密度
     */
    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
