package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.tools.oracle.BaseTestSupport;
import com.sandata.lab.tools.oracle.creator.JavaClassCreator;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Date: 8/29/15
 * Time: 11:49 AM
 */

public class JavaClassCreatorTests extends BaseTestSupport {

    @Test
    public void should_clean_jaxb_java_model_files() throws Exception {

        JavaClassCreator.getInstance().process();
    }

    @Test
    public void should_add_serialization_annotation_to_given_java_model_files() throws Exception {

        File folder = new File("src/main/java/com/sandata/lab/george/model");
        List<String> files = FileUtil.ListFiles(folder);
        for (String filePath : files) {
            JavaClassCreator.getInstance().addSerializedNameAnnotation(new File(filePath));
        }
    }

    @Test
    public void should_add_imports_to_given_java_model_files() throws Exception {

        File folder = new File("src/main/java/com/sandata/lab/george/model");
        List<String> files = FileUtil.ListFiles(folder);
        for (String filePath : files) {
            JavaClassCreator.getInstance().addImports(new File(filePath));
        }
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
