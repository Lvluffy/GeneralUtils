package com.luffy.utils.generallib;

import android.os.Build;
import android.os.StrictMode;

/**
 * Created by lvlufei on 2018/7/23
 *
 * @desc 系统-辅助工具
 */
public class CameraUtils {

    private CameraUtils() {
    }

    public static CameraUtils getInstance() {
        return CameraUtils.SystemUtilsHelper.M_CAMERA_UTILS;
    }

    private static class SystemUtilsHelper {
        private static final CameraUtils M_CAMERA_UTILS = new CameraUtils();
    }

    /**
     * 判断手机系统版本是否是7.0以上
     *
     * @return SDK是否是7.0以上
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

}

