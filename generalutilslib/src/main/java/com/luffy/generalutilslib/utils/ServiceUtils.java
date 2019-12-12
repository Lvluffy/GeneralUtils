package com.luffy.generalutilslib.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by lvlufei on 2019/5/21
 *
 * @name 服务-辅助工具
 * @desc
 */
public class ServiceUtils {

    private ServiceUtils() {
    }

    public static ServiceUtils getInstance() {
        return ServiceUtilsHelper.mServiceUtils;
    }

    private static class ServiceUtilsHelper {
        private static final ServiceUtils mServiceUtils;

        static {
            mServiceUtils = new ServiceUtils();
        }
    }

    /**
     * 服务是否运行
     *
     * @param mContext     上下文
     * @param serviceClass 类对象
     * @return 服务是否运行
     */
    public boolean isServiceRun(Context mContext, Class serviceClass) {
        String serviceName = ClassUtils.getInstance().getClassName(serviceClass);
        /*获取正在运行的所有服务*/
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfoList = mActivityManager.getRunningServices(Integer.MAX_VALUE);
        /*没有服务返回false*/
        if (runningServiceInfoList.size() <= 0) {
            return false;
        }
        /*判断指定服务是否运行*/
        boolean isRun = false;
        for (int i = 0; i < runningServiceInfoList.size(); i++) {
            String mName = runningServiceInfoList.get(i).service.getClassName();
            if (mName.equals(serviceName)) {
                isRun = true;
            }
        }
        return isRun;
    }

    /**
     * 启动服务
     *
     * @param mContext               上下文
     * @param serviceClass           服务类文件
     * @param iServiceStartIntercept 回调
     */
    public synchronized void startService(Context mContext, Class serviceClass, IServiceStartIntercept iServiceStartIntercept) {
        /*服务运行状态拦截：已运行结束；未运行执行*/
        if (isServiceRun(mContext, serviceClass)) {
            return;
        }
        /*业务拦截：拦截结束；不拦截执行*/
        if (iServiceStartIntercept != null && iServiceStartIntercept.onServiceStartIntercept()) {
            return;
        }
        /*启动心跳服务*/
        Intent intent = new Intent(mContext, serviceClass);
        mContext.startService(intent);
    }

    /**
     * 停止服务
     *
     * @param mContext 上下文
     * @param serviceClass 服务类文件
     */
    public synchronized void stopService(Context mContext, Class serviceClass) {
        /*服务运行状态拦截：正在运行执行；未运行结束*/
        if (isServiceRun(mContext, serviceClass)) {
            Intent intent = new Intent(mContext, serviceClass);
            mContext.stopService(intent);
        }
    }

    /**
     * 服务启动拦截
     */
    public interface IServiceStartIntercept {

        /**
         * 服务启动拦截
         *
         * @return true-拦截；false-不拦截
         */
        boolean onServiceStartIntercept();

    }
}
