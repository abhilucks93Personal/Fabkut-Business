package com.abhi.fabkutbusiness.retrofit;

import com.google.gson.Gson;

/**
 ** 4/11/15.
 */
public class BaseModel {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String emptyJson() {
        return "{}";
    }

    public static <T> T fromJson(Class<T> tClass, String s) {
        Gson gson = new Gson();
        return gson.fromJson(s, tClass);
    }
}
