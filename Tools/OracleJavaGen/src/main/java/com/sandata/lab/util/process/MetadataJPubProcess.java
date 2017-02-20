package com.sandata.lab.util.process;

/**
 * Metadata JPub Create Process.
 * <p/>
 *
 * @author David Rutgos
 */
public class MetadataJPubProcess extends JPubProcess {

    @Override
    protected String propertyResourceName() {
        return "metadata.jpub.cfg";
    }

    public static MetadataJPubProcess getInstance() {
        return new MetadataJPubProcess();
    }
}
