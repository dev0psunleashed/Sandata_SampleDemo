package com.sandata.lab.tools.oracle.mapper;

import com.sandata.lab.tools.oracle.model.GetterSetter;
import com.sandata.lab.tools.oracle.model.TypMappingMetadata;
import com.sandata.lab.tools.oracle.util.*;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Date: 9/6/15
 * Time: 8:08 PM
 */

public class MetadataMapper extends ModelMapper {

    protected Map<String, TypMappingMetadata> metadataMap;

    public void process() throws Exception {

        for (String targetFilePath : getTargetFiles()) {

            String targetFileName = FileUtil.RemoveFileExtension(new File(targetFilePath), ".java").trim();

            // TODO: Hacky: Fix me...
            String key = targetFileName;

            if (key.equals("ApplicationModuleLookup")
                    ||
                    (!key.startsWith("Application")
                    && !key.startsWith("BaseApplication")
                    && !key.startsWith("DataSecurity")
                    && !key.startsWith("AuditAction")
                    && !key.startsWith("Report"))) {

                if (targetFileName.equals("PatientDMEAndSupply")) {
                    key = "PatientDmeAndSupply";
                }

                TypMappingMetadata metadata = metadataMap.get(key);

                if (metadata != null) {

                    System.out.println(String.format("Process: --> %s [%s]", metadata.getModelClass(), metadata.getTypeClassName()));

                    for (String typFilePath : getModelFiles()) {

                        String typFileName = FileUtil.RemoveFileExtension(new File(typFilePath), ".java").trim();

                        //PatientDMEAndSupplTyp = PatientDmeSupplTyp
                        // TODO: Hacky: Fix me...
                        if (
                                (typFileName.equals("PatientDmeAndSupplyTyp") && metadata.getTypeClassName().equals("PatientDmeAndSupplyTyp"))
                                        || (typFileName.equals("PatientStrucBrsDetlTyp") && metadata.getTypeClassName().equals("PatientStructBrrsDetlTyp"))
                                        || (typFileName.equals("PlanOfCareTyp") && metadata.getTypeClassName().equals("PocTyp"))
                                        || (typFileName.equals("PlanOfCareActivityTyp") && metadata.getTypeClassName().equals("PocActivityTyp"))
                                        || (typFileName.equals("PlanOfCareServiceTyp") && metadata.getTypeClassName().equals("PocServiceTyp"))
                                        || (typFileName.equals("PlanOfCareTaskLstTyp") && metadata.getTypeClassName().equals("PocTaskLstTyp"))
                                ) {
                            create(new File(targetFilePath), new File(typFilePath), metadata);
                        } else if (typFileName.equals(metadata.getTypeClassName())) {

                            create(new File(targetFilePath), new File(typFilePath), metadata);
                            //System.out.println(String.format("\t\t **** TypFileName: [%s] ****", typFileName));
                        }
                    }
                }
            }

        } //for
    }

    protected void create(File targetFile, File sourceFile, TypMappingMetadata metadata) {

        List<GetterSetter> getterSetters = processProperties(sourceFile);

        String oracleMetadata = "@OracleMetadata(\n" +
                "        packageName = \"%s\",\n" +
                "        insertMethod = \"%s\",\n" +
                "        updateMethod = \"%s\",\n" +
                "        deleteMethod = \"%s\",\n" +
                "        getMethod = \"%s\",\n" +
                "        findMethod = \"%s\",\n" +
                "        jpubClass = \"%s\",\n" +
                "        primaryKeys = %s)\n";

        String insertMethod = (metadata.getInsertMethod() != null) ?
                metadata.getInsertMethod().getFunction().getMethodName() : "NotDefined_InsertMethod";

        String updateMethod = (metadata.getUpdateMethod() != null) ?
                metadata.getUpdateMethod().getFunction().getMethodName() : "NotDefined_UpdateMethod";

        String deleteMethod = (metadata.getDeleteMethod() != null) ?
                metadata.getDeleteMethod().getFunction().getMethodName() : "NotDefined_DeleteMethod";

        String getMethod = (metadata.getGetMethod() != null) ?
                metadata.getGetMethod().getFunction().getMethodName() : "NotDefined_GetMethod";

        String findMethod = (metadata.getFindMethod() != null) ?
                metadata.getFindMethod().getFunction().getMethodName() : "NotDefined_FindMethod";

        oracleMetadata = String.format(oracleMetadata,
                metadata.getPackageName(),
                insertMethod,
                updateMethod,
                deleteMethod,
                getMethod,
                findMethod,
                String.format("%s.%s", "com.sandata.lab.data.model.jpub.model", metadata.getTypeClassName()),
                metadata.primaryKeysStringArray());

        addCustomAnnotation(targetFile, getterSetters, oracleMetadata);
    }

    public static MetadataMapper getInstance(Map<String, TypMappingMetadata> metadataMap) throws Exception {
        return new MetadataMapper(metadataMap);
    }

    protected MetadataMapper(Map<String, TypMappingMetadata> metadataMap) throws Exception {
        this.metadataMap = metadataMap;
    }

    @Override
    protected String modelFileName() {
        return null;
    }

    @Override
    protected String normalizedFileName() {
        return null;
    }
}
