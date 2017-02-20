package com.sandata.lab.tools.oracle.mapper;

import com.sandata.lab.tools.oracle.model.TypMappingMetadata;
import com.sandata.lab.tools.oracle.util.FileUtil;

import java.io.File;
import java.util.Map;

/**
 * Short description of the class.
 * <p/>
 * (Optional) Additional detail about the class that we may want to document
 *
 * @author David Rutgos
 */
public class MetadataMapperExt extends MetadataMapper {

    public void process() throws Exception {
        for (String targetFilePath : getTargetFiles()) {

            String targetFileName = FileUtil.RemoveFileExtension(new File(targetFilePath), ".java").trim();

            // TODO: Hacky: Fix me...
            String key = targetFileName;

            if (key.startsWith("Application")
                    || key.startsWith("BaseApplication")
                    || key.startsWith("DataSecurity")
                    || key.startsWith("AuditAction")
                    || key.startsWith("Report")) {

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

        } // for
    }

    public static MetadataMapperExt getInstance(Map<String, TypMappingMetadata> metadataMap) throws Exception {
        return new MetadataMapperExt(metadataMap);
    }

    private MetadataMapperExt(Map<String, TypMappingMetadata> metadataMap) throws Exception {
        super(metadataMap);
    }
}
