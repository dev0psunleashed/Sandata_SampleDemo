package com.sandata.lab.eligibility.leveldb;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.OverlappingFileLockException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.eligibility.api.KeyValueStorageService;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Wrapper class for LevelDB to manage access to a shared LevelDB file.
 */
public class LevelDbStorageService implements KeyValueStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LevelDbStorageService.class);
    private static final String KEY_MUST_NOT_BE_BLANK_MESSAGE = "key must not be null, empty or whitspace";

    private File file;

    private int maxRetry = 3;

    // Milliseconds to wait for next retry
    private int retryWait = 1000;

    /**
     * Initializes a instance of {@link LevelDbStorageService} with specified
     * {@code dbDirectory}
     * 
     * @param dbDirectory
     *            the directory of LevelDB
     * @throws IllegalArgumentException
     *             (runtime) if dbDirectory is null, empty or whitespace; if
     *             value is null
     */
    public LevelDbStorageService(String dbDirectory) {
        Validate.isTrue(StringUtils.isNotBlank(dbDirectory), "dbDirectory must not be null, empty or whitspace");

        this.file = new File(dbDirectory);
    }

    /**
     * Gets max number of retries
     * 
     * @return max number of retries. Default is 3 if it not set.
     */
    public int getMaxRetry() {
        return maxRetry;
    }

    /**
     * Set maximum number of retries when cannot open LevelDB (because of being
     * locked by other processes - OverlappingFileLockException)
     * 
     * @param maxRetry
     *            maximum number of retries. If maxRetry < 0, it will be set to
     *            0
     */
    public void setMaxRetry(int maxRetry) {
        if (maxRetry < 0) {
            this.maxRetry = 0;
        } else {
            this.maxRetry = maxRetry;
        }
    }

    /**
     * Gets wait time in milliseconds between retries
     * 
     * @return wait time in milliseconds. Default is 1000 miliseconds if it is
     *         not set.
     */
    public int getRetryWait() {
        return retryWait;
    }

    /**
     * Sets wait time in milliseconds between retries
     * 
     * @param retryWait
     *            wait time in milliseconds before retrying. If retryWait < 0,
     *            it will be set to 0
     */
    public void setRetryWait(int retryWait) {
        if (retryWait < 0) {
            this.retryWait = 0;
        } else {
            this.retryWait = retryWait;
        }
    }

    /**
     * Puts provided {@code value} for the specified {@code key} to LevelDB
     * 
     * @param key
     *            the key associated with value to put to LevelDB
     * @param value
     *            the value to put to LevelDB for the key
     * @throws IllegalArgumentException
     *             (runtime) if key is null, empty or whitespace; if value is
     *             null
     * @throws SandataRuntimeException
     *             (runtime) if an exception happens when putting value for the
     *             key to LevelDB
     */
    @Override
    public void put(String key, Serializable value) {
        Validate.isTrue(StringUtils.isNotBlank(key), KEY_MUST_NOT_BE_BLANK_MESSAGE);
        Validate.isTrue(value != null, "value must not be null");
        
        final String methodName = "put";

        try (DB db = getDb()) {

            LOGGER.debug(LoggingUtils.getLogMessageInfo(this, methodName, "Putting key={}, value={} to LevelDB with file {}"),
                    new Object[] { key, value, getFile() });

            db.put(key.getBytes(), SerializationUtils.serialize(value));

            LOGGER.debug(LoggingUtils.getLogMessageInfo(this, methodName, "Put key={}, value={} to LevelDB with file {}"),
                    new Object[] { key, value, getFile() });

        } catch (SerializationException e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                    new StringBuilder("Error when serializing value= ").append(value)
                            .append(" for key=").append(key)
                            .append(" before putting to LevelDB with file ").append(getFile()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);

        } catch (DBException e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                    new StringBuilder("Error when putting value=").append(value)
                            .append(" for key=").append(key)
                            .append(" to LevelDB with file ").append(getFile()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);

        } catch (IOException e) {
            handleExceptionWhenClosingDb(methodName, e);
        }
    }

    /**
     * Gets value from LevelDB for the specified {@code key}
     * 
     * @param key
     *            the key to get value from LevelDB
     * @throws IllegalArgumentException
     *             (runtime) if key is null, empty or whitespace
     * @throws SandataRuntimeException
     *             (runtime) if an exception happens when getting value for the
     *             key
     */
    @Override
    public Object get(String key) {
        Validate.isTrue(StringUtils.isNotBlank(key), KEY_MUST_NOT_BE_BLANK_MESSAGE);

        final String methodName = "get";

        Object value = null;

        try (DB db = getDb()) {

            LOGGER.debug(LoggingUtils.getLogMessageInfo(this, methodName, "Getting value for key={} from LevelDB with file {}"),
                    new Object[] { key, getFile() });

            byte[] bytes = db.get(key.getBytes());
            if (bytes != null) {
                value = SerializationUtils.deserialize(bytes);
            }

            LOGGER.debug(LoggingUtils.getLogMessageInfo(this, methodName, "Got value={} for key={} from to LevelDB with file {}"),
                    new Object[] { key, value == null ? "NULL" : value, getFile() });

        } catch (DBException e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                    new StringBuilder("Error when getting value for key=").append(key)
                            .append(" from LevelDB with file ").append(getFile()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);

        } catch (SerializationException e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                    new StringBuilder("Error when deserializing value for key=").append(key)
                            .append(" from LevelDB with file ").append(getFile()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);

        } catch (IOException e) {
            handleExceptionWhenClosingDb(methodName, e);
        }

        return value;
    }

    /**
     * Gets Gets value from LevelDB for the specified {@code key} and cast value
     * to type of {@code clazz}
     * 
     * @param key
     *            the key to get value from LevelDB
     * @param clazz
     *            the type of value to return
     * @see #get(String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {
        return (T) get(key);
    }

    /**
     * Deletes the specified {@code key} out of LevelDB
     * 
     * @param key
     *            the key to delete out of LevelDB
     * 
     * @throws IllegalArgumentException
     *             (runtime) if key is null, empty or whitespace
     * @throws SandataRuntimeException
     *             (runtime) if an exception happens when deleting the key
     * 
     */
    @Override
    public void delete(String key) {
        Validate.isTrue(StringUtils.isNotBlank(key), KEY_MUST_NOT_BE_BLANK_MESSAGE);

        final String methodName = "delete";

        try (DB db = getDb()) {

            LOGGER.debug(LoggingUtils.getLogMessageInfo(this, methodName, "Deleting key={} out of LevelDB with file {}"),
                    new Object[] { key, getFile() });

            db.delete(key.getBytes());

            LOGGER.debug(LoggingUtils.getLogMessageInfo(this, methodName, "Deleted key={} out of LevelDB with file {}"),
                    new Object[] { key, getFile() });

        } catch (DBException e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                    new StringBuilder("Error when deleting key=").append(key)
                            .append(" out of LevelDB with file ").append(getFile()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);

        } catch (IOException e) {
            handleExceptionWhenClosingDb(methodName, e);
        }
    }

    private File getFile() {
        return this.file;
    }

    private DB getDb() {

        final String methodName = "getDb";
        DB db = null;
        int retryCount = 0;

        Options options = new Options().createIfMissing(true);

        while (retryCount <= getMaxRetry() && db == null) {
            try {

                LOGGER.debug(
                        LoggingUtils.getLogMessageInfo(this, methodName,
                                "[RETRY_COUNT:{}][RETRY_WAIT_MS:{}] Opening LevelDB with file {}"),
                        retryCount, getRetryWait(), getFile());

                getFile().getParentFile().mkdirs();
                db = Iq80DBFactory.factory.open(file, options);

                LOGGER.debug(
                        LoggingUtils.getLogMessageInfo(this, methodName,
                                "[RETRY_COUNT:{}][RETRY_WAIT_MS:{}] Opened LevelDB with file {}"),
                        retryCount, getRetryWait(), getFile());

            } catch (OverlappingFileLockException e) {
                if (retryCount == getMaxRetry()) {
                    String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                            String.format("Give up after %s retries. Error when opening LevelDB with file %s",
                                    retryCount, getFile()));
                    LOGGER.error(errorMessage, e);
                    throw new SandataRuntimeException(errorMessage, e);
                } else {
                    retryCount++;

                    LOGGER.debug(
                            LoggingUtils.getLogMessageInfo(this, methodName,
                                    "[RETRY_COUNT:{}][RETRY_WAIT_MS:{}] Waiting for opening LevelDB with file {}"),
                            retryCount, getRetryWait(), getFile());

                    waitForNextRetry();
                }

            } catch (SecurityException e) {
                String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                        String.format("Error when creating the directory named %s", getFile()));
                LOGGER.error(errorMessage, e);
                throw new SandataRuntimeException(errorMessage, e);

            } catch (IOException e) {
                String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                        String.format("Exception when opening LevelDB with file %s", getFile()));
                LOGGER.error(errorMessage, e);
                throw new SandataRuntimeException(errorMessage, e);
            }
        }

        return db;
    }

    private void waitForNextRetry() {
        try {
            Thread.sleep(getRetryWait(), RandomUtils.nextInt(1000000));
        } catch (InterruptedException ie) {
            // ignore the exception
        }
    }

    private void handleExceptionWhenClosingDb(String methodName, IOException e) {
        String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName,
                new StringBuilder("Error when closing LevelDB with file ").append(getFile()).toString());
        LOGGER.error(errorMessage, e);
        throw new SandataRuntimeException(errorMessage, e);
    }

}
