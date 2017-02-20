package com.sandata.lab.data.model.dl.model.custom;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.util.Date;


@OracleMetadata(
        packageName = "PKG_AUDIT",
        insertMethod = "N/A",
        updateMethod = "N/A",
        deleteMethod = "N/A",
        getMethod = "N/A",
        findMethod = "N/A",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AuditEmplStatusHistT",
        primaryKeys = {})
public class AuditEmploymentStatusHistory extends BaseObject {

    private static final long serialVersionUID = 1L;

    @Mapping(getter = "getAuditHost", setter = "setAuditHost", type = "String", index = 0)
    protected String auditHost;

    @Mapping(getter = "getUserGuid", setter = "setUserGuid", type = "String", index = 1)
    protected String userGuid;

    @Mapping(getter = "getEffectiveDate", setter = "setEffectiveDate", type = "java.sql.Timestamp", index = 2)
    protected Date effectiveDate;

    @Mapping(getter = "getStatus", setter = "setStatus", type = "String", index = 3)
    protected String status;

    @Mapping(getter = "getReasonCode", setter = "setReasonCode", type = "String", index = 4)
    protected String reasonCode;

    @Mapping(getter = "getNotes", setter = "setNotes", type = "String", index = 5)
    protected String notes;

    @Mapping(getter = "getModified", setter = "setModified", type = "java.sql.Timestamp", index = 6)
    protected Date modified;

    public String getAuditHost() {
        return auditHost;
    }

    public void setAuditHost(String auditHost) {
        this.auditHost = auditHost;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
