package com.luffy.generalutils.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.luffy.utils.generallib.LogUtils;

/**
 * Created by lvlufei on 2019/10/11
 *
 * @name 应用信息界面
 * @desc
 */
public class BaseActivity extends AppCompatActivity {

    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.logDebug(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.logDebug(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.logDebug(TAG, "onResume");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.logDebug(TAG, "onBackPressed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.logDebug(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.logDebug(TAG, "onStop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtils.logDebug(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.logDebug(TAG, "onDestroy");
    }

}
