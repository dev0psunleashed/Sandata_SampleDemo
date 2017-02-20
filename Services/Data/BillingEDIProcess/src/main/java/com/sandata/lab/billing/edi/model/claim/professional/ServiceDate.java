package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.DateFormatType;
import com.sandata.lab.billing.edi.model.enums.DateTypeCodeType;

public class ServiceDate {
    private long id;

    private DateTypeCodeType type;

    private Date startDate;
    private Date endDate;

    private long serviceId;

    private DateFormatType format = DateFormatType.Date;

    public final DateFormatType getFormat() {
        return format;
    }

    public final void setFormat(DateFormatType value) {
        format = value;
    }

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

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
}