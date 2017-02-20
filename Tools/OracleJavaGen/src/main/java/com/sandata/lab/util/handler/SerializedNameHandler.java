package com.sandata.lab.util.handler;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.io.IOException;

/**
 * Date: 8/29/15
 * Time: 12:45 PM
 */

public class SerializedNameHandler extends InjectionHandler {

    @Override
    public void write() throws IOException {

        writer.write(line + "\n");

        // Inject After
        if (line.contains(injectionPoint)) {

            try {
                String whitespace = line.substring(0, line.indexOf("@"));

                // Verify
                if (line.contains("@XmlAttribute(name")
                        || (line.contains("@XmlElement(name"))) {

                    int startIndex = line.indexOf("name = \"") + "name = \"".length();
                    byte[] bytes = line.getBytes();
                    int c;

                    StringBuilder builder = new StringBuilder();
                    // Strip out the name value
                    while ((c = bytes[startIndex]) != '\"') {

                        // skip underscores
                        if (c != '_') {
                            builder.append(line.substring(startIndex, startIndex + 1));
                        }

                        startIndex++;
                    }

                    String serializeTemplate = strings[0];
                    String serializedString = serializeTemplate.replace("__SERIALIZED_NAME__", builder.toString());

                    writer.write(whitespace + serializedString + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SandataRuntimeException(String.format("SerializedNameHandler: write: %s: [%s]",
                        e.getClass().getName(), e.getMessage()));
            }
        }
    }
}
