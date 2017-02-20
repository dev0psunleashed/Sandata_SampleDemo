package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.QuantityType;

public class ServiceQuantity {
    private long id;
    private long serviceId;

    private QuantityType type;

    private java.math.BigDecimal quantity = new java.math.BigDecimal(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public QuantityType getType() {
        return type;
    }

    public void setType(QuantityType type) {
        this.type = type;
    }

    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }
}