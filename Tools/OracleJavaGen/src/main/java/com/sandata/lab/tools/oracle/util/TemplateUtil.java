package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Date: 9/3/15
 * Time: 5:51 PM
 */
@SuppressWarnings("unchecked")
public class TemplateUtil {

    public static void KeyValueReplace(final File templateFile, final File targetFile,
                                       final HashMap<String, String> keyValueMap) throws SandataRuntimeException {

        try {

            BufferedReader reader = null;
            BufferedWriter writer = null;

            try {
                reader = new BufferedReader(new FileReader(templateFile));
                writer = new BufferedWriter(new FileWriter(targetFile));

                String line;
                while ((line = reader.readLine()) != null) {

                    Iterator itr = keyValueMap.entrySet().iterator();
                    while (itr.hasNext()) {
                        Map.Entry<String, String> pair = (Map.Entry<String, String>)itr.next();
                        if (line.contains(pair.getKey())) {
                            // Replace string and then assign new string to 'line'
                            line = line.replace(pair.getKey(), pair.getValue());
                        }
                    }

                    writer.write(line + "\n");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new SandataRuntimeException(String.format("TemplateUtil: KeyValueReplace: %s: [%s]",
                        e.getClass().getName(), e.getMessage()));
            }
            finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("TemplateUtil: KeyValueReplace: %s: %s", e.getClass().getName(), e.getMessage()));
        }
    }
}
