package com.luffy.utils.generallib;

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
        private static final PasswordShowUtils mPasswordShowUtils = new PasswordShowUtils();
    }

    /**
     * 密码隐藏显示
     *
     * @param imageView      图标
     * @param editText       编辑框
     * @param showResourceId 显示资源ID
     * @param hintResourceId 隐藏资源ID
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
     * @param imageView      图标
     * @param editText       编辑框
     * @param showResourceId 显示资源ID
     */
    public void showPassword(ImageView imageView, EditText editText, int showResourceId) {
        imageView.setImageResource(showResourceId);
        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        editText.setSelection(editText.getText().toString().length());
    }

    /**
     * 隐藏密码
     *
     * @param imageView      图标
     * @param editText       编辑框
     * @param hintResourceId 隐藏资源ID
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
