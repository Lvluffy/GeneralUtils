package com.luffy.utils.generallib;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 屏幕-辅助工具
 */
public class ScreenUtils {
    private ScreenUtils() {
    }

    public static ScreenUtils getInstance() {
        return ScreenUtilsHolder.instance;
    }

    private static class ScreenUtilsHolder {
        private static final ScreenUtils instance = new ScreenUtils();
    }

    /**
     * 获取屏幕宽度、高度、分辨率
     *
     * @param context 上下文
     * @return [0]=宽度;[1]=高度;[2]=分辨率
     */
    public int[] getScreenWidthHeightDensity(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        int[] result = new int[3];
        result[0] = mDisplayMetrics.widthPixels;
        result[1] = mDisplayMetrics.heightPixels;
        result[2] = mDisplayMetrics.densityDpi;
        return result;
    }

    /**
     * 获取navigationBar的高度
     *
     * @param context 上下文
     * @return navigationBar的高度
     */
    public int getNavigationBarHeight(Context context) {
        return getDimensionPixel(context, "navigation_bar_height");
    }

    /**
     * 获取statusBar的高度
     *
     * @param context 上下文
     * @return statusBar的高度
     */
    public int getStatusBarHeight(Context context) {
        return getDimensionPixel(context, "status_bar_height");
    }

    /**
     * 获取Dimension像素
     *
     * @param context               上下文
     * @param navigation_bar_height navigationBar的高度
     * @return imension像素
     */
    private int getDimensionPixel(Context context, String navigation_bar_height) {
        int result = 0;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(navigation_bar_height, "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取虚拟键盘的高度
     *
     * @param context 上下文
     * @return 虚拟键盘的高度
     */
    public int getVirtualKeyboardHeight(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    /**
     * 选择全屏显示与否
     *
     * @param mActivity    Activity
     * @param isFullScreen 是否显示全屏
     */
    public void switchFullScreen(Activity mActivity, boolean isFullScreen) {
        if (isFullScreen) {
            WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            mActivity.getWindow().setAttributes(params);
            mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mActivity.getWindow().setAttributes(params);
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 隐藏状态栏
     *
     * @param mActivity Activity
     */
    public void hideStatusBar(Activity mActivity) {
        WindowManager.LayoutParams attrs = mActivity.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mActivity.getWindow().setAttributes(attrs);
    }

    /**
     * 显示状态栏
     *
     * @param mActivity Activity
     */
    public void showStatusBar(Activity mActivity) {
        WindowManager.LayoutParams attrs = mActivity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mActivity.getWindow().setAttributes(attrs);
    }

    /**
     * 判断是否存在NavigationBar
     *
     * @param mActivity Activity
     * @return 是否存在NavigationBarø
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public boolean hasNavigationBar(Activity mActivity) {
        boolean show;
        Display display = mActivity.getWindow().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        View decorView = mActivity.getWindow().getDecorView();
        Configuration conf = mActivity.getResources().getConfiguration();
        if (Configuration.ORIENTATION_LANDSCAPE == conf.orientation) {
            View contentView = decorView.findViewById(android.R.id.content);
            show = (point.x != contentView.getWidth());
        } else {
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            show = (rect.bottom != point.y);
        }
        return show;
    }
}