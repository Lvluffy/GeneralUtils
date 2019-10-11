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
        private static ResourceUtils mResourceUtils;

        static {
            mResourceUtils = new ResourceUtils();
        }
    }

    /**
     * 获取String数组
     *
     * @param context
     * @param arrayResourceId
     * @return
     */
    public String[] getStringArray(Context context, int arrayResourceId) {
        Resources resources = context.getResources();
        String[] imgUrls = resources.getStringArray(arrayResourceId);
        return imgUrls;
    }
}
