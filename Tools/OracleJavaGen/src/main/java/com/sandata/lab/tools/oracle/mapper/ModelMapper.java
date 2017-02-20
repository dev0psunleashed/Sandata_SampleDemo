package com.sandata.lab.tools.oracle.mapper;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.creator.ClassCreator;
import com.sandata.lab.tools.oracle.model.GetterSetter;
import com.sandata.lab.tools.oracle.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/1/15
 * Time: 10:38 AM
 */

public abstract class ModelMapper extends ClassCreator {

    private final String targetPath;
    private final List<String> targetFiles;

    protected ModelMapper() throws Exception {
        super();

        this.targetPath = properties.getProperty("target.path");
        this.targetFiles = FileUtil.ListFiles(new File(this.targetPath));
    }

    public String getTargetPath() {
        return targetPath;
    }

    public List<String> getTargetFiles() {
        return targetFiles;
    }

    @Override
    protected void create(File file) {
        // Not used for ModelMapper instance..for now
    }

    protected void create(File targetFile, File sourceFile) {

        List<GetterSetter> getterSetters = processProperties(sourceFile);

        // REMINDER: Place holders will be set when we run OracleTools...
        String oracleMetadata = "@OracleMetadata(\n" +
                "        packageName = \"__ORACLE_PKG__\",\n" +
                "        insertMethod = \"__INSERT_METHOD__\",\n" +
                "        updateMethod = \"__UPDATE_METHOD__\",\n" +
                "        deleteMethod = \"__DELETE_METHOD__\",\n" +
                "        getMethod = \"__GET_METHOD__\",\n" +
                "        jpubClass = \"__JPUB_CLASS__\",\n" +
                "        primaryKeys = { __PRIMARY_KEYS__ })\n";

        addCustomAnnotation(targetFile, getterSetters, oracleMetadata);
    }

    protected List<GetterSetter> processProperties(final File file) {

        List<GetterSetter> getterSetters = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String line;
            String key = "public void set";
            while ((line = reader.readLine()) != null) {

                line = line.trim();
                String className = FileUtil.RemoveFileExtension(file, ".java");
                String constructor = String.format("public %s(", className);
                if (line.startsWith(constructor) && !line.startsWith(constructor + ")" /*Exclude Empty Constructor*/)) {

                    // parse out all the parameters and create getter/setter methods
                    int endIndex = line.lastIndexOf(")");
                    String params = line.substring(constructor.length(), endIndex);

                    String[] paramsArray = params.split(",");
                    for (String paramElement : paramsArray) {

                        // TYPE
                        paramElement = paramElement.trim();
                        String[] paramElementArray = paramElement.split(" ");
                        GetterSetter getterSetter = new GetterSetter();
                        getterSetter.setType(paramElementArray[0].trim());


                        String property = paramElementArray[1].trim();

                        // GET
                        String getter = "get" + property.substring(0, 1).toUpperCase() +
                                property.substring(1, property.length());
                        getterSetter.setGetter(getter);


                        // SET
                        String setter = "set" + property.substring(0, 1).toUpperCase() +
                                property.substring(1, property.length());
                        getterSetter.setSetter(setter);

                        // Set Array
                        getterSetters.add(getterSetter);
                    }

                    //System.out.println("Constructor PARAMS: " + params);
                }
                else if (line.startsWith(key) && getterSetters.size() == 0) {

                    GetterSetter getterSetter = new GetterSetter();
                    String property = line.substring(key.length(), line.indexOf("("));
                    String type = line.substring(line.indexOf("(") + 1, line.indexOf(")") - (property.length() + 1));
                    getterSetter.setType(type);
                    getterSetter.setGetter("get" + property);
                    getterSetter.setSetter("set" + property);
                    getterSetters.add(getterSetter);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            throw new SandataRuntimeException(String.format("PatientModelMapper: processProperties: %s: [%s]",
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
        }

        return getterSetters;
    }

    // Add the @Mapping(getter = "getPatientId", setter = "setPatientId") to all NON List properties
    // TODO: ASS-U-ME-TION: The properties are generated in the same order one-to-one (hope this is always true)... Needs work
    protected void addCustomAnnotation(final File targetFile, final List<GetterSetter> getterSetters, final String oracleMetadata) {

        int propertyIndex = 0;

        System.out.println(String.format("%s: **** Start: addCustomAnnotation(%s) ****",
                getClass().getSimpleName(), targetFile.getName()));

        final String originalFilePath = targetFile.getPath();
        final String tmpFilePath = String.format("%s.tmp", targetFile.getPath());

        BufferedReader reader = null;
        BufferedWriter writer = null;

        int gettersSettersIndex = 0;
        try {
            reader = new BufferedReader(new FileReader(originalFilePath));
            writer = new BufferedWriter(new FileWriter(tmpFilePath));

            String line;
            while ((line = reader.readLine()) != null) {

                String trimmedLine = line.trim();

                if (trimmedLine.startsWith("public class ")) {

                    //String className = FileUtil.RemoveFileExtension(sourceFile, ".java");
                    writer.write(oracleMetadata);
                }

                // Do not process lists
                else if (!trimmedLine.startsWith("protected List")
                        && trimmedLine.startsWith("protected")) {

                    GetterSetter getterSetter = getterSetters.get(gettersSettersIndex++);

                    String getterSetterString = String.format("\t@Mapping(getter = \"%s\", setter = \"%s\", type = \"%s\", index = %d)\n",

                            getterSetter.getGetter(),
                            getterSetter.getSetter(),
                            getterSetter.getType(),
                            propertyIndex++
                    );

                    // Inject @Mapping annotation
                    writer.write(getterSetterString);

                    systemOut("[" + targetFile.getName() + "]: " + getterSetterString);
                }

                // Print original line
                writer.write(line + "\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("ModelMapper: injectAfter: %s: [%s]",
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

        try {
            FileUtil.RenameTempFile(tmpFilePath, originalFilePath);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("ClassCreator: injectAfter: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: addCustomAnnotation(%s) ****",
                getClass().getSimpleName(), targetFile.getName()));
    }

    protected abstract String modelFileName();
    protected abstract String normalizedFileName();

    @Override
    public void process() throws Exception {

        System.out.println(String.format("%s: **** Start: process() ****", getClass().getSimpleName()));

        for (String modelFileStr : getModelFiles()) {

            File modelFile = new File(modelFileStr);
            if (modelFile.getName().equals(modelFileName())) {

                for (String targetFileStr : getTargetFiles()) {

                    File targetFile = new File(targetFileStr);
                    if (targetFile.getName().equals(normalizedFileName())) {

                        create(targetFile, modelFile);
                    }
                }
            }
        }
    }

    @Override
    protected String propertyResourceName() {
        return "model.mapper.cfg";
    }
}
