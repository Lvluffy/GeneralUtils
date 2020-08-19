package com.luffy.generalutils.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.luffy.generalutils.R;
import com.luffy.generalutils.base.BaseActivity;
import com.luffy.utils.securitylib.Base64Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecurityActivity extends BaseActivity {

    @BindView(R.id.txt_content)
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        String data = "123";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Base64加密前：").append(data).append("\n");
        stringBuilder.append("Base64加密后：").append(Base64Utils.getInstance().encodeBase64NoWrap(data)).append("\n");
        stringBuilder.append("Base64解密前：").append(Base64Utils.getInstance().encodeBase64NoWrap(data)).append("\n");
        stringBuilder.append("Base64解密后：").append(Base64Utils.getInstance().decodeBase64(Base64Utils.getInstance().encodeBase64NoWrap(data)));
        txtContent.setText(stringBuilder.toString());
    }
}
