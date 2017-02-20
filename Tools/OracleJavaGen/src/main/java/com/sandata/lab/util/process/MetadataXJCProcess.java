package com.sandata.lab.util.process;

/**
 * Process XSD files via XJC and create Java entities.
 * <p/>
 *
 * @author David Rutgos
 */
public class MetadataXJCProcess extends XJCProcess {

    public static MetadataXJCProcess getInstance() {
        return new MetadataXJCProcess();
    }

    @Override
    protected String propertyResourceName() {
        return "metadata.xjc.cfg";
    }
}
