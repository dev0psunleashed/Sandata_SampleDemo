package com.sandata.lab.eligibility.model.response;

import com.sandata.lab.eligibility.model.response.enums.DiagnosisCodeType;

public class DiagnosisCode {
    private long id;

    private int number;
    private DiagnosisCodeType type;
    private String code;

    private long personId;
    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DiagnosisCodeType getType() {
        return type;
    }

    public void setType(DiagnosisCodeType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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