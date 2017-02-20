package com.sandata.lab.common.utils.data.model;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * Models the abbreviation entity to store the logical and physical mappings.
 * <p/>
 *
 * @author David Rutgos
 */
public class Abbreviation extends BaseObject {

    private static final long serialVersionUID = 1L;

    public static Abbreviation createFromDelimString(String data, String delim) {

        if (data == null
                || delim == null) {
            return null;
        }

        String[] dataArray = data.split(delim);

        if (dataArray.length != 7) {
            return null;
        }

        Abbreviation abbreviation = new Abbreviation();

        abbreviation.setLogicalName(dataArray[0]);
        abbreviation.setPhysicalName(dataArray[1]);
        abbreviation.setPrimeInd(dataArray[2]);
        abbreviation.setQualifierInd(dataArray[3]);
        abbreviation.setClassInd(dataArray[4]);
        abbreviation.setIllegalInd(dataArray[5]);
        abbreviation.setPriority(dataArray[6]);

        return abbreviation;
    }

    private String logicalName;
    private String physicalName;
    private String primeInd;
    private String qualifierInd;
    private String classInd;
    private String illegalInd;
    private String priority;

    public String getLogicalName() {
        return logicalName;
    }

    public void setLogicalName(String logicalName) {
        // Replace Spaces With Underscore so that the CaseFormat.UPPER_UNDERSCORE converts it to proper camel case
        // JavaUtil.UnderscoresToCamelUppercase()
        this.logicalName = logicalName.replace(" ", "_");
    }

    public String getPhysicalName() {
        return physicalName;
    }

    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    public String getPrimeInd() {
        return primeInd;
    }

    public void setPrimeInd(String primeInd) {
        this.primeInd = primeInd;
    }

    public String getQualifierInd() {
        return qualifierInd;
    }

    public void setQualifierInd(String qualifierInd) {
        this.qualifierInd = qualifierInd;
    }

    public String getClassInd() {
        return classInd;
    }

    public void setClassInd(String classInd) {
        this.classInd = classInd;
    }

    public String getIllegalInd() {
        return illegalInd;
    }

    public void setIllegalInd(String illegalInd) {
        this.illegalInd = illegalInd;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
