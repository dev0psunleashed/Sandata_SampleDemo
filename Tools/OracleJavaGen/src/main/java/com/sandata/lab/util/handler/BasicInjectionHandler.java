package com.sandata.lab.util.handler;

import java.io.IOException;

/**
 * Date: 8/29/15
 * Time: 12:31 PM
 */

public class BasicInjectionHandler extends InjectionHandler {

    @Override
    public void write() throws IOException {

        writer.write(line + "\n");

        // Inject After
        if (line.contains(injectionPoint)) {
            writer.write("\n");

            for (String string2write : strings) {

                writer.write(string2write + "\n");
            }

            writer.write("\n");
        }
    }
}
