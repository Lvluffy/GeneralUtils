package com.luffy.generalutilslib.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 内存-辅助工具
 */
public class MemoryUtils {

    private MemoryUtils() {
    }

    public static MemoryUtils getInstance() {
        return MemoryUtilsHelper.mMemoryUtils;
    }

    private static class MemoryUtilsHelper {
        private static final MemoryUtils mMemoryUtils;

        static {
            mMemoryUtils = new MemoryUtils();
        }
    }

    /**
     * 获得SD卡总大小
     *
     * @param context 上下文
     * @return SD卡总大小
     */
    public String getSDTotalSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
        } else {
            blockSize = stat.getBlockSize();
        }
        long totalBlocks = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            totalBlocks = stat.getBlockCountLong();
        } else {
            totalBlocks = stat.getBlockCount();
        }
        return Formatter.formatFileSize(context, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @param context 上下文
     * @return sd卡剩余容量
     */
    public String getSDAvailableSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
        } else {
            blockSize = stat.getBlockSize();
        }
        long availableBlocks = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            availableBlocks = stat.getAvailableBlocks();
        }
        return Formatter.formatFileSize(context, blockSize * availableBlocks);
    }

    /**
     * 获得机身内存总大小
     *
     * @param context 上下文
     * @return 机身内存总大小
     */
    public String getRomTotalSize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
        } else {
            blockSize = stat.getBlockSize();
        }
        long totalBlocks = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            totalBlocks = stat.getBlockCountLong();
        } else {
            totalBlocks = stat.getBlockCount();
        }
        return Formatter.formatFileSize(context, blockSize * totalBlocks);
    }

    /**
     * 获得机身可用内存
     *
     * @param context 上下文
     * @return 机身可用内存
     */
    public String getRomAvailableSize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
        } else {
            blockSize = stat.getBlockSize();
        }
        long availableBlocks = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            availableBlocks = stat.getAvailableBlocks();
        }
        return Formatter.formatFileSize(context, blockSize * availableBlocks);
    }
}
