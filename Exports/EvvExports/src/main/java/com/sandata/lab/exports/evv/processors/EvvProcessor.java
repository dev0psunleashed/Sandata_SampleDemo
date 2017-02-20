package com.sandata.lab.exports.evv.processors;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.exports.evv.model.request.UploadScheduleRequest;
import com.sandata.lab.exports.evv.routes.EvvExportRoute;
import com.sandata.lab.exports.evv.utils.Log;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 2:32 PM
 */
public class EvvProcessor implements org.apache.camel.Processor {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Log log = new Log();
        String lastExportDateString = (String)in.getHeader(EvvExportRoute.LASTEXPORTED);
        if(in.getBody() instanceof String) {
            String bsnEntId = in.getBody(String.class);
            UploadScheduleRequest request = new UploadScheduleRequest();
            if(lastExportDateString == null) {
                throw new SandataRuntimeException(exchange, "Last export date was null, check for missing " +
                        "MDW_LAST_EVV_EXPORT in metadata APP_TENANT_KEY_CONF");
            }
            else if(lastExportDateString.isEmpty()) {
                throw new SandataRuntimeException(exchange, "Last export date was empty, check for missing " +
                        "MDW_LAST_EVV_EXPORT in metadata APP_TENANT_KEY_CONF");
            }
            else {
                log.getExportLog().info(String.format("value of lastExportDateString = %s", lastExportDateString));
            }
            Date lastExportDate = sdf.parse(lastExportDateString);
            request.setBsnEntId(bsnEntId);
            request.setFromDate(lastExportDateString);
            request.setFrom(lastExportDate);
            request.setLastExport(lastExportDate);
            in.setBody(request);
        }
        else {
            in.setBody(null);
        }
    }
}
