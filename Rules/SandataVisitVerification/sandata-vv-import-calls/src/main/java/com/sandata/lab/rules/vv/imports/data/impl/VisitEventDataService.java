package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.vv.imports.data.api.DataService;
import com.sandata.lab.rules.vv.imports.data.transformers.EVVCallTransformer;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 
 * @author Ralph Sylvain
 * Interact to EVV database to get unprocessed calls as well as mark call as processed.
 *
 */
public class VisitEventDataService implements DataService {

    private Logger archerVVCallImportLogger = LoggerFactory.getLogger(VisitEventDataService.class);

    private EVVOracleDataService evvOracleDataService;

    private EVVCallTransformer evvCallTransformer;

    private AbstractEVVCallStore evvCallStore;

    @PropertyInject("{{numberOfCallsToProcess}}")
    private int numCalls;

    @PropertyInject("{{evv.callExportKey}}")
    private String exportKey = "118";

    public void setEvvCallTransformer(EVVCallTransformer evvCallTransformer) {
        this.evvCallTransformer = evvCallTransformer;
    }

    public void setEvvOracleDataService(EVVOracleDataService evvOracleDataService) {
        this.evvOracleDataService = evvOracleDataService;
    }

    public void setEvvCallStore(AbstractEVVCallStore evvCallStore) {
        this.evvCallStore = evvCallStore;
    }

	/**
     * Get calls from EVV by group key
     * @param exchange contains the group key to retrieve calls for
     */
    public void getUnprocessedCalls (Exchange exchange) throws SandataRuntimeException {
        try {
            
            String groupKey = (String) exchange.getIn().getBody();
            List<EVVCall> calls = evvOracleDataService.getUnprocessedCalls(groupKey, exportKey, numCalls);
            
            //Discontinue processing if there are no calls
            if (calls == null || calls.size() == 0) {
 
                archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD 
                		, this.getClass(), "getUnprocessedCalls"
                		, String.format("There were no calls for group in db %s",groupKey)));
                
                exchange.getIn().setBody(null);
                return;
            }

            archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
            		, "getUnprocessedCalls"
            		, String.format("Retrieved %d calls for group %s", calls.size(), groupKey)));

            exchange.getIn().setBody(calls);
                       

        } catch (Exception ex) {
            
            String errMsg = String.format("%s: %s", getClass().getName(), ex.getMessage());
            archerVVCallImportLogger.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
            		, "getUnprocessedCalls"
            		, errMsg));
            throw new SandataRuntimeException(errMsg,ex);
            
        }
    }

    
   

    /**
     * Convert calls into VisitEvents grouped by VisitEvent.dnis
     * @param exchange
     */
    public void transformCallsToVisitEventGroups(final Exchange exchange) throws SandataRuntimeException {

        try {
            // get body of List Calls from db
            List<EVVCall> retrievedCalls = (List<EVVCall>) exchange.getIn().getBody();
            
            Map<String, VisitEventDNISGroup> visitEventGroupMap = new HashMap<>();
            List<EVVCall> newCalls = this.returnNewCalls(retrievedCalls);
            
            archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
            		, "transformCallsToVisitEventGroups"
            		, String.format("Processing %d calls", newCalls.size())));
            
            for (Iterator<EVVCall> callIterator = newCalls.iterator(); callIterator.hasNext();) {
                
                EVVCall call = callIterator.next();
                VisitEventExt visitEventExt = evvCallTransformer.createVisitEvent(call);
                String visitDNIS = "";
                
                if (StringUtils.isNotEmpty(visitEventExt.getDialedNumberIdentificationService())) {
                    visitDNIS = visitEventExt.getDialedNumberIdentificationService();
                }
                
                // Locate an existing group of VisitEvents by DNIS (map key)
                VisitEventDNISGroup visitEventDNISGroup = visitEventGroupMap.get(visitDNIS);
                
                // Create a new group if one was not found in the map
                if (visitEventDNISGroup == null) {
                    visitEventDNISGroup = new VisitEventDNISGroup();
                    visitEventDNISGroup.setDnis(visitDNIS);
                    
                    archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
                    		, "transformCallsToVisitEventGroups"
                    		, String.format("Created VisitEventDNISGroup for %s DNIS", visitDNIS)));
                }
                
                //VisitEvent
                List<VisitEventExt> visitEventsList = visitEventDNISGroup.getVisitEvents();
                if (visitEventsList == null) {
                    visitEventsList = new ArrayList<>();
                }
                visitEventsList.add(visitEventExt);
                visitEventDNISGroup.setVisitEvents(visitEventsList);
                visitEventGroupMap.put(visitDNIS, visitEventDNISGroup);
                
            }
            
            List<VisitEventDNISGroup> visitEventDNISGroupList = new ArrayList<>(visitEventGroupMap.values());
            if (visitEventDNISGroupList != null) {

                archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
                		, "transformCallsToVisitEventGroups"
                		, String.format("%d unique VisitEventGroups have been created", visitEventDNISGroupList.size())));
                
                exchange.getIn().setBody(visitEventDNISGroupList);
                exchange.getIn().setHeader("calls", newCalls);
            }
            
        } catch (Exception ex) {
            
            String errMsg = String.format("%s: %s", getClass().getName(), ex.getMessage());
            
            archerVVCallImportLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
            		, "transformCallsToVisitEventGroups", errMsg));
            
            throw new SandataRuntimeException(errMsg, ex);
            
        }
        
    }

    /**
     * Return list of calls that are not currently stored in the local store
     * @param retrievedCalls = list of retrieved calls
     * @return newCalls = difference between sets of call lists
     */
    private List<EVVCall> returnNewCalls(List<EVVCall> retrievedCalls) {
        
    	archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
         		, "returnNewCalls", "Calls from database : {}"),retrievedCalls != null ? retrievedCalls.size() : 0);
    	
        List<EVVCall> storedCalls = evvCallStore.getUnprocessedCalls();
        
        archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
         		, "returnNewCalls", "Calls from cached : {}"),storedCalls != null ? storedCalls.size() : 0);

        if (storedCalls == null || storedCalls.size() == 0) {
           
            return retrievedCalls;
        }
        
        List<EVVCall> newCalls = (List<EVVCall>) CollectionUtils.subtract(retrievedCalls, storedCalls);
        archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
         		, "returnNewCalls", "Calls after subtracting (db to cached): {}"),newCalls != null ? newCalls.size() : 0);
        
        return newCalls;
    }

    /**
     * Delete call from Store when a VisitEvent is pulled from processed queue
     * @param exchange
     */
    public void processedVisitEvent(Exchange exchange) {

        VisitEventExt visitEventExt = (VisitEventExt) exchange.getIn().getBody();
        EVVCall call = evvCallTransformer.createCall(visitEventExt);
        try {
            if (call == null || call.getSid() == null) {
            	  archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
                   		, "processedVisitEvent", "From cached: storedCall or storedCall.getSid() was null , stop removing call from cache."));
            } else {
                //remove from cache
                evvCallStore.deleteCallFromStore(call);
                //mark as processed in database
                evvOracleDataService.markCallsAsExported(call.getSid());

                archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
                        , "processedVisitEvent", "Already mark call as processed , sid {}."),call.getSid());
            }

        } catch (Exception ex) {
            
            String message = String.format("Unexpected error at processedVisitEvent %s ", ex.getMessage());
            archerVVCallImportLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
            		, "processedVisitEvent", message));
            throw new SandataRuntimeException(message, ex);
            
        }
    }

    /**
     * 
     * @param exchange Exchange with body is Call info from EVV database, select by group key.
     * This method used to put all the calls to cache.
     */
    public void storeCallsPostVisitEvent(Exchange exchange) {
        
        List<EVVCall> calls = (ArrayList<EVVCall>) exchange.getIn().getHeader("calls");
        if( calls != null ) {
            evvCallStore.storeCalls(calls);
            archerVVCallImportLogger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass()
               		, "storeCallsPostVisitEvent", "Already put {} calls to cache"),calls != null ? calls.size() : 0);
        }
    }
}
