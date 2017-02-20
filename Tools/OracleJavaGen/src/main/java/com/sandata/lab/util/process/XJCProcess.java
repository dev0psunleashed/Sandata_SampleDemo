package com.sandata.lab.util.process;

import java.io.File;
import java.io.InputStream;

/**
 * Date: 8/30/15
 * Time: 5:58 AM
 */

public class XJCProcess extends Process {

    protected String encoding;
    protected String packageName;
    protected String options;
    protected String targetPath;
    protected String sourcePath;
    protected String xjbPath;
    protected String xsdPath;

    public void create() throws Exception {

        System.out.println("XJCProcess: *** Starting XJC Process... ***");

        File targetFolder = new File(this.targetPath);

        ProcessBuilder pb = new ProcessBuilder(
                getName(),
                this.options,
                "-p",
                this.packageName,
                String.format("%s/%s", this.sourcePath, xsdPath),
                "-b",
                String.format("%s/%s", this.sourcePath, xjbPath),
                "-encoding",
                this.encoding,
                "-d",
                this.targetPath
        );
        pb.redirectErrorStream(true);

        java.lang.Process process = pb.start();

        InputStream stdin = process.getInputStream();

        int c;
        while ((c = stdin.read()) != -1) {

            System.out.print((char)c);
        }

        // please wait...
        try {
            Thread.sleep(250);
        }
        catch (Exception e) {
            // Oh well..
        }
    }

    public static XJCProcess getInstance() {
        return new XJCProcess();
    }

    protected XJCProcess() {
        super();

        this.encoding = properties.getProperty("encoding");
        this.packageName = properties.getProperty("package");
        this.options = properties.getProperty("options");
        this.targetPath = properties.getProperty("target.path");
        this.sourcePath = properties.getProperty("source.path");
        this.xjbPath = properties.getProperty("xjb");
        this.xsdPath = properties.getProperty("xsd");
    }

    @Override
    protected String propertyResourceName() {
        return "xjc.cfg";
    }
}
