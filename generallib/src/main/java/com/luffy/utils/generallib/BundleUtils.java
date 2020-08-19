package com.luffy.utils.generallib;

import android.os.Bundle;

import java.util.Iterator;

/**
 * Created by lvlufei on 2020-08-10
 *
 * @name Bundle-辅助工具
 */
public class BundleUtils {

    private BundleUtils() {

    }

    public static BundleUtils getInstance() {
        return BundleUtilsHolder.instance;
    }

    private static class BundleUtilsHolder {
        private static final BundleUtils instance = new BundleUtils();
    }

    /**
     * Bundle转String
     *
     * @param bundle
     * @return
     */
    public String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> keys = bundle.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = bundle.get(key);
            if (value instanceof Bundle) {
                stringBuilder.append(bundleToString((Bundle) value));
            } else {
                stringBuilder.append(key).append("=").append(value).append(",");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 从Bundle中获取指定key的值
     *
     * @param bundle
     * @param targetKey
     * @return
     */
    public String getValueFromBundleByKey(Bundle bundle, String targetKey) {
        if (bundle == null || targetKey == null) {
            return null;
        }
        String data = null;
        Iterator<String> keys = bundle.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = bundle.get(key);
            if (value instanceof Bundle) {
                String temp = getValueFromBundleByKey((Bundle) value, targetKey);
                if (temp != null) {
                    data = temp;
                    break;
                }
            } else {
                if (targetKey.equals(key)) {
                    data = (String) value;
                    break;
                }
            }
        }
        return data;
    }
}
