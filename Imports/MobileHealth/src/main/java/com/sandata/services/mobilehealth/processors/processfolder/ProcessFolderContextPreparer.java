package com.sandata.services.mobilehealth.processors.processfolder;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;

import com.sandata.services.mobilehealth.data.models.contexts.FileSetContext;
import com.sandata.services.mobilehealth.utils.Messaging;

public class ProcessFolderContextPreparer {
    
    @PropertyInject("{{project.name}}")
    private String projectName;
    
    /**
     * the file we process actually has (*.init) format and poll from SaveFolder. 
     * But we cut off the DownloadFileRoute which poll from InboxFolder with origin file name, 
     * then we have to change the file name and file path
     * @param exchange
     */
    public void prepareFileNameAndPath(final Exchange exchange) {
        FileSetContext context = exchange.getIn().getHeader(
                Messaging.Names.FILE_SET_CONTEXT_HEADER.toString(),
                FileSetContext.class);
        String originFileName = (String) exchange.getIn().getHeader("CamelFileName");
        String saveFolder = (String) exchange.getIn().getHeader(
                Messaging.Names.TARGET_ROOT_FOLDER_HEADER.toString());
        
        String processingFileName = projectName + "_" + originFileName + context.getExtInit();
        context.setNewFile(projectName + "_" + originFileName + context.getExtDone());
        
        exchange.getIn().setHeader("CamelFileName", processingFileName);
        
        /**
         * avoid file path has double '/' in the end of parent folder
         */
        if(saveFolder.lastIndexOf("/") == (saveFolder.length() - 1)) {
            exchange.getIn().setHeader(Exchange.FILE_PATH, saveFolder + processingFileName);
        } else {
            exchange.getIn().setHeader(Exchange.FILE_PATH, saveFolder + "/" + processingFileName);
        }
        
    }
    
    /**
     * prepare tempFileDone and tempFileError to copy after processing file init
     * @param exchange
     */
    public void prepareContext(final Exchange exchange) {
        FileSetContext context = exchange.getIn().getHeader(
                Messaging.Names.FILE_SET_CONTEXT_HEADER.toString(),
                FileSetContext.class);
        
        String tempFile = (String) exchange.getIn().getHeader("CamelFileName");
        String tempFileDone = tempFile.substring(0, tempFile.length() - 5) + context.getExtDone();
        String tempFileError = tempFile.substring(0, tempFile.length() - 5) + context.getExtError();

        exchange.getIn().setHeader(Messaging.Names.PROCESS_FILE_TEMP_DONE.toString(), tempFileDone);
        exchange.getIn().setHeader(Messaging.Names.PROCESS_FILE_TEMP_ERROR.toString(), tempFileError);
        
        String currentFilePath = (String) exchange.getIn().getHeader(Exchange.FILE_PATH);
        context.setFileName(currentFilePath);
    }
    
    /**
     * Save file content to header in case message's body has changed
     * @param exchange
     */
    public void saveFileContent(final Exchange exchange) {
        exchange.getIn().setHeader(Messaging.Names.SAVED_FILE_CONTENT.toString(), exchange.getIn().getBody());
    }

}
