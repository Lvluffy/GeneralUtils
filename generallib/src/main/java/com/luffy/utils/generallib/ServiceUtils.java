package com.luffy.utils.generallib;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by lvlufei on 2019/5/21
 *
 * @name 服务-辅助工具
 */
public class ServiceUtils {

    private ServiceUtils() {
    }

    public static ServiceUtils getInstance() {
        return ServiceUtilsHolder.instance;
    }

    private static class ServiceUtilsHolder {
        private static final ServiceUtils instance = new ServiceUtils();
    }

    /**
     * 服务是否运行
     *
     * @param context      上下文
     * @param serviceClass 类对象
     * @return 服务是否运行
     */
    public boolean isServiceRun(Context context, Class serviceClass) {
        String serviceName = ClassUtils.getInstance().getClassName(serviceClass);
        /*获取正在运行的所有服务*/
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfoList = mActivityManager.getRunningServices(Integer.MAX_VALUE);
        /*判断指定服务是否运行*/
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServiceInfoList) {
            if (TextUtils.equals(serviceName, runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 启动服务
     *
     * @param context                上下文
     * @param serviceClass           服务类文件
     * @param iServiceStartIntercept 回调
     */
    public synchronized void startService(Context context, Class serviceClass, IServiceStartIntercept iServiceStartIntercept) {
        /*服务运行状态拦截：已运行结束；未运行执行*/
        if (isServiceRun(context, serviceClass)) {
            return;
        }
        /*业务拦截：拦截结束；不拦截执行*/
        if (iServiceStartIntercept != null && iServiceStartIntercept.onServiceStartIntercept()) {
            return;
        }
        /*启动服务*/
        Intent intent = new Intent(context, serviceClass);
        context.startService(intent);
    }

    /**
     * 停止服务
     *
     * @param context      上下文
     * @param serviceClass 服务类文件
     */
    public synchronized void stopService(Context context, Class serviceClass) {
        /*服务运行状态拦截：正在运行执行；未运行结束*/
        if (isServiceRun(context, serviceClass)) {
            Intent intent = new Intent(context, serviceClass);
            context.stopService(intent);
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
