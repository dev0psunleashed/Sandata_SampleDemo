package com.sandata.lab.rest.patient.model.extended;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.PatientDiagnosis;

/**
 * Extends PatientDiagnosis to provide ICDDiagnosisCodeShortDescription and ICDDiagnosisCodeLongDescription
 * 
 * @author khangle
 *
 */
public class PatientDiagnosisExt extends PatientDiagnosis {
    private static final long serialVersionUID = 1L;
    
    @SerializedName("ICDDiagnosisCodeShortDescription")
    private String icdDiagnosisCodeShortDescription;
    
    @SerializedName("ICDDiagnosisCodeLongDescription")
    private String icdDiagnosisCodeLongDescription;
    
    /**
     * Default construcgtor
     */
    public PatientDiagnosisExt() {
        
    }
    
    /**
     * Constructor
     * 
     * @param ptDx
     */
    public PatientDiagnosisExt(PatientDiagnosis ptDx) {
        BeanUtils.copyProperties(ptDx, this);
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
    
}
