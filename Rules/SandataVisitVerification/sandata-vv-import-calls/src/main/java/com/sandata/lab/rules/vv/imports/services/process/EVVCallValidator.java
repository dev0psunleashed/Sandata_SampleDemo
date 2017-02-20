package com.sandata.lab.rules.vv.imports.services.process;

import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author thanhxle
 * This class used to validate required call info that being sent from REST api, to make sure it has enough info.
 *
 */
public class EVVCallValidator {

    private Logger archerVVCallImportLogger = LoggerFactory.getLogger(EVVCallValidator.class);
    
    /**
     * 
     * @param exchange CamelExchange with body is List<EVVCall> 
     * This method go through the list of calls and check required info, If not satify then will be remove.
     * This step only apply for calls that come from REST API.
     */
    public void validateAndFilter (final Exchange exchange) {

        List<EVVCall> evvCalls = (List<EVVCall>) exchange.getIn().getBody();
        if(evvCalls != null ) {
            
            for (Iterator<EVVCall> callIterator = evvCalls.iterator(); callIterator.hasNext();) {
                
                EVVCall call = callIterator.next();
                if(!isValidCall(call)) {
                    //remove invalid one from list
                    archerVVCallImportLogger.info(
                    		LoggingUtils.getErrorLogMessageForProcessor(
                    				LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, this.getClass(), 
                    				"validateAndFilter", "Remove invalid call"));
                    callIterator.remove();
                }
            }
            
        } 
        
        
        if(evvCalls == null || evvCalls.isEmpty()) {
            exchange.getIn().setBody(null);
        }else {
            exchange.getIn().setBody(evvCalls);
        }

    }
    
    
    private boolean isValidCall ( EVVCall call) {
        
        //check the required info
        if(call == null) {
            return false;
        }
        
        //required DNIS
        if(StringUtil.IsNullOrEmpty(call.getDnis())) {
            return false;
        }
        
        //required ANI
        if(StringUtil.IsNullOrEmpty(call.getAni())) {
            return false;
        }
        
        //required StaffID
        if(StringUtil.IsNullOrEmpty(call.getStxId())) {
            return false;
        }
        
        //Required Task Info
        if(call.getCallInfo() == null || call.getCallInfo().isEmpty()) {
            return false;
        }
        
        
        return true;
    }
    
}
