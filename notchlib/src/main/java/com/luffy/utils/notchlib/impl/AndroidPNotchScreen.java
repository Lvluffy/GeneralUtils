package com.luffy.utils.notchlib.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

/**
 * Created by lvlufei on 2019/12/6
 *
 * @name AndroidP设备刘海屏
 */
@TargetApi(Build.VERSION_CODES.P)
public class AndroidPNotchScreen implements INotchScreen {

    @Override
    public boolean hasNotch(Context context) {
        return false;
    }

    @Override
    public void getNotchWidthHeight(Context context, final NotchSizeCallback callback) {
        int[] notchWidthHeight = {0, 0};
        callback.onResult(notchWidthHeight);
    }
}
