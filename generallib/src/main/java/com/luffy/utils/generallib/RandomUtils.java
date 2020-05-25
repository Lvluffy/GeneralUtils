package com.luffy.utils.generallib;

import java.util.Random;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 随机数-辅助工具
 */
public class RandomUtils {

    private RandomUtils() {
    }

    public static RandomUtils getInstance() {
        return RandomUtilsHelper.mRandomUtils;
    }

    private static class RandomUtilsHelper {
        private static final RandomUtils mRandomUtils = new RandomUtils();
    }

    /**
     * 获取指定长度的随机数字符串
     *
     * @param length 长度
     * @return 字符串
     */
    public String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
