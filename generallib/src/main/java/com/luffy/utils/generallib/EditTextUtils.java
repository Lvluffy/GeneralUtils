package com.luffy.utils.generallib;

import android.widget.EditText;

/**
 * Created by lvlufei on 2018/10/10
 *
 * @name 编辑框-辅助工具
 */
public class EditTextUtils {

    /**
     * 设置编辑状态
     *
     * @param mEditText 编辑框
     * @param editable  是否可编辑（true-可编辑 false-不可编辑）
     */
    public static void setEditState(EditText mEditText, boolean editable) {
        if (editable) {
            mEditText.setFocusableInTouchMode(true);
            mEditText.setFocusable(true);
            mEditText.requestFocus();
            setCursorLocation(mEditText);
        } else {
            mEditText.setFocusableInTouchMode(false);
            mEditText.setFocusable(false);
        }
    }

    /**
     * 设置光标位置
     *
     * @param mEditText 编辑框
     */
    public static void setCursorLocation(EditText mEditText) {
        if (mEditText != null) {
            mEditText.setSelection(mEditText.getText().length());
        }
    }
}
