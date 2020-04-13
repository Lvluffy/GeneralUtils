package com.luffy.generalutilslib.utils;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 点击-辅助工具
 */
public class ClickUtils {

    /**
     * 上次点击时间
     */
    private long lastClickTime;

    private ClickUtils() {

    }

    public static ClickUtils getInstance() {
        return ClickUtilsHelper.mClickUtils;
    }

    private static class ClickUtilsHelper {
        private static final ClickUtils mClickUtils = new ClickUtils();
    }

    /**
     * 重置 最后点击
     */
    public void resetLastClickTime() {
        lastClickTime = 0;
    }

    /**
     * 是否是快速点击
     *
     * @return true:快速点击;false:非快速点击
     */
    public synchronized boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 是否是快速点击
     *
     * @param clickTime 时间戳
     * @return true:快速点击;false:非快速点击
     */
    public synchronized boolean isFastClick(long clickTime) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < clickTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
