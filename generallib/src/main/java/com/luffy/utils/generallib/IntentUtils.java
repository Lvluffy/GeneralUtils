package com.luffy.utils.generallib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 外部跳转-辅助工具
 */
public class IntentUtils {

    private IntentUtils() {
    }

    public static IntentUtils getInstance() {
        return IntentUtilsHelper.mIntentUtils;
    }

    /**
     * 静态内部类实现单例
     */
    private static class IntentUtilsHelper {
        private static final IntentUtils mIntentUtils = new IntentUtils();
    }

    /**
     * 界面跳转
     *
     * @param context 上下文对象
     * @param action  action
     */
    public void startActivity(Context context, String action) {
        startActivity(context, action, null);
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param action      目标对象
     * @param intentExtra 自定义传参
     */
    public void startActivity(Context context, String action, IntentExtra intentExtra) {
        if (action == null) throw new NullPointerException("Action cannot be empty");
        Intent intent = new Intent(action);
        if (intentExtra != null) {
            context.startActivity(intentExtra.putExtra(intent));
        } else {
            context.startActivity(intent);
        }
    }

    /**
     * 界面跳转
     *
     * @param context 上下文对象
     * @param target  目标对象
     */
    public void startActivity(Context context, Class<?> target) {
        startActivity(context, target, null);
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param intentExtra 自定义传参
     */
    public void startActivity(Context context, Class<?> target, IntentExtra intentExtra) {
        if (target == null) throw new NullPointerException("Target cannot be empty");
        Intent intent = new Intent(context, target);
        if (intentExtra != null) {
            context.startActivity(intentExtra.putExtra(intent));
        } else {
            context.startActivity(intent);
        }
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param requestCode 请求码
     */
    public void startActivityForResult(Activity context, Class<?> target, int requestCode) {
        startActivityForResult(context, target, requestCode, null);
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param requestCode 请求码
     * @param intentExtra 自定义传参
     */
    public void startActivityForResult(Activity context, Class<?> target, int requestCode, IntentExtra intentExtra) {
        if (target == null) throw new NullPointerException("Target cannot be empty");
        Intent intent = new Intent(context, target);
        if (intentExtra != null) {
            context.startActivityForResult(intentExtra.putExtra(intent), requestCode);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param requestCode 请求码
     */
    public void startActivityForResult(android.support.v4.app.Fragment context, Class<?> target, int requestCode) {
        startActivityForResult(context, target, requestCode, null);
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param requestCode 请求码
     * @param intentExtra 自定义传参
     */
    public void startActivityForResult(android.support.v4.app.Fragment context, Class<?> target, int requestCode, IntentExtra intentExtra) {
        if (target == null) throw new NullPointerException("Target cannot be empty");
        Intent intent = new Intent(context.getActivity(), target);
        if (intentExtra != null) {
            context.startActivityForResult(intentExtra.putExtra(intent), requestCode);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param requestCode 请求码
     */
    public void startActivityForResult(android.app.Fragment context, Class<?> target, int requestCode) {
        startActivityForResult(context, target, requestCode, null);
    }

    /**
     * 界面跳转
     *
     * @param context     上下文对象
     * @param target      目标对象
     * @param requestCode 请求码
     * @param intentExtra 自定义传参
     */
    public void startActivityForResult(android.app.Fragment context, Class<?> target, int requestCode, IntentExtra intentExtra) {
        if (target == null) throw new NullPointerException("Target cannot be empty");
        Intent intent = new Intent(context.getActivity(), target);
        if (intentExtra != null) {
            context.startActivityForResult(intentExtra.putExtra(intent), requestCode);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

    public interface IntentExtra {
        Intent putExtra(Intent intent);
    }

    /**
     * 打开浏览器
     *
     * @param mContext 上下文对象
     * @param url      网址
     */
    public void openBrowser(Context mContext, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        mContext.startActivity(intent);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param context  上下文
     * @param phoneNum 电话号码
     */
    public void openDiallPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 打开网络设置界面
     *
     * @param context 上下文
     */
    public void openSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.setAction(Settings.ACTION_SETTINGS);
        context.startActivity(mIntent);
    }
}