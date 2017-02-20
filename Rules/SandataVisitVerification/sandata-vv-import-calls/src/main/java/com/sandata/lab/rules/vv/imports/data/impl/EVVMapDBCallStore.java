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

public class EVVMapDBCallStore extends AbstractEVVCallStore {

    private static final Logger LOG = LoggerFactory.getLogger(EVVMapDBCallStore.class);

    private MapDBCacheHandler mapDBCacheHandler;


    public MapDBCacheHandler getMapDBCacheHandler() {
        return mapDBCacheHandler;
    }

    public void setMapDBCacheHandler(MapDBCacheHandler mapDBCacheHandler) {
        this.mapDBCacheHandler = mapDBCacheHandler;
    }

    @Override
    public void storeCalls(List<EVVCall> calls) {
        mapDBCacheHandler.storeCalls(calls);
    }

    @Override
    public EVVCall getCallFromStore(EVVCall evvCall) {
        return mapDBCacheHandler.getCallFromStore(evvCall);
    }

    @Override
    public void deleteCallFromStore(EVVCall evvCall) {

        mapDBCacheHandler.deleteCallFromStore(evvCall);
    }

    @Override
    public List<EVVCall> getUnprocessedCalls() {
        return mapDBCacheHandler.getUnprocessedCalls();
    }
}
