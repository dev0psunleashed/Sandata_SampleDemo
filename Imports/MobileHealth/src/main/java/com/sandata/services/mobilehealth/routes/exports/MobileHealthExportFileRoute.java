/**
 * 
 */
package com.sandata.services.mobilehealth.routes.exports;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.services.mobilehealth.data.models.Destination;
import com.sandata.services.mobilehealth.data.models.exports.MobileHealthGeorgeFileExport;
import com.sandata.services.mobilehealth.routes.BaseMobileHealthRoute;
import com.sandata.services.mobilehealth.strategy.MobileHealthExportStrategy;
import com.sandata.services.mobilehealth.utils.LoggingUtils;
import com.sandata.services.mobilehealth.utils.Messaging;

/**
 * @author huyvo
 *
 */
public class MobileHealthExportFileRoute extends BaseMobileHealthRoute {
    
    private static final Logger LOG = LoggerFactory.getLogger(MobileHealthExportFileRoute.class);
    
    @PropertyInject("{{export.local.folder}}")
    private String localSaveFolder = "target/MobileHealth/George";

    @PropertyInject("{{process.exportfile.folder.save.uri}}")
    private String folderSaveUri = "file://D:/TestFile/export";
    
    @PropertyInject("{{process.downloadfile.isUsedlocalWorkDir}}")
    private Boolean useLocalWorkDir;
    
    @Override
    protected void buildRoute() {
        BindyFixedLengthDataFormat bindyFixedLength = new BindyFixedLengthDataFormat(MobileHealthGeorgeFileExport.class);
        
        from(getStartEndpoint())
            .routeId(Messaging.Names.EXPORT_FILE_ROUTE.toString())
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
            .log(LoggingUtils.getLogMessageForRoute("Begin exporting route at ${date:now:yyyy-MM-dd HH:mm:ss}"))
            .beanRef("processExportFileProcessor", "buildFileNameForExport")
            .beanRef("processExportFileProcessor", "cleanUpLocalSavedFolder")
            
            // get export configuration for exporting
            .beanRef("processExportFileProcessor", "getExportConfiguration")
            .log(LoggingUtils.getLogMessageForRoute("Done get Configuration for export"))
            .beanRef("processExportFileProcessor", "buildSQLForGettingStaffToExport")
            .log(LoggingUtils.getLogMessageForRoute("Begin exporting Staffs to MobileHealth export file"))
            .to("jdbc:databasePoolDataSourceWrapper?outputType=StreamList")
            .split(body()).streaming().stopOnException()
                .choice()
                    .when(simple("${header.CamelSplitIndex} == 0"))
                        .log(LoggingUtils.getLogMessageForRoute("There are ${body[TOTAL_ROWS]} STAFF(s) to export"))
                .endChoice().end()
                .beanRef("processExportFileProcessor", "mapDataForExporting")
                
                .aggregate(constant(true), new MobileHealthExportStrategy())
                .completionSize(simple("{{export.batch.size}}"))
                .completionPredicate(new Predicate() {
                    @Override
                    public boolean matches(Exchange exchange) {                        
                        return (boolean)exchange.getProperty(Exchange.SPLIT_COMPLETE);
                    }
                })
                    .marshal(bindyFixedLength)
                    .to(getLocalFolderToExportEndpoint())
                .end()
            .end()
            .log(LoggingUtils.getLogMessageForRoute("Done Exporting file ${file:name}"))
            .setBody(simple(""))
            .setHeader(Exchange.FILE_NAME, simple("{{export.file.done.name}}"))
            .to(getLocalFolderToExportEndpoint())
            
            // update export configuration
            .beanRef("processExportFileProcessor", "updateExportConfiguration")
            .log(LoggingUtils.getLogMessageForRoute("Done update configuration for last export time"))
            .log(LoggingUtils.getLogMessageForRoute("Done export route at ${date:now:yyyy-MM-dd HH:mm:ss}"));
        
        from(getLocalFolderToUploadEndpoint())
            .routeId(Messaging.Names.EXPORT_FILE_UPLOAD_ROUTE.toString())
            .log(LoggingUtils.getLogMessageForRoute("Uploading file ${file:name} to MobileHealth save folder"))
            .to(getExportFolderEndpoint())
            .log(LoggingUtils.getLogMessageForRoute("Done Uploading file ${file:name} to MobileHealth save folder"));
        
    }

    public String getStartEndpoint() {
        return new StringBuilder(Messaging.Names.COMPONENT_TYPE_QUARTZ2.toString())
        		.append("MobileHealthGroup/")
        		.append(Messaging.Names.EXPORT_FILE_ROUTE.toString())
        		.append("?cron={{process.exportfile.cron}}")
        		.toString();
    }
    
    /**
     * destination endpoint of sftp to  complete exporting file
     * @return
     */
    public String getExportFolderEndpoint() {
        Destination saveDestinationUri = new Destination(folderSaveUri);
        saveDestinationUri = Destination.buildDefaultFTPProps(saveDestinationUri, useLocalWorkDir);
        
        return saveDestinationUri.getUrl();
    }
    
    /**
     * used for export file to local folder, prepare for uploading to sftp server
     * @return
     */
    public String getLocalFolderToExportEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_FILE + localSaveFolder + "?fileExist=Append";
    }
    
    /**
     * used for get saved file from local to export to sftp server
     * @return
     */
    public String getLocalFolderToUploadEndpoint() {
    	return new StringBuilder(Messaging.Names.COMPONENT_TYPE_MASTER.toString())	// master:
    			.append(Messaging.Names.EXPORT_FILE_UPLOAD_ROUTE.toString())		// clusterID
    			.append(":")
    			.append(Messaging.Names.COMPONENT_TYPE_FILE.toString())
    			.append(localSaveFolder)
    			.append("?doneFileName={{export.file.done.name}}&delete=true")
    			.toString();
    }
}
