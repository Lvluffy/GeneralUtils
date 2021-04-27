package com.luffy.generalutils.ui.bitmapLoad;

import android.os.Bundle;

import com.luffy.generalutils.base.BaseActivity;
import com.luffy.utils.generallib.FragmentUtils;

/**
 * Created by lvlufei on 2020-08-14
 *
 * @name 图片加载
 */
public class BitmapLoadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, new BitmapLoadFragment());
    }
}
