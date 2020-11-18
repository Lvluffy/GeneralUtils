package com.luffy.generalutils.base;

import android.app.Application;

import com.luffy.utils.generallib.LogUtils;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getInstance().init(true);
    }
}
