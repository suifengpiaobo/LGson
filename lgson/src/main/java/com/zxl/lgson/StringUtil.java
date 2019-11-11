package com.zxl.lgson;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Description
 *
 * @author: zxl
 * create on 2019-11-07 20:18.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return true—为空，false—不为空
     */
    public static boolean isStringEmpty(String str) {
        return str == null || "null".equals(str)
                || str.replace(" ", "").trim().length() == 0;
    }

    /**
     * 整形转换
     *
     * @param data 输入
     * @return Integer
     */
    public static Integer toInt(String data) {
        Integer result = 0;

        try {
            result = Integer.valueOf(data);
        } catch (Exception e) {
            Log.d("StringUtils", e.getMessage() + "-->String装换成Integer异常！");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Long转换
     *
     * @param data 输入
     * @return Long
     */
    public static Long toLong(String data) {
        Long result = 0L;

        try {
            result = Long.valueOf(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 浮点转换
     *
     * @param data 输入
     * @return Float
     */
    public static Float toFloat(String data) {
        Float result = 0.0f;

        if (data != null && data.length() > 0) {
            try {
                result = Float.valueOf(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 浮点转换
     *
     * @param data 输入
     * @return Double
     */
    public static Double toDouble(String data) {
        Double result = 0.0;

        try {
            result = Double.valueOf(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 判断JSON对象是否为空
     *
     * @param object JSON对象
     * @return true—为空, false—不为空
     */
    public static boolean isJSONObjectEmpty(JSONObject object) {
        return object == null || object.length() == 0;
    }

    /**
     * 判断JSON数组是否为空
     *
     * @param array JSON数组
     * @return true—为空, false—不为空
     */
    public static boolean isJSONArrayEmpty(JSONArray array) {
        return array == null || array.length() == 0;
    }
}
