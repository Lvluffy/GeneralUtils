package com.luffy.utils.generallib;

import android.os.Looper;

/**
 * Created by lvlufei on 2020-08-26
 *
 * @name 线程辅助工具
 */
public class ThreadUtils {
    /**
     * 是否为主线程
     *
     * @return
     */
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 获取当前线程的名字
     *
     * @return
     */
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}
