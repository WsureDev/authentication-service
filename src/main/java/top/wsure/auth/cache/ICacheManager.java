package top.wsure.auth.cache;

import java.util.Map;
import java.util.Set;

public interface ICacheManager<T> {
     void  putCache(String key, CacheEntity<T> cache);

     void putCache(String key, T data, long timeOut);

     CacheEntity<T> getCacheByKey(String key);

     T getCacheDataByKey(String key);

     Map<String, CacheEntity<T>> getCacheAll();

    boolean isContains(String key);

    void clearAll();

    void clearByKey(String key);

    boolean isTimeOut(String key);

    Set<String> getAllKeys();
}
