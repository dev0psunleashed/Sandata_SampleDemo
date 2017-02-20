package com.sandata.lab.tools.oracle.model;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.util.JavaUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/3/15
 * Time: 7:16 PM
 */

public abstract class RestEndpoint {

    public enum Type {

        INSERT ("@POST"),
        UPDATE ("@PUT"),
        DELETE ("@DELETE"),
        GET_SK ("@GET_SK"),
        GET_PK ("@GET_PK"),
        GET_LOOKUP ("@GET_LOOKUP");

        private final String type;

        Type(final String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    private String pathName;
    private String modelClass;
    private Type type;
    private String methodName;
    private String oraclePackage;
    private List<String> matrixParams;

    public RestEndpoint(final Type type) {

        this.type = type;
    }

    public void write(BufferedWriter writer) throws SandataRuntimeException {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(templateFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("__PATH__")) {
                    writer.write(line.replace("__PATH__", getPathName()) + "\n");
                }
                else if (line.contains("__METHOD__")
                        || line.contains("__MODEL_CLASS__")) {
                    String newLine = line.replace("__METHOD__", getMethodName()) + "\n";
                    newLine = newLine.replace("__MODEL_CLASS__", getModelClass()) + "\n";

                    if (newLine.contains("__MATRIX_PARAMS__")) {
                        if (matrixParams == null || matrixParams.size() == 0) {
                            throw new SandataRuntimeException("RestEndpoint: write: matrixParams is NULL or EMPTY!");
                        }

                        StringBuilder paramsBuilder = new StringBuilder();
                        for (int index = 0; index < matrixParams.size() - 1; index++) {
                            String param = matrixParams.get(index);
                            String paramCamelCase = JavaUtil.CamelCaseSmallFirstLetter(param);
                            paramsBuilder.append(String.format("@MatrixParam(\"%s\") String %s, ", param, paramCamelCase));
                        }

                        String param = matrixParams.get(matrixParams.size() - 1);
                        String paramCamelCase = JavaUtil.CamelCaseSmallFirstLetter(param);

                        paramsBuilder.append(String.format("@MatrixParam(\"%s\") String %s", param, paramCamelCase));

                        newLine = newLine.replace("__MATRIX_PARAMS__", paramsBuilder.toString());
                    }

                    writer.write(newLine);
                }
                else {
                    writer.write(line + "\n");
                }
            }

            writer.write("\n");

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("RestEndpoint: write: %s: [%s]",
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
        }
    }

    public void addMatrixParam(String param) {
        if (this.matrixParams == null) {
            this.matrixParams = new ArrayList<>();
        }

        this.matrixParams.add(param);
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {

        if (oraclePackage == null) {
            throw new SandataRuntimeException(String.format("%s: oraclePackage == null", getClass().getName()));
        }

        if (modelClass == null) {
            throw new SandataRuntimeException(String.format("%s: modelClass == null", getClass().getName()));
        }

        this.methodName = String.format("%s_%s_%s", getOraclePackage(), methodName, getModelClass());
    }

    public List<String> getMatrixParams() {
        return matrixParams;
    }

    public void setMatrixParams(List<String> matrixParams) {
        this.matrixParams = matrixParams;
    }

    public String getModelClass() {
        return modelClass;
    }

    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    public String getOraclePackage() {
        return oraclePackage;
    }

    public void setOraclePackage(String oraclePackage) {
        this.oraclePackage = oraclePackage;
    }

    protected abstract String templateFile();
}
