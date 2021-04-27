package com.luffy.utils.generallib;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 类-辅助工具
 */
public class ClassUtils {

    /**
     * 获取包名类名
     *
     * @param cla 类对象
     * @return 包名类名（com.example.test.Main）
     */
    public static String getClassName(Class cla) {
        return cla.getName();
    }


    /**
     * 获取类名
     *
     * @param cla 类对象
     * @return 类名（Main）
     */
    public static String getClassSimpleName(Class cla) {
        return cla.getSimpleName();
    }
}
