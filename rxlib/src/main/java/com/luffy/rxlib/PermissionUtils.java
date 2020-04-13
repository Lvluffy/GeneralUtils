package com.luffy.rxlib;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 权限管理-辅助工具
 */
public class PermissionUtils {

    private PermissionUtils() {
    }

    public static PermissionUtils getInstance() {
        return PermissionUtils.PermissionUtilsHelper.mPermissionUtils;
    }

    private static class PermissionUtilsHelper {
        private static final PermissionUtils mPermissionUtils = new PermissionUtils();
    }

    /**
     * 是否开启了指定权限
     *
     * @param ctx      上下文
     * @param permName 权限名称
     * @return 是否开启了指定权限
     */
    public boolean checkPermission(Context ctx, String permName) {
        return PackageManager.PERMISSION_GRANTED == ctx.checkCallingOrSelfPermission(permName);
    }

    /**
     * 同时申请单个运行时权限
     *
     * @param activity                         Activity
     * @param meanWhileApplyPermissionCallBack 回调
     * @param permissions                      权限
     */
    public void meanWhileApplySinglePermission(Activity activity, final MeanWhileApplyPermissionCallBack meanWhileApplyPermissionCallBack, final String... permissions) {
        new RxPermissions(activity)
                .request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        //true表示获取权限成功（注意这里在android6.0以下默认为true）
                        if (aBoolean) {
                            meanWhileApplyPermissionCallBack.onSucceed();
                        } else {
                            meanWhileApplyPermissionCallBack.onFailure();
                        }
                    }
                });
    }

    /**
     * 同时申请多个运行时权限
     *
     * @param activity                         Activity
     * @param meanWhileApplyPermissionCallBack 回调
     * @param permissions                      权限
     */
    public void meanWhileApplyMultiPermission(Activity activity, final MeanWhileApplyPermissionCallBack meanWhileApplyPermissionCallBack, final String... permissions) {
        new RxPermissions(activity)
                .request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        //当所有权限都允许之后，返回true
                        if (aBoolean) {
                            meanWhileApplyPermissionCallBack.onSucceed();
                        } else {
                            meanWhileApplyPermissionCallBack.onFailure();
                        }
                    }
                });
    }

    /**
     * 分别申请运行时权限
     *
     * @param activity                        Activity
     * @param separateApplyPermissionCallBack 回调
     * @param permissions                     权限
     */
    public void separateApplyPermission(Activity activity, final SeparateApplyPermissionCallBack separateApplyPermissionCallBack, final String... permissions) {
        new RxPermissions(activity)
                .requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(@NonNull Permission permission) throws Exception {
                        separateApplyPermissionCallBack.onCallBack(permission);
                    }
                });
    }

    /**
     * 同时申请权限回调
     */
    public interface MeanWhileApplyPermissionCallBack {
        void onSucceed();

        void onFailure();
    }

    /**
     * 分别申请权限回调
     */
    public interface SeparateApplyPermissionCallBack {
        void onCallBack(Permission permission);
    }
}
