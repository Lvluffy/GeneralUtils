package com.luffy.generalutilslib.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lvlufei on 2019/6/28
 *
 * @name RecycleView-辅助工具
 * @desc
 */
public class RecycleViewUtils {

    private RecycleViewUtils() {
    }

    public static RecycleViewUtils getInstance() {
        return RecycleViewUtilsHelper.mRecycleViewUtils;
    }

    private static class RecycleViewUtilsHelper {
        private static RecycleViewUtils mRecycleViewUtils;

        static {
            mRecycleViewUtils = new RecycleViewUtils();
        }
    }

    /**
     * 移动到指定位置
     *
     * @param recyclerView 控件
     * @param position     指定位置
     */
    public void moveToPosition(RecyclerView recyclerView, int position) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            linearManager.scrollToPositionWithOffset(position, 0);
            linearManager.setStackFromEnd(true);
        }
    }
}