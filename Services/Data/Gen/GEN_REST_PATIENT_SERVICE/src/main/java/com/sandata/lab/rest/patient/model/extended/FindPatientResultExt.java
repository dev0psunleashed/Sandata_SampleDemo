package com.sandata.lab.rest.patient.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.FindPatientResult;
import com.sandata.lab.data.model.dl.model.PatientStatusName;
import com.sandata.lab.data.model.dl.model.extended.admin.AdminPatientParentRelTypeResponse;

public class FindPatientResultExt extends FindPatientResult {

    private static final long serialVersionUID = 1L;

    @SerializedName("Service")
    private String service;
    
    @SerializedName("PatientStatusName")
    private PatientStatusName status;
    
    @SerializedName("PatientPriorityLevel")
    private String priorityLevel;

    @SerializedName("Coordinator")
    private AdminPatientParentRelTypeResponse coordinator;
    
    @SerializedName("Nurse")
    private AdminPatientParentRelTypeResponse nurse;
    
    @SerializedName("MedicaidID")
    private String medicaidId;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

	public PatientStatusName getStatus() {
		return status;
	}

	public void setStatus(PatientStatusName status) {
		this.status = status;
	}

	public String getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

    public AdminPatientParentRelTypeResponse getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(AdminPatientParentRelTypeResponse coordinator) {
        this.coordinator = coordinator;
    }

    public AdminPatientParentRelTypeResponse getNurse() {
        return nurse;
    }

    public void setNurse(AdminPatientParentRelTypeResponse nurse) {
        this.nurse = nurse;
    }
    
    public String getMedicaidId() {
        return medicaidId;
    }

    public void setMedicaidId(String medicaidId) {
        this.medicaidId = medicaidId;
    }
    
}
