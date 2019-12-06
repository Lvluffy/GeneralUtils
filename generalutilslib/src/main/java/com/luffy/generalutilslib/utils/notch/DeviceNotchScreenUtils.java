package com.luffy.generalutilslib.utils.notch;

import android.content.Context;

import com.luffy.generalutilslib.utils.DeviceBrandUtils;
import com.luffy.generalutilslib.utils.notch.impl.AndroidPNotchScreen;
import com.luffy.generalutilslib.utils.notch.impl.HuaweiNotchScreen;
import com.luffy.generalutilslib.utils.notch.impl.INotchScreen;
import com.luffy.generalutilslib.utils.notch.impl.MiNotchScreen;
import com.luffy.generalutilslib.utils.notch.impl.OppoNotchScreen;
import com.luffy.generalutilslib.utils.notch.impl.VivoNotchScreen;

/**
 * Created by lvlufei on 2019/12/5
 *
 * @name 设备刘海屏-辅助工具
 * @desc
 */
public class DeviceNotchScreenUtils {

    private DeviceNotchScreenUtils() {

    }

    public static DeviceNotchScreenUtils getInstance() {
        return DeviceNotchScreenUtilsHelper.mDeviceNotchScreenUtils;
    }

    private static class DeviceNotchScreenUtilsHelper {
        private static DeviceNotchScreenUtils mDeviceNotchScreenUtils;

        static {
            mDeviceNotchScreenUtils = new DeviceNotchScreenUtils();
        }
    }

    /**
     * @param context
     * @param notchScreenCallback
     */
    public void getNotchInfo(final Context context, final INotchScreen.NotchScreenCallback notchScreenCallback) {
        INotchScreen notchScreen = getNotchScreen();
        final INotchScreen.NotchScreenInfo notchScreenInfo = new INotchScreen.NotchScreenInfo();
        if (notchScreen != null && notchScreen.hasNotch(context)) {
            notchScreen.getNotchWidthHeight(context, new INotchScreen.NotchSizeCallback() {
                @Override
                public void onResult(int[] notchWidthHeight) {
                    notchScreenInfo.hasNotch = true;
                    notchScreenInfo.notchWidthHeight = notchWidthHeight;
                    notchScreenCallback.onResult(notchScreenInfo);
                }
            });
        } else {
            notchScreenInfo.hasNotch = false;
            notchScreenInfo.notchWidthHeight = new int[]{0, 0};
            notchScreenCallback.onResult(notchScreenInfo);
        }
    }

    /**
     * @return
     */
    private INotchScreen getNotchScreen() {
        INotchScreen notchScreen = null;
        if (DeviceBrandUtils.getInstance().getOSType() == DeviceBrandUtils.OSType.Huawei) {
            notchScreen = new HuaweiNotchScreen();
        } else if (DeviceBrandUtils.getInstance().getOSType() == DeviceBrandUtils.OSType.Oppo) {
            notchScreen = new OppoNotchScreen();
        } else if (DeviceBrandUtils.getInstance().getOSType() == DeviceBrandUtils.OSType.Vivo) {
            notchScreen = new VivoNotchScreen();
        } else if (DeviceBrandUtils.getInstance().getOSType() == DeviceBrandUtils.OSType.Xiaomi) {
            notchScreen = new MiNotchScreen();
        } else {
            notchScreen = new AndroidPNotchScreen();
        }
        return notchScreen;
    }
}
