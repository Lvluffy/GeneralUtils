package com.luffy.generalutilslib.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 资源获取-辅助工具
 */
public class ResourceUtils {

    private ResourceUtils() {
    }

    public static ResourceUtils getInstance() {
        return ResourceUtilsHelper.mResourceUtils;
    }

    private static class ResourceUtilsHelper {
        private static final ResourceUtils mResourceUtils;

        static {
            mResourceUtils = new ResourceUtils();
        }
    }

    /**
     * 获取String数组
     *
     * @param context         上下文
     * @param arrayResourceId 数组资源ID
     * @return 字符串数组
     */
    public String[] getStringArray(Context context, int arrayResourceId) {
        Resources resources = context.getResources();
        return resources.getStringArray(arrayResourceId);
    }
}
