package com.luffy.generalutilslib.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by lvlufei on 2018/7/24
 *
 * @desc 渠道-辅助工具
 */
public class ChannelUtils {
    private ChannelUtils() {
    }

    public static ChannelUtils getInstance() {
        return ChannelUtils.ChannelUtilsHelper.mChannelUtils;
    }

    private static class ChannelUtilsHelper {
        private static ChannelUtils mChannelUtils;

        static {
            mChannelUtils = new ChannelUtils();
        }
    }

    /**
     * 获取渠道
     *
     * @param mContext 上下文对象
     * @param key      application中指定的meta-data，调用方法时key就是UMENG_CHANNEL
     * @return
     */
    public String getAppMetaData(Context mContext, String key) {
        if (mContext == null || !ValidUtils.getInstance().isValid(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = mContext.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }
}
