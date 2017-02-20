/**
 * 
 */
package com.sandata.services.mobilehealth.routes.imports;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.services.mobilehealth.data.models.contexts.FileSetContext;
import com.sandata.services.mobilehealth.data.models.imports.MobileHealthGeorgeFileImport;
import com.sandata.services.mobilehealth.routes.BaseMobileHealthRoute;
import com.sandata.services.mobilehealth.utils.Messaging;
import com.sandata.services.mobilehealth.utils.LoggingUtils;
import com.sandata.up.commons.exception.SandataRuntimeException;

/**
 * @author huyvo
 *
 */
public class MobileHealthImportFileRoute extends BaseMobileHealthRoute {
    private static final Logger LOG = LoggerFactory.getLogger(MobileHealthImportFileRoute.class);
    
    @Override
    protected void buildRoute() {
        BindyFixedLengthDataFormat importFileFormat = new BindyFixedLengthDataFormat(MobileHealthGeorgeFileImport.class);
        
        onException(Exception.class)
            .handled(true)
            .log(LoggingLevel.ERROR, 
                    LoggingUtils.getErrorLogMessageForRoute("Exception occured when importing to GEORGE : stacktrace : ${exception.stacktrace}"))
            .process(new Processor() {
                
                @Override
                public void process(Exchange exchange) throws Exception {
                    FileSetContext context = exchange.getIn().getHeader(
                            Messaging.Names.FILE_SET_CONTEXT_HEADER.toString(),
                            FileSetContext.class);
                    
                    if (context != null && context.getNewFile().lastIndexOf(".") > 0) {
                        String errorFileName = context.getNewFile().substring(0, context.getNewFile().lastIndexOf(".")) + context.getExtError();
                        
                        context.setNewFile(errorFileName);
                    }
                    
                }
            });
        
        from(getStartEndpoint())
            .routeId(Messaging.Names.IMPORT_FILE_ROUTE.toString())
            .process(new Processor() {
                
                @Override
                public void process(Exchange exchange) throws Exception {
                    exchange.getIn().setHeader("START_TIME", System.currentTimeMillis());
                }
            })
            .onCompletion()
                .process(new Processor() {
                    
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        long startTime = (long) exchange.getIn().getHeader("START_TIME");
                        long endTime = System.currentTimeMillis();
                        
                        LOG.info(LoggingUtils.getLogMessageForProcessor(getClass(), "executionTime", "executionTime={}(ms)"), 
                                (endTime - startTime));
                    }
                })
            .end()
            .log(LoggingUtils.getLogMessageForRoute("Begin processing file: ${header.CamelFilePath}"))
            
            // Reset the body content with file content
            .setBody(simple("${header." + Messaging.Names.SAVED_FILE_CONTENT.toString() + "}"))
            
            // Unmarshal fixed length content onto MobileHealthFixedLengthModel object
            .unmarshal(importFileFormat)
            .setHeader(
                    Messaging.Names.PROCESS_FILE_FIXED_LENGTH_LIST.toString(), 
                    simple("${body}"))
            .beanRef("processImportFileProcessor", "sortFixedLengthModelList")
            .beanRef("processImportFileProcessor", "getUniqueVendorList")
            // Iterating through unique vendors set
            .split(simple("${header." + Messaging.Names.PROCESS_FILE_UNIQUE_VENDOR_LIST.toString() + "}"))
                .log(LoggingUtils.getLogMessageForRoute("Processing vendor=${body}"))
                .setHeader(Messaging.Names.PROCESS_FILE_VENDOR_ID_HEADER.toString(), simple("${body}"))
                .beanRef("processImportFileProcessor", "filterFixedLengthModelsByVendor")
                .beanRef("processImportFileProcessor", "importData")
            .end()
            .log(LoggingUtils.getLogMessageForRoute("Done importing file: ${header.CamelFilePath}"));
    }
    
    public String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_DIRECT + Messaging.Names.IMPORT_FILE_ROUTE.toString();
    }
}
