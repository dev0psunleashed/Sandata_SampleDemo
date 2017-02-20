package com.sandata.lab.logger.impl;

import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.ApplicationLog;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.logger.api.LoggerService;
import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class LoggerDataService implements LoggerService {

    private OracleDataService oracleDataService;

    private String userGuid = null;

    @PropertyInject("log.level")
    private String logLevel = "ERROR";

    public void logHandler(Exchange exchange) {

        String logLevel = (String)exchange.getIn().getHeader("SANDATA_LOG_LVL");
        if (StringUtil.IsNullOrEmpty(logLevel)) {
            logLevel = "UNKNOWN";
        }

        String process = (String)exchange.getIn().getHeader("SANDATA_LOG_PROCESS");
        Long pid = (Long)exchange.getIn().getHeader("SANDATA_LOG_PID");
        Long thread = (Long)exchange.getIn().getHeader("SANDATA_LOG_THREAD");

        String message = exchange.getIn().getBody(String.class);

        log(process, pid, thread, logLevel, message);
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    @Override
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    @Override
    public void log(String process, Long pid, Long thread, String level, String message) {

        if (!shouldLog(level)) {
            // Do Not Log
            return;
        }

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            ApplicationLog applicationLog = createApplicationLog();
            applicationLog.setLogProcess(process);

            if (pid != null) {
                applicationLog.setLogProcessID(BigInteger.valueOf(pid));
            }

            if (thread != null) {
                applicationLog.setLogThread(BigInteger.valueOf(thread));
            }

            applicationLog.setLogLevel(level);
            applicationLog.setLogMessage(message);

            oracleDataService.addLog(connection, applicationLog);

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public void log(String userGuid, String process, Long pid, Long thread, String level, String message) {

        if (!shouldLog(level)) {
            // Do Not Log
            return;
        }

        Connection connection = null;

        // overwrite the userGuid passed in with the internal value if it was set
        if (this.userGuid != null) {
            userGuid = this.userGuid;
        }

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            ApplicationLog applicationLog = createApplicationLog();
            applicationLog.setLogProcess(process);

            if (pid != null) {
                applicationLog.setLogProcessID(BigInteger.valueOf(pid));
            }

            if (thread != null) {
                applicationLog.setLogThread(BigInteger.valueOf(thread));
            }

            applicationLog.setLogLevel(level);
            applicationLog.setLogMessage(message);

            oracleDataService.addLogForUserGUID(connection, userGuid, applicationLog);

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public void log(String userGuid, String host, String process, Long pid, Long thread, String level, String message) {

        if (!shouldLog(level)) {
            // Do Not Log
            return;
        }

        Connection connection = null;

        // overwrite the userGuid passed in with the internal value if it was set
        if (this.userGuid != null) {
            userGuid = this.userGuid;
        }

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            ApplicationLog applicationLog = createApplicationLog();
            applicationLog.setLogHost(host);
            applicationLog.setLogProcess(process);

            if (pid != null) {
                applicationLog.setLogProcessID(BigInteger.valueOf(pid));
            }

            if (thread != null) {
                applicationLog.setLogThread(BigInteger.valueOf(thread));
            }

            applicationLog.setLogLevel(level);
            applicationLog.setLogMessage(message);

            oracleDataService.addLogForUserGUID(connection, userGuid, applicationLog);

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public void info(String process, String message) {
        log(process, null, null, "INFO", message);
    }

    @Override
    public void info(Long pid, String process, String message) {
        log(process, pid, null, "INFO", message);
    }

    @Override
    public void warn(String process, String message) {
        log(process, null, null, "WARN", message);
    }

    @Override
    public void warn(Long pid, String process, String message) {
        log(process, pid, null, "WARN", message);
    }

    @Override
    public void error(String process, String message) {
        log(process, null, null, "ERROR", message);
    }

    @Override
    public void error(Long pid, String process, String message) {
        log(process, pid, null, "ERROR", message);
    }

    @Override
    public void trace(String process, String message) {
        log(process, null, null, "TRACE", message);
    }

    @Override
    public void trace(Long pid, String process, String message) {
        log(process, pid, null, "TRACE", message);
    }

    @Override
    public void debug(String process, String message) {
        log(process, null, null, "DEBUG", message);
    }

    @Override
    public void debug(Long pid, String process, String message) {
        log(process, pid, null, "DEBUG", message);
    }

    private ApplicationLog createApplicationLog() {
        ApplicationLog applicationLog = new ApplicationLog();
        applicationLog.setRecordCreateTimestamp(new Date());
        applicationLog.setRecordUpdateTimestamp(new Date());
        applicationLog.setLogHost("MW");
        applicationLog.setLogLevel("INFO");
        return applicationLog;
    }

    private boolean isTraceOn() {
        if (logLevel.equalsIgnoreCase("TRACE")
                || isDebugOn()) {
            return true;
        }

        return false;
    }

    private boolean isDebugOn() {
        if (logLevel.equalsIgnoreCase("DEBUG")) {
            return true;
        }

        return false;
    }

    private boolean shouldLog(String level) {
        if (!StringUtil.IsNullOrEmpty(level)) {

            if (level.equalsIgnoreCase("DEBUG") && isDebugOn()) {
                return true;
            }

            if (level.equalsIgnoreCase("TRACE") && isTraceOn()) {
                return true;
            }

            if (level.equalsIgnoreCase("INFO")
                    || level.equalsIgnoreCase("WARN")
                    || level.equalsIgnoreCase("ERROR")
                    || level.equalsIgnoreCase("UNKNOWN")) {
                return true;
            }
        }

        // Logging is off
        return false;
    }
}
