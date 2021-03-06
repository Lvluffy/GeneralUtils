package com.luffy.utils.netlib;

import android.content.Context;

/**
 * Created by lvlufei on 2020-04-29
 *
 * @name 网络处理调用者
 */
public class NetHandleInvoker {

    private NetHandleInvoker() {
    }

    public static void gotoHint(Context context, INetHandle iNetHandle) {
        if (iNetHandle != null) {
            if (NetUtils.isConnected(context)) {
                iNetHandle.hasNetwork();
                if (NetUtils.isWifi(context)) {
                    iNetHandle.wifiNetwork();
                } else if (NetUtils.isMobile(context)) {
                    iNetHandle.mobileNetwork();
                }
            } else {
                iNetHandle.noNetwork();
            }
        }
    }
}
