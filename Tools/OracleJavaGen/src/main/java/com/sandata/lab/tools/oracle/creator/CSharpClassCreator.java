package com.sandata.lab.tools.oracle.creator;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.converter.CSharpTypeConverter;
import com.sandata.lab.tools.oracle.util.FileUtil;

import java.io.*;
import java.util.List;

/**
 * Date: 8/29/15
 * Time: 3:05 PM
 */

public class CSharpClassCreator extends ClassCreator {

    private List<String> sourceFiles = null;

    private String[] usingStatements = {

            "using Sandata.George.Common;",
            "using Sandata.George.Common.Helpers;",
            "using System;",
            "using System.Collections.Generic;",
            "using System.Linq;",
            "using System.Runtime.Serialization;",
            "using System.Text;",
            "using System.Threading.Tasks;"

    };

    @Override
    public void process() throws Exception {

        System.out.println(String.format("%s: **** Start: process() ****", getClass().getSimpleName()));

        if (sourceFiles == null || sourceFiles.size() == 0) {
            throw new SandataRuntimeException("CSharpClassCreator: process: sourceFiles can not be null or empty!");
        }

        cleanFolder();

        for (String sourceFilePath : sourceFiles) {
            create(new File(sourceFilePath));
        }

        System.out.println(String.format("%s: **** Complete: process() ****", getClass().getSimpleName()));
    }

    @Override
    protected void create(final File file) throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: create() ****", getClass().getSimpleName()));

        try {
            if (!shouldSkipThisFile(file.getName())) {
                int indexOfJavaExt = file.getName().indexOf(".java");

                if (indexOfJavaExt == -1) {
                    System.out.println(String.format("WARNING: CSharpClassCreator: indexOfJavaExt == -1: [%s]", file.getName()));
                }

                String csharpFileName = String.format("%s/%s.cs", this.modelPath,
                        file.getName().substring(0, indexOfJavaExt));

                overwrite(file, new File(csharpFileName));

                System.out.println(csharpFileName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("CSharpClassCreator: Create: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: create() ****", getClass().getSimpleName()));
    }

    private void overwrite(final File sourceFile, final File targetFile) throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: overwrite() ****", getClass().getSimpleName()));

        final String sourceFilePath = sourceFile.getPath();
        final String targetFilePath = targetFile.getPath();

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            boolean writeLine = true;

            boolean startedGettersSetters = false;

            boolean stagedProperty = false;
            boolean stagedIsRequired = false;
            String stagedPropertyTemplate = null;
            String stagedPropertyName = null;

            boolean propOrder = false;  // when this is enabled; do not write until the line ends with "})"

            //dmr--06-09-2016:boolean houseKeepingField = false;  // skip over house keeping fields

            String className = null;

            reader = new BufferedReader(new FileReader(sourceFilePath));
            writer = new BufferedWriter(new FileWriter(targetFilePath));

            String line;
            while ((line = reader.readLine()) != null) {

                // Create Using Statements
                if (line.contains("package ")) {

                    for (String usingStatement : usingStatements) {

                        writer.write(usingStatement + "\n");
                    }
                }
                else if (line.contains("public class ")) {

                    // Create Namespace
                    writer.write("namespace Sandata.George.Domain.Entities\n");
                    writer.write("{\n");

                    writer.write("\t[DataContract]\n");

                    // Strip off the Java style bracket from the end
                    int indexOfBracket = line.indexOf("{");

                    className = line.substring(0, indexOfBracket);

                    // Strip out " extends BaseObject"
                    int indexOfBaseObject = className.indexOf(" extends BaseObject");
                    if (indexOfBaseObject >= 0 && indexOfBaseObject < className.length()) {

                        className = className.substring(0, indexOfBaseObject);
                    }

                    writer.write("\t" + className + "\n");
                    writer.write("\t{");
                }
                else if (line.contains("private static final long serialVersionUID = 1L;")) {
                    // Do not render...
                }
                else if (line.trim().contains("@SerializedName")) {

                    int indexOfPropertyName = line.indexOf("\"");
                    String property = line.substring(indexOfPropertyName + 1, line.length() - 2);

                    // Do not process House Keeping Fields that are stored proc only...
                    //dmr--06-09-2016: Generate a complete entity including housekeeping fields
                    /*if (houseKeepingField = isHouseKeepingField(property)) {

                        // DO NOT PROCESS
                        continue;
                    }*/

                    // Staging the property for the next line...
                    stagedProperty = true;

                    stagedPropertyTemplate = String.format("\t\t\tpublic %%s %%s { get; set; }\n\n");

                    // check if the property is similar to the className; If they are similar, add "Property" to the end
                    if (className.toLowerCase().contains(property.toLowerCase())) {
                        property += "Property";
                    }

                    stagedPropertyName = property;

                }
                else if (line.contains("@XmlAttribute")
                        || line.contains("@XmlElement")) {

                    stagedIsRequired = (line.contains("required = true"));
                }
                else if (line.contains("import ")
                        || line.contains("@Xml")) {
                      // Don't write anything...

                    // Check if we are dealing with a propOrder = { }
                    if (line.contains("propOrder =")) {
                        propOrder = true;
                    }
                }
                else if (stagedProperty && stagedPropertyTemplate != null) {

                    startedGettersSetters = true;

                    String trimmedLine = line.trim();
                    int indexOfTypeStart = trimmedLine.indexOf(" ");
                    String typeStart = trimmedLine.substring(indexOfTypeStart + 1, trimmedLine.length());
                    int indexOfTypeEnd = typeStart.lastIndexOf(" ");
                    String type = typeStart.substring(0, indexOfTypeEnd);

                    String csharpType = CSharpTypeConverter.JavaToCSharp(type, stagedIsRequired);

                    // Append an 'Collection' to the end of a List property name
                    if (csharpType.startsWith("List")) {
                        stagedPropertyName += "Collection";
                    }

                    if (type.equals("Date")) {

                        writer.write("\t\t\t[IgnoreDataMember]\n");
                        writer.write(String.format(stagedPropertyTemplate, csharpType, stagedPropertyName));

                        if (stagedIsRequired) {
                            writer.write(String.format("\t\t\t[DataMember(Name = \"%s\", IsRequired = true)]\n", stagedPropertyName));
                        }
                        else {
                            writer.write(String.format("\t\t\t[DataMember(Name = \"%s\")]\n", stagedPropertyName));
                        }

                        // Handle CSharp DateTime
                        writer.write(String.format("\t\t\tpublic %s %s\n", "string", stagedPropertyName+"Str"));
                        writer.write("\t\t\t{\n");
                        writer.write("\t\t\t\tget\n");
                        writer.write("\t\t\t\t{\n");
                        writer.write(String.format("\t\t\t\t\treturn DateTimeHelper.ToDateTimeString(%s);\n", stagedPropertyName));
                        writer.write("\t\t\t\t}\n");
                        writer.write("\t\t\t\tset\n");
                        writer.write("\t\t\t\t{\n");
                        writer.write("\t\t\t\t\tif (!String.IsNullOrEmpty(value))\n");
                        writer.write("\t\t\t\t\t{\n");
                        writer.write(String.format("\t\t\t\t\t\t%s = DateTimeHelper.ToDateTime(value);\n", stagedPropertyName));
                        writer.write("\t\t\t\t\t}\n");
                        writer.write("\t\t\t\t}\n");
                        writer.write("\t\t\t}\n\n");
                    }
                    else {

                        if (stagedIsRequired) {
                            writer.write("\t\t\t[DataMember(IsRequired = true)]\n");
                        }
                        else {
                            writer.write("\t\t\t[DataMember]\n");
                        }

                        writer.write(String.format(stagedPropertyTemplate, csharpType, stagedPropertyName));
                    }

                    stagedProperty = false;
                    stagedIsRequired = false;
                    stagedPropertyTemplate = null;
                    stagedPropertyName = null;
                }
                else if (line.contains("/**") && startedGettersSetters) {

                    // Stop Writing
                    writeLine = false;
                }
                else {

                    if (writeLine) {

                        if (propOrder) {

                            // properOrder is closed by a "})" on a line of its own... I hope...
                            if (line.startsWith("})")) {
                                propOrder = false;
                            }

                            // Do not print this line
                            continue;
                        }

                        writer.write(line + "\n");

                        //dmr--06-09-2016
                        /*if (!houseKeepingField) {
                            writer.write(line + "\n");
                        }
                        else {
                            // Skip house keeping field and reset state for next line
                            houseKeepingField = false;
                        }*/
                    }
                }
            }

            // Close Class
            writer.write("\t}");

            // Close Namespace
            writer.write("\n}\n");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("ClassCreator: overwrite: %s: [%s]",
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

        System.out.println(String.format("%s: **** Complete: overwrite() ****", getClass().getSimpleName()));
    }

    public static CSharpClassCreator getInstance() throws Exception {
        return new CSharpClassCreator();
    }

    private CSharpClassCreator() throws Exception {
        super();
    }

    @Override
    protected String propertyResourceName() {
        return "csharp.cfg";
    }

    public List<String> getSourceFiles() {
        return sourceFiles;
    }

    public void setSourceFiles(List<String> sourceFiles) {
        this.sourceFiles = sourceFiles;
    }
}
