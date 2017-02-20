package com.sandata.lab.util.handler;

import java.io.IOException;

/**
 * Date: 8/30/15
 * Time: 7:11 PM
 */

public class BaseObjectHandler extends InjectionHandler {

    @Override
    public void write() throws IOException {

        if (excludedFile()) {
            writer.write(line + "\n");
            return;
        }

        if (line.contains(injectionPoint)) {

            int indexOfClass = line.lastIndexOf("{") - 1;

            // Make sure index is in range!
            if (indexOfClass >= 0 && indexOfClass < line.length()) {
                String extendsBaseObject = line.substring(0, indexOfClass);
                String className = String.format("%s extends BaseObject {\n", extendsBaseObject);
                writer.write(className);

                writer.write("\n\tprivate static final long serialVersionUID = 1L;\n");
            } else {

                System.out.println(String.format("WARN: Index out of range: --------------->: %s: ", line));
            }
        }
        else {

            writer.write(line + "\n");
        }
    }
}
