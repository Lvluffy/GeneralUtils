package com.luffy.utils.generallib;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 金额转换-辅助工具
 */
public class MoneyFormatUtils {

    /**
     * double转String,保留小数点后两位（四舍五入）
     *
     * @param money 金额
     * @return 处理后的金额
     */
    public static String doubleToString(double money) {
        DecimalFormat mDecimalFormat = new DecimalFormat("0.00");
        mDecimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return mDecimalFormat.format(money);
    }

    /**
     * double转String,保留小数点后几位，自己定义（四舍五入）
     *
     * @param money   金额
     * @param pattern 模式
     * @return 处理后的金额
     */
    public static String doubleToString(double money, String pattern) {
        DecimalFormat mDecimalFormat = new DecimalFormat(pattern);
        mDecimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return mDecimalFormat.format(money);
    }

    /**
     * double转String,保留小数点后两位（不四舍五入）
     *
     * @param money 金额
     * @return 处理后的金额
     */
    public static String doubleToStringRoundingNo(double money) {
        DecimalFormat mDecimalFormat = new DecimalFormat("0.00");
        mDecimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return mDecimalFormat.format(money);
    }

    /**
     * double转String,保留小数点后几位，自己定义（不四舍五入）
     *
     * @param money   金额
     * @param pattern 模式
     * @return 处理后的金额
     */
    public static String doubleToStringRoundingNo(double money, String pattern) {
        DecimalFormat mDecimalFormat = new DecimalFormat(pattern);
        mDecimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return mDecimalFormat.format(money);
    }
}
