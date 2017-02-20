package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.BaseTestSupport;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Date: 8/28/15
 * Time: 5:04 PM
 */
public class FileUtilTests extends BaseTestSupport {

    @Test
    public void should_list_files_in_a_given_folder() throws Exception {

        File folder = new File("src/main/java/com/sandata/lab/george/model");
        List<String> files = FileUtil.ListFiles(folder);

        Assert.assertNotNull(files);
        Assert.assertTrue(files.size() > 0);
    }

    @Test
    public void should_write_resource_file_data_to_target_path() throws Exception {

        String data = FileUtil.ReadResource("templates/java.model.template");

        FileUtil.Write(data, "OracleJava/", "Java.template");
    }

    @Test
    public void should_read_resource_file_to_string() throws Exception {

        String data = FileUtil.ReadResource("templates/java.model.template");

        System.out.println(data);
    }

    @Test
    public void should_throw_sandata_runtime_exception_when_file_not_found() throws Exception {

        try {

            FileUtil.ReadResource("no.such.file");

        } catch (Exception e) {

            Assert.assertTrue(e instanceof SandataRuntimeException);
            Assert.assertTrue(e.getMessage().endsWith("InputStream is NULL! File not found!"));
        }
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
