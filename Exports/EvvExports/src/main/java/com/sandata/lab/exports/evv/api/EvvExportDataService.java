package com.sandata.lab.exports.evv.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 8:08 AM
 */
public interface EvvExportDataService {
    void getSchedules(Exchange exchange) throws SandataRuntimeException;
    void getStaff(Exchange exchange) throws SandataRuntimeException;
    void getPatient(Exchange exchange) throws SandataRuntimeException;
    //
    void getUploadSchedules(Exchange exchange) throws SandataRuntimeException;
    void getAccountsScheduledToBeExported(Exchange exchange) throws Exception;
}
