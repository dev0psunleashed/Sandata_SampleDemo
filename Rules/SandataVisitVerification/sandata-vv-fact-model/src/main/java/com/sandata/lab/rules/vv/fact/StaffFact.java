package com.sandata.lab.rules.vv.fact;

import java.io.Serializable;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.rules.vv.model.VisitState;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/4/2015
 * Time: 11:42 AM
 */
public class StaffFact extends BaseObject {
    private static  final long serialVersionUID = 1L;

    private String staffId;
    private String staffVisitVerificationId;
    private String staffIdFingered;
    private String dnis;
    private VisitState visitState;
    private int rule;
    private boolean useVVId = false;



    public StaffFact(){}

    /***
     * Use this to populate StaffFact for rules engine from the database request only!
     * @param staffId
     * @param dnis
     * Internally the state will be set to State.AGG_INSERTED_FROM_RESPONSE
     */
    public StaffFact(String staffId, String dnis) {
        this.visitState = VisitState.AGG_INSERTED_FROM_RESPONSE;
        this.staffId = staffId;
        this.dnis = dnis;
    }

    /***
     *
     * @param staffIdfingered this is what Staff manually entered on phone.
     * @param dnis toll free number of Agency
     * @param rulesState current state in rules engine
     */
    public StaffFact(String staffIdfingered, String dnis, VisitState rulesState) {
        this.visitState = rulesState;
        this.staffId = staffIdfingered;
        this.staffIdFingered = staffIdfingered;
        this.dnis = dnis;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StaffFact)) {
            return false;
        }
        StaffFact that = (StaffFact) obj;

        if (visitState != that.visitState) return false;
        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (staffVisitVerificationId != null ? !staffVisitVerificationId.equals(that.staffVisitVerificationId) : that.staffVisitVerificationId != null) return false;
        if (dnis != null ? !dnis.equals(that.dnis) : that.dnis != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (visitState != null ? visitState.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (staffVisitVerificationId != null ? staffVisitVerificationId.hashCode() : 0);
        result = 31 * result + (dnis != null ? dnis.hashCode() : 0);

        return result;
    }

    public String getStaffId() {
        if(this.staffId == null) {
            this.staffId = "";
        }
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDnis() {
        if(this.dnis == null) {
            this.dnis = "";
        }
        return this.dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public VisitState getVisitState() {
        return visitState;
    }

    public void setVisitState(VisitState state) {
        this.visitState = state;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public String getStaffIdFingered() {
        if(this.staffIdFingered == null) {
            this.staffIdFingered = "";
        }
        return this.staffIdFingered;
    }

    public void setStaffIdFingered(String staffIdFingered) {
        this.staffIdFingered = staffIdFingered;
    }

    public String getStaffVisitVerificationId() {
        return staffVisitVerificationId;
    }

    public void setStaffVisitVerificationId(String staffVVId) {
        this.staffVisitVerificationId = staffVVId;
    }

    public boolean isUseVVId() {
        return useVVId;
    }

    public void setUseVVId(boolean useVVId) {
        this.useVVId = useVVId;
    }
}
