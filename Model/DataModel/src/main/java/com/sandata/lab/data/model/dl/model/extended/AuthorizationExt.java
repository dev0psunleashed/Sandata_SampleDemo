package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Authorization;
import com.sandata.lab.data.model.dl.model.PatientPayer;
import com.sandata.lab.data.model.dl.model.PlanOfCare;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>AuthorizationExt.java</p>
 * <p><t>Extension of Authorization to include reference to Payer and Plan of Care.</t></p>
 * <p>Date: 6/24/2016</p>
 * <p>Time: 11:37 AM</p>
 *
 * @author jasonscott
 */
public class AuthorizationExt extends Authorization {
    private static final long serialVersionUID = 1L;

    public AuthorizationExt() {

    }

    public AuthorizationExt(Authorization authorization) {
        BeanUtils.copyProperties(authorization, this);
        this.getAuthorization().addAll(authorization.getAuthorization());
        this.getClaimLine().addAll(authorization.getClaimLine());
        this.getVisitAuthorization().addAll(authorization.getVisitAuthorization());
        this.getScheduleEventAuthorization().addAll(authorization.getScheduleEventAuthorization());
        this.getScheduleAuthorization().addAll(authorization.getScheduleAuthorization());
        this.getAuthorizationService().addAll(authorization.getAuthorizationService());
        this.getScheduleEvent().addAll(authorization.getScheduleEvent());
        this.getClaim().addAll(authorization.getClaim());
    }

    @SerializedName("PlanOfCare")
    protected List<PlanOfCare> planOfCareList;

    @SerializedName("PatientPayer")
    protected PatientPayer patientPayer;

    @SerializedName("PayerName")
    protected String payerName;

    @SerializedName("AuthorizationUnitsUsed")
    protected BigDecimal authorizationUnitsUsed;

    @SerializedName("AuthorizationUnitsRemaining")
    protected BigDecimal authorizationUnitsRemaining;

    public List<PlanOfCare> getPlanOfCareList() {
        if (planOfCareList == null) {
            planOfCareList = new ArrayList<>();
        }
        return this.planOfCareList;
    }

    public PatientPayer getPatientPayer() {
        return patientPayer;
    }

    public void setPatientPayer(PatientPayer patientPayer) {
        this.patientPayer = patientPayer;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public BigDecimal getAuthorizationUnitsUsed() {
        return authorizationUnitsUsed;
    }

    public void setAuthorizationUnitsUsed(BigDecimal authorizationUnitsUsed) {
        this.authorizationUnitsUsed = authorizationUnitsUsed;
    }

    public BigDecimal getAuthorizationUnitsRemaining() {
        return authorizationUnitsRemaining;
    }

    public void setAuthorizationUnitsRemaining(BigDecimal authorizationUnitsRemaining) {
        this.authorizationUnitsRemaining = authorizationUnitsRemaining;
    }
}
