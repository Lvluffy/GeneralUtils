package com.luffy.utils.generallib;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 字符串-辅助工具
 */
public class StringUtils {

    private StringUtils() {
    }

    public static StringUtils getInstance() {
        return StringUtilsHolder.instance;
    }

    private static class StringUtilsHolder {
        private static final StringUtils instance = new StringUtils();
    }

    /**
     * 格式化简介内容
     *
     * @param str 需要处理的string
     * @return 处理后的数据
     */
    public String formatSummary(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replaceAll("【*", "")
                .replaceAll("】", "")
                .replaceAll("", "")
                .replaceAll("】", "")
                .replaceAll(" *", "")
                .replaceAll("——", "")
                .replaceAll("-", "")
                .replaceAll("<br />", "")
                .replaceAll("　　", "")
                .replaceAll("\r\n\r\n", "\n")
                .replace("\n  ", "")
                .replace("\n\n", "\n");
    }

    /**
     * @param input 输入的内容
     * @return 正则匹配取出中文, 返回数组
     */
    public List<String> getReplaceAll(String input) {
        if (input == null) {
            return null;
        }
        List<String> stringList = new ArrayList<>();
        Pattern p = Pattern.compile("([\u4e00-\u9fa5]+)");
        Matcher m = p.matcher(input);
        while (m.find()) {
            stringList.add(m.group(0).trim());
        }
        return stringList;
    }

    /**
     * 字符模糊处理（保留首尾，中间用指定字符替换）
     *
     * @param str 要处理的字符
     * @return 处理后的字符串
     */
    public String obscure(String str) {
        return obscure(str, null);
    }

    /**
     * 字符模糊处理（保留首尾，中间用指定字符替换）
     *
     * @param str        要处理的字符
     * @param repacleStr 替换字符（默认是*）
     * @return 处理后的字符串
     */
    public String obscure(String str, String repacleStr) {
        if (repacleStr == null || repacleStr.length() == 0 || "".equals(repacleStr)) {
            repacleStr = "*";
        }
        if (str.length() > 2) {
            StringBuilder content = new StringBuilder();
            for (int i = 1; i < str.length() - 1; i++) {
                content.append(repacleStr);
            }
            str = str.substring(0, 1) + content + str.substring(str.length() - 1, str.length());
        } else if (str.length() == 2) {
            str = str.substring(0, 1) + "*";
        }
        return str;
    }
}
