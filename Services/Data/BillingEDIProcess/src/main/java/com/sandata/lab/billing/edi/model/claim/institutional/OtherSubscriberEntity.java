package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.NameTypeCodeType;

public class OtherSubscriberEntity {
    private long id;
    private long otherSubscriberId;

    private NameTypeCodeType entityType;

    private Collection<OtherSubscriberEntityAdditionalId> additionalIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOtherSubscriberId() {
        return otherSubscriberId;
    }

    public void setOtherSubscriberId(long otherSubscriberId) {
        this.otherSubscriberId = otherSubscriberId;
    }

    public NameTypeCodeType getEntityType() {
        return entityType;
    }

    public void setEntityType(NameTypeCodeType entityType) {
        this.entityType = entityType;
    }

    public Collection<OtherSubscriberEntityAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public void setAdditionalIds(Collection<OtherSubscriberEntityAdditionalId> additionalIds) {
        this.additionalIds = additionalIds;
    }
}