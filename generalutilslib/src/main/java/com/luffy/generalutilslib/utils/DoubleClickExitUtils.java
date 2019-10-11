package com.luffy.generalutilslib.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.luffy.generalutilslib.R;


/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 双击退出-辅助工具
 */
public class DoubleClickExitUtils {
    /*结束退出*/
    public static final int END_FINISH = 0;
    /*结束回到桌面*/
    public static final int END_BACK_HOME = 1;
    /*结束~默认退出*/
    private int endType = END_FINISH;
    /*定义一个变量，来标识是否退出*/
    private static boolean isExit = false;

    private DoubleClickExitUtils() {
    }

    public static DoubleClickExitUtils getInstance() {
        return DoubleClickExitUtilsHelper.mDoubleClickExitUtils;
    }

    private static class DoubleClickExitUtilsHelper {
        private static DoubleClickExitUtils mDoubleClickExitUtils;

        static {
            mDoubleClickExitUtils = new DoubleClickExitUtils();
        }
    }

    /**
     * 双击退出
     *
     * @param activity
     * @param iDoubleClickExitCallBack
     * @return
     */
    public boolean exit(Activity activity, IDoubleClickExitCallBack iDoubleClickExitCallBack) {
        return exit(activity, END_FINISH, iDoubleClickExitCallBack);
    }

    /**
     * 双击退出
     *
     * @param activity
     * @param type
     * @param iDoubleClickExitCallBack
     * @return
     */
    public boolean exit(Activity activity, int type, IDoubleClickExitCallBack iDoubleClickExitCallBack) {
        endType = type;
        if (isExit) {
            if (endType == END_FINISH) {
                // 退出
                activity.finish();
                System.exit(0);
            } else {
                // 返回桌面
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                activity.startActivity(intent);
            }
        } else {
            isExit = true;
            if (iDoubleClickExitCallBack != null) {
                iDoubleClickExitCallBack.hanlderToastShow();
            } else {
                Toast.makeText(activity, String.format(activity.getString(R.string.utils_double_click_exit_hint), AppUtils.getInstance().getAppName(activity)), Toast.LENGTH_SHORT).show();
            }
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    isExit = false;
                }
            }.sendEmptyMessageDelayed(0, 2000);
        }
        return true;
    }

    public interface IDoubleClickExitCallBack {
        void hanlderToastShow();
    }
}
