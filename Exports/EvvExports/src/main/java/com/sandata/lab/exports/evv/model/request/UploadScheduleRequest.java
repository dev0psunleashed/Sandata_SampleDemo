package com.sandata.lab.exports.evv.model.request;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 10:13 AM
 */
public class UploadScheduleRequest {
    private String fromDate;
    private String toDate;
    private Date from;
    private Date to;
    private String bsnEntId;
    private String santraxId;
    private Date lastExport;
    private String uuid;
    private String vvId;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getBsnEntId() {
        return bsnEntId;
    }

    public void setBsnEntId(String bsnEntId) {
        this.bsnEntId = bsnEntId;
    }

    public String getSantraxId() {
        return santraxId;
    }

    public void setSantraxId(String santraxId) {
        this.santraxId = santraxId;
    }

    public Date getLastExport() {
        return lastExport;
    }

    public void setLastExport(Date lastExport) {
        this.lastExport = lastExport;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVvId() {
        return vvId;
    }

    public void setVvId(String vvId) {
        this.vvId = vvId;
    }
}
