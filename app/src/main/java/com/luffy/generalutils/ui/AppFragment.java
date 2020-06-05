package com.luffy.generalutils.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luffy.generalutils.R;
import com.luffy.generalutils.base.BaseFragment;
import com.luffy.utils.generallib.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppFragment extends BaseFragment {

    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.img_icon)
    ImageView imgIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_get, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                /*获取信息*/
                String appName = AppUtils.getInstance().getAppName(getActivity(), edit.getText().toString());
                Drawable appIcon = AppUtils.getInstance().getAppIcon(getActivity(), edit.getText().toString());
                /*绑定信息*/
                txtName.setText(appName);
                imgIcon.setImageDrawable(appIcon);
                break;
            case R.id.btn_back:
                getActivity().finish();
                break;
        }
    }
}