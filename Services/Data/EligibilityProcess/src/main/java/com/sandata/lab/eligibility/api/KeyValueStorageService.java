package com.sandata.lab.eligibility.api;

import java.io.Serializable;

/**
 * Interface to put/get/delete key-value pairs in storage.
 */
public interface KeyValueStorageService {
    void put(String key, Serializable value);
    Object get(String key);
    <T> T get(String key, Class<T> clazz);
    void delete(String key);
}
