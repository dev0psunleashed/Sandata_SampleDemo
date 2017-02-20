package com.sandata.lab.rules.data.service.cache.mapdb;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.camel.PropertyInject;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Cache using Map DB.</p>
 *
 * @author thanhxle
 */
public class MapDBCacheHandler {

	private static final Logger LOG = LoggerFactory.getLogger(MapDBCacheHandler.class);

    @PropertyInject("{{vv.data.service.cache.mapdb.path}}")
    private static String filePath = "S:\\testdir\\";

    @PropertyInject("{{storeExpireSeconds}}")
    private static int storeExpireSeconds = 600;

    final private static String MapName = "EvvDataServiceMap";

    private static DB db = null;

    public MapDBCacheHandler()
    {
        createDB(filePath);
    }

    private static synchronized void createDB(String filePath)
    {
        db =  DBMaker.memoryDB()
                .asyncWriteEnable()
                .make();

        /*
        DB db =  DBMaker.newFileDB((new File(filePath)))
                .snapshotEnable()
                .make();
                */
    }

    private static synchronized DB getDB(String filePath)
    {
        return db;
    }

    /**
     * Store calls retrieved from EVV in levelDB
     * @param calls = list of EVV calls
     */
    public static synchronized void storeData(String cachedKey, String cachedData)
    {
        try
        {
            DB db = getDB(filePath);

            Map<String,String> map ;
            try {

                map = db.createHashMap((MapName))
                        .expireAfterWrite(storeExpireSeconds, TimeUnit.SECONDS).
                                make();
            }
            catch (Exception ex)
            {
                map = db.getHashMap((MapName));
            }

            try {
                try {
                    map.put(cachedKey, cachedData);
                    db.commit();
                }
                catch (Exception ex)  {
                    db.rollback();
                    throw ex;
                }

            } finally {

              //db.close();
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }
    }

    public static synchronized String getCachedData(String key)
    {
        try {

            DB db = getDB(filePath);
            Map<String, Object> map = db.getHashMap((MapName));

            try {
                String  storedData = (String) map.get(key);

                return storedData;

            } catch (Exception ex) {
                db.rollback();

            } finally {
                // Make sure you close the db to shutdown the
                // database and avoid resource leaks.
               //  db.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }



}
