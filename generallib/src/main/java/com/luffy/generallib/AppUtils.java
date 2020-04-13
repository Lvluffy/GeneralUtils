package com.luffy.generallib;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc APP-辅助工具
 */
public class AppUtils {

    private AppUtils() {
    }

    public static AppUtils getInstance() {
        return AppUtilsHelper.mAppUtils;
    }

    private static class AppUtilsHelper {
        private static final AppUtils mAppUtils = new AppUtils();
    }

    /**
     * 获取应用程序-包名
     *
     * @param context 上下文
     * @return 应用包名
     */
    public String getPackName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            //得到当前应用
            if (info.pid == pid)
                //返回包名
                return info.processName;
        }
        return "";
    }

    /**
     * 获取应用程序-名称
     *
     * @param context 上下文
     * @return 应用名称
     */
    public String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-名称-通过包名
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用程序名称
     */
    public String getAppName(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-图标
     *
     * @param context 上下文
     * @return 应用图标
     */
    public Drawable getApkIcon(Context context) {
        try {
            PackageManager mPackageManager = context.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo mApplicationInfo = mPackageInfo.applicationInfo;
            mApplicationInfo.sourceDir = context.getApplicationContext().getFilesDir().getAbsolutePath();
            mApplicationInfo.publicSourceDir = context.getApplicationContext().getFilesDir().getAbsolutePath();
            return mApplicationInfo.loadIcon(mPackageManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-图标-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用图标
     */
    public Drawable getAppIcon(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return packageManager.getApplicationIcon(applicationInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-版本名称
     *
     * @param context 上下文
     * @return 应用版本
     */
    public String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-版本名称-通过包名
     *
     * @param context 上下文
     * @return 应用版本
     */
    public String getVersionName(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo mPackageInfo = packageManager.getPackageInfo(packageName, 0);
            return mPackageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-版本号
     *
     * @param context 上下文
     * @return 应用版本
     */
    public int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取应用程序-版本号-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用版本
     */
    public int getVersionCode(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取应用程序-APK大小
     *
     * @param context 上下文
     * @return 应用包大小
     */
    public String getApkSize(Context context) {
        try {
            PackageManager mPackageManager = context.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo mApplicationInfo = mPackageInfo.applicationInfo;
            // 获取应用的路径
            String dir = mApplicationInfo.publicSourceDir;
            // 获取应用的大小
            int size = (int) new File(dir).length();
            // 处理应用的大小
            BigDecimal bd = new BigDecimal((double) size / (1024 * 1024));
            BigDecimal apkSize = bd.setScale(2, BigDecimal.ROUND_DOWN);
            return apkSize.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序-APK大小-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用包大小
     */
    public String getApkSize(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo mApplicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            // 获取应用的路径
            String dir = mApplicationInfo.publicSourceDir;
            // 获取应用的大小
            int size = (int) new File(dir).length();
            // 处理应用的大小
            BigDecimal bd = new BigDecimal((double) size / (1024 * 1024));
            BigDecimal apkSize = bd.setScale(2, BigDecimal.ROUND_DOWN);
            return apkSize.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否安装了指定的应用程序
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 是否安装
     */
    public boolean isInstallApp(Context context, String packageName) {
        try {
            PackageManager mPackageManager = context.getPackageManager();
            List<PackageInfo> mPackageInfos = mPackageManager.getInstalledPackages(PackageManager.PERMISSION_GRANTED);
            for (PackageInfo info : mPackageInfos) {
                if (info.packageName.equalsIgnoreCase(packageName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取指定应用的签名信息
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用签名信息
     */
    public Signature getSign(Context context, String packageName) {
        try {
            PackageManager mPackageManager = context.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return mPackageInfo.signatures[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定应用的签名信息MD5
     *
     * @param ctx         上下文
     * @param packageName 应用包名
     * @return 应用MD5签名信息
     */
    public String getMD5(Context ctx, String packageName) {
        try {
            PackageManager mPackageManager = ctx.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature signatures = mPackageInfo.signatures[0];
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(signatures.toByteArray());
            byte[] digest = mDigest.digest();
            return toHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();
        int len = block.length;
        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

    private void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }
}