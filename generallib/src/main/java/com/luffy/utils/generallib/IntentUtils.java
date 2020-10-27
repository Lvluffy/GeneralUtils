package com.luffy.utils.generallib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 外部跳转-辅助工具
 */
public class IntentUtils {

    private IntentUtils() {
    }

    public static IntentUtils getInstance() {
        return IntentUtilsHolder.instance;
    }

    /**
     * 静态内部类实现单例
     */
    private static class IntentUtilsHolder {
        private static final IntentUtils instance = new IntentUtils();
    }

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
        if (action == null) {
            throw new NullPointerException("Action cannot be empty");
        }
        Intent intent = new Intent(action);
        if (intentExtra != null) {
            context.startActivity(intentExtra.putExtra(intent));
        } else {
            context.startActivity(intent);
        }
    }

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
        if (target == null) {
            throw new NullPointerException("Target cannot be empty");
        }
        Intent intent = new Intent(context, target);
        if (context instanceof Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (intentExtra != null) {
            context.startActivity(intentExtra.putExtra(intent));
        } else {
            context.startActivity(intent);
        }
    }

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
        if (target == null) {
            throw new NullPointerException("Target cannot be empty");
        }
        Intent intent = new Intent(context, target);
        if (intentExtra != null) {
            context.startActivityForResult(intentExtra.putExtra(intent), requestCode);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

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
        if (target == null) {
            throw new NullPointerException("Target cannot be empty");
        }
        Intent intent = new Intent(context.getActivity(), target);
        if (intentExtra != null) {
            context.startActivityForResult(intentExtra.putExtra(intent), requestCode);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

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
        if (target == null) {
            throw new NullPointerException("Target cannot be empty");
        }
        Intent intent = new Intent(context.getActivity(), target);
        if (intentExtra != null) {
            context.startActivityForResult(intentExtra.putExtra(intent), requestCode);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 打开指定应用商店中指定应用详情
     *
     * @param context           上下文对象
     * @param appPackageName    应用包名
     * @param marketPackageName 应用商店包名
     */
    public void openAppDetail(Context context, String appPackageName, String marketPackageName) {
        if (TextUtils.isEmpty(appPackageName) || TextUtils.isEmpty(marketPackageName)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
        intent.setPackage(marketPackageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public interface IntentExtra {
        Intent putExtra(Intent intent);
    }
}