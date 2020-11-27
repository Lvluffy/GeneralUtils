package com.luffy.generalutils.ui.security;

import android.os.Bundle;
import android.widget.TextView;

import com.luffy.generalutils.R;
import com.luffy.generalutils.base.BaseActivity;
import com.luffy.utils.securitylib.AESUtils;
import com.luffy.utils.securitylib.Base64Utils;
import com.luffy.utils.securitylib.DESUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
        stringBuilder.append("Base64解密后：").append(Base64Utils.getInstance().decodeBase64(Base64Utils.getInstance().encodeBase64NoWrap(data))).append("\n");

        try {
            String des_key = "12345678".substring(0, 8);
            stringBuilder.append("DES加密前：").append(data).append("\n");
            String des_encrypt_data = DESUtils.getInstance().encrypt(data, des_key);
            stringBuilder.append("DES加密后：").append(des_encrypt_data).append("\n");
            stringBuilder.append("DES解密前：").append(des_encrypt_data).append("\n");
            stringBuilder.append("DES解密后：").append(DESUtils.getInstance().decrypt(des_encrypt_data, des_key)).append("\n");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        try {
            String aes_key = "12345678asdfqwer".substring(0, 16);
            stringBuilder.append("AES加密前：").append(data).append("\n");
            String aes_encrypt_data = AESUtils.getInstance().encrypt(data, aes_key);
            stringBuilder.append("AES加密后：").append(aes_encrypt_data).append("\n");
            stringBuilder.append("AES解密前：").append(aes_encrypt_data).append("\n");
            stringBuilder.append("AES解密后：").append(AESUtils.getInstance().decrypt(aes_encrypt_data, aes_key)).append("\n");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        txtContent.setText(stringBuilder.toString());

    }
}
