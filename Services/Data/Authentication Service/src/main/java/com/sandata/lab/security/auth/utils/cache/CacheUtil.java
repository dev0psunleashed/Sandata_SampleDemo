package com.sandata.lab.security.auth.utils.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

public class CacheUtil {

    private static CacheManager cacheManager= null;

    public CacheUtil()
    {
        createCache(Caches.BE_TNT_ID_XWALK.getValue());
    }

    public void createCache(String cacheName)
    {
        cacheManager = CacheManager.create();
        cacheManager.addCache(cacheName);

        Cache cache = cacheManager.getCache(cacheName);
        CacheConfiguration config = cache.getCacheConfiguration();
        config.setTimeToIdleSeconds(60);
        config.setTimeToLiveSeconds(600);

    }

    public Object getValue(String cacheName, String key)
    {
        Cache cache = cacheManager.getCache(cacheName);
        Element element = (Element) cache.get(key);

       if(element != null) {
           return element.getObjectValue();
       }

        return null;
    }

    public void putValue(String cacheName, String key, Object value)
    {
        Cache cache = cacheManager.getCache(cacheName);
        cache.put(new Element(key, value));
    }

}
