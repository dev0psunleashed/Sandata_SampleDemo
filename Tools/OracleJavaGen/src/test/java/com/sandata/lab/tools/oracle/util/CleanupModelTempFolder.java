package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.tools.oracle.BaseTestSupport;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class CleanupModelTempFolder extends BaseTestSupport {

    // Execute delete on all TRef.java files
    @Test
    public void should_delete_ref_files_from_folder() throws Exception {

        List<String> files = FileUtil.ListFiles(new File("H:\\tmp\\JPubProcess\\com\\sandata\\lab\\data\\model\\jpub\\model"));
        int count = 0;
        for (String filePath : files) {

            if (filePath.endsWith("TRef.java")) {
                System.out.println(filePath);
                FileUtil.DeleteFile(filePath);
                count++;
            }
        }

        System.out.println(String.format("Total REF Files: %d Deleted!", count));
    }

    @Test
    public void should_delete_class_files_from_folder() throws Exception {

        List<String> files = FileUtil.ListFiles(new File("H:\\tmp\\JPubProcess\\com\\sandata\\lab\\data\\model\\jpub\\model"));
        int count = 0;
        for (String filePath : files) {

            if (filePath.endsWith(".class")) {
                System.out.println(filePath);
                FileUtil.DeleteFile(filePath);
                count++;
            }
        }

        System.out.println(String.format("Total Class Files: %d Deleted!", count));
    }

    @Override
    protected void onSetup() throws Exception {
    }
}
