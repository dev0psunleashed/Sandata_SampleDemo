package com.sandata.lab.exports.evv.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 7:34 AM
 */
public class Log {
    /*
    <logger name="exportLogger" level="INFO" additivity="false">
        <appender-ref ref="EVV_EXPORT_LOGGER"/>
    </logger>
    <logger name="exportExceptions" level="INFO" additivity="false">
        <appender-ref ref="EVV_EXPORT_EXCEPTION"/>
    </logger>
    <logger name="exportSqlExceptions" level="INFO" additivity="false">
        <appender-ref ref="EVV_EXPORT_SQL_EXCEPTION"/>
    </logger>
    <root level="INFO">
        <appender-ref ref="EVV_EXPORT_DEFAULT_LOGGER"/>
    </root>
     */
    private Logger exportLog = LoggerFactory.getLogger("exportLogger");
    private Logger exportExceptions = LoggerFactory.getLogger("exportExceptions");
    private Logger exportSqlExceptions = LoggerFactory.getLogger("exportSqlExceptions");

    public Logger getExportLog() {
        if(this.exportLog == null) {
            this.exportLog = LoggerFactory.getLogger("exportLogger");
        }
        return this.exportLog;
    }

    public void setExportLog(Logger exportLog) {
        this.exportLog = exportLog;
    }

    public Logger getExportExceptions() {
        if(this.exportExceptions == null) {
            this.exportExceptions = LoggerFactory.getLogger("exportExceptions");
        }
        return this.exportExceptions;
    }

    public void setExportExceptions(Logger exportExceptions) {
        this.exportExceptions = exportExceptions;
    }

    public Logger getExportSqlExceptions() {
        if(this.exportSqlExceptions == null) {
            this.exportSqlExceptions = LoggerFactory.getLogger("exportSqlExceptions");
        }
        return this.exportSqlExceptions;

    }

    public void setExportSqlExceptions(Logger exportSqlExceptions) {
        this.exportSqlExceptions = exportSqlExceptions;
    }
}
