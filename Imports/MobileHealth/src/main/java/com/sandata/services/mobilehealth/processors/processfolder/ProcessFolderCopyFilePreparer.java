package com.sandata.services.mobilehealth.processors.processfolder;

import org.apache.camel.Exchange;

import com.sandata.services.mobilehealth.data.models.StatusCode;
import com.sandata.services.mobilehealth.data.models.contexts.FileSetContext;
import com.sandata.services.mobilehealth.data.models.contexts.ProcessFileContext;
import com.sandata.services.mobilehealth.utils.Messaging;

public class ProcessFolderCopyFilePreparer {

    public void update(final Exchange exchange) {
        FileSetContext context = exchange.getIn().getHeader(
                Messaging.Names.FILE_SET_CONTEXT_HEADER.toString(),
                FileSetContext.class);

        ProcessFileContext processFileContext = exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_CONTEXT_HEADER.toString(),
                ProcessFileContext.class);
        
        StatusCode processFileStatus = processFileContext.getReturnStatus();
        
        String tempFileError = (String) exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_TEMP_ERROR.toString());
        String tempFileDone = (String) exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_TEMP_DONE.toString());

        if (StatusCode.STATUS_ERROR.equals(processFileStatus)) {
            context.setNewFile(tempFileError);
        } else {
            context.setNewFile(tempFileDone);
        }
    }
}
