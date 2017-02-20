package com.sandata.lab.tools.oracle.model;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/3/15
 * Time: 7:51 PM
 */
@SuppressWarnings("unchecked")
public class RestServicesTemplate {

    private final String templateFilePath = "src/main/resources/templates/RestfulServices.template";
    private String groupId;
    private String className;
    private String jpubPackage;
    private String tableName;

    private List<RestEndpoint> restEndpoints;

    public void create(File targetFile, String className) throws SandataRuntimeException {

        this.className = className;

        if (getGroupId() == null) {
            throw new SandataRuntimeException("RestServicesTemplate: create: groupId == null");
        }

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            File templateFile = new File(templateFilePath);

            reader = new BufferedReader(new FileReader(templateFile));
            writer = new BufferedWriter(new FileWriter(targetFile));

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("__GROUP_ID__")) {
                    writer.write(line.replace("__GROUP_ID__", getGroupId()) + "\n");
                }
                else if (line.contains("__REST_PATH__")) {
                    writer.write(line.replace("__REST_PATH__", getTableName()) + "\n");
                }
                else if (line.contains("__REST_CLASS_NAME__")) {
                    writer.write(line.replace("__REST_CLASS_NAME__", getClassName()) + "\n");
                }
                else if (line.contains("__REST_SERVICE_TEMPLATE__")) {

                    if (restEndpoints == null || restEndpoints.size() == 0) {
                        throw new SandataRuntimeException("RetServicesTemplate: create: restEndpoints is NULL or EMPTY!");
                    }

                    for (RestEndpoint restEndpoint : restEndpoints) {
                        restEndpoint.write(writer);
                    }
                }
                else {
                    writer.write(line + "\n");
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("ClassCreator: injectAfter: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getClassName() {
        return className;
    }

    public List<RestEndpoint> getRestEndpoints() {
        return restEndpoints;
    }

    public void setRestEndpoints(List<RestEndpoint> restEndpoints) {
        this.restEndpoints = restEndpoints;
    }

    public void addRestEndpoint(RestEndpoint restEndpoint) {

        if (this.restEndpoints == null) {
            this.restEndpoints  = new ArrayList<>();
        }

        this.restEndpoints.add(restEndpoint);
    }

    public RestServicesTemplate GroupID(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public RestServicesTemplate ClassName(String className) {
        this.className = className;
        return this;
    }

    public RestServicesTemplate JpubPackage(String jpubPackage) {
        this.jpubPackage = jpubPackage;
        return this;
    }

    public RestServicesTemplate TableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getTableName() {
        return this.tableName;
    }

    public static RestServicesTemplate getInstance() {
        return new RestServicesTemplate();
    }

    private RestServicesTemplate() {
    }
}
