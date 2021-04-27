package com.luffy.utils.generallib;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


/**
 * Created by lvlufei on 2018/1/1
 *
 * @name 双击退出-辅助工具
 */
public class DoubleClickExitUtils {
    /*结束退出*/
    public static final int END_FINISH = 0;
    /*结束回到桌面*/
    public static final int END_BACK_HOME = 1;
    /*定义一个变量，来标识是否退出*/
    private static boolean isExit = false;

    /**
     * 双击退出
     *
     * @param activity                 Activity
     * @param iDoubleClickExitCallBack 回调
     * @return 是否双击退出ø
     */
    public static boolean exit(Activity activity, IDoubleClickExitCallBack iDoubleClickExitCallBack) {
        return exit(activity, END_FINISH, iDoubleClickExitCallBack);
    }

    /**
     * 双击退出
     *
     * @param activity                 Activity
     * @param type                     类型
     * @param iDoubleClickExitCallBack 回调
     * @return 是否双击退出
     */
    public static boolean exit(Activity activity, int type, IDoubleClickExitCallBack iDoubleClickExitCallBack) {
        if (isExit) {
            if (type == END_FINISH) {
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
                iDoubleClickExitCallBack.handlerToastShow();
            } else {
                Toast.makeText(activity, String.format(activity.getString(R.string.utils_double_click_exit_hint), AppUtils.getAppName(activity)), Toast.LENGTH_SHORT).show();
            }
            new MyHandler().sendEmptyMessageDelayed(0, 2000);
        }
        return true;
    }

    public interface IDoubleClickExitCallBack {
        void handlerToastShow();
    }

    private static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    }
}
