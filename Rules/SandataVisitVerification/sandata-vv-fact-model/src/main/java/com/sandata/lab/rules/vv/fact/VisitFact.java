package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.rules.vv.model.CallPreferences;
import com.sandata.lab.rules.vv.model.VisitState;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by thanhxle
 */
public class VisitFact extends BaseObject {

    private static final long serialVersionUID = 1L;
    private String businessEntityId;
    private String ani;
    private String dnis;
    private String staffId;
    private String patientId;
    private BigInteger visitSk;
    private BigInteger visitEvntSk;
    private BigInteger schedEvntSk;
    private Date visitEvntDtime;
    private Date visitActualStartTimestamp;
    private Date visitActualEndTimestamp;
    private Date visitAdjustedStartTimestamp;
    private Date visitAdjustedEndTimestamp;
    private Boolean visitApprovedIndicator;
    private boolean visitCancelledInd;
    
    private List<BusinessEntityExceptionListExt> applicableBeExceptionList;
    private List<ContractExceptionListExt> applicableContractExceptionList;
    private List<VisitException> visitExceptions;
    
	public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getVisitAdjustedStartTimestamp() {
        return visitAdjustedStartTimestamp;
    }

    public void setVisitAdjustedStartTimestamp(Date visitAdjustedStartTimestamp) {
        this.visitAdjustedStartTimestamp = visitAdjustedStartTimestamp;
    }


    public BigInteger getVisitSk() {
        return visitSk;
    }

    public void setVisitSk(BigInteger visitSk) {
        this.visitSk = visitSk;
    }

    
    public BigInteger getSchedEvntSk() {
		return schedEvntSk;
	}

	public void setSchedEvntSk(BigInteger schedEvntSk) {
		this.schedEvntSk = schedEvntSk;
	}
	
	

	public BigInteger getVisitEvntSk() {
		return visitEvntSk;
	}

	public void setVisitEvntSk(BigInteger visitEvntSk) {
		this.visitEvntSk = visitEvntSk;
	}

	public Date getVisitActualStartTimestamp() {
		return visitActualStartTimestamp;
	}

	public void setVisitActualStartTimestamp(Date visitActualStartTimestamp) {
		this.visitActualStartTimestamp = visitActualStartTimestamp;
	}

	public Date getVisitActualEndTimestamp() {
		return visitActualEndTimestamp;
	}

	public void setVisitActualEndTimestamp(Date visitActualEndTimestamp) {
		this.visitActualEndTimestamp = visitActualEndTimestamp;
	}

	public Date getVisitAdjustedEndTimestamp() {
		return visitAdjustedEndTimestamp;
	}

	public void setVisitAdjustedEndTimestamp(Date visitAdjustedEndTimestamp) {
		this.visitAdjustedEndTimestamp = visitAdjustedEndTimestamp;
	}

	public Boolean getVisitApprovedIndicator() {
		return visitApprovedIndicator;
	}

	public void setVisitApprovedIndicator(Boolean visitApprovedIndicator) {
		this.visitApprovedIndicator = visitApprovedIndicator;
	}

	public boolean isVisitCancelledInd() {
		return visitCancelledInd;
	}

	public void setVisitCancelledInd(boolean visitCancelledInd) {
		this.visitCancelledInd = visitCancelledInd;
	}

	
	
	public Date getVisitEvntDtime() {
		return visitEvntDtime;
	}

	public void setVisitEvntDtime(Date visitEvntDtime) {
		this.visitEvntDtime = visitEvntDtime;
	}

	public List<BusinessEntityExceptionListExt> getApplicableBeExceptionList() {
        return applicableBeExceptionList;
    }

    public void setApplicableBeExceptionList(List<BusinessEntityExceptionListExt> applicableBeExceptionList) {
        this.applicableBeExceptionList = applicableBeExceptionList;
    }

    public List<ContractExceptionListExt> getApplicableContractExceptionList() {
        return applicableContractExceptionList;
    }

    public void setApplicableContractExceptionList(List<ContractExceptionListExt> applicableContractExceptionList) {
        this.applicableContractExceptionList = applicableContractExceptionList;
    }

    public List<VisitException> getVisitExceptions() {
        return visitExceptions;
    }

    public void setVisitExceptions(List<VisitException> visitExceptions) {
        this.visitExceptions = visitExceptions;
    }

    /**
     * Use this method to add new VisitException by visitSk and exceptionId. The rest attributes of VisitException must be populated
     * from JBoss Fuse before saving into DB
     *
     * @param visitSk
     * @param exceptionId
     */
    public void addVisitException(BigInteger visitSk, BigInteger exceptionId) {
        if (null == visitExceptions) {
            visitExceptions = new ArrayList<VisitException>();
        }

        VisitException visitException = new VisitException();
        visitException.setVisitSK(visitSk);
        visitException.setExceptionID(exceptionId);

        visitExceptions.add(visitException);
    }
    
    public void setData(Visit visit) {
    	this.visitSk = visit.getVisitSK();
    	this.businessEntityId = visit.getBusinessEntityID();
    	this.schedEvntSk = visit.getScheduleEventSK();
    	this.patientId = visit.getPatientID();
    	this.staffId = visit.getStaffID();
    	this.visitActualStartTimestamp = visit.getVisitActualStartTimestamp();
    	this.visitActualEndTimestamp = visit.getVisitActualEndTimestamp();
    	
    	this.visitAdjustedStartTimestamp = visit.getVisitAdjustedStartTimestamp();
    	this.visitAdjustedEndTimestamp = visit.getVisitAdjustedEndTimestamp();
    	if (visit.getVisitApprovedIndicator() != null) {
    		this.visitApprovedIndicator = visit.getVisitApprovedIndicator();
    	}
    	if (visit.getVisitCancelledIndicator() != null) {
    		this.visitCancelledInd = visit.getVisitCancelledIndicator();
    	}
    	    
    }
}
