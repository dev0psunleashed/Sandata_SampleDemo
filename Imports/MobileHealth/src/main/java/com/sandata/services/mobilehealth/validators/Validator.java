package com.sandata.services.mobilehealth.validators;

import com.sandata.services.mobilehealth.exception.TargetValidationException;

/**
 * Copied from EvvBatch
 */
public interface Validator {

    /**
     * Check if a file existed based on relative path
     *
     * @param filePath relative path to the file for checking including file name and its extension
     * @return true if existed
     */
    boolean isExisted(final String filePath) throws TargetValidationException;

    /**
     * This is for validators to clean up resources after use
     */
    void cleanUp();
    
    /**
     * delete file
     */
    boolean deleteFile(final String filePath) throws TargetValidationException;
}
