package com.luffy.utils.generallib;

import android.os.Handler;

/**
 * Created by lvlufei on 2019/2/27
 *
 * @name 快速点击-辅助工具
 */
public class QuickClickUtils {
    private QuickClickUtils() {
    }

    public static QuickClickUtils getInstance() {
        return QuickClickUtilsHolder.instance;
    }

    private static class QuickClickUtilsHolder {
        private static final QuickClickUtils instance = new QuickClickUtils();
    }

    /*记录点击的次数*/
    private int mClickTimes = 0;
    private final MyHandler handler = new MyHandler();
    /*点击间距的有效时间处理*/
    private final Runnable mDebugClickRunnable = new Runnable() {
        @Override
        public void run() {
            mClickTimes = 0;
        }
    };

    /**
     * 快速点击
     *
     * @param times              点击次数
     * @param quickClickCallback 执行回调
     */
    public void quickClick(int times, QuickClickCallback quickClickCallback) {
        //点击数量增加
        mClickTimes++;
        //移除上次点击间距的有效时间处理事件
        handler.removeCallbacks(mDebugClickRunnable);
        //如果点击大于指定次，则可以执行
        if (mClickTimes == times && quickClickCallback != null) {
            mClickTimes = 0;
            quickClickCallback.onExecute();
            return;
        }
        //点击间距的有效时间
        handler.postDelayed(mDebugClickRunnable, 1000);
    }

    public interface QuickClickCallback {
        void onExecute();
    }

    private static class MyHandler extends Handler{

    }
}
