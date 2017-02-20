package com.sandata.lab.rest.visit.model;

import java.math.BigInteger;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;

/**
 *
 * @author richardwu
 */
public class PatientForSearch extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PTSK")
    protected BigInteger ptSk;
    
    @SerializedName("PTID")
    protected String ptId;
    
    @SerializedName("PTFirstName")
    protected String ptFirstName;

    @SerializedName("PTLastName")
    protected String ptLastName;
    
    @SerializedName("PTMedicareID")
    protected String ptMedicareId;
    
    @SerializedName("PTMedicaidID")
    protected String ptMedicaidId;    
    
    @SerializedName("BEID")
    protected String beId;
    
    @SerializedName("ServiceNameList")
    protected List<String> serviceNameList;

	public BigInteger getPtSk() {
		return ptSk;
	}

	public void setPtSk(BigInteger ptSk) {
		this.ptSk = ptSk;
	}


	public String getPtId() {
		return ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public String getPtFirstName() {
		return ptFirstName;
	}

	public void setPtFirstName(String ptFirstName) {
		this.ptFirstName = ptFirstName;
	}

	public String getPtLastName() {
		return ptLastName;
	}

	public void setPtLastName(String ptLastName) {
		this.ptLastName = ptLastName;
	}

	public String getPtMedicareId() {
		return ptMedicareId;
	}

	public void setPtMedicareId(String ptMedicareId) {
		this.ptMedicareId = ptMedicareId;
	}

	public String getPtMedicaidId() {
		return ptMedicaidId;
	}

	public void setPtMedicaidId(String ptMedicaidId) {
		this.ptMedicaidId = ptMedicaidId;
	}

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public List<String> getServiceNameList() {
		return serviceNameList;
	}

	public void setServiceNameList(List<String> serviceNameList) {
		this.serviceNameList = serviceNameList;
	}

    
}
