package com.sandata.lab.cache.data.manager;

import com.sandata.lab.cache.BaseTestSupport;
import com.sandata.lab.data.model.cache.Cache;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.status.Status;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * CacheManager integration tests.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class CacheManagerIntegrationTests extends BaseTestSupport {

    private CacheManager<Cache> cacheManager;

    @Test
    public void should_retrieve_given_object_from_leveldb_cache() throws Exception {

        Cache cache = createCache();

        Assert.notNull(cache);

        String key = objectToCache(cache);

        Assert.notNull(key);

        Cache cacheObject = cacheManager.get(key);

        Assert.notNull(cacheObject);

        Assert.isTrue(cache.getStatus() == Status.PENDING);
        Assert.notNull(cache.getRequest());

        Object request = cache.getRequest();

        Assert.isTrue(request instanceof Schedule);

        Schedule schedule = (Schedule) request;
        Assert.isTrue(schedule.getScheduleId() == 999);
        Assert.isTrue(schedule.getRestrictions().equals("JUnit Test"));
    }

    @Test
    public void should_put_given_object_to_leveldb_cache() throws Exception {

        Cache cache = createCache();

        Assert.notNull(cache);

        Assert.notNull(objectToCache(cache));
    }

    private String objectToCache(final Cache cache) {

        return cacheManager.put(cache);
    }

    private Cache createCache() {

        Schedule schedule = new Schedule();
        schedule.setScheduleId(999);
        schedule.setRestrictions("JUnit Test");

        Cache cache = new Cache();
        cache.setStatus(Status.PENDING);
        cache.setRequest(schedule);

        return cache;
    }

    @Override
    protected void onSetup() throws Exception {
        cacheManager = CacheManager.getInstance();
        cacheManager.setDatabasePath("target\\leveldb");
    }
}
