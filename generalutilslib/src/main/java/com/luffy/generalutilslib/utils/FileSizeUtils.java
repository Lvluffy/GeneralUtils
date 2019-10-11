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
        private static FileSizeUtils mFileSizeUtils;

        static {
            mFileSizeUtils = new FileSizeUtils();
        }
    }

    /**
     * 转换指定文件夹或文件大小
     *
     * @param filePath
     * @return
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
     * @param file
     * @return
     * @throws Exception
     */
    public long getFileSizes(File file) throws Exception {
        long size = 0;
        if(file.exists()){
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    size = size + getFileSizes(files[i]);
                } else {
                    size = size + getFileSize(files[i]);
                }
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public long getFileSize(File file) throws Exception {
        if (file.exists()) {
            return file.length();
        }
        return 0;
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
     * @param file
     * @return
     */
    public boolean deleteFile(File file) {
        if (file != null && file.isDirectory()) {
            String[] children = file.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteFile(new File(file, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
