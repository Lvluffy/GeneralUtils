package com.luffy.generalutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luffy.utils.generallib.LogUtils;

public class BaseFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.logDebug(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.logDebug(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.logDebug(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.logDebug(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.logDebug(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.logDebug(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.logDebug(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.logDebug(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.logDebug(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.logDebug(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.logDebug(TAG, "onDetach");
    }
}
