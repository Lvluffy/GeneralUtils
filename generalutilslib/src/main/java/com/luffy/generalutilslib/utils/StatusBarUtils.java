package com.luffy.generalutilslib.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2018/7/29
 *
 * @desc 状态栏-辅助工具
 */
public class StatusBarUtils {

    private StatusBarUtils() {

    }

    public static StatusBarUtils getInstance() {
        return StatusBarUtils.StatusBarUtilsHelper.mStatusBarUtils;
    }

    private static class StatusBarUtilsHelper {
        private static StatusBarUtils mStatusBarUtils;

        static {
            mStatusBarUtils = new StatusBarUtils();
        }
    }

    /**
     * 设置状态栏
     *
     * @param activity                Activity窗口界面
     * @param hideStatusBarBackground 隐藏状态栏（true-隐藏；false-显示）
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setStatusBar(Activity activity, boolean hideStatusBarBackground) {
        Window window = activity.getWindow();
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //设置状态栏为透明
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //如果为全透明模式，取消设置Window半透明的Flag
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            //设置window的状态栏不可见
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            //处理导航栏
            handlerNavigationBar(activity);
        } else {
            //如果为半透明模式，添加设置Window半透明的Flag
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置系统状态栏处于可见状态
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        //view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

    /**
     * 设置状态栏
     *
     * @param mActivity   Activity窗口界面
     * @param colorId     colorId（颜色ID）
     * @param isDarkColor ture:实现状态栏图标和文字颜色为暗色;false:实现状态栏图标和文字颜色为浅色
     */
    public void setStatusBar(Activity mActivity, int colorId, boolean isDarkColor) {
        /*5.0及以上*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = mActivity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色
            mActivity.getWindow().setStatusBarColor(mActivity.getResources().getColor(colorId));
        }
        /*4.4到5.0*/
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = mActivity.getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
        }
        /*6.0以后可以对状态栏文字颜色和图标进行修改*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarIconColor(mActivity, isDarkColor);
        }
    }

    /**
     * 设置状态栏-渐变色
     *
     * @param mActivity   Activity窗口界面
     * @param resid       resourceId（资源ID-渐变色文件）
     * @param isDarkColor ture:实现状态栏图标和文字颜色为暗色;false:实现状态栏图标和文字颜色为浅色
     */
    public void setStatusBarGradient(final Activity mActivity, final int resid, boolean isDarkColor) {
        /*5.0及以上*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mActivity.getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    View statusBarView = null;
                    if (statusBarView == null) {
                        //利用反射机制修改状态栏背景
                        int identifier = mActivity.getResources().getIdentifier("statusBarBackground", "id", "android");
                        statusBarView = mActivity.getWindow().findViewById(identifier);
                    }
                    if (statusBarView != null) {
                        statusBarView.setBackgroundResource(resid);
                    }
                    mActivity.getWindow().getDecorView().removeOnLayoutChangeListener(this);
                }
            });
        }
        /*4.4到5.0*/
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = mActivity.getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
        }
        /*6.0以后可以对状态栏文字颜色和图标进行修改*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarIconColor(mActivity, isDarkColor);
        }

    }

    /**
     * 设置状态栏图标和文字颜色
     *
     * @param mActivity   Activity窗口界面
     * @param isDarkColor ture:实现状态栏图标和文字颜色为暗色;false:实现状态栏图标和文字颜色为浅色
     */
    private void setStatusBarIconColor(Activity mActivity, boolean isDarkColor) {
        /*实现状态栏图标和文字颜色为暗色*/
        if (isDarkColor) {
            /*6.0以后可以对状态栏文字颜色和图标进行修改*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        /*实现状态栏图标和文字颜色为浅色*/
        else {
            /*6.0以后可以对状态栏文字颜色和图标进行修改*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变黑(目前支持MIUI6以上,Flyme4以上,Android M以上)
     *
     * @param activity 目标activity
     */
    public void setDarkMode(@NonNull Activity activity) {
        darkMode(activity.getWindow(), true);
    }

    /**
     * 设置状态栏darkMode,字体颜色及icon变亮(目前支持MIUI6以上,Flyme4以上,Android M以上)
     *
     * @param activity 目标activity
     */
    public void setLightMode(@NonNull Activity activity) {
        darkMode(activity.getWindow(), false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void darkMode(Window window, boolean dark) {
        if (isFlyme4()) {
            setModeForFlyme4(window, dark);
        } else if (isMIUI6()) {
            setModeForMIUI6(window, dark);
        }
        darkModeForM(window, dark);
    }

    /**
     * android 6.0设置字体颜色
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private void darkModeForM(Window window, boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            if (dark) {
                systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    /**
     * 设置MIUI6+的状态栏的darkMode,darkMode时候字体颜色及icon
     * http://dev.xiaomi.com/doc/p=4769/
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    private void setModeForMIUI6(Window window, boolean dark) {
        Class<? extends Window> clazz = window.getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, dark ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            Log.e("StatusBar", "darkIcon: failed");
        }

    }

    /**
     * 设置Flyme4+的状态栏的darkMode,darkMode时候字体颜色及icon
     * http://open-wiki.flyme.cn/index.php?title=Flyme%E7%B3%BB%E7%BB%9FAPI
     *
     * @param window 目标window
     * @param dark   亮色 or 暗色
     */
    private void setModeForFlyme4(Window window, boolean dark) {
        try {
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
            Log.e("StatusBar", "darkIcon: failed");
        }
    }

    /**
     * 判断是否Flyme4以上
     */
    private boolean isFlyme4() {
        return Build.FINGERPRINT.contains("Flyme_OS_4") || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern.CASE_INSENSITIVE).matcher(Build.DISPLAY).find();
    }

    /**
     * 判断是否MIUI6以上
     */
    private boolean isMIUI6() {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method mtd = clz.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, "ro.miui.ui.version.name");
            val = val.replaceAll("[vV]", "");
            int version = Integer.parseInt(val);
            return version >= 6;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据百分比设置颜色透明度
     *
     * @param color    颜色值
     * @param fraction 百分比
     * @return
     */
    public int setAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * 处理导航栏
     * <p>
     * 解决：全屏时，由于视图布局会填充到状态栏和导航栏下方，如果不使用android:fitsSystemWindows=”true”属性，就会使底部导航栏和应用底部按钮重叠，导视按钮点击失效。
     *
     * @param mActivity Activity窗口界面
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void handlerNavigationBar(Activity mActivity) {
        if (ScreenUtils.getInstance().hasNavigationBar(mActivity)) {
            mActivity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, ScreenUtils.getInstance().getNavigationBarHeight(mActivity));
        }
    }
}
