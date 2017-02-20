package com.sandata.lab.dl.doc.api;

import com.sandata.lab.common.utils.log.SandataLogger;

/**
 * The document status update contract that is called during a file/doc upload.
 * <p/>
 *
 * @author David Rutgos
 */
public interface DocumentStatusUpdate {

    void updateStatus(long totalBytesRead, long totalBytes, String status, String statusId, SandataLogger logger);
    void errorMessage(String errorMessage, String status, String statusId, SandataLogger logger);
}
