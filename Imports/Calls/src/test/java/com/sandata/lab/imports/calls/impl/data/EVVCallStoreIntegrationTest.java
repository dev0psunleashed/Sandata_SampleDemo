package com.sandata.lab.imports.calls.impl.data;

import com.sandata.lab.imports.calls.BaseTestSupport;
import com.sandata.lab.imports.calls.model.external.EVV.Call;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EVVCallStoreIntegrationTest extends BaseTestSupport {

    private EVVCallStore evvCallStore;

    @Override
    protected void onSetup() throws IOException {

        evvCallStore  = new EVVCallStore();
    }

    @Test
    public void insert_data_into_call_store()
    {
        Call call = new Call();
        call.setSid("sid");

        List<Call> calls = new ArrayList<>();
        calls.add(call);

        evvCallStore.storeCalls(calls);

      //  boolean value = evvCallStore.callExists(call);

       // assertTrue(value);

        List<Call> ucalls = evvCallStore.getUnprocessedCalls();

        assertEquals(call, ucalls.get(0));
    }
}
