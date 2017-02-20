package com.sandata.services.mobilehealth.validators;

import java.io.File;

/**
 * Copied from EvvBatch
 */
public class FileValidator implements Validator {

    @Override
    public boolean isExisted(final String filePath) {
        File workingFile = new File(filePath);
        return workingFile.exists();
    }

    @Override
    public void cleanUp() {
        // Nothing needs to be done for cleanup with File component
    }

    @Override
    public boolean deleteFile(final String filePath) {
        File workingFile = new File(filePath);
        return workingFile.delete();
    }
}
