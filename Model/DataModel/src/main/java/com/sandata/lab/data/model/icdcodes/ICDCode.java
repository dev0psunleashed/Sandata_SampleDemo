package com.sandata.lab.data.model.icdcodes;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Date: 12/16/15
 * Time: 5:56 PM
 */

public class ICDCode extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ICDDiagnosisCodeSK")
    private BigInteger icdDiagnosisCodeSk;

    @SerializedName("ICDDiagnosisCode")
    private String icdDiagnosisCode;

    @SerializedName("ICDDiagnosisCodeRevision")
    private String icdDiagnosisCodeRevision;

    @SerializedName("RecordCreateTimestamp")
    private Date recordCreateTimestamp;

    @SerializedName("RecordUpdateTimestamp")
    private Date recordUpdateTimestamp;

    @SerializedName("ChangeVersionID")
    protected BigInteger changeVersionID;

    @SerializedName("ICDDiagnosisCodeShortDescription")
    private String icdDiagnosisCodeShortDescription;

    @SerializedName("ICDDiagnosisCodeLongDescription")
    private String icdDiagnosisCodeLongDescription;

    @SerializedName("ICDDiagnosisCodeEffectiveDate")
    private Date icdDiagnosisCodeEffectiveDate;

    @SerializedName("ICDDiagnosisCodeTerminationDate")
    private Date icdDiagnosisCodeTerminationDate;

    public BigInteger getIcdDiagnosisCodeSk() {
        return icdDiagnosisCodeSk;
    }

    public void setIcdDiagnosisCodeSk(BigInteger icdDiagnosisCodeSk) {
        this.icdDiagnosisCodeSk = icdDiagnosisCodeSk;
    }

    public String getIcdDiagnosisCode() {
        return icdDiagnosisCode;
    }

    public void setIcdDiagnosisCode(String icdDiagnosisCode) {
        this.icdDiagnosisCode = icdDiagnosisCode;
    }

    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    public void setRecordCreateTimestamp(Date recordCreateTimestamp) {
        this.recordCreateTimestamp = recordCreateTimestamp;
    }

    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    public void setRecordUpdateTimestamp(Date recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }

    public BigInteger getChangeVersionID() {
        return changeVersionID;
    }

    public void setChangeVersionID(BigInteger changeVersionID) {
        this.changeVersionID = changeVersionID;
    }

    public String getIcdDiagnosisCodeShortDescription() {
        return icdDiagnosisCodeShortDescription;
    }

    public void setIcdDiagnosisCodeShortDescription(String icdDiagnosisCodeShortDescription) {
        this.icdDiagnosisCodeShortDescription = icdDiagnosisCodeShortDescription;
    }

    public String getIcdDiagnosisCodeLongDescription() {
        return icdDiagnosisCodeLongDescription;
    }

    public void setIcdDiagnosisCodeLongDescription(String icdDiagnosisCodeLongDescription) {
        this.icdDiagnosisCodeLongDescription = icdDiagnosisCodeLongDescription;
    }

    public Date getIcdDiagnosisCodeEffectiveDate() {
        return icdDiagnosisCodeEffectiveDate;
    }

    public void setIcdDiagnosisCodeEffectiveDate(Date icdDiagnosisCodeEffectiveDate) {
        this.icdDiagnosisCodeEffectiveDate = icdDiagnosisCodeEffectiveDate;
    }

    public Date getIcdDiagnosisCodeTerminationDate() {
        return icdDiagnosisCodeTerminationDate;
    }

    public void setIcdDiagnosisCodeTerminationDate(Date icdDiagnosisCodeTerminationDate) {
        this.icdDiagnosisCodeTerminationDate = icdDiagnosisCodeTerminationDate;
    }

    public String getIcdDiagnosisCodeRevision() {
        return icdDiagnosisCodeRevision;
    }

    public void setIcdDiagnosisCodeRevision(String icdDiagnosisCodeRevision) {
        this.icdDiagnosisCodeRevision = icdDiagnosisCodeRevision;
    }
}
