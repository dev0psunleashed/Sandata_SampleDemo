package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.DateTypeCodeType;

public class ServicePaymentDate {
    private long id;

    private DateTypeCodeType type;
    private boolean isRange;
    private Date startDate;
    private Date endDate;

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
}