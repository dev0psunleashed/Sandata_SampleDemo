package com.sandata.lab.rules.data.service.cache.mapdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.rules.data.service.cache.AbstractVVDataServiceStore;
import com.sandata.lab.rules.data.service.cache.redis.VVDataServiceRedisStore;

/**
 * <p>Cache using Map DB.</p>
 *
 * @author thanhxle
 */
public class MapDBCacheProxy extends AbstractVVDataServiceStore {

	 private static final Logger LOG = LoggerFactory.getLogger(VVDataServiceRedisStore.class);

	 private MapDBCacheHandler mapDBCacheHandler;

	    

	 public MapDBCacheHandler getMapDBCacheHandler() {
		 return mapDBCacheHandler;
	 }

	 public void setMapDBCacheHandler(MapDBCacheHandler mapDBCacheHandler) {
		 this.mapDBCacheHandler = mapDBCacheHandler;
	 }

	 @Override
	 public void storeData(String cachedKey, String cachedData) {
		 mapDBCacheHandler.storeData(cachedKey,cachedData);
	 }

	 @Override
	 public String getCachedData(String cachedKey) {
		 return mapDBCacheHandler.getCachedData(cachedKey);
	 }
   

}
