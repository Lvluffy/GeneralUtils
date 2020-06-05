package com.luffy.utils.generallib;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by lvlufei on 2020-06-05
 *
 * @name 碎片-辅助工具
 */
public class FragmentUtils {
    private FragmentUtils() {
    }

    public static FragmentUtils getInstance() {
        return FragmentUtilsHelper.mFragmentUtils;
    }

    private static class FragmentUtilsHelper {
        private static final FragmentUtils mFragmentUtils = new FragmentUtils();
    }

    public void replaceFragment(FragmentActivity activity, Fragment fragment) {
        replaceFragment(activity, fragment, android.R.id.content);
    }

    public void replaceFragment(FragmentActivity activity, Fragment fragment, int resId) {
        if (activity == null) {
            return;
        }
        //碎片管理器
        FragmentManager manager = activity.getSupportFragmentManager();
        //碎片事务
        FragmentTransaction transaction = manager.beginTransaction();
        //替换
        transaction.replace(resId, fragment, fragment.getClass().getName());
        //提交
        transaction.commitAllowingStateLoss();
    }
}
