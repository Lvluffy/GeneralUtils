package com.luffy.utils.generallib;

import java.util.Collection;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 验证-辅助工具
 */
public class ValidUtils {

    private ValidUtils() {
    }

    public static ValidUtils getInstance() {
        return ValidUtilsHelper.mValidUtils;
    }

    private static class ValidUtilsHelper {
        private static final ValidUtils mValidUtils = new ValidUtils();
    }

    /**
     * 判断string的有效性
     *
     * @param string 字符串
     * @return 是否有效
     */
    public boolean isValid(String string) {
        return !(string == null || "".equals(string.trim()) || "null".equals(string) || string.length() == 0);
    }

    /**
     * 判断对象的有效性
     *
     * @param obj 对象
     * @return 是否有效
     */
    public boolean isValid(Object obj) {
        return obj != null;
    }

    /**
     * 判断集合的有效性
     *
     * @param collection 集合
     * @return 是否有效
     */
    public boolean isValid(Collection collection) {
        return !(collection == null || collection.isEmpty());
    }
}