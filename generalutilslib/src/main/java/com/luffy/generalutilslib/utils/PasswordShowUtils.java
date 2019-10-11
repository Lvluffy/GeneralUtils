package com.luffy.generalutilslib.utils;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 密码显示-辅助工具
 */
public class PasswordShowUtils {

    private PasswordShowUtils() {
    }

    public static PasswordShowUtils getInstance() {
        return PasswordShowUtilsHelper.mPasswordShowUtils;
    }

    private static class PasswordShowUtilsHelper {
        private static PasswordShowUtils mPasswordShowUtils;

        static {
            mPasswordShowUtils = new PasswordShowUtils();
        }
    }

    /**
     * 密码隐藏显示
     *
     * @param imageView      图标
     * @param editText       编辑框
     * @param showResourceId
     * @param hintResourceId
     */
    public void passwordShowHint(ImageView imageView, EditText editText, int showResourceId, int hintResourceId, PasswordShowHintCallback passwordShowHintCallback) {
        if (passwordShowHintCallback.isShowPassword()) {
            /*显示密码*/
            showPassword(imageView, editText, showResourceId);
        } else {
            /*隐藏密码*/
            hintPassword(imageView, editText, hintResourceId);
        }
        passwordShowHintCallback.change(!passwordShowHintCallback.isShowPassword());
    }

    /**
     * 显示密码
     *
     * @param imageView
     * @param editText
     * @param showResourceId
     */
    public void showPassword(ImageView imageView, EditText editText, int showResourceId) {
        imageView.setImageResource(showResourceId);
        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        editText.setSelection(editText.getText().toString().length());
    }

    /**
     * 隐藏密码
     *
     * @param imageView
     * @param editText
     * @param hintResourceId
     */
    public void hintPassword(ImageView imageView, EditText editText, int hintResourceId) {
        imageView.setImageResource(hintResourceId);
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editText.setSelection(editText.getText().toString().length());
    }

    /**
     * 密码显示隐藏回调监听
     */
    public interface PasswordShowHintCallback {

        boolean isShowPassword();

        void change(boolean isShowPassword);

    }
}
