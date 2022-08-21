package com.mi_two_bot.core.utils;

import com.jayway.jsonpath.JsonPath;

public class JsonUtils {
    public static String getElementByJsonpath(String response, String path) {
        return JsonPath.read(response, path).toString();
    }
}
