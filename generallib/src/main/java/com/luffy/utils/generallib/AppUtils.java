package com.luffy.utils.generallib;

import android.app.ActivityManager;
import android.content.Context;
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
    public String getAppPackName(Context context) {
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

    public String getAppName(Context context) {
        return getAppName(context, context.getPackageName());
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
            return context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public Drawable getAppIcon(Context context) {
        return getAppIcon(context, context.getPackageName());
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
            return context.getPackageManager().getApplicationIcon(context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    /**
     * 获取应用程序-版本名称-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用版本
     */
    public String getAppVersionName(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public int getAppVersionCode(Context context) {
        return getAppVersionCode(context, context.getPackageName());
    }

    /**
     * 获取应用程序-版本号-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用版本
     */
    public int getAppVersionCode(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    public String getAppSize(Context context) {
        return getAppSize(context, context.getPackageName());
    }

    /**
     * 获取应用程序-APK大小-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用包大小
     */
    public String getAppSize(Context context, String packageName) {
        try {
            // 获取应用的路径
            String dir = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA).publicSourceDir;
            // 获取应用的大小
            int size = (int) new File(dir).length();
            // 处理应用的大小
            return new BigDecimal(size / (1024 * 1024)).setScale(2, BigDecimal.ROUND_DOWN).toString() + "M";
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public Signature getAppSign(Context context) {
        return getAppSign(context, context.getPackageName());
    }

    /**
     * 获取应用程序-签名信息-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用签名信息
     */
    public Signature getAppSign(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures[0];
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public String getAppSignMD5(Context context) {
        return getAppSignMD5(context, context.getPackageName());
    }

    /**
     * 获取应用程序-签名信息MD5-通过包名
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 应用MD5签名信息
     */
    public String getAppSignMD5(Context context, String packageName) {
        try {
            Signature signatures = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures[0];
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(signatures.toByteArray());
            byte[] digest = mDigest.digest();
            return toHexString(digest);
        } catch (Exception e) {
            return null;
        }
    }

    public String getAppChannel(Context mContext, String key) {
        return getAppChannel(mContext, mContext.getPackageName(), key);
    }

    /**
     * 获取应用程序-渠道-通过包名
     *
     * @param mContext    上下文
     * @param packageName 应用包名
     * @param key         application中指定的meta-data
     * @return 渠道信息
     */
    public String getAppChannel(Context mContext, String packageName, String key) {
        try {
            return mContext.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否安装了指定的应用程序
     *
     * @param context     上下文
     * @param packageName 应用包名
     * @return 是否安装
     */
    public boolean isInstallApp(Context context, String packageName) {
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (info.packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
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