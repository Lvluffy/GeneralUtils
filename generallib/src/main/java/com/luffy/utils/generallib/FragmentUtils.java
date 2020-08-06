package com.luffy.utils.generallib;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/**
 * Created by lvlufei on 2020-06-05
 *
 * @name 碎片-辅助工具
 */
public class FragmentUtils {
    private FragmentUtils() {
    }

    public static FragmentUtils getInstance() {
        return FragmentUtilsHolder.instance;
    }

    private static class FragmentUtilsHolder {
        private static final FragmentUtils instance = new FragmentUtils();
    }

    public void replaceFragment(android.app.Activity activity, android.app.Fragment target) {
        replaceFragment(activity, android.R.id.content, target);
    }

    public void replaceFragment(android.app.Activity activity, int resId, android.app.Fragment target) {
        replaceFragment(activity, resId, target, null);
    }

    /**
     * 替换碎片
     *
     * @param activity      上下文对象
     * @param resId         目标ID
     * @param target        目标对象
     * @param fragmentExtra 参数接口传参
     */
    public void replaceFragment(android.app.Activity activity, int resId, android.app.Fragment target, FragmentExtra fragmentExtra) {
        if (activity == null) return;
        if (fragmentExtra != null) {
            target.setArguments(fragmentExtra.putExtra());
        }
        //碎片管理器
        android.app.FragmentManager manager = activity.getFragmentManager();
        //碎片事务
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        //替换
        transaction.replace(resId, target, target.getClass().getName());
        //提交
        transaction.commitAllowingStateLoss();
    }

    public void replaceFragment(android.support.v4.app.FragmentActivity activity, android.support.v4.app.Fragment target) {
        replaceFragment(activity, android.R.id.content, target);
    }

    public void replaceFragment(android.support.v4.app.FragmentActivity activity, int resId, android.support.v4.app.Fragment target) {
        replaceFragment(activity, resId, target, null);
    }

    /**
     * 替换碎片
     *
     * @param activity      上下文对象
     * @param resId         目标ID
     * @param target        目标对象
     * @param fragmentExtra 参数接口传参
     */
    public void replaceFragment(android.support.v4.app.FragmentActivity activity, int resId, android.support.v4.app.Fragment target, FragmentExtra fragmentExtra) {
        if (activity == null) return;
        if (fragmentExtra != null) {
            target.setArguments(fragmentExtra.putExtra());
        }
        //碎片管理器
        android.support.v4.app.FragmentManager manager = activity.getSupportFragmentManager();
        //碎片事务
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        //替换
        transaction.replace(resId, target, target.getClass().getName());
        //提交
        transaction.commitAllowingStateLoss();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void replaceFragment(android.app.Fragment fragment, android.app.Fragment target) {
        replaceFragment(fragment, android.R.id.content, target);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void replaceFragment(android.app.Fragment fragment, int resId, android.app.Fragment target) {
        replaceFragment(fragment, resId, target, null);
    }

    /**
     * 替换碎片
     *
     * @param fragment      上下文对象
     * @param resId         目标ID
     * @param target        目标对象
     * @param fragmentExtra 参数接口传参
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void replaceFragment(android.app.Fragment fragment, int resId, android.app.Fragment target, FragmentExtra fragmentExtra) {
        if (fragment == null) return;
        if (fragmentExtra != null) {
            target.setArguments(fragmentExtra.putExtra());
        }
        //碎片管理器
        android.app.FragmentManager manager = fragment.getChildFragmentManager();
        //碎片事务
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        //替换
        transaction.replace(resId, target, target.getClass().getName());
        //提交
        transaction.commitAllowingStateLoss();
    }

    public void replaceFragment(android.support.v4.app.Fragment fragment, android.support.v4.app.Fragment target) {
        replaceFragment(fragment, android.R.id.content, target);
    }

    public void replaceFragment(android.support.v4.app.Fragment fragment, int resId, android.support.v4.app.Fragment target) {
        replaceFragment(fragment, resId, target, null);
    }

    /**
     * 替换碎片
     *
     * @param fragment      上下文对象
     * @param resId         目标ID
     * @param target        目标对象
     * @param fragmentExtra 参数接口传参
     */
    public void replaceFragment(android.support.v4.app.Fragment fragment, int resId, android.support.v4.app.Fragment target, FragmentExtra fragmentExtra) {
        if (fragment == null) return;
        if (fragmentExtra != null) {
            target.setArguments(fragmentExtra.putExtra());
        }
        //碎片管理器
        android.support.v4.app.FragmentManager manager = fragment.getChildFragmentManager();
        //碎片事务
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        //替换
        transaction.replace(resId, target, target.getClass().getName());
        //提交
        transaction.commitAllowingStateLoss();
    }

    public interface FragmentExtra {
        Bundle putExtra();
    }
}
