package com.luffy.generalutilslib.utils;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 类-辅助工具
 */
public class ClassUtils {

    private ClassUtils() {
    }

    public static ClassUtils getInstance() {
        return ClassUtilsHelper.mClassUtils;
    }

    private static class ClassUtilsHelper {
        private static ClassUtils mClassUtils;

        static {
            mClassUtils = new ClassUtils();
        }
    }

    /**
     * 获取包名类名
     *
     * @param cla
     * @return com.example.test.Main
     */
    public String getClassName(Class cla) {
        return cla.getName();
    }


    /**
     * 获取类名
     *
     * @param cla
     * @return
     */
    public String getClassSimpleName(Class cla) {
        return cla.getSimpleName();
    }
}
