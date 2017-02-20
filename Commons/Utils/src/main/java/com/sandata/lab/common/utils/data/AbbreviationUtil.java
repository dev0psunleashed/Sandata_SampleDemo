package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.data.model.Abbreviation;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.tools.Tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility to convert between logical and physical names using the latest abbreviation csv resource.
 * <p/>
 *
 * @author David Rutgos
 */
public class AbbreviationUtil extends Tools {

    public static int COREDATA_TYPE = 0;
    public static int METADATA_TYPE = 1;

    private Map<String, String> abbrMapToPhysical = new HashMap<>();
    private Map<String, String> abbrMapToLogical = new HashMap<>();

    /**
     * Exceptions are defined in the abbreviations.cfg.
     * @param entityName is the result of the convertEntity method.
     * @return is the value assigned to the key or return the original if there is no exception.
     */
    private String exceptionMapper(String entityName) {
        String exception = getProperty(entityName);
        if (exception != null) {
            return exception;
        }
        return entityName;
    }

    public String toLogical(String physicalName) {
        return convertEntity(physicalName, abbrMapToLogical);
    }

    public String toPhysical(String logicalName) {
        return convertEntity(logicalName, abbrMapToPhysical).toUpperCase();
    }

    private String convertEntity(String entityName, Map<String, String> map) {

        StringBuilder result = new StringBuilder();

        String[] parts = entityName.split("_");
        for (int index = 0; index < parts.length - 1; index++) {
            String abbr = map.get(parts[index]);
            if (abbr != null) {
                result.append(abbr + "_");
            }
            else {
                result.append(parts[index] + "_");
            }
        }

        String abbr = map.get(parts[parts.length - 1]);
        if (abbr != null) {
            result.append(abbr);
        }
        else {
            result.append(parts[parts.length - 1]);
        }

        return exceptionMapper(result.toString());
    }

    public AbbreviationUtil(int type) {
        init(type);
    }

    @Override
    protected String propertyResourceName() {
        return "abbreviations.cfg";
    }

    private void init(int type) throws SandataRuntimeException {

        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            String fileName = getProperty("coredata"); //default

            if (type == METADATA_TYPE) {
                fileName = getProperty("metadata");
            }

            ClassLoader classLoader = getClass().getClassLoader();
            inputStream = classLoader.getResourceAsStream(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int lineNumber = 0;
            while((line = reader.readLine()) != null) {

                lineNumber++;  // skip the first line which is the headers

                if (lineNumber > 1) {

                    Abbreviation abbreviation = Abbreviation.createFromDelimString(line, ",");

                    abbrMapToPhysical.put(abbreviation.getLogicalName(), abbreviation.getPhysicalName());
                    abbrMapToLogical.put(abbreviation.getPhysicalName(), abbreviation.getLogicalName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: init(): EXCEPTION: %s: %s",
                            getClass().getName(), e.getClass().getName(), e.getMessage()));
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
