package com.sandata.lab.tools.oracle.mapper;

import java.io.File;

/**
 * Date: 8/31/15
 * Time: 10:15 PM
 */

public class PatientModelMapper extends ModelMapper {

    public static PatientModelMapper getInstance() throws Exception {
        return new PatientModelMapper();
    }

    private PatientModelMapper() throws Exception {
        super();
        setVerbosity(false);    // turn off some logging
    }

    @Override
    protected String modelFileName() {
        return "PatientTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "Patient.java";
    }

    @Override
    public void process() throws Exception {

        System.out.println(String.format("%s: **** Start: process() ****", getClass().getSimpleName()));

        String patientFileStr = null;

        int typeFileIndex = 0;
        for (String targetFileStr : getTargetFiles()) {

            File targetFile = new File(targetFileStr);

            if (targetFile.getName().equals(normalizedFileName())) {
                patientFileStr = targetFileStr;
                systemOut(">>> Skip Patient.java <<<");
                continue;
            }

            if (!targetFile.getName().startsWith("Patient")
                    || targetFile.getName().equals("PatientFind.java")) {

                systemOut(String.format(">>> Skip %s <<<", targetFile.getName()));
                continue;
            }

            if (shouldSkipThisFile(targetFileStr)) {
                continue;
            }

            String modelFileStr = this.modelFiles.get(typeFileIndex++);
            File modelFile = new File(modelFileStr);

            // LOOP: through skipped files
            while (shouldSkipThisFile(modelFile.getName())) {
                modelFileStr = this.modelFiles.get(typeFileIndex++);
                modelFile = new File(modelFileStr);
            }

            // LOOP: through files that are not Patient!
            while (!modelFile.getName().startsWith("Patient")) {
                modelFileStr = this.modelFiles.get(typeFileIndex++);
                modelFile = new File(modelFileStr);
            }

            // check range
            if (typeFileIndex < this.modelFiles.size()) {

                System.out.println("[Target: " + targetFile.getName() + "] = [Model: " + modelFile.getName() + "]");

                create(targetFile, modelFile);
            }

        } //for

        // Expecting this to be PatientTyp
        String patientFileTypStr = this.modelFiles.get(typeFileIndex);
        File patientFileTyp = new File(patientFileTypStr);
        if (!patientFileTyp.getName().equals(modelFileName())) {
            throw new Exception(String.format("FATAL: Expecting %s!: [%s]", modelFileName(), patientFileTyp.getName()));
        }

        if (patientFileStr == null) {
            throw new Exception("FATAL: Expecting Patient.java!: NULL");
        }

        File patientFile = new File(patientFileStr);

        System.out.println("[Target: " + patientFile.getName() + "] = [Model: " + patientFileTyp.getName() + "]");

        // Process Patient last...
        create(patientFile, patientFileTyp);

        System.out.println(String.format("%s: **** Complete: process() ****", getClass().getSimpleName()));
    }
}
