package com.luffy.utils.generallib;

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
 * @name 设备-辅助工具
 */
public class DeviceUtils {

    private DeviceUtils() {
    }

    public static DeviceUtils getInstance() {
        return DeviceUtilsHolder.instance;
    }

    private static class DeviceUtilsHolder {
        private static final DeviceUtils instance = new DeviceUtils();
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public synchronized String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机品牌
     *
     * @return 手机品牌
     */
    public synchronized String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public synchronized String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机系统版本
     *
     * @return 手机系统版本
     */
    public synchronized String getDeviceSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取IMSI号(SubscriberId)
     *
     * @param context 上下文
     * @return IMSI
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public synchronized String getImsi(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager != null ? mTelephonyManager.getSubscriberId() : null;
    }

    /**
     * 获取IMEI号(DeviceId)
     *
     * @param context 上下文
     * @return IMEI
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public synchronized String getImei(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager != null ? mTelephonyManager.getDeviceId() : null;
    }

    /**
     * 获取UUID
     *
     * @param context 上下文
     * @return UUID
     */
    @SuppressLint({"MissingPermission"})
    public synchronized String getUUID(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager != null) {
            @SuppressLint("HardwareIds") final String tmDevice = String.format("%s", mTelephonyManager.getDeviceId());
            @SuppressLint("HardwareIds") final String tmSerial = String.format("%s", mTelephonyManager.getSimSerialNumber());
            @SuppressLint("HardwareIds") final String androidId = String.format("%s", android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID));
            UUID uuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return uuid.toString();
        }
        return null;
    }

    /**
     * 获取MAC地址
     *
     * @return MAC地址
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
        StringBuilder fileData = new StringBuilder(1000);
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
     * @return 本地IP
     */
    public synchronized String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}