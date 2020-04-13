package com.luffy.generalutilslib.utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 文件大小-辅助工具
 */
public class FileSizeUtils {

    private FileSizeUtils() {
    }

    public static FileSizeUtils getInstance() {
        return FileSizeUtilsHelper.mFileSizeUtils;
    }

    private static class FileSizeUtilsHelper {
        private static final FileSizeUtils mFileSizeUtils = new FileSizeUtils();
    }

    /**
     * 转换指定文件夹或文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public String getFormatFileSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.getInstance().logError("获取失败!");
        }
        return FormatFileSize(blockSize);
    }

    /**
     * 获取指定文件夹大小
     *
     * @param file 文件
     * @return 文件大小
     * @throws Exception 异常
     */
    public long getFileSizes(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            File files[] = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    size = size + getFileSizes(file1);
                } else {
                    size = size + getFileSize(file1);
                }
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param file 文件
     * @return 文件大小
     * @throws Exception 异常
     */
    public long getFileSize(File file) throws Exception {
        return file.exists() ? file.length() : 0;
    }

    /**
     * 转换文件大小
     *
     * @param fileSize 文件大小
     */
    public String FormatFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileSize == 0) {
            return wrongSize;
        }
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return 是否删除
     */
    public boolean deleteFile(File file) {
        if (file != null && file.isDirectory()) {
            String[] children = file.list();
            for (String aChildren : children) {
                boolean success = deleteFile(new File(file, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
