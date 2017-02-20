package com.sandata.lab.rules.call.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/4/2015
 * Time: 11:42 AM
 */
public class StaffFact implements Serializable {
    private static  final long serialVersionUID = 1L;

    private String staffId;
    private String vv_id;
    private String staffIdFingered;
    private String dnis;
    private State state;
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
        this.state = State.AGG_INSERTED_FROM_RESPONSE;
        this.staffId = staffId;
        this.dnis = dnis;
    }

    /***
     *
     * @param staffIdfingered this is what Staff manually entered on phone.
     * @param dnis toll free number of Agency
     * @param rulesState current state in rules engine
     */
    public StaffFact(String staffIdfingered, String dnis, State rulesState) {
        this.state = rulesState;
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
        StaffFact staff = (StaffFact) obj;
        return staff.getDnis().equals(this.getDnis()) && staff.getStaffId().equals(this.getStaffId());
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = (int) (31 * result + this.getStaffId().hashCode());
        result = 31 * result + this.getDnis().hashCode();
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public String getVv_id() {
        return vv_id;
    }

    public void setVv_id(String vv_id) {
        this.vv_id = vv_id;
    }

    public boolean isUseVVId() {
        return useVVId;
    }

    public void setUseVVId(boolean useVVId) {
        this.useVVId = useVVId;
    }
}
