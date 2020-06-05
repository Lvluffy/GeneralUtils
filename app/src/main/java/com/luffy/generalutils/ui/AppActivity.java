package com.luffy.generalutils.ui;

import android.os.Bundle;

import com.luffy.generalutils.base.BaseActivity;
import com.luffy.utils.generallib.FragmentUtils;

/**
 * Created by lvlufei on 2019/10/11
 *
 * @name 应用信息界面
 * @desc
 */
public class AppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.getInstance().replaceFragment(this, new AppFragment());
    }
}
