package com.luffy.generalutilslib.utils;

import java.math.BigDecimal;

/**
 * Created by lvlufei on 2018/6/27
 *
 * @desc 数字转换
 */

public class NumConversionUtils {

    private NumConversionUtils() {
    }

    public static NumConversionUtils getInstance() {
        return NumConversionUtils.NumConversionUtilsHelper.mNumConversionUtils;
    }

    /**
     * 静态内部类实现单例
     */
    private static class NumConversionUtilsHelper {
        private static final NumConversionUtils mNumConversionUtils = new NumConversionUtils();
    }

    /**
     * 保留两位小数
     *
     * @param num      数字值
     * @param position 保留数量
     * @return 处理后的数据
     */
    public float retainDecimal(float num, int position) {
        BigDecimal mBigDecimal = new BigDecimal(num);
        mBigDecimal = mBigDecimal.setScale(position, BigDecimal.ROUND_HALF_UP);//四舍五入
        return mBigDecimal.floatValue();
    }
}
