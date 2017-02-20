package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.rules.vv.imports.model.EVVCall;
import org.apache.camel.PropertyInject;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapDBCacheHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MapDBCacheHandler.class);

    @PropertyInject("{{evv.callstore.path}}")
    private static String filePath = "S:\\testdir\\";

    @PropertyInject("{{storeExpireSeconds}}")
    private static int storeExpireSeconds = 600;

    final private static String MapName = "callsMap";

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
    public static synchronized void storeCalls(List<EVVCall> calls)
    {
        try
        {
            DB db = getDB(filePath);

            Map<String, EVVCall> map;

            try {

                map = db.createHashMap((MapName))
                        .expireAfterWrite(storeExpireSeconds, TimeUnit.SECONDS).
                                make();
            }
            catch (Exception ex)
            {
                map = db.getHashMap((MapName));
            }

            try
            {
                try {

                    for (EVVCall call : calls) {

                        String key = getKey(call);
                        map.put(key, call);
                    }

                    db.commit();
                }
                catch (Exception ex)
                {
                    db.rollback();
                    throw ex;

                }

            } finally
            {

              //db.close();
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }
    }

    public static synchronized EVVCall getCallFromStore(EVVCall call)
    {
        try {

            DB db = getDB(filePath);
            Map<String, Object> map = db.getHashMap((MapName));

            try {
                String key = getKey(call);
                EVVCall storedCall = (EVVCall) map.get(key);

                return storedCall;

            }
            catch (Exception ex)
            {
                db.rollback();

            }
            finally {
                // Make sure you close the db to shutdown the
                // database and avoid resource leaks.
               //  db.close();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static synchronized void deleteCallFromStore(EVVCall call)
    {
        try {

            DB db = getDB(filePath);
            Map<String, EVVCall> map = db.getHashMap((MapName));

            try {
                String key = getKey(call);
                map.remove(key);
                db.commit();
            }
            catch (Exception ex)
            {
                db.rollback();

            }
            finally {
                // Make sure you close the db to shutdown the
                // database and avoid resource leaks.
              //  db.close();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


    /**
     * Get all calls stored in levelDB for a group
     * @return
     */
    public static synchronized List<EVVCall> getUnprocessedCalls() {

        //SandataLogger sandataLogger = CallImportLogger.CreateLogger();

        List<EVVCall> calls = new ArrayList<>();
        DB db = null;

        try {
            db  = getDB(filePath);


            Map<Object, Object> map;

            try {

                map = db.getHashMap((MapName));
                LOG.info(String.format("Retrieved HashMap %s", MapName));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return null;
            }


            if(map != null) {

                for (Map.Entry<Object, Object> entry : map.entrySet()) {

                	EVVCall call = (EVVCall) entry.getValue();
                    calls.add(call);
                }

                LOG.info(String.format("Returning %d from local call store", calls.size()));


            }

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {

                // Make sure you close the db to shutdown the
                // database and avoid resource leaks.
                // db.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return calls;

    }


    private static String getKey(EVVCall call)
    {
        String key = "";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(key);

        if(StringUtils.isNotEmpty(call.getDnis()))
        {
            stringBuilder.append(call.getDnis());
        }

        if(call.getCallDtimeUTC() != null)
        {
            DateTime dateTime = call.getCallDtimeUTC();
            stringBuilder.append(Long.toString(dateTime.getMillis()));
        }

        if(StringUtils.isNotEmpty(call.getAni()))
        {
            stringBuilder.append(call.getAni());
        }

        return stringBuilder.toString();
    }
}
