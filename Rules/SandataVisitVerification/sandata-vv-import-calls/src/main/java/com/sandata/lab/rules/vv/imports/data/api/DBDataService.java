package com.sandata.lab.rules.vv.imports.data.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

/**
 * 
 * @author thanh.le
 * Define interfaces to interact to EVV database.
 *
 */
public interface DBDataService {

    Object getUnprocessedCalls(String groupKey, String exportKey, int numOfCalls) throws SandataRuntimeException;
}
