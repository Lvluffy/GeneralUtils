package com.luffy.generalutilslib.utils.notch.impl;

import android.content.Context;

/**
 * Created by lvlufei on 2019/12/6
 *
 * @name 刘海屏接口回调
 * @desc
 */
public interface INotchScreen {

    /**
     * 判断有没有刘海屏
     *
     * @param context
     * @return
     */
    boolean hasNotch(Context context);

    /**
     * 获取刘海屏宽高
     *
     * @param context
     * @param callback
     */
    void getNotchWidthHeight(Context context, NotchSizeCallback callback);

    interface NotchSizeCallback {
        void onResult(int[] notchWidthHeight);
    }

    interface NotchScreenCallback {
        void onResult(NotchScreenInfo notchScreenInfo);
    }

    class NotchScreenInfo {
        public boolean hasNotch;
        public int[] notchWidthHeight;//[0]=Width;[1]=Height
    }
}
