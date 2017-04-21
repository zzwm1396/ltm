package com.lb.core.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import java.lang.reflect.Type;

/**
 * Gson构建器
 * Created by libo on 2017/4/21.
 */
public final class GsonFactory {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

    private static volatile Gson gson = GSON_BUILDER.create();

    /**
     * 注册Gson解析对象.
     *
     * @param type Gson解析对象类型
     * @param typeAdapter Gson解析对象适配器
     */
    public static synchronized void registerTypeAdapter(final Type type, final TypeAdapter typeAdapter) {
        GSON_BUILDER.registerTypeAdapter(type, typeAdapter);
        gson = GSON_BUILDER.create();
    }

    /**
     * 获取Gson实例.
     *
     * @return Gson实例
     */
    public static Gson getGson() {
        return gson;
    }
}
