package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.ArrayList;
import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.NameTypeCodeType;

public class ServiceEntity {
    private long id;
    private long serviceId;

    private NameTypeCodeType entityType;
    private String lastOrOrganizationName;
    private String firstName;
    private String middleName;
    private String suffixName;
    private NameIdentifierTypeCodeType entityIdType;
    private String entitiyId;

    private Collection<ServiceEntityAdditionalId> additionalIds = new ArrayList<ServiceEntityAdditionalId>();

    public final Collection<ServiceEntityAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<ServiceEntityAdditionalId> value) {
        additionalIds = value;
    }

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

    public NameTypeCodeType getEntityType() {
        return entityType;
    }

    public void setEntityType(NameTypeCodeType entityType) {
        this.entityType = entityType;
    }

    public String getLastOrOrganizationName() {
        return lastOrOrganizationName;
    }

    public void setLastOrOrganizationName(String lastOrOrganizationName) {
        this.lastOrOrganizationName = lastOrOrganizationName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public NameIdentifierTypeCodeType getEntityIdType() {
        return entityIdType;
    }

    public void setEntityIdType(NameIdentifierTypeCodeType entityIdType) {
        this.entityIdType = entityIdType;
    }

    public String getEntitiyId() {
        return entitiyId;
    }

    public void setEntitiyId(String entitiyId) {
        this.entitiyId = entitiyId;
    }
}