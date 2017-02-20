package com.sandata.lab.imports.calls.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

public interface DBDataService {

    Object getUnprocessedCalls(String groupKey, String exportKey, int numOfCalls) throws SandataRuntimeException;
}
