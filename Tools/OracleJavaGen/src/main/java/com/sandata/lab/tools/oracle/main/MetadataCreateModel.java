package com.sandata.lab.tools.oracle.main;

import com.sandata.lab.tools.oracle.creator.JavaClassCreator;
import com.sandata.lab.util.process.MetadataXJCProcess;

/**
 * Generate the Java Model classes based on the XSD.
 * <p/>
 *
 * @author David Rutgos
 */
public class MetadataCreateModel {

    public static void main(String[] args) throws Exception {

        // Create Java Model classes from Logical Model XSD
        MetadataXJCProcess.getInstance().create();

        System.out.println("MetadataXJCProcess: **** Complete! ****");

        // Process Created Java Model classes
        JavaClassCreator javaClassCreator = JavaClassCreator.getInstance();
        javaClassCreator.process();

        System.out.println("JavaClassCreator: **** Complete! ****");
    }
}
