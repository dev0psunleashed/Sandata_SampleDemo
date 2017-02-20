package com.sandata.lab.util.process;

import com.sandata.lab.util.tools.Tools;

/**
 * Date: 8/30/15
 * Time: 6:03 AM
 */

public abstract class Process extends Tools {

    private final String name;

    public Process() {
        super();
        this.name = properties.getProperty("process.name");
    }

    public String getName() {
        return name;
    }
}
