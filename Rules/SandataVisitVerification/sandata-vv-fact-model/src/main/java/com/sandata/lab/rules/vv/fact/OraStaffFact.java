package com.sandata.lab.rules.vv.fact;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/4/2015
 * Time: 12:51 PM
 */

public class OraStaffFact extends StaffFact implements Serializable {

    private static  final long serialVersionUID = 1L;
    /***
     * Use this to populate StaffFact for rules engine from the database request only!
     * @param staffId
     * @param dnis
     * Internally the state will be set to State.AGG_INSERTED_FROM_RESPONSE
     */
    public OraStaffFact(String staffId, String dnis) {
        super(staffId, dnis);

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
        return staff.getDnis().equals(this.getDnis()) && staff.getStaffId().equals(this.getStaffId()) && staff.getVisitState() == this.getVisitState();
    }
    @Override
    public int hashCode() {
        return super.hashCode() + 1;
    }
}
