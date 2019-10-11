package com.luffy.generalutilslib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 设备-辅助工具
 */
public class DeviceUtils {

    private DeviceUtils() {
    }

    public static DeviceUtils getInstance() {
        return DeviceUtilsHelper.mDeviceUtils;
    }

    private static class DeviceUtilsHelper {
        private static DeviceUtils mDeviceUtils;

        static {
            mDeviceUtils = new DeviceUtils();
        }
    }

    /**
     * 获取手机厂商
     *
     * @return
     */
    public synchronized String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public synchronized String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public synchronized String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机系统版本
     *
     * @return
     */
    public synchronized String getDeviceSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取IMSI号(SubscriberId)
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public synchronized String getImsi(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager != null) {
            return mTelephonyManager.getSubscriberId();
        }
        return null;
    }

    /**
     * 获取IMEI号(DeviceId)
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public synchronized String getImei(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager != null) {
            return mTelephonyManager.getDeviceId();
        }
        return null;
    }

    /**
     * 获取UUID
     *
     * @param context
     * @return
     */
    @SuppressLint({"MissingPermission"})
    public synchronized String getUUID(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager != null) {
            final String tmDevice = "" + mTelephonyManager.getDeviceId();
            final String tmSerial = "" + mTelephonyManager.getSimSerialNumber();
            final String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID uuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return uuid.toString();
        }
        return null;
    }

    /**
     * 获取MAC地址
     *
     * @return
     */
    public synchronized String getMacAddress() {
        try {
            return loadFileAsString("/sys/class/net/wlan0/address").toUpperCase().substring(0, 17);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private synchronized String loadFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    public synchronized String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }
}