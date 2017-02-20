package com.sandata.lab.dl.db.pool.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

/**
 * Contract for handling different pool states.
 * <p/>
 *
 * @author David Rutgos
 */
public interface ConnectionPoolDataHandler {

    void destroyConnection() throws SandataRuntimeException;
}
