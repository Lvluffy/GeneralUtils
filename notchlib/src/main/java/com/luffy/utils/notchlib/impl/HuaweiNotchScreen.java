package com.luffy.utils.notchlib.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created by lvlufei on 2019/12/6
 *
 * @name 华为设备刘海屏
 */
@TargetApi(Build.VERSION_CODES.O)
public class HuaweiNotchScreen implements INotchScreen {

    @Override
    public boolean hasNotch(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (Throwable ignore) {
        }
        return ret;
    }

    @Override
    public void getNotchWidthHeight(Context context, NotchSizeCallback callback) {
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            int[] ret = (int[]) get.invoke(HwNotchSizeUtil);
            callback.onResult(ret);
        } catch (Throwable ignore) {
            callback.onResult(null);
        }
    }
}
