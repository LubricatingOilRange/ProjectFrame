package com.frame.projectframe.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static Gson _gson;

    public static Gson defaultGson() {
        if (_gson == null) {
            _gson = new GsonBuilder()
                    .setDateFormat("yyyy/MM/dd HH:mm:ss")
//                    .registerTypeAdapter(Date.class,new DateSerizlier())
                    .create();
        }
        return _gson;
    }
}
