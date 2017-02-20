package com.sandata.lab.util.process;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dmrutgos on 8/31/2015.
 */
public class JPubProcess extends Process {

    protected final String processPath;
    protected final String user;
    protected final String password;
    protected final String server;
    protected final String database;
    protected String packageName;
    protected String typName;
    protected String className;
    protected final String targetPath;
    protected final String executable;

    @Override
    public String toString() {

        return String.format("call %s %s %s %s %s %s %s %s %s",
                        getExecutable(),
                        this.user,
                        this.password,
                        this.server,
                        this.database,
                        this.typName,
                        this.className,
                        this.packageName,
                        this.targetPath);
    }

    public void createTyp(final String typName, final String className, final String packageName) throws IOException {

        this.typName = typName;
        this.className = className;
        this.packageName = packageName;
    }

    public void execute() throws IOException {
        executeTyp(this.typName, this.className, this.packageName);
    }

    private void executeTyp(final String typName, final String className, final String packageName) throws IOException {

        System.out.println(String.format("JPubProcess: *** Starting createTyp... [%s][%s]***",
                typName, className));

        ProcessBuilder pb = new ProcessBuilder(

                getExecutable(),
                this.user,
                this.password,
                this.server,
                this.database,
                typName,
                className,
                packageName,
                targetPath
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

        System.out.println(String.format("JPubProcess: *** Complete createTyp! [%s][%s]***",
                typName, className));
    }

    public static JPubProcess getInstance() {
        return new JPubProcess();
    }

    protected JPubProcess() {
        super();

        this.processPath = properties.getProperty("process.path");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
        this.server = properties.getProperty("server");
        this.database = properties.getProperty("database");
        //dmr--this.packageName = properties.getProperty("package");
        this.targetPath = properties.getProperty("target.path");
        this.executable = String.format("%s\\%s", this.processPath, this.getName());
    }

    @Override
    protected String propertyResourceName() {
        return "jpub.cfg";
    }

    public String getProcessPath() {
        return processPath;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getServer() {
        return server;
    }

    public String getDatabase() {
        return database;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getExecutable() {
        return executable;
    }
}
