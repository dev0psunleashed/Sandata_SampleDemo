package com.sandata.lab.exports.payroll.utils;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.core.Ehcache;
import org.ehcache.xml.model.ObjectFactory;

public class SandataCache {

    CacheManager cacheManager;
    Cache cache;

    SandataCache(String cacheName, Class keyClass, Class valueClass)
    {
         cacheManager
                = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(cacheName,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(keyClass, valueClass))
                .build(true);
    }

    public void createCache(String cacheName, Class keyClass, Class valueClass)
    {
        this.cache = cacheManager.createCache(cacheName,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(keyClass, valueClass).build());
    }

    public void openCache(String cacheName, Class keyClass, Class valueClass) {
        this.cache = cacheManager.getCache(cacheName, keyClass, valueClass);
    }

    public Object getValue(Object key) {
        return (Object) this.cache.get(key);
    }

    public void closeCache(String cacheName) {
        cacheManager.removeCache(cacheName);
    }
}
