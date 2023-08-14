package com.uralkali.server.utils;

import com.google.gson.*;

import java.util.Base64;

public class Json {

    private static Gson mGson;

    private static void init() {
        if (mGson != null) {
            return;
        }
        mGson = new GsonBuilder()
                //Формат даты
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

                //Bytes в виде строки Base64
                .registerTypeAdapter(byte[].class, (JsonDeserializer<byte[]>) (json, typeOfT, context) -> Base64.getDecoder().decode(json.getAsString().getBytes()))
                .registerTypeAdapter(byte[].class, (JsonSerializer<byte[]>) (src, typeOfSrc, context) -> new JsonPrimitive(Base64.getEncoder().encodeToString(src)))

                .setLenient()
                .create();
    }

    public static Gson getGson() {
        init();
        return mGson;
    }

    public static <T> T fromJson(String json, Class<T> c) {
        init();
        return mGson.fromJson(json, c);
    }

    public static <T> String toJson(T o) {
        init();
        return mGson.toJson(o);
    }

}
