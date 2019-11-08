package com.zxl.lgson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Description
 * 使用方法
 * 1）MGson.newGson()得到Gson对象
 * 2)然后使用相对应的gson方法即可
 * 解决:
 * 1)要{}后端给[]返回实例对象
 * 2)要[]后端给{}返回空数组
 * 3)要int.class, Integer.class,short.class, Short.class,long.class, Long.class,double.class, Double.class,
 * float.class, Float.class后端给的非数字类型返回0
 * 4)要String后端给了[],{}等类型返回""
 * @author: zxl
 * create on 2019-11-07 20:24.
 */
public class LGson {
    /**
     * 生成注册自定义的对象处理器与集合处理器的Gson，方法
     *
     * @return
     */
    public static Gson newGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Class builder = (Class) gsonBuilder.getClass();
        Field f = null;
        try {
            //通过反射得到构造器
            f = builder.getDeclaredField("instanceCreators");
            f.setAccessible(true);
            final Map<Type, InstanceCreator<?>> val = (Map<Type, InstanceCreator<?>>) f.get(gsonBuilder);//得到此属性的值
            //注册String类型处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(String.class,GsonTools.stringTypeAdapter()));
            //注册int.class, Integer.class处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, GsonTools.longAdapter(0)));
            //注册short.class, Short.class处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(short.class, Short.class, GsonTools.longAdapter(1)));
            //注册long.class, Long.class处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, GsonTools.longAdapter(2)));
            //注册double.class, Double.class处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, GsonTools.longAdapter(3)));
            //注册float.class, Float.class处理器
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(float.class, Float.class, GsonTools.longAdapter(4)));
            //注册反射对象的处理器
            gsonBuilder.registerTypeAdapterFactory(new ReflectiveTypeAdapterFactory(new ConstructorConstructor(val), FieldNamingPolicy.IDENTITY, Excluder.DEFAULT));
            //注册集合的处理器
            gsonBuilder.registerTypeAdapterFactory(new CollectionTypeAdapterFactory(new ConstructorConstructor(val)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gsonBuilder.create();
    }

    /**
     *  通过GSON解析数据
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseDataByGson(String jsonString, Class<T> tClass) {
        if (StringUtil.isStringEmpty(jsonString)) {
            return null;
        }
        T data = null;
        try {
            data = LGson.newGson().fromJson(jsonString, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static <T> T parseDataByGson(JSONObject jsonObject, Class<T> tClass) {
        if (StringUtil.isJSONObjectEmpty(jsonObject)) {
            return null;
        }
        return parseDataByGson(jsonObject.toString(), tClass);
    }

    public static <T> T parseDataByGson(JSONArray jsonArray, Class<T> tClass) {
        if (StringUtil.isJSONArrayEmpty(jsonArray)) {
            return null;
        }
        return parseDataByGson(jsonArray.toString(), tClass);
    }

    public static <T> T parseTypeTokenDataByGson(String jsonString, TypeToken<T> typeToken) {
        if (StringUtil.isStringEmpty(jsonString)) {
            return null;
        }

        T data = null;
        try {
            data = LGson.newGson().fromJson(jsonString, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static <T> T parseTypeTokenDataByGson(JSONArray jsonArray, TypeToken<T> typeToken) {
        if (StringUtil.isJSONArrayEmpty(jsonArray)) {
            return null;
        }
        return parseTypeTokenDataByGson(jsonArray.toString(), typeToken);
    }


    public static <T> T parseTypeTokenDataByGson(JSONObject jsonObject, TypeToken<T> typeToken) {
        if (StringUtil.isJSONObjectEmpty(jsonObject)) {
            return null;
        }
        return parseTypeTokenDataByGson(jsonObject.toString(), typeToken);
    }

    /**
     * 判断对象中的字段是否都为空
     * @param obj
     * @return 返回ture表示所有属性为null  返回false表示不是所有属性都是null
     */
    public static boolean isObjEmpty(Object obj){
        boolean flag = true;
        for (Field f: obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            try {
                if (f.get(obj) != null){
                    flag = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
