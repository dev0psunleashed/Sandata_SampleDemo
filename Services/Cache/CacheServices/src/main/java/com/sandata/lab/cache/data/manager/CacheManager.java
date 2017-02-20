/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.cache.data.manager;

import com.sandata.lab.cache.app.AppContext;
import com.sandata.lab.cache.utils.log.CacheServiceLogger;
import com.sandata.lab.common.utils.error.AbstractErrorHandler;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.CamelContext;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.Options;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Implementation of CacheManager to handle caching memory map leveldb.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class CacheManager<T> extends AbstractErrorHandler {

    private String databasePath;

    private JniDBFactory factory;

    private Options options;

    public static CacheManager getInstance() {

        CamelContext context = AppContext.getContext();
        if (context != null) {
            return (CacheManager) context.getRegistry().lookupByName("cacheManager");
        }

        return new CacheManager<>();
    }

    public CacheManager() {
        super(CacheServiceLogger.CreateLogger());
    }

    public CacheManager(final String databasePath) {
        super(CacheServiceLogger.CreateLogger());

        this.databasePath = databasePath;
    }

    public String put(final T data, final String key) throws SandataRuntimeException {

        if (data == null) {
            throw new SandataRuntimeException("CacheManager::put: data == null");
        }

        if (key == null || key.length() == 0) {
            throw new SandataRuntimeException("CacheManager::put: key is null or empty!");
        }

        getLogger().info(String.format("put: UUID: %s", key));

        JniDBFactory.pushMemoryPool(1024 * 512);

        try(org.iq80.leveldb.DB db = getFactory().open(new File(getDatabasePath()), getOptions())) {
            db.put(objectToBytes(key), objectToBytes(data));
        }
        catch (Exception e) {
            handleFatalError(e, "CacheManager::put");
        }
        finally {
            JniDBFactory.popMemoryPool();
        }

        return key;
    }

    public String put(final T data) throws SandataRuntimeException {

        String uuid = createUUID();
        return put(data, uuid);
    }

    public T get(String key) throws SandataRuntimeException {

        getLogger().info(String.format("get: UUID: %s", key));

        JniDBFactory.pushMemoryPool(1024 * 512);

        Object object = null;
        try(org.iq80.leveldb.DB db = getFactory().open(new File(getDatabasePath()), getOptions())) {
            byte[] data = db.get(objectToBytes(key));
            object = bytesToObject(data);
        }
        catch (Exception e) {
            handleFatalError(e, "CacheManager::get");
        }
        finally {
            JniDBFactory.popMemoryPool();
        }

        return (T)object;
    }

    public String getDatabasePath() {
        return databasePath;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public String createUUID() {
        return UUID.randomUUID().toString();
    }

    private Object bytesToObject(byte[] bytes) throws Exception {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        Object object = null;
        try {
            in = new ObjectInputStream(bis);
            object = in.readObject();
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return object;
    }

    private byte[] objectToBytes(Object object) throws Exception {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            bytes = bos.toByteArray();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return bytes;
    }

    private byte[] longToBytes(long longValue) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);
        buffer.putLong(longValue);
        return buffer.array();
    }

    private long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public JniDBFactory getFactory() {
        if (factory == null) {
            factory = new JniDBFactory();
        }
        return factory;
    }

    public void setFactory(JniDBFactory factory) {
        this.factory = factory;
    }

    public Options getOptions() {
        if (options == null) {
            options = new Options();
            this.options.createIfMissing(true);
        }
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
