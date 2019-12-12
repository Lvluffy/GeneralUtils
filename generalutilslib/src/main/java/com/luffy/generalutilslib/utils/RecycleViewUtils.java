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
        private static final RecycleViewUtils mRecycleViewUtils;

        static {
            mRecycleViewUtils = new RecycleViewUtils();
        }
    }

    /**
     * 移动到指定位置
     *
     * @param mRecyclerView 控件
     * @param position      指定位置
     */
    public void moveToPosition(RecyclerView mRecyclerView, int position) {
        if (mRecyclerView == null)
            return;
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            linearManager.scrollToPositionWithOffset(position, 0);
            linearManager.setStackFromEnd(true);
        }
    }

    /**
     * 从下往上滚
     *
     * @param mRecyclerView 控件
     * @param position      指定位置
     */
    public void bottomToTopRoll(RecyclerView mRecyclerView, int position) {
        bottomToTopRoll(mRecyclerView, position, true);
    }

    /**
     * 从下往上滚
     *
     * @param mRecyclerView 控件
     * @param position      指定位置
     * @param hasRollEffect 是有有滑动效果
     */
    public void bottomToTopRoll(RecyclerView mRecyclerView, int position, boolean hasRollEffect) {
        /*判断控件是否有效*/
        if (mRecyclerView == null)
            return;
        /*选择滑动策略*/
        if (hasRollEffect) {
            /*带滑动效果*/
            mRecyclerView.smoothScrollToPosition(position);
        } else {
            /*不带滑动效果*/
            mRecyclerView.scrollToPosition(position);
        }
    }

    /**
     * 从上往下滚
     *
     * @param mRecyclerView 控件
     * @param position      指定位置
     */
    public void topToBottomRoll(RecyclerView mRecyclerView, int position) {
        /*判断控件是否有效*/
        if (mRecyclerView == null)
            return;
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            /*获取第一个可见的下标*/
            int fir = manager.findFirstVisibleItemPosition();
            /*判断滑动条件是否有效*/
            if (position - fir <= 0)
                return;
            /*获取滑动的高度*/
            int top = mRecyclerView.getChildAt(position - fir).getTop();
            mRecyclerView.scrollBy(0, top);
        }
    }
}