package com.luffy.utils.generallib;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 单位转换-辅助工具
 */
public class DensityUtils {

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpVal   dp
     * @return px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spVal   sp
     * @return px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxVal   px
     * @return dp
     */
    public static int px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context 上下文
     * @param pxVal   px
     * @return sp
     */
    public static int px2sp(Context context, float pxVal) {
        return (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}