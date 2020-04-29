package com.luffy.netlib;

import android.content.Context;

/**
 * Created by lvlufei on 2020-04-29
 *
 * @name 网络处理调用者
 * @desc
 */
public class NetHandleInvoker {
    public static void gotoHint(Context context, INetHandle iNetHandle) {
        if (iNetHandle != null) {
            if (NetUtils.getInstance().isConnected(context)) {
                iNetHandle.hasNetwork();
                if (NetUtils.getInstance().isWifi(context)) {
                    iNetHandle.wifiNetwork();
                } else if (NetUtils.getInstance().isMobile(context)) {
                    iNetHandle.mobileNetwork();
                }
            } else {
                iNetHandle.noNetwork();
            }
        }
    }
}
