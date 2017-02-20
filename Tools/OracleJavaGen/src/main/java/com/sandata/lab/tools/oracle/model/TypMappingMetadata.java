package com.sandata.lab.tools.oracle.model;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.util.JavaUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmrutgos on 8/31/2015.
 */
public class TypMappingMetadata {

    private Table table;
    private String packageName;
    private String typePackageName;
    private List<StoredProcedure> storedProcedures;
    private List<String> primaryKeys;
    private String modelClass;

    public StoredProcedure getInsertMethod() {

        if (storedProcedures == null) {
            return null;
        }

        for (StoredProcedure storedProcedure : storedProcedures) {

            if (storedProcedure.getExecuteType().equals(StoredProcedure.INSERT_TYPE)) {
                return storedProcedure;
            }
        }

        return null;
    }

    public StoredProcedure getUpdateMethod() {

        if (storedProcedures == null) {
            return null;
        }

        for (StoredProcedure storedProcedure : storedProcedures) {

            if (storedProcedure.getExecuteType().equals(StoredProcedure.UPDATE_TYPE)) {
                return storedProcedure;
            }
        }

        return null;
    }

    public StoredProcedure getDeleteMethod() {

        if (storedProcedures == null) {
            return null;
        }

        for (StoredProcedure storedProcedure : storedProcedures) {

            if (storedProcedure.getExecuteType().equals(StoredProcedure.DELETE_TYPE)) {
                return storedProcedure;
            }
        }

        return null;
    }

    public StoredProcedure getGetMethod() {

        if (storedProcedures == null) {
            return null;
        }

        for (StoredProcedure storedProcedure : storedProcedures) {

            if (storedProcedure.getExecuteType().equals(StoredProcedure.GET_TYPE)) {
                return storedProcedure;
            }
        }

        return null;
    }

    public StoredProcedure getFindMethod() {

        if (storedProcedures == null) {
            return null;
        }

        for (StoredProcedure storedProcedure : storedProcedures) {

            if (storedProcedure.getExecuteType().equals(StoredProcedure.FIND_TYPE)) {
                return storedProcedure;
            }
        }

        return null;
    }

    public void addStoredProcedure(final StoredProcedure storedProcedure) {

        if (this.storedProcedures == null) {
            this.storedProcedures = new ArrayList<>();
        }

        this.storedProcedures.add(storedProcedure);
    }

    public String primaryKeysStringArray() {

        if (primaryKeys == null || primaryKeys.size() == 0) {
            return "{}";
        }

        StringBuilder builder = new StringBuilder("{ ");

        for(int index = 0; index < primaryKeys.size() - 1; index++) {

            builder.append(String.format("\"%s\", ", primaryKeys.get(index)));
        }

        builder.append(String.format("\"%s\"", primaryKeys.get(primaryKeys.size() - 1)));
        builder.append(" }");

        return builder.toString();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTypName() {
        return String.format("%s_T", table.getShortName());
    }

    public String getTypeClassName() {
        return JavaUtil.CamelCase(getTypName());
    }

    public String getTypePackageName() {
        return typePackageName;
    }

    public void setTypePackageName(String typePackageName) {
        this.typePackageName = typePackageName;
    }

    public List<StoredProcedure> getStoredProcedures() {
        return storedProcedures;
    }

    public void setStoredProcedures(List<StoredProcedure> storedProcedures) {
        this.storedProcedures = storedProcedures;
    }

    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    public String getModelClass() {
        return modelClass;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }
}
