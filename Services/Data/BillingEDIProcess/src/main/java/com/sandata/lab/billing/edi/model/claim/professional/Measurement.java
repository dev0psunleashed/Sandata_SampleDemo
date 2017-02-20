package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.MeasurementReferenceType;
import com.sandata.lab.billing.edi.model.enums.MeasurementType;

public class Measurement {
    private long id;
    private long serviceId;

    private MeasurementReferenceType measurementReference;
    private MeasurementType measurementType;
    private java.math.BigDecimal value = new java.math.BigDecimal(0);

    public long getId() {
        return id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public MeasurementReferenceType getMeasurementReference() {
        return measurementReference;
    }

    public void setMeasurementReference(MeasurementReferenceType measurementReference) {
        this.measurementReference = measurementReference;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public java.math.BigDecimal getValue() {
        return value;
    }

    public void setValue(java.math.BigDecimal value) {
        this.value = value;
    }

    public void setId(long id) {
        this.id = id;
    }
}