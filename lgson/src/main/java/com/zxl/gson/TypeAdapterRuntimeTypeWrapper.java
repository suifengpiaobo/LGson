package com.zxl.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Description
 * @author: zxl
 * create on 2019-11-07 20:19.
 */
public class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson context;
    private final TypeAdapter<T> delegate;
    private final Type type;

    public TypeAdapterRuntimeTypeWrapper(Gson context, TypeAdapter<T> delegate, Type type) {
        this.context = context;
        this.delegate = delegate;
        this.type = type;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        TypeAdapter chosen = delegate;
        Type runtimeType = getRuntimeTypeIfMoreSpecific(type, value);
        if (runtimeType != type) {
            TypeAdapter runtimeTypeAdapter = context.getAdapter(TypeToken.get(runtimeType));
            if (!(runtimeTypeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                //注册一个类型适配器
                chosen = runtimeTypeAdapter;
            } else if (!(delegate instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                //用户为基类注册了类型适配器，因此我们更喜欢它而不是运行时类型的反射类型适配器
                chosen = delegate;
            } else {
                //为运行时类型使用类型适配器
                chosen = runtimeTypeAdapter;
            }
        }
        chosen.write(out, value);
    }

    @Override
    public T read(JsonReader in) throws IOException {
        return delegate.read(in);
    }

    /**
     * 查找兼容的运行时类型
     */
    private Type getRuntimeTypeIfMoreSpecific(Type type, Object value) {
        if (value != null
                && (type == Object.class || type instanceof TypeVariable<?> || type instanceof Class<?>)) {
            type = value.getClass();
        }
        return type;
    }
}
