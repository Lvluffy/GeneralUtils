package com.luffy.utils.generallib;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 日志-辅助工具
 */
public class LogUtils {
    private static final String TAG = "com.luffy.generalutils";

    public static void v(String msg) {
        println(Log.VERBOSE, "", msg);
    }

    public static void v(String tag, String msg) {
        println(Log.VERBOSE, tag, msg);
    }

    public static void d(String msg) {
        println(Log.DEBUG, "", msg);
    }

    public static void d(String tag, String msg) {
        println(Log.DEBUG, tag, msg);
    }

    public static void i(String msg) {
        println(Log.INFO, "", msg);
    }

    public static void i(String tag, String msg) {
        println(Log.INFO, tag, msg);
    }

    public static void w(String msg) {
        println(Log.WARN, "", msg);
    }

    public static void w(String tag, String msg) {
        println(Log.WARN, tag, msg);
    }

    public static void e(String msg) {
        println(Log.ERROR, "", msg);
    }

    public static void e(String tag, String msg) {
        println(Log.ERROR, tag, msg);
    }

    private static void println(int priority, String tag, String msg) {
        if (priority >= Log.DEBUG) {
            if (TextUtils.isEmpty(tag)) {
                Log.println(priority, TAG, msg);
            } else {
                Log.println(priority, TAG, "[" + tag + "] " + msg);
            }
        }
    }
}