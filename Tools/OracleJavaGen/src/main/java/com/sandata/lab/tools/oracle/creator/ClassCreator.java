package com.sandata.lab.tools.oracle.creator;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.util.FileUtil;
import com.sandata.lab.util.tools.Tools;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 8/29/15
 * Time: 3:42 PM
 */
@SuppressWarnings("unchecked")
public abstract class ClassCreator extends Tools {

    private boolean verbosity = true;

    protected final String modelPath;

    protected final List<String> modelFiles;

    protected ClassCreator() throws Exception {
        this.modelPath = properties.getProperty("model.path");
        this.modelFiles = FileUtil.ListFiles(new File(this.modelPath));

        excludedFilesMap = new HashMap();
        excludedFilesMap.put("Adapter1.java", new Object());
        excludedFilesMap.put("Adapter2.java", new Object());
        excludedFilesMap.put("Adapter3.java", new Object());
        excludedFilesMap.put(".DS_Store", new Object());
        excludedFilesMap.put("ObjectFactory.java", new Object());
        excludedFilesMap.put("package-info.java", new Object());
        excludedFilesMap.put("PatientFind.java", new Object());
    }

    private Map excludedFilesMap;

    // Fields that should not be part of the CSharp model
    protected String[] houseKeepingFields = {

            // Used by stored proc's to logically delete a record
            "CurrentRecordIndicator",

            // UI/Service should provide the reason the record has changed...
            //"ReasonForChange",

            // Used by stored proc's to set insert timestamp
            "RecordCreateTimestamp",

            // UI/Service should provide a UID so we know the source of a new record...
            //"RecordCreatedBy",

            // UI/Service should provide the Date/Time this record takes effect
            //"RecordEffectiveTimestamp",

            // Used by stored proc's to logically delete a record (12/31/9999) is used when a record is not deleted. Any other date indicates the record was deleted on that date/time.
            "RecordTerminationTimestamp",

            // Used by stored proc's to set update timestamp
            "RecordUpdateTimestamp",

            // UI/Service should provide a UID so we know the source of the update to the record...
            //"RecordUpdatedBy",

            // Used by stored proc's to set a version for the particular change (why do we need this?)
            "ChangeVersionID"

    };

    protected boolean isHouseKeepingField(final String fieldName) {

        for (String hkf : houseKeepingFields) {

            if (fieldName.toLowerCase().equals(hkf.toLowerCase())) {

                return true;
            }
        }

        return false;
    }

    protected boolean shouldSkipThisFile(final String fileName) {

        return (excludedFilesMap.get(fileName) != null);
    }

    protected abstract void create(final File file);

    public void process() throws Exception {

        systemOut(String.format("%s: **** Start: process() ****", getClass().getSimpleName()));

        for (String filePath : this.modelFiles) {
            create(new File(filePath));
        }

        systemOut(String.format("%s: **** Complete: process() ****", getClass().getSimpleName()));
    }

    public List<String> getModelFiles() {
        return modelFiles;
    }

    public boolean isVerbosity() {
        return verbosity;
    }

    public void setVerbosity(boolean verbosity) {
        this.verbosity = verbosity;
    }

    protected void systemOut(final String log) {
        if (isVerbosity()) {
            System.out.println(log);
        }
    }

    public Map getExcludedFilesMap() {
        return excludedFilesMap;
    }

    protected void cleanFolder() throws Exception {

        System.out.println(String.format("%s: **** Start: cleanFolder() ****", getClass().getSimpleName()));

        File folder = new File(this.modelPath);
        List<String> files = FileUtil.ListFiles(folder);
        for (String filePath : files) {

            File file = new File(filePath);
            if (file.getName().equals("package-info.java")) {
                continue;
            }

            try {
                FileUtil.DeleteFile(filePath);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new SandataRuntimeException(String.format("CSharpClassCreator: cleanFolder: %s: [%s]",
                        e.getClass().getName(), e.getMessage()));
            }
        }

        System.out.println(String.format("%s: **** Complete: cleanFolder() ****", getClass().getSimpleName()));
    }
}
