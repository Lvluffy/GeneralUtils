package com.luffy.utils.generallib;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lvlufei on 2020-11-18
 *
 * @name Json转化-辅助工具
 */
public class JsonUtils {
    private JsonUtils() {
    }

    public static JsonUtils getInstance() {
        return JsonUtilsHolder.instance;
    }

    private static class JsonUtilsHolder {
        private static final JsonUtils instance = new JsonUtils();
    }

    /**
     * Json字符串转Map
     *
     * @param jsonStr
     * @return
     */
    public Map<String, Object> json2Map(String jsonStr) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json2Map(jsonObject);
    }

    /**
     * Json对象转Map
     *
     * @param jsonObject
     * @return
     */
    public Map<String, Object> json2Map(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        } else {
            HashMap<String, Object> map = new HashMap<>();
            Iterator iterator = jsonObject.keys();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Object value = jsonObject.opt(key);
                map.put(key, convertObj(value));
            }

            return map;
        }
    }

    private Object convertObj(Object obj) {
        if (obj instanceof JSONObject) {
            return json2Map((JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            int size = array.length();
            List<Object> list = new ArrayList();

            for (int i = 0; i < size; ++i) {
                list.add(convertObj(array.opt(i)));
            }
            return list;
        } else {
            return obj == JSONObject.NULL ? null : obj;
        }
    }
}
