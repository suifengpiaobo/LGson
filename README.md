## 本库主要解决:
 1、 要{}后端给[]返回实例对象
 2、 要[]后端给{}返回空数组
 3、 要int.class, Integer.class,short.class, Short.class,long.class, Long.class,double.class, Double.class,
     float.class, Float.class后端给的非数字类型返回0
 4、 要String后端给了[],{}等类型返回""

## 1、项目中添加依赖方法：
gradle：
```
implementation 'com.google.code.gson:gson:2.8.6'
implementation 'com.zxl.lgson:lgson:1.0.1'
```

maven:
```
<dependency>
  <groupId>com.zxl.lgson</groupId>
  <artifactId>lgson</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

lvy:
```
<dependency org='com.zxl.lgson' name='lgson' rev='1.0.1'>
  <artifact name='lgson' ext='pom' ></artifact>
</dependency>
```

## 2、使用方法：
```
LGson工具提供以下方法：
public static <T> T parseDataByGson(String jsonString, Class<T> tClass){}
public static <T> T parseDataByGson(JSONObject jsonObject, Class<T> tClass){}
public static <T> T parseDataByGson(JSONArray jsonArray, Class<T> tClass){}
public static <T> T parseTypeTokenDataByGson(String jsonString, TypeToken<T> typeToken){}
public static <T> T parseTypeTokenDataByGson(JSONArray jsonArray, TypeToken<T> typeToken){}
public static <T> T parseTypeTokenDataByGson(JSONObject jsonObject, TypeToken<T> typeToken){}
```
