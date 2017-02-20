package com.sandata.lab.imports.calls.impl;

import com.sandata.lab.imports.calls.api.DataService;
import com.sandata.lab.imports.calls.impl.data.EVVCallStore;
import com.sandata.lab.imports.calls.impl.data.EVVOracleDataService;
import com.sandata.lab.imports.calls.impl.data.transformers.EVVCallTransformer;
import com.sandata.lab.imports.calls.model.external.EVV.Call;
import com.sandata.lab.imports.calls.utils.log.CallImportLogger;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class VisitEventDataService implements DataService
{
    private EVVOracleDataService evvOracleDataService;

    private EVVCallTransformer evvCallTransformer;

    private EVVCallStore evvCallStore;

    @PropertyInject("{{numberOfCallsToProcess}}")
    private int numCalls;

    @PropertyInject("{{evv.callExportKey}}")
    private String exportKey = "118";

    public void setEvvCallTransformer(EVVCallTransformer evvCallTransformer)
    {
        this.evvCallTransformer = evvCallTransformer;
    }

    public void setEvvOracleDataService(EVVOracleDataService evvOracleDataService)
    {
        this.evvOracleDataService = evvOracleDataService;
    }

    public void setEvvCallStore(EVVCallStore evvCallStore)
    {
        this.evvCallStore = evvCallStore;
    }

    /**
     * Get calls from EVV by group key
     * @param exchange contains the group key to retrieve calls for
     */
    public void getUnprocessedCalls(Exchange exchange) throws SandataRuntimeException
    {
        SandataLogger sandataLogger = CallImportLogger.CreateLogger();

        try {

            String groupKey = (String) exchange.getIn().getBody();

            List<Call> calls = evvOracleDataService.getUnprocessedCalls(groupKey, exportKey, numCalls);

            //Discontinue processing if there are no calls
            if (calls == null || calls.size() == 0) {

                sandataLogger.info(String.format("There were no calls for group %s",groupKey));
                exchange.getIn().setBody(null);
                return;
            }


            sandataLogger.info(String.format("Retrieved %d calls for group %s", calls.size(), groupKey));

            List<VisitEventDNISGroup> visitEventDNISGroupList = transformCallsToVisitEventGroups(groupKey, calls);


            if (visitEventDNISGroupList != null) {

                sandataLogger.info(String.format("%d unique VisitEventGroups have been created", visitEventDNISGroupList.size()));
                exchange.getIn().setBody(visitEventDNISGroupList);
                exchange.getIn().setHeader("calls", calls);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            String errMsg = String.format("%s: %s", getClass().getName(), ex.getMessage());
            throw new SandataRuntimeException(errMsg);
        }
        finally {
            sandataLogger.stop();
        }
    }

    /**
     * Store call retrieved from EVV in a store to prevent duplicate calls from being put on queue
     * @param calls
     */
    private void storeUnprocessedCalls(String groupKey, List<Call> calls)
    {
        evvCallStore.storeCalls(calls);
    }

    /**
     * Convert calls into VisitEvents grouped by VisitEvent.dnis
     * @param retrievedCalls
     */
    public List<VisitEventDNISGroup> transformCallsToVisitEventGroups(String groupKey, List<Call> retrievedCalls) throws Exception
    {
        SandataLogger sandataLogger = CallImportLogger.CreateLogger();

        Map<String, VisitEventDNISGroup> visitEventGroupMap = new HashMap<>();

        List<Call> calls = this.returnNewCalls(retrievedCalls);

        sandataLogger.logger().info(String.format("Processing %d calls", calls.size()));

        for(Iterator<Call> callIterator = calls.iterator(); callIterator.hasNext();)
        {
            try
            {
                Call call = callIterator.next();

                VisitEventExt visitEventExt = evvCallTransformer.createVisitEvent(call);

                String visitDNIS = "";

                if (StringUtils.isNotEmpty(visitEventExt.getDialedNumberIdentificationService())) {
                    visitDNIS = visitEventExt.getDialedNumberIdentificationService();
                }

                //Locate an existing group of VisitEvents by DNIS (map key)
                VisitEventDNISGroup visitEventDNISGroup = visitEventGroupMap.get(visitDNIS);

                //Create a new group if one was not found in the map
                if (visitEventDNISGroup == null) {
                    visitEventDNISGroup = new VisitEventDNISGroup();
                    visitEventDNISGroup.setDnis(visitDNIS);

                    sandataLogger.info(String.format("Created VisitEventDNISGroup for %s DNIS",visitDNIS));
                }

                List<VisitEventExt> visitEventsList = visitEventDNISGroup.getVisitEvents();

                if (visitEventsList == null) {
                    visitEventsList = new ArrayList<>();
                }

                visitEventsList.add(visitEventExt);

                visitEventDNISGroup.setVisitEvents(visitEventsList);


                visitEventGroupMap.put(visitDNIS, visitEventDNISGroup);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        sandataLogger.stop();

        return new ArrayList<>(visitEventGroupMap.values());
    }

    /**
     * Return list of calls that are not currently stored in the local store
     * @param calls = list of retrieved calls
     * @return newCalls = difference between sets of call lists
     */
    private List<Call> returnNewCalls(List<Call> calls)
    {
        List<Call> storedCalls = evvCallStore.getUnprocessedCalls();

        SandataLogger sandataLogger = CallImportLogger.CreateLogger();

        sandataLogger.logger().info("Received stored call list...");

        if(storedCalls == null || storedCalls.size() == 0)
        {
            sandataLogger.logger().info("There were no stored calls. Returning new calls...");
            return calls;
        }


        List<Call> newCalls = (List<Call>) CollectionUtils.subtract(calls, storedCalls);

        sandataLogger.logger().info("Returning new calls...");
        return newCalls;
    }

    /**
     * Delete call from Store when a VisitEvent is pulled from processed queue
     * @param exchange
     */
    public void processedVisitEvent(Exchange exchange)
    {
        SandataLogger sandataLogger = CallImportLogger.CreateLogger();

        VisitEventExt visitEventExt = (VisitEventExt) exchange.getIn().getBody();

        Call call = evvCallTransformer.createCall(visitEventExt);

        try {

            Call storedCall = evvCallStore.getCallFromStore(call);

            evvCallStore.deleteCallFromStore(storedCall);
            if(storedCall == null || storedCall.getSid() == null) {
                sandataLogger.error("storedCall or storedCall.getSid() was null did not call database.");
            }
            else {
                evvOracleDataService.setCallsAsExported(storedCall.getSid());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            sandataLogger.stop();
        }
    }

    public void storeCallsPostVisitEvent(Exchange exchange)
    {
        List<Call> calls = (ArrayList<Call>)exchange.getIn().getHeader("calls");

        evvCallStore.storeCalls(calls);
    }
}
