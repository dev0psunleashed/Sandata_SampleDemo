package com.sandata.lab.tools.oracle.manager;

/**
 * Manager for generating Metadata Oracle Java packages and classes.
 * <p/>
 *
 * @author David Rutgos
 */
public class MetadataPackageManager extends PackageManager {

    private MetadataPackageManager() throws Exception {
        super();
    }

    public static MetadataPackageManager getInstance() throws Exception {

        return new MetadataPackageManager();
    }

    @Override
    protected String propertyResourceName() {
        return "metadata.packages.cfg";
    }

    @Override
    protected String tablePropertyResourceName() {
        return "metadata.oracle.cfg";
    }
}
