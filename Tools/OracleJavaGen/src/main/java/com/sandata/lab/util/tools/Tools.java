package com.sandata.lab.util.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Date: 8/30/15
 * Time: 6:07 AM
 */

public abstract class Tools {

    protected Properties properties;

    private boolean verbosity = true;

    public Tools() {

        try {
            properties = loadProperties(propertyResourceName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties loadProperties(String fileName) throws IOException {

        Properties properties;

        InputStream is = Tools.class.getClassLoader().getResourceAsStream(fileName);
        properties = new Properties();
        properties.load(is);

        is.close();

        return properties;
    }

    protected void printStdOut(String message) {

        if (isVerbosity()) {
            System.out.println(message);
        }
    }

    public boolean isVerbosity() {
        return verbosity;
    }

    public void setVerbosity(boolean verbosity) {
        this.verbosity = verbosity;
    }

    protected abstract String propertyResourceName();

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
