package com.sandata.lab.rules.call.matching.app;

import ch.qos.logback.classic.LoggerContext;

import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/14/2016
 * Time: 12:18 PM
 */
public class Slf4jConfigurator {
    private static final String BUNDLE_NAME = FrameworkUtil.getBundle(Slf4jConfigurator.class).getSymbolicName();
    private static final String LOGBACK_XML = "/logback.xml";

    public static void configure() {
        //reset slf4j config
        /*JoranConfigurator configurator = new JoranConfigurator();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        configurator.setContext(loggerContext);
        loggerContext.reset();

        //read logback.xml
        try {
            //URL configFile = new URL(LOGBACK_XML);
            //InputStream configStream = configFile.openStream();
            URL configFile = new URL(LOGBACK_XML);
            InputStream configStream = configFile.openStream();
            configurator.doConfigure(configStream);

        } catch( JoranException | IOException e) {
            //problem reading logback.xml file
            e.printStackTrace();
        }
        Logger logger = LoggerFactory.getLogger(Slf4jConfigurator.class);
        logger.info("Logging configurator initialised!");
        */
    }
}
