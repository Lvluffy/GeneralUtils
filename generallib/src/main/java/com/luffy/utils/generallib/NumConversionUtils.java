package com.luffy.utils.generallib;

import java.math.BigDecimal;

/**
 * Created by lvlufei on 2018/6/27
 *
 * @name 数字转换
 */
public class NumConversionUtils {

    /**
     * 保留两位小数
     *
     * @param num      数字值
     * @param position 保留数量
     * @return 处理后的数据
     */
    public static float retainDecimal(float num, int position) {
        BigDecimal mBigDecimal = new BigDecimal(num);
        mBigDecimal = mBigDecimal.setScale(position, BigDecimal.ROUND_HALF_UP);//四舍五入
        return mBigDecimal.floatValue();
    }
}
