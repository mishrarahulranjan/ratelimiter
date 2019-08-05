package com.ratelimiter.api.cache;

import java.util.HashMap;
import java.util.Map;

/*
In memory cache to keep the user access count
 */
public class InMemoryCache {

    private static Map<String,Object> cache = new HashMap<>(10);

    public static void put(String key, Object value){
        cache.put(key,value);
    }


    public static Object get(String key){
        return cache.get(key);
    }
}
