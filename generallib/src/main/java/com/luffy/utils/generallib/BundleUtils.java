package com.luffy.utils.generallib;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public String bundleToString(Bundle bundle) {
        return bundleToMap(bundle).toString();
    }

    /**
     * Bundle转String
     *
     * @param bundle
     * @return
     */
    public Map bundleToMap(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keys = bundle.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = bundle.get(key);
            if (value instanceof Bundle) {
                map.put(key, bundleToMap((Bundle) value));
            } else {
                map.put(key, value);
            }
        }
        return map;
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
