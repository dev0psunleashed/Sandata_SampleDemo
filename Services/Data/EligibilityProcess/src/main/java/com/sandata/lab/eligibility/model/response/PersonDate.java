package com.sandata.lab.eligibility.model.response;

import java.util.*;

import com.sandata.lab.eligibility.model.response.enums.DateTypeCodeType;

public class PersonDate {
    private long id;

    private DateTypeCodeType type;
    private boolean isRange;
    private Date startDate;
    private Date endDate;

    private long personId;
    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTypeCodeType getType() {
        return type;
    }

    public void setType(DateTypeCodeType type) {
        this.type = type;
    }

    public boolean isRange() {
        return isRange;
    }

    public void setRange(boolean isRange) {
        this.isRange = isRange;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}