package com.luffy.generalutilslib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 手机-辅助工具
 */
public class MobileUtils {

    private MobileUtils() {
    }

    public static MobileUtils getInstance() {
        return MobileUtilsHelper.mMobileUtils;
    }

    private static class MobileUtilsHelper {
        private static final MobileUtils mMobileUtils = new MobileUtils();
    }

    /**
     * 替换手机号中间4位数
     *
     * @param mobilePhone 手机号
     * @return 处理后的字符串
     */
    public String hintMobile(String mobilePhone) {
        return mobilePhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 字符串是否为手机号码
     *
     * @param str 手机号
     * @return 是否为手机号码
     */
    public boolean isMobile(String str) {
        // ^：首字母
        // [1]：第一个数字必须是1
        // [0-9]：第二个数字为0-9之间
        // +：表示至少一个[0-9]
        // \\d：表示数字
        // {9}：表示9个,就是9个数字。
        Pattern pattern = Pattern.compile("^[1][0-9]+\\d{9}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
