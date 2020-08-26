package com.luffy.utils.netlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 网络-辅助工具
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
        return NetUtilsHolder.instance;
    }

    private static class NetUtilsHolder {
        private static final NetUtils instance = new NetUtils();
    }

    /**
     * 获取当前网络连接的类型
     *
     * @param context 上下文
     * @return
     */
    public int getConnectedType(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
     * 网络是否连接
     *
     * @param context 上下文
     * @return
     */
    public boolean isConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
     * 是否是Wifi连接
     *
     * @param context
     * @return
     */
    public boolean isWifi(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != mConnectivityManager) {
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (null != info && info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是手机连接
     *
     * @param context
     * @return
     */
    public boolean isMobile(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != mConnectivityManager) {
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (null != info && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

}
