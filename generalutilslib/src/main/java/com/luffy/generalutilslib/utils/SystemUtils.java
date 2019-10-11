package com.luffy.generalutilslib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2018/7/23
 *
 * @desc 系统-辅助工具
 */
public class SystemUtils {

    private SystemUtils() {
    }

    public static SystemUtils getInstance() {
        return SystemUtils.SystemUtilsHelper.mSystemUtils;
    }

    private static class SystemUtilsHelper {
        private static SystemUtils mSystemUtils;

        static {
            mSystemUtils = new SystemUtils();
        }
    }

    /**
     * 判断手机系统版本是否是7.0以上
     *
     * @return
     */
    public boolean isThanVersionN() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /**
     * 解决7.0拍照问题
     */
    public void settingCamera() {
        if (isThanVersionN()) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }

    /**
     * 打开指定应用商店中指定应用详情
     *
     * @param context   上下文对象
     * @param appPkg    指定应用包名
     * @param marketPkg 指定应用商店包名
     */
    public void openAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否安装了某应用
     *
     * @param context     上下文对象
     * @param packageName 指定应用包名
     * @return
     */
    public boolean isInstall(Context context, String packageName) {
        /*获取包名管理器*/
        final PackageManager packageManager = context.getPackageManager();
        /*获取所有已安装程序的包信息*/
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        /*存储所有已安装程序的包名*/
        List<String> packageNameList = new ArrayList<>();
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                String pn = packageInfoList.get(i).packageName;
                packageNameList.add(pn);
            }
        }
        return packageNameList.contains(packageName);
    }
}

