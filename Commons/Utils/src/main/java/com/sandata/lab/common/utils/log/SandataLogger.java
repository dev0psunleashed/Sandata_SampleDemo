/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.common.utils.log;

import com.sandata.lab.common.utils.data.SandataRandUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.logger.api.LoggerService;
import org.apache.camel.*;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import java.util.Date;
import java.util.UUID;

/**
 * Implements a standard logging and metrics class.
 * <p/>
 *
 * @author David Rutgos
 */
public abstract class SandataLogger {

    @Produce
    private ProducerTemplate template;

    private LoggerService loggerService;

    private CamelContext camelContext;

    private Exchange exchange;

    private final String loggerId;
    private final String containerName;
    private final long pid;
    private String methodName;
    private StopWatch stopWatch = new StopWatch();

    protected SandataLogger() {
        this.containerName = "DEFAULT";
        this.loggerId = UUID.randomUUID().toString();
        this.pid = SandataRandUtil.RandomLong();
        this.start();
    }

    protected SandataLogger(Exchange exchange) {
        this.exchange = exchange;
        this.containerName = exchange.getIn().getHeader("karaf.name") == null?"DEFAULT":(String)exchange.getIn().getHeader("karaf.name");

        String loggerId = (String)exchange.getIn().getHeader("SANDATA_LOGGER_ID");
        if (!StringUtil.IsNullOrEmpty(loggerId)) {
            this.loggerId = loggerId;
        } else {
            this.loggerId = UUID.randomUUID().toString();
            exchange.getIn().setHeader("SANDATA_LOGGER_ID", this.loggerId);
        }

        Long pid = (Long)exchange.getIn().getHeader("SANDATA_PID");
        if (pid != null) {
            this.pid = pid;
        } else {
            this.pid = SandataRandUtil.RandomLong();
            exchange.getIn().setHeader("SANDATA_PID", this.pid);
        }
    }

    protected SandataLogger(Exchange exchange, LoggerService loggerService) {
        this.exchange = exchange;
        this.containerName = exchange.getIn().getHeader("karaf.name") == null?"DEFAULT":(String)exchange.getIn().getHeader("karaf.name");

        String loggerId = (String)exchange.getIn().getHeader("SANDATA_LOGGER_ID");
        if (!StringUtil.IsNullOrEmpty(loggerId)) {
            this.loggerId = loggerId;
        } else {
            this.loggerId = UUID.randomUUID().toString();
            exchange.getIn().setHeader("SANDATA_LOGGER_ID", this.loggerId);
        }

        Long pid = (Long)exchange.getIn().getHeader("SANDATA_PID");
        if (pid != null) {
            this.pid = pid;
        } else {
            this.pid = SandataRandUtil.RandomLong();
            exchange.getIn().setHeader("SANDATA_PID", this.pid);
        }

        this.loggerService = loggerService;
        this.loggerService.setUserGuid(processName());
    }

    protected SandataLogger(Exchange exchange, CamelContext camelContext) {
        this.exchange = exchange;
        this.camelContext = camelContext;
        this.containerName = exchange.getIn().getHeader("karaf.name") == null?"DEFAULT":(String)exchange.getIn().getHeader("karaf.name");

        String loggerId = (String)exchange.getIn().getHeader("SANDATA_LOGGER_ID");
        if (!StringUtil.IsNullOrEmpty(loggerId)) {
            this.loggerId = loggerId;
        } else {
            this.loggerId = UUID.randomUUID().toString();
            exchange.getIn().setHeader("SANDATA_LOGGER_ID", this.loggerId);
        }

        Long pid = (Long)exchange.getIn().getHeader("SANDATA_PID");
        if (pid != null) {
            this.pid = pid;
        } else {
            this.pid = SandataRandUtil.RandomLong();
            exchange.getIn().setHeader("SANDATA_PID", this.pid);
        }
    }

    public void start() {
        this.stopWatch.start();
        this.logger().info(String.format("[%s][%s] ---> START: [%s][%d]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime())}));
    }

    public void stop(String result) {
        this.stopWatch.stop();
        this.logger().info(String.format("[%s][%s] ---> END: [%s][%d]: Ran In: [%s]: RESULT: [%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), this.stopWatch.toString(), result}));
    }

    public void stop() {
        this.stopWatch.stop();
        this.logger().info(String.format("[%s][%s] ---> END: [%s][%d]: Ran In: [%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), this.stopWatch.toString()}));
    }

    public void info(String message) {
        this.logger().info(String.format("[%s][%s] ---> INFO: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(null, null, message, "INFO");

        if (loggerService != null) {
            loggerService.info(this.pid, this.loggerId, message);
        }
    }

    public void info(String process, Long thread, String message) {
        this.logger().info(String.format("[%s][%s] ---> INFO: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(process, thread, message, "INFO");

        if (loggerService != null) {
            String proc = (process != null) ? process + ":" + this.loggerId : this.loggerId;
            loggerService.log(processName() /*UserGUID*/, this.containerName /*Host*/, proc, this.pid, thread, "INFO", message);
        }
    }

    public void warn(String message) {
        this.logger().info(String.format("[%s][%s] ---> WARN: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(null, null, message, "WARN");

        if (loggerService != null) {
            loggerService.warn(this.pid, this.loggerId, message);
        }
    }

    public void warn(String process, Long thread, String message) {
        this.logger().info(String.format("[%s][%s] ---> WARN: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(process, thread, message, "WARN");

        if (loggerService != null) {
            String proc = (process != null) ? process + ":" + this.loggerId : this.loggerId;
            loggerService.log(processName() /*UserGUID*/, this.containerName /*Host*/, proc, this.pid, thread, "WARN", message);
        }
    }

    public void error(String message) {
        this.logger().error(String.format("[%s][%s] ---> ERROR: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(null, null, message, "ERROR");

        if (loggerService != null) {
            loggerService.error(this.pid, this.loggerId, message);
        }
    }

    public void error(String process, Long thread, String message) {
        this.logger().error(String.format("[%s][%s] ---> ERROR: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(process, thread, message, "ERROR");

        if (loggerService != null) {
            String proc = (process != null) ? process + ":" + this.loggerId : this.loggerId;
            loggerService.log(processName() /*UserGUID*/, this.containerName /*Host*/, proc, this.pid, thread, "ERROR", message);
        }
    }

    public void trace(String message) {
        this.logger().trace(String.format("[%s][%s] ---> TRACE: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(null, null, message, "TRACE");

        if (loggerService != null) {
            loggerService.trace(this.pid, this.loggerId, message);
        }
    }

    public void trace(String process, Long thread, String message) {
        this.logger().trace(String.format("[%s][%s] ---> TRACE: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(process, thread, message, "TRACE");

        if (loggerService != null) {
            String proc = (process != null) ? process + ":" + this.loggerId : this.loggerId;
            loggerService.log(processName() /*UserGUID*/, this.containerName /*Host*/, proc, this.pid, thread, "TRACE", message);
        }
    }

    public void debug(String message) {
        this.logger().debug(String.format("[%s][%s] ---> DEBUG: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(null, null, message, "DEBUG");

        if (loggerService != null) {
            loggerService.debug(this.pid, this.loggerId, message);
        }
    }

    public void debug(String process, Long thread, String message) {
        this.logger().debug(String.format("[%s][%s] ---> DEBUG: [%s][%d][%s]", new Object[]{this.containerName, this.getName(), this.loggerId, Long.valueOf((new Date()).getTime()), message}));

        queueMessage(process, thread, message, "DEBUG");

        if (loggerService != null) {
            String proc = (process != null) ? process + ":" + this.loggerId : this.loggerId;
            loggerService.log(processName() /*UserGUID*/, this.containerName /*Host*/, proc, this.pid, thread, "DEBUG", message);
        }
    }

    public abstract String processName();

    public abstract Logger logger();

    private String getName() {
        return this.getMethodName() != null && this.getMethodName().length() > 0?this.processName() + "::" + this.getMethodName():this.processName();
    }

    public String getLoggerId() {
        return this.loggerId;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    private void queueMessage(String process, Long thread, String message, String logLevel) {

        String queueName = "activemq:queue:LOGGER_PROCESS_MESSAGE";

        if (this.exchange == null) {
            return;
        }

        if (this.camelContext == null) {
            return;
        }

        if (message == null) {
            error(String.format("%s: [QUEUE: %s]: ERROR: message == NULL", getClass().getName(), queueName));
            return;
        }

        if (this.template == null) {

            if (this.camelContext.getStatus() != ServiceStatus.Started) {
                error(String.format("%s:[QUEUE: %s]:  ERROR: camelContext.getStatus() != ServiceStatus.Started", getClass().getName(), queueName));
                return;
            }

            this.template = camelContext.createProducerTemplate();
        }

        this.exchange.getIn().setHeader("SANDATA_LOG_PROCESS", process);
        this.exchange.getIn().setHeader("SANDATA_LOG_PID", this.pid);
        this.exchange.getIn().setHeader("SANDATA_LOG_THREAD", thread);
        this.exchange.getIn().setHeader("SANDATA_LOG_LVL", logLevel);

        this.template.sendBodyAndHeaders(queueName, message, this.exchange.getIn().getHeaders());
    }
}
