package com.sandata.lab.cache.data.manager;

import com.sandata.lab.cache.BaseTestSupport;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.cache.Cache;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.Options;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.File;

/**
 * CacheManager unit tests.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
@RunWith(JMockit.class)
public class CacheManagerTests extends BaseTestSupport {

    private CacheManager<Cache> cacheManager;

    @Injectable
    private JniDBFactory factory;

    @Injectable
    private Options options;

    private String databasePath;

    @Test
    public void should_verify_cache_manager_get_calls_required_factory_methods_are_called_as_expected() throws Exception {

        try {
            cacheManager.get("12345");
        }
        catch (Exception e) {
            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().contains("CacheManager::get: Fatal: EXCEPTION: java.io.EOFException: null"));
        }

        new Verifications() {{

            factory.open(withAny(new File(databasePath)), options); times = 1;
        }};
    }

    @Test
    public void should_verify_cache_manager_put_calls_required_factory_methods_in_strict_order() throws Exception {

        new StrictExpectations() {{

            JniDBFactory.pushMemoryPool(1024 * 512); times = 1;
            factory.open(withAny(new File(databasePath)), options); times = 1;
            JniDBFactory.popMemoryPool(); times = 1;
        }};

        String key = cacheManager.put(new Cache(), "12345");

        Assert.notNull(key);
        Assert.isTrue(key.equals("12345"));

        new FullVerifications(factory) {};
    }

    @Test
    public void should_throw_exception_when_key_is_empty() throws Exception {

        try {
            cacheManager.put(new Cache(), "");

            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {

            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("CacheManager::put: key is null or empty!"));
        }
    }

    @Test
    public void should_throw_exception_when_key_is_null() throws Exception {
    
        try {
            cacheManager.put(new Cache(), null);
            
            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {
    
            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("CacheManager::put: key is null or empty!"));
        }
    }
    
    @Test
    public void should_throw_exception_when_data_is_null() throws Exception {
    
        try {
            cacheManager.put(null, null);
            
            // Should not hit this since we are expecting an exception
            Assert.isTrue(false);
        }
        catch(Exception e) {
    
            Assert.isInstanceOf(SandataRuntimeException.class, e);
            Assert.isTrue(e.getMessage().equals("CacheManager::put: data == null"));
        }
    }

    @Override
    protected void onSetup() throws Exception {
        databasePath = "target\\leveldb";
        cacheManager = CacheManager.getInstance();
        cacheManager.setDatabasePath(databasePath);
        cacheManager.setFactory(factory);
        cacheManager.setOptions(options);
    }
}
