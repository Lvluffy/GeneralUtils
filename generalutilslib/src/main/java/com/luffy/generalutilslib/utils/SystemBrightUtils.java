package com.luffy.generalutilslib.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 系统亮度-辅助工具
 */
public class SystemBrightUtils {

    private SystemBrightUtils() {
    }

    public static SystemBrightUtils getInstance() {
        return SystemBrightUtilsHelper.mSystemBrightUtils;
    }

    private static class SystemBrightUtilsHelper {
        private static final SystemBrightUtils mSystemBrightUtils;

        static {
            mSystemBrightUtils = new SystemBrightUtils();
        }
    }

    /**
     * 判断是否开启了自动亮度调节
     *
     * @param activity Activity
     * @return 是否开启了自动亮度调节
     */
    public boolean isAutoBrightness(Activity activity) {
        boolean autoBrightness = false;
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            autoBrightness = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autoBrightness;
    }

    /**
     * 获取当前系统亮度值
     *
     * @param activity Activity
     * @return 当前系统亮度值
     */
    public int getBrightness(Activity activity) {
        int brightValue = 0;
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            brightValue = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightValue;
    }

    // 改变屏幕亮度
    public void setBrightness(Activity activity, int brightValue) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = (brightValue <= 0 ? 0f : brightValue / 255f);
        activity.getWindow().setAttributes(lp);
    }

    // 开启亮度自动亮度模式
    public void startAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        Uri uri = Settings.System.getUriFor("screen_brightness");
        activity.getContentResolver().notifyChange(uri, null);
    }

    // 停止自动亮度模式
    public void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Uri uri = Settings.System.getUriFor("screen_brightness");
        activity.getContentResolver().notifyChange(uri, null);
    }

    /**
     * 设置当前屏幕亮度的模式
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     */
    public void setBrightnessMode(Activity activity, int brightMode) {
        Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, brightMode);
    }
}
