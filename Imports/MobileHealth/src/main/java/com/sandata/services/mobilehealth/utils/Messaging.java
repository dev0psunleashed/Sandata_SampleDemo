package com.sandata.services.mobilehealth.utils;

public class Messaging {
    public enum Names {   
        COMPONENT_TYPE_DIRECT("direct:"),
        COMPONENT_TYPE_FILE("file://"),
        COMPONENT_TYPE_FTP("ftp://"),
        COMPONENT_TYPE_SFTP("sftp://"),
        COMPONENT_TYPE_JDBC("jdbc:"),
        COMPONENT_TYPE_QUARTZ2("quartz2://"),
        COMPONENT_TYPE_TIMER("timer://"),
        COMPONENT_TYPE_MASTER("master:"),
        
        SAM_DATASOURCE_KEY("samDataSource"),
        LENS_DATASOURCE_KEY("lensDataSource"),

        /* Constants for contexts */
        PROCESS_FILE_CONTEXT_HEADER("PROCESS_FILE_CONTEXT_HEADER"),
        DB_LOG_CONTEXT_HEADER("DB_LOG_CONTEXT_HEADER"),
        FILE_SET_CONTEXT_HEADER("FILE_SET_CONTEXT_HEADER"),
        DB_AGEN_CONTEXT_HEADER("DB_AGEN_CONTEXT_HEADER"),
        
        /* Constants for ProcessFolderRoute */
        PROCESS_FOLDER_ROUTE("MobileHealthProcessFolderRoute"),
        SAVED_FILE_CONTENT("SAVED_FILE_CONTENT"),
        CONTINUE_PROCESSING_FILE("CONTINUE_PROCESSING_FILE"),
        
        /* Constants for Import File Route */
        IMPORT_FILE_ROUTE("MobileHealthImportFileRoute"),
        
        PROCESS_FILE_FIXED_LENGTH_LIST("PROCESS_FILE_FIXED_LENGTH_LIST"),
        PROCESS_FILE_FIXED_LENGTH_SORTED_LIST("PROCESS_FILE_FIXED_LENGTH_SORTED_LIST"),
        PROCESS_FILE_FIXED_LENGTH_FILTERED_LIST("PROCESS_FILE_FIXED_LENGTH_FILTERED_LIST"),
        PROCESS_FILE_UNIQUE_VENDOR_LIST("PROCESS_FILE_UNIQUE_VENDOR_LIST"),
                
        PROCESS_FILE_VENDOR_ID_HEADER("vendor"),
        PROCESS_FILE_AGENCY_DB_EXIST_HEADER("PROCESS_FILE_AGENCY_DB_EXIST_HEADER"),
        
        PROCESS_FILE_AGENCY_DB_DATASOURCE_HEADER("PROCESS_FILE_AGENCY_DB_DATASOURCE_HEADER"),
        
        PROCESS_FILE_TEMP_DONE("PROCESS_FILE_TEMP_DONE"),
        PROCESS_FILE_TEMP_ERROR("PROCESS_FILE_TEMP_ERROR"),
        PROCESS_FILE_RETURN_STATUS("PROCESS_FILE_RETURN_STATUS"),
        PROCESS_FILE_RETURN_MESSAGE("PROCESS_FILE_RETURN_MESSAGE"),
        
        /* Constants for Export File Route */
        EXPORT_FILE_ROUTE("MobileHealthExportFileRoute"),
        EXPORT_FILE_UPLOAD_ROUTE("MobileHealthUploadFileRoute"),
        EXPORT_CURRENT_EXPORT_TIME("EXPORT_LAST_EXPORT_TIME"),
        EXPORT_CONFIGURATION_HEADER("EXPORT_CONFIGURATION_HEADER"),
        
        /* Constants for DownloadFileRoute */
        DOWNLOAD_FILE_ROUTE_ID("DownloadFileRoute"),
        CURRENT_FILE_NAME_HEADER("CURRENT_FILE_NAME_HEADER"),
        DESTINATION_FILE_NAME_HEADER("DESTINATION_FILE_NAME_HEADER"),
        TARGET_URL_HEADER("TARGET_URL_HEADER"),
        TARGET_VALIDATOR_HEADER("TARGET_VALIDATOR_HEADER"),
        TARGET_ROOT_FOLDER_HEADER("TARGET_ROOT_FOLDER_HEADER"),
        
        /* Constants for MobileHealthNotifyRoute */
        MOBILE_HEALTH_NOTIFY_ROUTE_ID("MobileHealthNotifyRoute"),
        NOTIFY_CONTEXT_HEADER("NOTIFY_CONTEXT_HEADER"),
        
        /* Constants for "etl_log_mobilehealth" table */
        ETL_LOG_MOBILEHEALTH_PROCESS_START_TIME("process_start_time"),
        ETL_LOG_MOBILEHEALTH_PROCESS_END_TIME("process_end_time"),
        ETL_LOG_MOBILEHEALTH_STATUS("status"),
        ETL_LOG_MOBILEHEALTH_MESSAGE("message"),
        ETL_LOG_MOBILEHEALTH_NOTIFY("notify"),
        ETL_LOG_MOBILEHEALTH_SESSION_ID("session_id"),
        ETL_LOG_MOBILEHEALTH_FILE_NAME("filename"),
        ;
        private final String name;

        /**
         * Constructs message names.
         *
         * @param names Specified names
         */
        private Names(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
