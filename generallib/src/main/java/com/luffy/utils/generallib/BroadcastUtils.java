package com.luffy.utils.generallib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * Created by lvlufei on 2021/4/20
 * <p>
 * 广播-辅助工具
 */
public class BroadcastUtils {

    /**
     * 发送广播
     *
     * @param context
     * @param action
     * @param extras
     */
    public static void sendBroadcast(Context context, String action, Bundle extras) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(action);
        // 突破限制，实现所有应用都可以收到广播
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.sendBroadcast(intent);
    }

    /**
     * 注册广播
     *
     * @param context
     * @param action
     * @param receiver
     */
    public static void registerReceiver(Context context, String action, BroadcastReceiver receiver) {
        if (context == null || action == null || receiver == null) {
            return;
        }
        IntentFilter filter = new IntentFilter(action);
        context.registerReceiver(receiver, filter);
    }

    /**
     * 注销广播
     *
     * @param context
     * @param receiver
     */
    public static void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        if (context == null || receiver == null) {
            return;
        }
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
