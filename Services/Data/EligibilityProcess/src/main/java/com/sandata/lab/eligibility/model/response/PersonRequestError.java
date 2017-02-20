package com.sandata.lab.eligibility.model.response;

import com.sandata.lab.eligibility.model.response.enums.FollowUpActionCodeType;
import com.sandata.lab.eligibility.model.response.enums.RejectReasonCodeType;

public class PersonRequestError {
    private long id;

    private long personId;
    private RejectReasonCodeType rejectReasonCode;
    private FollowUpActionCodeType followUpActionCode;

    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public RejectReasonCodeType getRejectReasonCode() {
        return rejectReasonCode;
    }

    public void setRejectReasonCode(RejectReasonCodeType rejectReasonCode) {
        this.rejectReasonCode = rejectReasonCode;
    }

    public FollowUpActionCodeType getFollowUpActionCode() {
        return followUpActionCode;
    }

    public void setFollowUpActionCode(FollowUpActionCodeType followUpActionCode) {
        this.followUpActionCode = followUpActionCode;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}