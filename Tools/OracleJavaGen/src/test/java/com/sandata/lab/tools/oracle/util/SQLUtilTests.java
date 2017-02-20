package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.tools.oracle.BaseTestSupport;
import com.sandata.lab.util.sql.SQLUtil;
import org.junit.Test;
import org.springframework.util.Assert;

import java.io.File;

public class SQLUtilTests extends BaseTestSupport {

    private String genCoredataPath = "src/main/resources/gen/coredata";
    private String genMetadataPath = "src/main/resources/gen/metadata";

    @Test
    public void should_create_coredata_reference_script() throws Exception {

        String referenceScript = SQLUtil.createReferenceScript(genCoredataPath);

        Assert.notNull(referenceScript);

        System.out.println(referenceScript);

        FileUtil.DeleteFile(genCoredataPath + "/install.coredata.sql");
        FileUtil.Write(referenceScript, genCoredataPath, "install.coredata.sql");
    }

    @Test
    public void should_create_metadata_reference_script() throws Exception {

        String referenceScript = SQLUtil.createReferenceScript(genMetadataPath);

        Assert.notNull(referenceScript);

        System.out.println(referenceScript);

        FileUtil.DeleteFile(genMetadataPath + "/install.metadata.sql");
        FileUtil.Write(referenceScript, genMetadataPath, "install.metadata.sql");
    }

    @Test
    public void should_create_format_reference_script() throws Exception {

        String formatStringRef = SQLUtil.formatReferenceScript("java", "AddressPriorityLkup.java.sql");

        Assert.notNull(formatStringRef);
        Assert.isTrue(formatStringRef.equals("@@\"./java/AddressPriorityLkup.java.sql\";"));
    }

    @Test
    public void should_list_sql_files_under_gen_folder() throws Exception {

        File file = new File(genCoredataPath);

        Assert.notNull(file);

        File[] files = file.listFiles();

        Assert.notNull(files);
        Assert.isTrue(files.length > 0);

    }
    
    @Override
    protected void onSetup() throws Exception {

    }
}
