package com.sandata.one.aggregator.documents.settings;

import com.sandata.one.aggregator.documents.app.AppContext;
import org.apache.camel.CamelContext;

/**
 * Document service settings class.
 * <p/>
 *
 * @author David Rutgos
 */
public class DocumentServiceSettings {

    private String documentCachePath = "target/sandata-document-data-service/cache";
    private String documentBackupPath = "target/sandata-document-data-service/backup";
    private String documentStatusPath = "target/sandata-document-data-service/status";

    public String getDocumentBackupPath() {
        return documentBackupPath;
    }

    public void setDocumentBackupPath(String documentBackupPath) {
        this.documentBackupPath = documentBackupPath;
    }

    public String getDocumentStatusPath() {
        return documentStatusPath;
    }

    public void setDocumentStatusPath(String documentStatusPath) {
        this.documentStatusPath = documentStatusPath;
    }

    public String getDocumentCachePath() {
        return documentCachePath;
    }

    public void setDocumentCachePath(String documentCachePath) {
        this.documentCachePath = documentCachePath;
    }

    public static DocumentServiceSettings getInstance() {

        CamelContext context = AppContext.getContext();
        if(context != null) {
            return (DocumentServiceSettings) context.getRegistry().lookupByName("documentServiceSettings");
        }

        return new DocumentServiceSettings();
    }
}
