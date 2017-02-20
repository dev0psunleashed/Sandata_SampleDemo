package com.sandata.lab.imports.calls.impl.data;

import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.imports.calls.model.external.EVV.Call;
import com.sandata.lab.imports.calls.utils.log.CallImportLogger;
import org.apache.camel.PropertyInject;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.mapdb.DB;
import org.mapdb.DBMaker;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EVVCallStore
{
    @PropertyInject("{{evv.callstore.path}}")
    private static String filePath = "S:\\testdir\\";

    @PropertyInject("{{storeExpireSeconds}}")
    private static int storeExpireSeconds = 600;

    final private static String MapName = "callsMap";

    private static DB db = null;

    public EVVCallStore()
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
    public static synchronized void storeCalls(List<Call> calls)
    {
        try
        {
            DB db = getDB(filePath);

            Map<String, Call> map;

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

                    for (Call call : calls) {

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

    public static synchronized Call getCallFromStore(Call call)
    {
        try {

            DB db = getDB(filePath);
            Map<String, Object> map = db.getHashMap((MapName));

            try {
                String key = getKey(call);
                Call storedCall = (Call) map.get(key);

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

    public static synchronized void deleteCallFromStore(Call call)
    {
        try {

            DB db = getDB(filePath);
            Map<String, Call> map = db.getHashMap((MapName));

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
    public static synchronized List<Call> getUnprocessedCalls() {

        SandataLogger sandataLogger = CallImportLogger.CreateLogger();

        List<Call> calls = new ArrayList<>();
        DB db = null;

        try {
            db  = getDB(filePath);


            Map<Object, Object> map;

            try {

                map = db.getHashMap((MapName));
                sandataLogger.logger().info(String.format("Retrieved HashMap %s", MapName));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return null;
            }


            if(map != null) {

                for (Map.Entry<Object, Object> entry : map.entrySet()) {

                    Call call = (Call) entry.getValue();
                    calls.add(call);
                }

                sandataLogger.logger().info(String.format("Returning %d from local call store", calls.size()));


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


    private static String getKey(Call call)
    {
        String key = "";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(key);

        if(StringUtils.isNotEmpty(call.getDnis()))
        {
            stringBuilder.append(call.getDnis());
        }

        if(call.getCall_dtimeUTC() != null)
        {
            DateTime dateTime = call.getCall_dtimeUTC();
            stringBuilder.append(Long.toString(dateTime.getMillis()));
        }

        if(StringUtils.isNotEmpty(call.getAni()))
        {
            stringBuilder.append(call.getAni());
        }

        return stringBuilder.toString();
    }
}
