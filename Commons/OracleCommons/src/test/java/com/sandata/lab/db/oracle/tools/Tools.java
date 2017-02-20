package com.sandata.lab.db.oracle.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Date: 8/19/15
 * Time: 5:41 AM
 */

public abstract class Tools {

    protected Properties properties;

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

    protected abstract String propertyResourceName();
}
