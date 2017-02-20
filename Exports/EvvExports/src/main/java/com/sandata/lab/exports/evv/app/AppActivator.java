package com.sandata.lab.exports.evv.app;

import com.sandata.lab.common.utils.app.AppUtils;
import com.sandata.lab.common.utils.app.AppVersion;
import com.sandata.lab.exports.evv.utils.Log;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 7:04 AM
 */
public class AppActivator implements BundleActivator {
    private static Logger logger = LoggerFactory.getLogger(AppActivator.class);
    private static Logger exportLog;
    private static Logger exportExceptions;
    private static Logger exportSqlExceptions;

    @Override
    public void start(BundleContext context) throws Exception {
        Log log = new Log();
        exportLog = log.getExportLog();
        exportExceptions = log.getExportExceptions();
        exportSqlExceptions = log.getExportSqlExceptions();
        logger.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
        exportLog.info("Starting");
        exportLog.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
        exportSqlExceptions.info("Starting");
        exportSqlExceptions.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
        exportExceptions.info("Starting");
        exportExceptions.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {

        logger.info(AppUtils.stopInfo(context, new AppVersion(context.getBundle()).toString()));
        exportLog.info(AppUtils.stopInfo(context, new AppVersion(context.getBundle()).toString()));
    }
}
