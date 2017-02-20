package com.sandata.lab.tools.oracle.creator;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.model.*;
import com.sandata.lab.tools.oracle.util.TemplateUtil;
import com.sandata.lab.util.tools.Tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/3/15
 * Time: 3:45 PM
 */
@SuppressWarnings("unchecked")
public class RestServicesCreator extends Tools {

    public static void main(String[] args) throws Exception {

        RestServicesCreator.getInstance()
                .ProjectName("GenPatientRestService")
                .GroupID("com.sandata.lab.rest.oracle")
                .ArtifactID("sandata-rest-oracle-data-service")
                .Description("Sandata REST Oracle Service")
                .Version("1.0")
                .RestServiceTemplate(GenerateRestEndpointsMockData())
                .Create();
    }

    private List<String> jpubExecProcess;

    private final String projectPath;
    private final String className;
    private final String templatePath;
    private final String serviceResourcePath;
    private final String jpubExecName;
    private String tableName;

    private String groupId;
    private String artifactId;
    private String description;
    private String projectName;
    private String version;

    private final String javaPath;
    private final String resourcesPath;
    private String completeProjectPath;
    private String completeResourcePath;
    private String completeJavaPath;
    private String completeTestPath;

    private RestServicesTemplate restServicesTemplate;

    private HashMap<String, String> hashReplace;

    public RestServicesCreator AddExecProcess(String jpubExecProcess) {

        if (this.jpubExecProcess == null) {
            this.jpubExecProcess = new ArrayList<>();
        }

        this.jpubExecProcess.add(jpubExecProcess);
        return this;
    }

    public RestServicesCreator MockRestServiceTemplate() {
        this.restServicesTemplate = GenerateRestEndpointsMockData()
                .GroupID(getGroupId())
                .ClassName(getClassName());
        return this;
    }

    public RestServicesCreator RestServiceTemplate(final RestServicesTemplate restServicesTemplate) {
        this.restServicesTemplate = restServicesTemplate
                                        .GroupID(getGroupId())
                                        .ClassName(getClassName());
        return this;
    }

    public RestServicesCreator ProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public RestServicesCreator GroupID(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public RestServicesCreator ArtifactID(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public RestServicesCreator Description(String description) {
        this.description = description;
        return this;
    }

    public RestServicesCreator Version(String version) {
        this.version = version;
        return this;
    }

    public RestServicesCreator TableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getTableName() {
        return this.tableName;
    }

    public RestServicesCreator Create() throws SandataRuntimeException {

        if (this.restServicesTemplate == null) {
            throw new SandataRuntimeException("RestServiceCreator: restServicesTemplate == null");
        }

        // String projectName, String groupId, String artifactId, String description, String version
        if (this.projectName == null) {
            throw new SandataRuntimeException("RestServiceCreator: projectName == null");
        }

        if (this.groupId == null) {
            throw new SandataRuntimeException("RestServiceCreator: groupId == null");
        }

        if (this.artifactId == null) {
            throw new SandataRuntimeException("RestServiceCreator: artifactId == null");
        }

        if (this.description == null) {
            throw new SandataRuntimeException("RestServiceCreator: description == null");
        }

        if (this.version == null) {
            throw new SandataRuntimeException("RestServiceCreator: version == null");
        }

        System.out.println(String.format("%s: **** Start: create() ****", getClass().getSimpleName()));

        hashReplace = new HashMap<>();
        hashReplace.put("__GROUP_ID__", getGroupId());
        hashReplace.put("__ARTIFACT_ID__", getArtifactId());
        hashReplace.put("__REST_VERSION__", getVersion());
        hashReplace.put("__DESCRIPTION__", getDescription());
        hashReplace.put("__REST_CLASS_NAME__", getClassName());
        hashReplace.put("__REST_PATH__", getTableName());

        try {
            generateFolderStructure();
            generatePOM();
            generateTestSupport();
            generateFeatures();
            generateBlueprint();
            generateJava();
            generateJpubExec();

            this.restServicesTemplate.create(new File(String.format("%s/%s.java", this.completeJavaPath, this.className)),
                    this.className);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: %s: %s",
                    getClass().getName(), e.getClass().getName(), e.getMessage()));
        }

        return this;
    }

    private void generateJpubExec() throws SandataRuntimeException, IOException {

        if (jpubExecProcess == null) {
            throw new SandataRuntimeException("RestServicesCreator: generateJpubExec: jpubExecProcess == null");
        }

        for (String jpubExec : jpubExecProcess) {

            jpubExec = jpubExec + "\n";

            File execBatchFile = new File(String.format("%s/%s", this.completeResourcePath, this.jpubExecName));
            if (!execBatchFile.exists()) {
                Files.write(Paths.get(execBatchFile.getPath()),
                        jpubExec.getBytes(), StandardOpenOption.CREATE);
            }
            else {
                Files.write(Paths.get(execBatchFile.getPath()),
                        jpubExec.getBytes(), StandardOpenOption.APPEND);
            }
        }
    }

    private void generateJava() {

        File templateRootFolder = new File(serviceResourcePath + "/src/main/java");
        processJavaFolder(templateRootFolder, null);
    }

    private void processJavaFolder(File folder, File completeTargetPath) {

        for (File file : folder.listFiles()) {

            boolean isDir = file.isDirectory();

            if (isDir) {
                //System.out.println(String.format("[D] %s", file.getPath()));

                // Substring: Get the path after the template file path which are the folders/files we will generate for
                // the service project.
                String javaSourcePath = file.getPath().substring((serviceResourcePath + "/src/main/java/").length());
                File targetFile = new File(completeJavaPath + "/" + javaSourcePath);

                // Create the folder in the target location
                if (!targetFile.exists()) {

                    if (!targetFile.mkdirs()) {
                        throw new SandataRuntimeException(String.format("RestServicesCreator: processJavaFolder: " +
                                "Could not create folder: [%s]", file.getPath()));
                    }
                }

                // WARNING: Recursive!!!
                processJavaFolder(file, targetFile);
            }
            else {
                // Skip
                if (file.getName().equals(".DS_Store")) {
                    continue;
                }

                if (completeTargetPath == null) {
                    completeTargetPath = new File(completeJavaPath);
                }

                TemplateUtil.KeyValueReplace(file, new File(completeTargetPath.getPath() + "/" + file.getName()), hashReplace);

                //System.out.println(String.format("\t%s", file.getPath()));
            }
        }
    }

    private void generateBlueprint() {

        File templateFile = new File(serviceResourcePath + "/src/main/resources/OSGI-INF/blueprint/blueprint.xml");
        File targetFile = new File(completeResourcePath + "/OSGI-INF/blueprint");

        if (!targetFile.exists()) {
            if (!targetFile.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create folder: %s",
                                targetFile.getPath()));
            }
        }

        TemplateUtil.KeyValueReplace(templateFile, new File(targetFile.getPath() + "/blueprint.xml"), hashReplace);
    }

    private void generateFeatures() {

        File templateFile = new File(serviceResourcePath + "/src/main/resources/features.xml");
        File targetFile = new File(completeResourcePath + "/features.xml");

        TemplateUtil.KeyValueReplace(templateFile, targetFile, hashReplace);
    }

    private void generateTestSupport() {

        File templateFile = new File(serviceResourcePath + "/src/test/BaseTestSupport.java");
        File targetTestFile = new File(completeTestPath + "/BaseTestSupport.java");

        File templateIntegrationFile = new File(serviceResourcePath + "/src/test/integration/BaseIntegrationTest.java");

        File targetTestIntegrationFile = new File(completeTestPath + "/integration");
        if (!targetTestIntegrationFile.exists()) {
            if (!targetTestIntegrationFile.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create folder: %s",
                                targetTestIntegrationFile.getPath()));
            }
        }

        File completeTestIntegrationFile = new File(targetTestIntegrationFile.getPath() + "/" + "/BaseIntegrationTest.java");

        TemplateUtil.KeyValueReplace(templateFile, targetTestFile, hashReplace);
        TemplateUtil.KeyValueReplace(templateIntegrationFile, completeTestIntegrationFile, hashReplace);
    }

    private void generatePOM() {

        File templateFile = new File(serviceResourcePath + "/pom.xml");
        File targetFile = new File(completeProjectPath + "/pom.xml");

        TemplateUtil.KeyValueReplace(templateFile, targetFile, hashReplace);
    }

    private void generateFolderStructure() {

        this.completeProjectPath = this.projectPath + "/" + getProjectName();

        // Create project folder
        File projectDirectory = new File(completeProjectPath);
        if (!projectDirectory.exists()) {

            if (!projectDirectory.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create services project folder: %s",
                                projectDirectory.getPath()));
            }
        }

        /**
         * Create Source Tree
         */

        // Java
        File javaDir = new File(projectDirectory.getPath() + "/" + javaPath);
        if (!javaDir.exists()) {
            if (!javaDir.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create java source project folder: %s",
                                javaDir.getPath()));
            }
        }

        // Resources
        File resourcesDir = new File(projectDirectory.getPath() + "/" + resourcesPath);
        if (!resourcesDir.exists()) {
            if (!resourcesDir.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create resources project folder: %s",
                                resourcesDir.getPath()));
            }
        }

        this.completeResourcePath = resourcesDir.getPath();

        String testPath = "src/test";
        File testDir = new File(projectDirectory.getPath() + "/" + testPath);
        if (!testDir.exists()) {
            if (!testDir.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create test project folder: %s",
                                testDir.getPath()));
            }
        }

        File srcTreeDir = new File(javaDir.getPath() + "/" + rootSourceTree());
        if (!srcTreeDir.exists()) {
            if (!srcTreeDir.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create root source folder structure: %s",
                                srcTreeDir.getPath()));
            }
        }

        this.completeJavaPath = srcTreeDir.getPath();

        File testTreeDir = new File(testDir.getPath() + "/" + rootSourceTree());
        if (!testTreeDir.exists()) {
            if (!testTreeDir.mkdirs()) {
                throw new SandataRuntimeException(
                        String.format("RestServicesCreator: create: Could not create root test folder structure: %s",
                                testTreeDir.getPath()));
            }
        }

        this.completeTestPath = testTreeDir.getPath();
    }

    private String rootSourceTree() {

        String[] srcTree = getGroupId().split("\\.");

        StringBuilder rootPath = new StringBuilder();

        for (int index = 0; index < srcTree.length - 1; index++) {
            rootPath.append(srcTree[index] + "/");
        }

        rootPath.append(srcTree[srcTree.length - 1]);

        return rootPath.toString();
    }

    /**
     * TEST DATA
     * @return Fake data to test RestServicesTemplate rendering.
     */
    private static RestServicesTemplate GenerateRestEndpointsMockData() {

        RestServicesTemplate restServicesTemplate = RestServicesTemplate.getInstance();

        InsertEndpoint insertEndpoint = new InsertEndpoint();
        insertEndpoint.setPathName("patient");
        insertEndpoint.setModelClass("Patient");
        insertEndpoint.setMethodName("insertMethod");
        restServicesTemplate.addRestEndpoint(insertEndpoint);

        UpdateEndpoint updateEndpoint = new UpdateEndpoint();
        updateEndpoint.setPathName("patient");
        updateEndpoint.setModelClass("Patient");
        updateEndpoint.setMethodName("updateMethod");
        restServicesTemplate.addRestEndpoint(updateEndpoint);

        DeleteEndpoint deleteEndpoint = new DeleteEndpoint();
        deleteEndpoint.setPathName("patient");
        deleteEndpoint.setModelClass("Patient");
        deleteEndpoint.setMethodName("deleteMethod");
        restServicesTemplate.addRestEndpoint(deleteEndpoint);

        GetSkEndpoint getSkEndpoint = new GetSkEndpoint();
        getSkEndpoint.setPathName("patient");
        getSkEndpoint.setModelClass("Patient");
        getSkEndpoint.setMethodName("getSkMethod");
        restServicesTemplate.addRestEndpoint(getSkEndpoint);

        GetPkEndpoint getPkEndpoint = new GetPkEndpoint();
        getPkEndpoint.setPathName("patient");
        getPkEndpoint.setModelClass("Patient");
        getPkEndpoint.setMethodName("getPkMethod");

        getPkEndpoint.addMatrixParam("patient_id");
        getPkEndpoint.addMatrixParam("entity_id");

        restServicesTemplate.addRestEndpoint(getPkEndpoint);

        return restServicesTemplate;
    }

    public static RestServicesCreator getInstance() {
        return new RestServicesCreator();
    }

    private RestServicesCreator() {

        this.projectPath = properties.getProperty("project.path");
        this.className = properties.getProperty("service.endpoints.classname");
        this.templatePath = properties.getProperty("template.path");
        this.serviceResourcePath = properties.getProperty("rest.service.resource.path");
        this.jpubExecName = properties.getProperty("jpub.exe");

        this.javaPath = "src/main/java";
        this.resourcesPath = "src/main/resources";
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getClassName() {
        return className;
    }

    public String getVersion() {
        return version;
    }

    @Override
    protected String propertyResourceName() {
        return "rest.create.cfg";
    }
}
