package com.luffy.generalutils.ui;

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
        sb.append("Imei:").append(DeviceUtils.getInstance().getImei(getContext())).append("\n");
        sb.append("Imsi:").append(DeviceUtils.getInstance().getImsi(getContext())).append("\n");
        sb.append("DeviceBrand:").append(DeviceUtils.getInstance().getDeviceBrand()).append("\n");
        sb.append("MacAddress:").append(DeviceUtils.getInstance().getMacAddress()).append("\n");
        sb.append("DeviceModel:").append(DeviceUtils.getInstance().getDeviceModel()).append("\n");
        sb.append("DeviceSystemVersion:").append(DeviceUtils.getInstance().getDeviceSystemVersion()).append("\n");
        sb.append("DeviceManufacturer:").append(DeviceUtils.getInstance().getDeviceManufacturer());
        txtDeviceInfo.setText(sb.toString());
    }

    @OnClick({R.id.btn_get, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                if (!ValidUtils.getInstance().isValid(edit.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入包名", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*获取信息*/
                String appName = AppUtils.getInstance().getAppName(getActivity(), edit.getText().toString());
                Drawable appIcon = AppUtils.getInstance().getAppIcon(getActivity(), edit.getText().toString());
                String appVersionName = AppUtils.getInstance().getAppVersionName(getActivity(), edit.getText().toString());
                int appVersionCode = AppUtils.getInstance().getAppVersionCode(getActivity(), edit.getText().toString());
                String appSize = AppUtils.getInstance().getAppSize(getActivity(), edit.getText().toString());
                String appSign = AppUtils.getInstance().getAppSign(getActivity(), edit.getText().toString());
                String appSignMD5 = AppUtils.getInstance().getAppSignMD5(getActivity(), edit.getText().toString());
                String appChannel = AppUtils.getInstance().getAppChannel(getActivity(), edit.getText().toString(), "BaiduMobAd_CHANNEL");

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
