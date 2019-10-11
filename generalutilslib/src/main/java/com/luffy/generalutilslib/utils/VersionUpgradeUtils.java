package com.luffy.generalutilslib.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lvlufei on 2019/3/25
 *
 * @desc 版本升级-辅助工具
 */
public class VersionUpgradeUtils {

    private VersionUpgradeUtils() {
    }

    public static VersionUpgradeUtils getInstance() {
        return VersionUpgradeUtilsHelper.mVersionUpgradeUtils;
    }

    private static class VersionUpgradeUtilsHelper {
        private static VersionUpgradeUtils mVersionUpgradeUtils;

        static {
            mVersionUpgradeUtils = new VersionUpgradeUtils();
        }
    }

    /**
     * 外部升级（应用宝市场更新）
     *
     * @param mContext
     * @param url
     */
    public void externalUpgrade(Context mContext, String url) {
        if (SystemUtils.getInstance().isInstall(mContext, "com.tencent.android.qqdownloader")) {
            SystemUtils.getInstance().openAppDetail(mContext, AppUtils.getInstance().getPackName(mContext), "com.tencent.android.qqdownloader");
        } else {
            IntentUtils.getInstance().openBrowser(mContext, url);
        }
    }

    /**
     * 外部升级（下载apk）
     *
     * @param mContext
     * @param uri
     */
    public void internalUpgrade(final Context mContext, final String uri) {
        final ProgressDialog mProgressDialog;    //进度条对话框
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage("正在下载更新");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = downloadApk(uri, mProgressDialog);
                    sleep(3000);
                    installApk(mContext, file);
                    mProgressDialog.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 下载apk文件
     *
     * @param uri
     * @param mProgressDialog
     * @return
     * @throws Exception
     */
    public File downloadApk(String uri, ProgressDialog mProgressDialog) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            mProgressDialog.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                mProgressDialog.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装Apk
     *
     * @param mContext
     * @param file
     */
    public void installApk(Context mContext, File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //安装好后打开新版本
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }
}
