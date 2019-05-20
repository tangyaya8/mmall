package com.tangyaya8.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author tangxuejun
 * 用guava做本地缓存
 * @version 2019-05-07 17:06
 */
@Slf4j
public class TokenCache {
    //LRU
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<>() {
                //默认的数据加载实现，当调用get读取值的时候，如果key没有对应的值，就调用这个方法进行加载
                @Override
                public String load(String s) {
                    return "";
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getKey(String key) {
        String value;
        try {
            value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            log.error("localCache get a error", e);
        }
        return null;
    }


}
