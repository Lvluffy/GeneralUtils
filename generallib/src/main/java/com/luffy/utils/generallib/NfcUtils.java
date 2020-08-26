package com.luffy.utils.generallib;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lvlufei on 2020-08-26
 *
 * @name NFC-辅助工具
 */
public class NfcUtils {

    private NfcManager mNFCManager;
    private NfcAdapter mNfcAdapter;
    private WeakReference<Context> mContextRF;

    private NfcUtils(Context context) {
        mContextRF = new WeakReference<>(context.getApplicationContext());
        mNFCManager = (NfcManager) mContextRF.get().getSystemService(Context.NFC_SERVICE);
        mNfcAdapter = mNFCManager.getDefaultAdapter();
    }

    public static NfcUtils getInstance(Context context) {
        return new NfcUtilsHolder(context).instance;
    }

    private static class NfcUtilsHolder {
        private final NfcUtils instance;

        public NfcUtilsHolder(Context context) {
            instance = new NfcUtils(context);
        }
    }

    /**
     * 打开NFC
     */
    public void openNfc() {
        if (hasOpenNfc()) {
            return;
        }
        try {
            Method method = mNfcAdapter.getClass().getDeclaredMethod("enable");
            method.setAccessible(true);
            method.invoke(mNfcAdapter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭NFC
     */
    public void closeNfc() {
        if (!hasOpenNfc()) {
            return;
        }
        try {
            mNfcAdapter.getClass().getDeclaredMethods();
            Method method = mNfcAdapter.getClass().getDeclaredMethod("disable");
            method.setAccessible(true);
            method.invoke(mNfcAdapter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否打开NFC
     *
     * @return
     */
    public boolean hasOpenNfc() {
        if (mContextRF.get() == null) {
            return false;
        }
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            return true;
        }
        return false;
    }

    /**
     * 是否支持NFC
     *
     * @return
     */
    public boolean isSupportNfc() {
        return mNfcAdapter != null;
    }

}
