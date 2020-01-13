package com.luffy.generalutilslib.utils;

import android.widget.EditText;

/**
 * Created by lvlufei on 2018/10/10
 *
 * @desc 编辑框-辅助工具
 */
public class EditTextUtils {

    private EditTextUtils() {
    }

    public static EditTextUtils getInstance() {
        return EditTextUtilsHelper.mEditTextUtils;
    }

    private static class EditTextUtilsHelper {
        private static final EditTextUtils mEditTextUtils = new EditTextUtils();
    }

    /**
     * 设置编辑状态
     *
     * @param mEditText 编辑框
     * @param editable  是否可编辑（true-可编辑 false-不可编辑）
     */
    public void setEditState(EditText mEditText, boolean editable) {
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
    public void setCursorLocation(EditText mEditText) {
        if (mEditText != null) {
            mEditText.setSelection(mEditText.getText().length());
        }
    }
}
