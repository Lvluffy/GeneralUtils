package com.luffy.utils.generallib;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by lvlufei on 2020-08-13
 *
 * @name 通知-辅助工具
 */
public class NotificationUtils {

    /**
     * 发送通知
     *
     * @param context
     * @param channelId
     * @param channelName
     * @param notification
     * @param notificationId
     */
    public static void notifyNotification(Context context, String channelId, String channelName, Notification notification, int notificationId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }
        setChannel(notificationManager, channelId, channelName);
        notificationManager.notify(notificationId, notification);
    }

    /**
     * 取消通知
     *
     * @param context
     * @param notificationId
     */
    public static void cancelNotification(Context context, int notificationId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancel(notificationId);
        }
    }

    /**
     * 创建Builder
     *
     * @param context
     * @param channelId
     * @return
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static NotificationCompat.Builder createBuilder(Context context, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(context, channelId).setAutoCancel(true);
        } else {
            return new NotificationCompat.Builder(context).setAutoCancel(true);
        }
    }

    /**
     * 设置渠道
     *
     * @param notificationManager
     * @param channelId
     * @param channelName
     */
    @TargetApi(Build.VERSION_CODES.O)
    private static void setChannel(NotificationManager notificationManager, String channelId, String channelName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);
            if (channel == null) {
                channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
