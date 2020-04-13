package com.luffy.generalutilslib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 网络-辅助工具
 */
public class NetUtils {
    /**
     * 没有连接网络
     */
    private static final int NETWORK_NONE = -1;
    /**
     * 移动网络
     */
    private static final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    private static final int NETWORK_WIFI = 1;

    private NetUtils() {
    }

    public static NetUtils getInstance() {
        return NetUtilsHelper.mNetUtils;
    }

    private static class NetUtilsHelper {
        private static final NetUtils mNetUtils = new NetUtils();
    }

    /**
     * @param mContext 上下文
     * @return 获取当前网络连接的类型
     */
    public int getConnectedType(Context mContext) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != mConnectivityManager) {
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return NETWORK_MOBILE;
                } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    return NETWORK_WIFI;
                }
            }
        }
        return NETWORK_NONE;
    }

    /**
     * @param mContext mContext
     * @return 网络是否连接
     */
    public boolean isConnected(Context mContext) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != mConnectivityManager) {
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param mContext mContext
     * @return 是否是Wifi连接
     */
    public boolean isWifi(Context mContext) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != mConnectivityManager) {
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (null != info && info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param mContext mContext
     * @return 是否是手机连接
     */
    public boolean isMobile(Context mContext) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != mConnectivityManager) {
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (null != info && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param activity 打开网络设置界面
     */
    public void openSetting(Activity activity) {
        Intent mIntent = new Intent();
        mIntent.setAction(Settings.ACTION_SETTINGS);
        activity.startActivity(mIntent);
    }
}
