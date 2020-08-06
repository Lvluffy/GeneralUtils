package com.luffy.utils.generallib;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by lvlufei on 2020-04-10
 *
 * @name 异常关闭-辅助工具
 */
public class ExceptionCloseUtils {

    private ExceptionCloseUtils() {

    }

    public static ExceptionCloseUtils getInstance() {
        return ExceptionCloseUtilsHolder.instance;
    }

    private static class ExceptionCloseUtilsHolder {
        private static final ExceptionCloseUtils instance = new ExceptionCloseUtils();
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
