package com.luffy.netlib;

/**
 * Created by lvlufei on 2020-04-29
 *
 * @name 网络处理回调
 * @desc
 */
public interface INetHandle {
    /**
     * 有网络
     *
     * @return true:自己处理；false:向下处理
     */
    void hasNetwork();

    /**
     * WIFI网络
     */
    void wifiNetwork();

    /**
     * 手机（4G/3G/2G）网络
     */
    void mobileNetwork();

    /**
     * 无网络
     */
    void noNetwork();
}
