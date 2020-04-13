package com.luffy.notchlib.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.O)
public class OppoNotchScreen implements INotchScreen {

    @Override
    public boolean hasNotch(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    @Override
    public void getNotchWidthHeight(Context context, NotchSizeCallback callback) {
        int[] notchWidthHeight = {0, 80};
        callback.onResult(notchWidthHeight);
    }
}
