package com.luffy.utils.generallib;

import android.content.Context;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 资源获取-辅助工具
 */
public class ResourceUtils {

    /**
     * 获取String数组
     *
     * @param context         上下文
     * @param arrayResourceId 数组资源ID
     * @return 字符串数组
     */
    public static String[] getStringArray(Context context, int arrayResourceId) {
        return context.getResources().getStringArray(arrayResourceId);
    }

    public static int getStringIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "string");
    }

    public static int getStyleIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "style");
    }

    public static int getDimenIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "dimen");
    }

    public static int getDrawableIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "drawable");
    }

    public static int getAnimatorIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "animator");
    }

    public static int getId(Context context, String resName) {
        return getIdentifier(context, resName, "id");
    }

    public static int getColorIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "color");
    }

    public static int getLayoutIdentifier(Context context, String resName) {
        return getIdentifier(context, resName, "layout");
    }

    public static String getString(Context context, String resName) {
        String value = "";
        int id = getStringIdentifier(context, resName);
        if (id != 0) {
            value = context.getString(id);
        }
        return value;
    }

    private static int getIdentifier(Context context, String resName, String type) {
        return context.getResources().getIdentifier(resName, type, context.getPackageName());
    }
}
