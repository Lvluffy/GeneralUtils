package com.luffy.utils.generallib;

import android.util.Log;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 日志-辅助工具
 */
public class LogUtils {
    public static final String LOG_TAG = LogUtils.class.getSimpleName();
    private static boolean DEBUG;

    private LogUtils() {
    }

    public static LogUtils getInstance() {
        return LogUtilsHolder.instance;
    }

    private static class LogUtilsHolder {
        private static final LogUtils instance = new LogUtils();
    }

    public void init(boolean isDebug) {
        DEBUG = isDebug;
    }

    /**
     * @param log 日志
     */
    public void logVerbose(String log) {
        if (DEBUG)
            Log.v(LOG_TAG, log);
    }

    /**
     * @param tag 标签
     * @param log 日志
     */
    public void logVerbose(String tag, String log) {
        if (DEBUG)
            Log.v(tag, log);
    }

    /**
     * @param log 日志
     */
    public void logDebug(String log) {
        if (DEBUG)
            Log.d(LOG_TAG, log);
    }

    /**
     * @param tag 标签
     * @param log 日志
     */
    public void logDebug(String tag, String log) {
        if (DEBUG)
            Log.d(tag, log);
    }

    /**
     * @param log 日志
     */
    public void logError(String log) {
        if (DEBUG)
            Log.e(LOG_TAG, log);
    }

    /**
     * @param tag 标签
     * @param log 日志
     */
    public void logError(String tag, String log) {
        if (DEBUG)
            Log.e(tag, log);
    }

    /**
     * @param log 日志
     */
    public void logInfo(String log) {
        if (DEBUG)
            Log.i(LOG_TAG, log);
    }

    /**
     * @param tag 标签
     * @param log 日志
     */
    public void logInfo(String tag, String log) {
        if (DEBUG)
            Log.i(tag, log);
    }

    /**
     * @param log 日志
     */
    public void logWarn(String log) {
        if (DEBUG)
            Log.v(LOG_TAG, log);
    }

    /**
     * @param tag 标签
     * @param log 日志
     */
    public void logWarn(String tag, String log) {
        if (DEBUG)
            Log.w(tag, log);
    }
}