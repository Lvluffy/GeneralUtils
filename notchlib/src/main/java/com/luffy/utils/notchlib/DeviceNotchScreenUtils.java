package com.luffy.utils.notchlib;

import android.content.Context;

import com.luffy.utils.notchlib.impl.AndroidPNotchScreen;
import com.luffy.utils.notchlib.impl.HuaweiNotchScreen;
import com.luffy.utils.notchlib.impl.INotchScreen;
import com.luffy.utils.notchlib.impl.MiNotchScreen;
import com.luffy.utils.notchlib.impl.OppoNotchScreen;
import com.luffy.utils.notchlib.impl.VivoNotchScreen;

/**
 * Created by lvlufei on 2019/12/5
 *
 * @name 设备刘海屏-辅助工具
 */
public class DeviceNotchScreenUtils {

    private DeviceNotchScreenUtils() {

    }

    public static DeviceNotchScreenUtils getInstance() {
        return DeviceNotchScreenUtilsHolder.instance;
    }

    private static class DeviceNotchScreenUtilsHolder {
        private static final DeviceNotchScreenUtils instance = new DeviceNotchScreenUtils();
    }

    /**
     * @param context             上下文
     * @param notchScreenCallback 回调
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
     * @return 刘海屏接口实例
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
