package com.luffy.utils.notchlib.impl;

import android.content.Context;

/**
 * Created by lvlufei on 2019/12/6
 *
 * @name 刘海屏接口回调
 */
public interface INotchScreen {

    /**
     * 判断有没有刘海屏
     *
     * @param context 上下文
     * @return 有没有刘海屏
     */
    boolean hasNotch(Context context);

    /**
     * 获取刘海屏宽高
     *
     * @param context  上下文
     * @param callback 回调
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
