package com.sandata.services.mobilehealth.processors.processfolder;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.services.mobilehealth.data.models.contexts.FileSetContext;
import com.sandata.services.mobilehealth.exception.TargetValidationException;
import com.sandata.services.mobilehealth.utils.LoggingUtils;
import com.sandata.services.mobilehealth.utils.Messaging;
import com.sandata.services.mobilehealth.validators.Validator;

public class FileSetContextPreparer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSetContextPreparer.class);
    
    @PropertyInject("{{project.name}}")
    private String projectName;
    
    /**
     * FileSetContext params
     */
    @PropertyInject("{{process.downloadfile.folder.inbox}}")
    private String folderInbox;
    @PropertyInject("{{process.downloadfile.folder.save}}")
    private String folderSave;    
    @PropertyInject("{{process.downloadfile.file.ext.init}}")
    private String extInit;
    @PropertyInject("{{process.downloadfile.file.ext.done}}")
    private String extDone;
    @PropertyInject("{{process.downloadfile.file.ext.error}}")
    private String extError;
    @PropertyInject("{{process.downloadfile.file.cc}}")
    private String cc;
    
    /**
     * Set the FileSetContext for the route
     * @param exchange
     */
    public void buildFileSetContext(final Exchange exchange) {
        FileSetContext context = new FileSetContext();
        context.setFolderInbox(folderInbox);
        context.setFolderSave(folderSave);
        context.setExtDone(extDone);
        context.setExtError(extError);
        context.setExtInit(extInit);
        context.setCc(cc);
        
        exchange.getIn().setHeader(Messaging.Names.FILE_SET_CONTEXT_HEADER.toString(), context);
    }
    
    /**
     * check if files exist either file or ftp or sftp
     * @param exchange
     */
    public void checkFileExists(final Exchange exchange) {
        FileSetContext context = exchange.getIn().getHeader(
                Messaging.Names.FILE_SET_CONTEXT_HEADER.toString(),
                FileSetContext.class);

        Validator targetValidator = exchange.getIn().getHeader(
                Messaging.Names.TARGET_VALIDATOR_HEADER.toString(),
                Validator.class);
        
        String targetRootFolder = exchange.getIn().getHeader(
                Messaging.Names.TARGET_ROOT_FOLDER_HEADER.toString(),
                String.class);
        
        String currentFileName = (String) exchange.getIn().getHeader("CamelFileName");
        String tempFileInit = projectName + "_" + currentFileName + context.getExtInit();
        String tempFileDone = projectName + "_" + currentFileName + context.getExtDone();
        String tempFileError = projectName + "_" + currentFileName + context.getExtError();

        LOGGER.info(LoggingUtils.getLogMessageForProcessor(getClass(), "checkFileExists", 
                "Checking for file " + currentFileName + " at target " + targetRootFolder));
        
        boolean initFileExists = false, doneFileExists = false, errorFileExists = false;

        try {
            /* check if tempfile_done exists */
            doneFileExists = targetValidator.isExisted(targetRootFolder + "/" + tempFileDone);
            
            /* check if tempfile_error exists */
            errorFileExists = targetValidator.isExisted(targetRootFolder + "/" + tempFileError);
            
            /* check if tempfile_init exists */
            initFileExists = targetValidator.isExisted(targetRootFolder + "/" + tempFileInit);
            
            if(initFileExists) {
                
                /* delete tempfile_init anyway if it exists*/
                targetValidator.deleteFile(targetRootFolder + "/" + tempFileInit);
            }
        
        } catch (TargetValidationException e) {
            LOGGER.error(
                    LoggingUtils.getErrorLogMessageForProcessor(getClass(), "checkFileExists", 
                            "Error while checking done/error/init file existance for fileName='{}'"),
                    new Object[]{currentFileName, e});
        }
        
        if(!(doneFileExists || errorFileExists)) {
            exchange.getIn().setHeader(Messaging.Names.CONTINUE_PROCESSING_FILE.toString(), true);
        } else {
            exchange.getIn().setHeader(Messaging.Names.CONTINUE_PROCESSING_FILE.toString(), false);
        }
    }
}