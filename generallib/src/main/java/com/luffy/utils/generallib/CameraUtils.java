package com.luffy.utils.generallib;

import android.os.Build;
import android.os.StrictMode;

/**
 * Created by lvlufei on 2018/7/23
 *
 * @name 相机-辅助工具
 */
public class CameraUtils {

    private CameraUtils() {
    }

    public static CameraUtils getInstance() {
        return SystemUtilsHolder.instance;
    }

    private static class SystemUtilsHolder {
        private static final CameraUtils instance = new CameraUtils();
    }

    /**
     * 解决7.0拍照问题
     */
    public void settingCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }

}

