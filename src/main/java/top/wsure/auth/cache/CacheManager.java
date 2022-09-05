package top.wsure.auth.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/*
    FileName:   CacheManager
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class CacheManager<T> implements ICacheManager<T>{
    private Map<String, CacheEntity<T>> caches = null;

    public CacheManager(Map<String, CacheEntity<T>> caches) {
        this.caches = caches;
    }

    public CacheManager(){
        this.caches = new HashMap<>();
    }

    @Override
    public void putCache(String key, CacheEntity<T> cache) {
        clearCache();

        caches.put(key, cache);
    }

    @Override
    public void putCache(String key, T data, long timeOut) {
        clearCache();

        timeOut = Math.max(timeOut,0L);
        caches.put(key,new CacheEntity<T>(data,timeOut == 0L ? 0L : ( System.currentTimeMillis() + timeOut)));
    }

    @Override
    public CacheEntity<T> getCacheByKey(String key) {
        clearCache();

        return caches.get(key);
    }

    @Override
    public T getCacheDataByKey(String key) {
        clearCache();

        return Optional.ofNullable(caches.get(key))
                .map(CacheEntity::getData)
                .orElse(null);
    }

    @Override
    public Map<String, CacheEntity<T>> getCacheAll() {
        clearCache();

        return caches;
    }

    @Override
    public boolean isContains(String key) {
        clearCache();

        return caches.containsKey(key);
    }

    @Override
    public void clearAll() {
        caches.clear();
    }

    @Override
    public void clearByKey(String key) {
        clearCache();

        caches.remove(key);
    }

    @Override
    public boolean isTimeOut(String key) {
        clearCache();

        return !caches.containsKey(key);
    }

    @Override
    public Set<String> getAllKeys() {
        clearCache();

        return caches.keySet();
    }

    private void clearCache(){
        long now = System.currentTimeMillis();
        for(Map.Entry<String,CacheEntity<T>> e: caches.entrySet()){
            long expired = e.getValue().getExpired();
            if(expired != 0L && now > expired) {
                caches.remove(e.getKey());
            }
        }
    }
}
