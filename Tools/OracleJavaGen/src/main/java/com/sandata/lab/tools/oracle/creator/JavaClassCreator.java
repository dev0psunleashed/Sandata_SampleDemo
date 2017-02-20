package com.sandata.lab.tools.oracle.creator;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.util.FileUtil;
import com.sandata.lab.util.handler.BaseObjectHandler;
import com.sandata.lab.util.handler.BasicInjectionHandler;
import com.sandata.lab.util.handler.InjectionHandler;
import com.sandata.lab.util.handler.SerializedNameHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 8/29/15
 * Time: 11:31 AM
 */

@SuppressWarnings("unchecked")
public class JavaClassCreator extends ClassCreator {

    private String PACKAGE = "package com.sandata.lab.rest.oracle.model;";

    private String[] imports = {

            "import com.sandata.lab.data.model.*;",
            "import com.google.gson.annotations.SerializedName;",
            "import com.sandata.lab.data.model.base.BaseObject;",
            "import com.sandata.lab.data.model.dl.annotation.Mapping;",
            "import com.sandata.lab.data.model.dl.annotation.OracleMetadata;"
    };

    private String[] serialization = {

            "@SerializedName(\"__SERIALIZED_NAME__\")"
    };

    @Override
    protected void create(final File file) throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: create() ****", getClass().getSimpleName()));

        try {

            if (!shouldSkipThisFile(file.getName())) {
                addImports(file);
                addSerializedNameAnnotation(file);
                addBaseObjectExtension(file);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("JavaClassCreator: Clean: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: create() ****", getClass().getSimpleName()));
    }

    public void addSerializedNameAnnotation(final File file) throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: addSerializedNameAnnotation() ****", getClass().getSimpleName()));

        try {
            inject(file, "@XmlAttribute", new SerializedNameHandler(), serialization);
            inject(file, "@XmlElement", new SerializedNameHandler(), serialization);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("JavaClassCreator: addSerializedNameAnnotation: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: addSerializedNameAnnotation() ****", getClass().getSimpleName()));
    }

    public void addBaseObjectExtension(final File file) throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: addBaseObjectExtension() ****", getClass().getSimpleName()));

        try {
            inject(file, "public class ", new BaseObjectHandler(), "extends BaseObject");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("JavaClassCreator: addBaseObjectExtension: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: addBaseObjectExtension() ****", getClass().getSimpleName()));
    }

    public void addImports(final File file) throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: addImports() ****", getClass().getSimpleName()));

        try {

            inject(file, PACKAGE, new BasicInjectionHandler(), imports);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("JavaClassCreator: addImports: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: addImports() ****", getClass().getSimpleName()));
    }

    private void inject(final File file, final String injectionPoint, final InjectionHandler handler, final String... strings)
            throws SandataRuntimeException {

        System.out.println(String.format("%s: **** Start: injectAfter(%s, %s) ****",
                getClass().getSimpleName(), file.getName(), injectionPoint));

        handler.setStrings(strings);
        handler.setInjectionPoint(injectionPoint);
        handler.setFileName(file.getName());

        handler.setExcludedFile(getExcludedFilesMap());

        final String originalFilePath = file.getPath();
        final String tmpFilePath = String.format("%s.tmp", file.getPath());

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(originalFilePath));
            writer = new BufferedWriter(new FileWriter(tmpFilePath));

            String line;
            while ((line = reader.readLine()) != null) {

                    handler.setLine(line);
                    handler.setWriter(writer);
                    handler.write();
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

        try {
            FileUtil.RenameTempFile(tmpFilePath, originalFilePath);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("ClassCreator: injectAfter: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        System.out.println(String.format("%s: **** Complete: injectAfter(%s, %s) ****",
                getClass().getSimpleName(), file.getName(), injectionPoint));
    }

    public static JavaClassCreator getInstance() throws Exception {

        return new JavaClassCreator();
    }

    private JavaClassCreator() throws Exception {
        super();
    }

    @Override
    protected String propertyResourceName() {
        return "java.cfg";
    }
}
