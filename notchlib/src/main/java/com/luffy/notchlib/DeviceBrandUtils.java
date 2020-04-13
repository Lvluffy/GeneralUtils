package com.luffy.notchlib;

import android.os.Build;

/**
 * Created by lvlufei on 2019/12/3
 *
 * @name 设备品牌-辅助工具
 * @desc
 */
public class DeviceBrandUtils {

    private DeviceBrandUtils() {
    }

    public static DeviceBrandUtils getInstance() {
        return DeviceBrandUtils.DeviceBrandUtilsHelper.mDeviceBrandUtils;
    }

    private static class DeviceBrandUtilsHelper {
        private static final DeviceBrandUtils mDeviceBrandUtils = new DeviceBrandUtils();
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public OSType getOSType() {
        String brand = Build.BRAND;
        if (brand.equalsIgnoreCase(Huawei.BRAND_1) || brand.equalsIgnoreCase(Huawei.BRAND_2)) {
            return OSType.Huawei;
        } else if (brand.equalsIgnoreCase(Sanxing.BRAND_1)) {
            return OSType.Sanxing;
        } else if (brand.equalsIgnoreCase(Xiaomi.BRAND_1)) {
            return OSType.Xiaomi;
        } else if (brand.equalsIgnoreCase(Vivo.BRAND_1)) {
            return OSType.Vivo;
        } else if (brand.equalsIgnoreCase(Oppo.BRAND_1)) {
            return OSType.Oppo;
        } else if (brand.equalsIgnoreCase(Meizu.BRAND_1)) {
            return OSType.Meizu;
        } else {
            return OSType.Other;
        }
    }

    /**
     * 华为
     */
    private static class Huawei {
        /*华为*/
        public static final String BRAND_1 = "HUAWEI";
        /*荣耀*/
        public static final String BRAND_2 = "HONOR";
    }

    /**
     * 三星
     */
    private static class Sanxing {
        /*三星*/
        public static final String BRAND_1 = "samsung";
    }

    /**
     * 小米
     */
    private static class Xiaomi {
        /*小米*/
        public static final String BRAND_1 = "Xiaomi";

    }

    /**
     * VIVO
     */
    private static class Vivo {
        public static final String BRAND_1 = "vivo";
    }

    /**
     * OPPO
     */
    private static class Oppo {
        public static final String BRAND_1 = "OPPO";
    }

    /**
     * 魅族
     */
    private static class Meizu {
        /*魅族*/
        public static final String BRAND_1 = "Meizu";
    }

    public enum OSType {
        Huawei,
        Sanxing,
        Xiaomi,
        Vivo,
        Oppo,
        Meizu,
        Other
    }
}
