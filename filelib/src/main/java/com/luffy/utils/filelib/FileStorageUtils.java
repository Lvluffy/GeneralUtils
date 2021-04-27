package com.luffy.utils.filelib;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 存储-辅助工具
 * @desc 内部存储目录
 * 1、data/data/包名/shared_prefs（/data/user/0/包名/shared_prefs）
 * 2、data/data/包名/databases（/data/user/0/包名/databases）
 * 3、data/data/包名/files（/data/user/0/包名/files）
 * 4、data/data/包名/cache（/data/user/0/包名/cache）
 * 外部存储目录
 * ——共有目录（九大共有目录）
 * ——私有目录
 * 1、storage/sdcard/Android/data/包名/files
 * ---1、storage/sdcard/Android/data/包名/files/Alarms（闹钟）
 * ---2、storage/sdcard/Android/data/包名/files/Dcim
 * ---3、storage/sdcard/Android/data/包名/files/Documents（文档）
 * ---4、storage/sdcard/Android/data/包名/files/Downloads（下载）
 * ---5、storage/sdcard/Android/data/包名/files/Movies（电影）
 * ---6、storage/sdcard/Android/data/包名/files/Music（音乐）
 * ---7、storage/sdcard/Android/data/包名/files/Notifications（通知）
 * ---8、storage/sdcard/Android/data/包名/files/Pictures（图片）
 * ---9、storage/sdcard/Android/data/包名/files/Podcasts（博客）
 * ---10、storage/sdcard/Android/data/包名/files/Ringtones（手机铃声）
 * 2、storage/sdcard/Android/data/包名/cache
 */
public class FileStorageUtils {

    /**
     * 获取内部文件路径
     *
     * @param context 上下文
     * @return data/data/包名/files（/data/user/0/包名/files）
     */
    public static String getInternalFilesPath(Context context) {
        return context.getFilesDir().getPath();
    }

    /**
     * 获取内部缓存路径
     *
     * @param context 上下文
     * @return data/data/包名/cache（/data/user/0/包名/cache）
     */
    public static String getInternalCachePath(Context context) {
        return context.getCacheDir().getPath();
    }

    /**
     * 获取外部闹钟文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Alarms
     */
    public static String getExternalFilesAlarmsPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_ALARMS).getPath();
    }

    /**
     * 获取外部Dcim文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Dcim
     */
    public static String getExternalFilesDcimPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath();
    }

    /**
     * 获取外部文档文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Documents
     */
    public static String getExternalFilesDocumentsPath(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            return context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath();
        return null;
    }

    /**
     * 获取外部下载文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Downloads
     */
    public static String getExternalFilesDownloadsPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
    }

    /**
     * 获取外部电影文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Movies
     */
    public static String getExternalFilesMoviesPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath();
    }

    /**
     * 获取外部音乐文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Music
     */
    public static String getExternalFilesMusicPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath();
    }

    /**
     * 获取外部通知文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Notifications
     */
    public static String getExternalFilesNotificationsPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getPath();
    }

    /**
     * 获取外部图片文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Podcasts
     */
    public static String getExternalFilesPicturesPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
    }

    /**
     * 获取外部博客文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Podcasts
     */
    public static String getExternalFilesPodcastsPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getPath();
    }

    /**
     * 获取外部手机铃声文件路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/files/Ringtones
     */
    public static String getExternalFilesRingtonesPath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getPath();
    }

    /**
     * 获取外部缓存路径
     *
     * @param context 上下文
     * @return storage/sdcard/Android/data/包名/cache
     */
    public static String getExternalCachePath(Context context) {
        return context.getExternalCacheDir().getPath();
    }
}
