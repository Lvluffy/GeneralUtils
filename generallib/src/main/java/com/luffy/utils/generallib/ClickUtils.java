package com.luffy.utils.generallib;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 点击-辅助工具
 */
public class ClickUtils {

    /**
     * 上次点击时间
     */
    private long lastClickTime;

    private ClickUtils() {

    }

    public static ClickUtils getInstance() {
        return ClickUtilsHolder.instance;
    }

    private static class ClickUtilsHolder {
        private static final ClickUtils instance = new ClickUtils();
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
        return isFastClick(1000);
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
