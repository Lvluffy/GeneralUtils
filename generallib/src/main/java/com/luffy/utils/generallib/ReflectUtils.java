package com.luffy.utils.generallib;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lvlufei on 2020-06-05
 *
 * @name 反射--辅助工具
 */
public class ReflectUtils {

    private String TAG = getClass().getSimpleName();

    private ReflectUtils() {
    }

    public static ReflectUtils getInstance() {
        return ReflectUtilsHolder.instance;
    }

    private static class ReflectUtilsHolder {
        private static final ReflectUtils instance = new ReflectUtils();
    }

    public Object invokeMethod(Object target, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = target.getClass();
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, values);
    }

    public Class<?> getClassFromName(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "getClassFromName:" + className, e);
        }
        return clazz;
    }

    public Object newInstance(String className, Class<?>[] parameterTypes, Object... values) {
        Object instance = null;
        Class<?> clazz = getClassFromName(className);
        if (clazz != null) {
            instance = newInstance(clazz, parameterTypes, values);
        }
        return instance;
    }

    public Object newInstance(Class<?> clazz, Class<?>[] parameterTypes, Object... values) {
        Object instance = null;
        try {
            Constructor constructor = clazz.getConstructor(parameterTypes);
            instance = constructor.newInstance(values);
        } catch (Exception e) {
            Log.e(TAG, "newInstance failed", e);
        }
        return instance;
    }

    public Object proxyCallBack(ClassLoader loader, String callBackName, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(loader, new Class[]{getClassFromName(callBackName)}, invocationHandler);
    }
}
