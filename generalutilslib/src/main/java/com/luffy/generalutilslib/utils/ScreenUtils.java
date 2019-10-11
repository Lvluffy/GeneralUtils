package com.luffy.generalutilslib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 屏幕-辅助工具
 */
public class ScreenUtils {
    private ScreenUtils() {
    }

    public static ScreenUtils getInstance() {
        return ScreenUtilsHelper.mScreenUtils;
    }

    private static class ScreenUtilsHelper {
        private static ScreenUtils mScreenUtils;

        static {
            mScreenUtils = new ScreenUtils();
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public int getScreenWidth(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public int getScreenHeight(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics.heightPixels;
    }

    /**
     * 获得屏幕分辨率
     *
     * @param context
     * @return
     */
    public int getScreenDpi(Context context) {
        WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics.densityDpi;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取虚拟键盘的高度
     *
     * @param context
     * @return
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
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public Bitmap getScreenShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public Bitmap getScreenShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();

        Matrix matrix = new Matrix();
        matrix.setScale(0.3f, 0.3f);
        bp = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
        return bp;
    }

    /**
     * 选择全屏显示与否
     *
     * @param mActivity
     * @param isFullScreen
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
     * @param mActivity
     */
    public void hideStatusBar(Activity mActivity) {
        WindowManager.LayoutParams attrs = mActivity.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mActivity.getWindow().setAttributes(attrs);
    }

    /**
     * 显示状态栏
     *
     * @param mActivity
     */
    public void showStatusBar(Activity mActivity) {
        WindowManager.LayoutParams attrs = mActivity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mActivity.getWindow().setAttributes(attrs);
    }

    /**
     * 判断是否存在NavigationBar
     *
     * @param mActivity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public boolean hasNavigationBar(Activity mActivity) {
        return checkNavigationBarShow(mActivity);
    }

    /**
     * 获取底部导航栏高度
     *
     * @return
     */
    public int getNavigationBarHeight(Activity mActivity) {
        int navigationHeight;
        Resources resources = mActivity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        navigationHeight = resources.getDimensionPixelSize(resourceId);
        return navigationHeight;
    }

    /**
     * 判断是否有导航栏
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean checkNavigationBarShow(Context context) {
        boolean show;
        Display display = ((Activity) context).getWindow().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);

        View decorView = ((Activity) context).getWindow().getDecorView();
        Configuration conf = context.getResources().getConfiguration();
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

    /**
     * 判断是否是刘海屏
     *
     * @return
     */
    public boolean hasNotchScreen(Activity activity) {
        if (hasNotchAtXiaomi(activity)
                || hasNotchAtHuawei(activity)
                || hasNotchAtOPPO(activity)
                || hasNotchAtVivo(activity)) {
            return true;
        }
        return false;
    }

    /**
     * 小米刘海屏判断
     *
     * @param activity
     * @return
     */
    private boolean hasNotchAtXiaomi(Activity activity) {
        boolean ret = false;
        //是小米手机
        if ("Xiaomi".equals(Build.MANUFACTURER)) {
            try {
                ClassLoader classLoader = activity.getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
                //参数类型
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[2];
                paramTypes[0] = String.class;
                paramTypes[1] = int.class;
                Method getInt = SystemProperties.getMethod("hasNotchAtXiaomi", paramTypes);
                //参数
                Object[] params = new Object[2];
                params[0] = new String("ro.miui.notch");
                params[1] = new Integer(0);
                int result = (Integer) getInt.invoke(SystemProperties, params);
                ret = result == 1;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                return ret;
            }
        }
        return ret;
    }

    /**
     * 华为刘海屏判断
     *
     * @param context
     * @return
     */
    private boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Huawei", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Huawei", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Huawei", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * vivo刘海屏判断
     *
     * @param context
     * @return
     */
    private boolean hasNotchAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, 0x00000020);
        } catch (ClassNotFoundException e) {
            Log.e("Vivo", "hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Vivo", "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Vivo", "hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }

    /**
     * OPPO刘海屏判断
     *
     * @param context
     * @return
     */
    private boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }
}