package com.sandata.services.mobilehealth.routes.imports;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.Route;
import org.apache.camel.impl.CompositeRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.support.RoutePolicySupport;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.RoutePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.services.mobilehealth.processors.processfolder.FileSetContextPreparer;
import com.sandata.services.mobilehealth.processors.processfolder.ProcessFolderContextPreparer;
import com.sandata.services.mobilehealth.routes.BaseMobileHealthRoute;
import com.sandata.services.mobilehealth.utils.CommonUtils;
import com.sandata.services.mobilehealth.data.models.Destination;
import com.sandata.services.mobilehealth.utils.LoggingUtils;
import com.sandata.services.mobilehealth.utils.Messaging;
import com.sandata.services.mobilehealth.utils.builders.ValidatorBuilder;
import com.sandata.services.mobilehealth.validators.Validator;

/**
 * RouteBuilder instance to build Camel route that will poll fixed-length files from "inbox" folder 
 * for importing into Agency database. This route will only pick up a file for processing if it does not already
 * exist in "save" folder (see FileSetContextPreparer.checkFileExists method).
 * 
 * @author khangle
 */
public class MobileHealthProcessFolderRoute extends BaseMobileHealthRoute {
    @PropertyInject("{{project.name}}")
    private String projectName;
    
    @PropertyInject("{{process.downloadfile.isUsedlocalWorkDir}}")
    private Boolean useLocalWorkDir;
    
    @PropertyInject("{{connection.attempt}}")
    private int connectionAttempt = 3;
    
    @PropertyInject("{{connection.attempt.delay}}")
    private long connectionAttemptDelay = 20000;
    
    private static final String TARGET_VALIDATOR_NAME = "targetValidator";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MobileHealthProcessFolderRoute.class);
    
    /**
     * FileSetContext params
     */
    @PropertyInject("{{process.downloadfile.folder.inbox}}")
    private String folderInbox;
    @PropertyInject("{{process.downloadfile.folder.save}}")
    private String folderSave;
    @PropertyInject("{{files.regex.exclude}}")
    private String regexExclude = "(?i).*TEST.*";
    
    @Override
    protected void buildRoute() {
        // Build uri for inbox endpoint
        Destination inboxDestinationUri = getInboxDestination();
        
        // Build uri for save endpoint
        Destination saveDestinationUri = getSaveDestination();
        
        // Build validator for checking files and put it into CamelContext registry
        Validator targetValidator = getValidator(saveDestinationUri);
        putValidatorToRegsitry(this.getContext(), TARGET_VALIDATOR_NAME, targetValidator);
        
        // RoutePolicy to release (s)FTP connection when route is stopped
        RoutePolicy routePolicy = new RoutePolicySupport() {            
            @Override
            public void onStop(Route route) {
                Validator validator = route.getRouteContext().getCamelContext()
                        .getRegistry().lookupByNameAndType(TARGET_VALIDATOR_NAME, Validator.class);
                
                if (validator != null) {
                    LOGGER.info("[ProcessFolderRoute]: Route is stopping, prepare cleaning validator resource");
                    validator.cleanUp();
                    LOGGER.info("[ProcessFolderRoute]: Complete cleaning validator resource");
                }
            }
        };
        
        // Route definition
        from(getStartEndpoint())
            .routeId(Messaging.Names.PROCESS_FOLDER_ROUTE.toString())
            .routePolicy(routePolicy)
            
            .bean(FileSetContextPreparer.class, "buildFileSetContext")
            .setHeader(Messaging.Names.TARGET_VALIDATOR_HEADER.toString(), constant(targetValidator))
            .setHeader(Messaging.Names.TARGET_ROOT_FOLDER_HEADER.toString(), simple(saveDestinationUri.getRootFolder()))
            .log(LoggingUtils.getLogMessageForRoute("Begin " + projectName 
                    + " Download Job in inbox folder: "
                    + CommonUtils.hideUsernameAndPassword(inboxDestinationUri.getUrl())))
            
            /* check if both error and done file exist, continue processing file */
            .bean(FileSetContextPreparer.class, "checkFileExists")
            
            .choice()
                .when(header(Messaging.Names.CONTINUE_PROCESSING_FILE.toString()).isEqualTo(true))
                    .log(LoggingUtils.getLogMessageForRoute("Begin " + projectName + " Processing inbox save folder: "
                            + CommonUtils.hideUsernameAndPassword(saveDestinationUri.getUrl())))
                    .bean(ProcessFolderContextPreparer.class, "prepareFileNameAndPath")
                    .bean(ProcessFolderContextPreparer.class, "prepareContext")
                    .bean(ProcessFolderContextPreparer.class, "saveFileContent")
                    
                    // Call processFile route to process file content
                    .to(getProcessFileRouteEndpoint())
                    
                    /* get saved file content, then copy to new file */
                    .setBody(header(Messaging.Names.SAVED_FILE_CONTENT.toString()))
                    .to(getSavedFileEndpoint())
                    .log(LoggingUtils.getLogMessageForRoute("Copied and deleted file: ${file:name} = ${in.headers."
                            + Messaging.Names.FILE_SET_CONTEXT_HEADER.toString() + ".newFile}"))
                .otherwise()
                    .log(LoggingLevel.WARN, 
                            LoggingUtils.getLogMessageForRoute("Ignoring fileName='${header.CamelFileName}' due to processed file exists"))
            .endChoice() 
            .end();
    }
    
    public String getStartEndpoint() {
    	return new StringBuilder(Messaging.Names.COMPONENT_TYPE_MASTER.toString())	// master:
    			.append(Messaging.Names.PROCESS_FOLDER_ROUTE.toString())			// clusterID
    			.append(":")
    			.append(getInboxDestination().getUrl())
    			.toString();
    }

    public Destination getInboxDestination() {
        Destination inboxDestinationUri = new Destination(folderInbox);
        inboxDestinationUri = inboxDestinationUri.appendOptions("noop=true&idempotentKey=${file:name}-${file:modified}"
                + "&exclude=" + regexExclude + "&readLock=changed");
        inboxDestinationUri = Destination.buildDefaultFTPProps(inboxDestinationUri, useLocalWorkDir);
        
        return inboxDestinationUri;
    }

    public Destination getSaveDestination() {
        Destination saveDestinationUri = new Destination(folderSave);
        saveDestinationUri = saveDestinationUri.appendOptions("fileName=${in.headers." 
                + Messaging.Names.FILE_SET_CONTEXT_HEADER.toString() + ".newFile}");
        saveDestinationUri = Destination.buildDefaultFTPProps(saveDestinationUri, useLocalWorkDir);
        
        return saveDestinationUri;
    }

    public Validator getValidator(Destination saveDestinationUri) {
        return ValidatorBuilder.createBuilder(
                saveDestinationUri.getUrl()).build(connectionAttempt, connectionAttemptDelay);
    }

    public String getProcessFileRouteEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_DIRECT + Messaging.Names.IMPORT_FILE_ROUTE.toString();
    }
    
    public String getSavedFileEndpoint() {
        return getSaveDestination().getUrl();
    }
    
    /**
     * Method to register a validator into CamelContext registry
     * 
     * @param context
     * @param validatorName
     * @param validator
     */
    private void putValidatorToRegsitry(final CamelContext context, final String validatorName, final Validator validator) {
        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        CompositeRegistry compositeRegistry = (CompositeRegistry) registry.getRegistry();
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        simpleRegistry.put(validatorName, validator);
        compositeRegistry.addRegistry(simpleRegistry);
    }
}
