package com.luffy.generalutils.ui.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luffy.generalutils.R;
import com.luffy.generalutils.base.BaseFragment;
import com.luffy.utils.generallib.AppUtils;
import com.luffy.utils.generallib.DeviceUtils;
import com.luffy.utils.generallib.ValidUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppFragment extends BaseFragment {

    @BindView(R.id.txt_device_info)
    TextView txtDeviceInfo;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_version_name)
    TextView txtVersionName;
    @BindView(R.id.txt_version_code)
    TextView txtVersionCode;
    @BindView(R.id.txt_size)
    TextView txtSize;
    @BindView(R.id.txt_sign)
    TextView txtSign;
    @BindView(R.id.txt_sign_md5)
    TextView txtSignMd5;
    @BindView(R.id.txt_channel)
    TextView txtChannel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StringBuilder sb = new StringBuilder();
        sb.append("Imei:").append(DeviceUtils.getImei(getContext())).append("\n");
        sb.append("Imsi:").append(DeviceUtils.getImsi(getContext())).append("\n");
        sb.append("DeviceBrand:").append(DeviceUtils.getDeviceBrand()).append("\n");
        sb.append("MacAddress:").append(DeviceUtils.getMacAddress()).append("\n");
        sb.append("DeviceModel:").append(DeviceUtils.getDeviceModel()).append("\n");
        sb.append("DeviceSystemVersion:").append(DeviceUtils.getDeviceSystemVersion()).append("\n");
        sb.append("DeviceManufacturer:").append(DeviceUtils.getDeviceManufacturer());
        txtDeviceInfo.setText(sb.toString());
    }

    @OnClick({R.id.btn_get, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                if (!ValidUtils.isValid(edit.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入包名", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*获取信息*/
                String appName = AppUtils.getAppName(getActivity(), edit.getText().toString());
                Drawable appIcon = AppUtils.getAppIcon(getActivity(), edit.getText().toString());
                String appVersionName = AppUtils.getAppVersionName(getActivity(), edit.getText().toString());
                int appVersionCode = AppUtils.getAppVersionCode(getActivity(), edit.getText().toString());
                String appSize = AppUtils.getAppSize(getActivity(), edit.getText().toString());
                String appSign = AppUtils.getAppSign(getActivity(), edit.getText().toString());
                String appSignMD5 = AppUtils.getAppSignMD5(getActivity(), edit.getText().toString());
                String appChannel = AppUtils.getAppChannel(getActivity(), edit.getText().toString(), "BaiduMobAd_CHANNEL");

                /*绑定信息*/
                txtName.setText(appName);
                imgIcon.setImageDrawable(appIcon);
                txtVersionName.setText(appVersionName);
                txtVersionCode.setText(appVersionCode + "");
                txtSize.setText(appSize);
                txtSign.setText(appSign);
                txtSignMd5.setText(appSignMD5);
                txtChannel.setText(appChannel);
                break;
            case R.id.btn_back:
                getActivity().finish();
                break;
        }
    }

}
