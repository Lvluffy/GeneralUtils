package com.luffy.utils.generallib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2019/9/16
 *
 * @name 正则匹配-辅助工具
 */
public class PatternUtils {
    private PatternUtils() {
    }

    public static PatternUtils getInstance() {
        return PatternUtilsHolder.instance;
    }

    private static class PatternUtilsHolder {
        private static final PatternUtils instance = new PatternUtils();
    }

    /**
     * 通过正则表达式的方式获取字符串中指定字符的个数
     *
     * @param text    指定的字符串
     * @param contain 匹配的内容
     * @return 指定字符的个数
     */
    public int patternStrCount(String text, String contain) {
        // 根据指定的字符构建正则
        // Pattern.CASE_INSENSITIVE 忽略大小写
        Pattern pattern = Pattern.compile(contain, Pattern.CASE_INSENSITIVE);
        // 构建字符串和正则的匹配
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        // 循环依次往下匹配
        while (matcher.find()) { // 如果匹配,则数量+1
            count++;
        }
        return count;
    }
}
