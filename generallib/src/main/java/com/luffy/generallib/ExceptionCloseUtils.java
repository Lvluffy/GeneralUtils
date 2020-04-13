package com.luffy.generallib;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by lvlufei on 2020-04-10
 *
 * @name 异常关闭-辅助工具
 * @desc
 */
public class ExceptionCloseUtils {

    private ExceptionCloseUtils() {

    }

    public static ExceptionCloseUtils getInstance() {
        return ExceptionCloseUtilsHelper.mExceptionCloseUtils;
    }

    private static class ExceptionCloseUtilsHelper {
        private static final ExceptionCloseUtils mExceptionCloseUtils = new ExceptionCloseUtils();
    }

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
