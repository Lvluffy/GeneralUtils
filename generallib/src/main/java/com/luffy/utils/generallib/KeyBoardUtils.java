package com.luffy.utils.generallib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 软键盘-辅助工具
 */
public class KeyBoardUtils {

    int usableHeightPrevious;//可用高度之前

    private KeyBoardUtils() {
    }

    public static KeyBoardUtils getInstance() {
        return KeyBoardUtilsHelper.mKeyBoardUtils;
    }

    private static class KeyBoardUtilsHelper {
        private static final KeyBoardUtils mKeyBoardUtils = new KeyBoardUtils();
    }

    /**
     * 打开软键盘
     *
     * @param context 上下文
     * @param view    View
     */
    public void openKeyBoard(Context context, View view) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param context 上下文
     * @param view    View
     */
    public void closeKeyBoard(Context context, View view) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 解决在页面底部置输入框，输入法弹出遮挡部分输入框的问题
     *
     * @param context     上下文
     * @param rootView    根布局
     * @param visibleView 可视布局
     */
    public void controlKeyboardLayout(final Context context, final View rootView, final View visibleView) {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                rootView.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInVisibleHeigh = rootView.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInVisibleHeigh > ScreenUtils.getInstance().getVirtualKeyboardHeight(context)) {
                    int[] location = new int[2];
                    //获取editLayout在窗体的坐标
                    visibleView.getLocationInWindow(location);
                    //计算root滚动高度，使editLayout在可见区域
                    int srollHeight = (location[1] + visibleView.getHeight()) - rect.bottom;
                    rootView.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    rootView.scrollTo(0, 0);
                }
            }
        });
    }

    /**
     * 键盘状态观察者
     *
     * @param activity Activity
     * @param listener 回调
     */
    public void keyboardStateObserver(Activity activity, final OnKeyboardVisibilityListener listener) {
        final FrameLayout content = activity.findViewById(android.R.id.content);
        final View contentView = content.getChildAt(0);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int usableHeightNow = computeUsableHeight(contentView);
                if (usableHeightNow != usableHeightPrevious) {
                    int usableHeightSansKeyboard = contentView.getRootView().getHeight();
                    int heightDifference = usableHeightSansKeyboard - usableHeightNow;
                    if (heightDifference > (usableHeightSansKeyboard / 4)) {
                        if (listener != null) {
                            listener.onKeyboardShow();
                        }
                    } else {
                        if (listener != null) {
                            listener.onKeyboardHide();
                        }
                    }
                    usableHeightPrevious = usableHeightNow;
                }
            }
        });
    }


    /**
     * 计算可用高度
     *
     * @param contentView 内容视图
     * @return 可用高度
     */
    private int computeUsableHeight(View contentView) {
        Rect r = new Rect();
        contentView.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);// 全屏模式下： return r.bottom
    }

    /**
     * 软键盘能见度监听器
     */
    public interface OnKeyboardVisibilityListener {
        void onKeyboardShow();

        void onKeyboardHide();
    }
}
