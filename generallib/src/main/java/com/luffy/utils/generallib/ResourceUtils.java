package com.luffy.utils.generallib;

import android.content.Context;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 资源获取-辅助工具
 */
public class ResourceUtils {

    private ResourceUtils() {
    }

    public static ResourceUtils getInstance() {
        return ResourceUtilsHolder.instance;
    }

    private static class ResourceUtilsHolder {
        private static final ResourceUtils instance = new ResourceUtils();
    }

    /**
     * 获取String数组
     *
     * @param context         上下文
     * @param arrayResourceId 数组资源ID
     * @return 字符串数组
     */
    public String[] getStringArray(Context context, int arrayResourceId) {
        return context.getResources().getStringArray(arrayResourceId);
    }
}
