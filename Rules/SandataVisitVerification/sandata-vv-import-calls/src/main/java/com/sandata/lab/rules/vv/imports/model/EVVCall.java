package com.sandata.lab.rules.vv.imports.model;

import com.sandata.lab.data.model.base.BaseObject;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author thanh.le
 * 
 * This class map 1-1 to record in evv database (table calls)
 *
 */
public class EVVCall extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * Call Exp Key
     */
    private String callExpKey;

    /**
     * Event Type : FVV,MVV,EVV
     */
    private String eventType;
    
    /**
     * stx account
     */
    private String account;
    
    /**
     * Export Key
     */
    private String exportKey;

    /**
     * Unique identifier for call
     */
    private String sid;

    /**
     * call key : key to get calls from EVV db
     */
    private String callKey;

    /**
     * Time if call in local time 
     */
    private String callDtime;

    /**
     * Time if call in local time in UTC
     */
    private DateTime callDtimeUTC;

    /**
     * Phone number of where call was made from 
     */
    private String ani;

    /**
     * toll free number 
     */
    private String dnis;

    /**
     * duration of call 
     */
    private int duration;

    /**
     * n800num
     */
    private String n800num;

    /**
     * staff id
     */
    private String stxId;

    /**
     * clientId - patient id
     */
    private String clientId;

    /**
     * parentCallKey
     */
    private String parentCallKey;

    /**
     * gps flag
     */
    private boolean gpsFlag;

    /**
     * latitude
     */
    private BigDecimal latitude;

    /**
     * longitude
     */
    private BigDecimal longitude;

    /**
     * callerRecordFlag
     */
    private boolean callerRecordFlag;

    /**
     * callerRecordFileName
     */
    private String callerRecordFileName;

    /**
     * speakerVFlag
     */
    private boolean speakerVFlag;

    /**
     * Indicator call in or call out
     */
    private String inOutFlag;

    /**
     * Offset of hours of call time from UTC (Can be used to convert call_dtime to UTC)
     */
    private int hoursOffset;

    /**
     * fobFlag used to indecate VisitType : FVV
     */
    private String fobFlag;

    /**
     * fobValue
     */
    private String fobValue;

    /**
     * Time zone name
     */
    private String timeZoneName;

    /**
     * selectedInOut
     */
    private String selectedInOut;

    /**
     * List of EVVCallInfo , used to store task list info
     */
    private List<EVVCallInfo> callInfo;

    public String getCallExpKey() {
        return callExpKey;
    }

    public void setCallExpKey(String callExpKey) {
        this.callExpKey = callExpKey;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getExportKey() {
        return exportKey;
    }

    public void setExportKey(String exportKey) {
        this.exportKey = exportKey;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCallKey() {
        return callKey;
    }

    public void setCallKey(String callKey) {
        this.callKey = callKey;
    }

    public String getCallDtime() {
        return callDtime;
    }

    public void setCallDtime(String callDtime) {
        this.callDtime = callDtime;
    }

    public DateTime getCallDtimeUTC() {
        return callDtimeUTC;
    }

    public void setCallDtimeUTC(DateTime callDtimeUTC) {
        this.callDtimeUTC = callDtimeUTC;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getN800num() {
        return n800num;
    }

    public void setN800num(String n800num) {
        this.n800num = n800num;
    }

    public String getStxId() {
        return stxId;
    }

    public void setStxId(String stxId) {
        this.stxId = stxId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getParentCallKey() {
        return parentCallKey;
    }

    public void setParentCallKey(String parentCallKey) {
        this.parentCallKey = parentCallKey;
    }

    public boolean isGpsFlag() {
        return gpsFlag;
    }

    public void setGpsFlag(boolean gpsFlag) {
        this.gpsFlag = gpsFlag;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public boolean isCallerRecordFlag() {
        return callerRecordFlag;
    }

    public void setCallerRecordFlag(boolean callerRecordFlag) {
        this.callerRecordFlag = callerRecordFlag;
    }

    public String getCallerRecordFileName() {
        return callerRecordFileName;
    }

    public void setCallerRecordFileName(String callerRecordFileName) {
        this.callerRecordFileName = callerRecordFileName;
    }

    public boolean isSpeakerVFlag() {
        return speakerVFlag;
    }

    public void setSpeakerVFlag(boolean speakerVFlag) {
        this.speakerVFlag = speakerVFlag;
    }

    public String getInOutFlag() {
        return inOutFlag;
    }

    public void setInOutFlag(String inOutFlag) {
        this.inOutFlag = inOutFlag;
    }

    public int getHoursOffset() {
        return hoursOffset;
    }

    public void setHoursOffset(int hoursOffset) {
        this.hoursOffset = hoursOffset;
    }

    public String getFobFlag() {
        return fobFlag;
    }

    public void setFobFlag(String fobFlag) {
        this.fobFlag = fobFlag;
    }

    public String getFobValue() {
        return fobValue;
    }

    public void setFobValue(String fobValue) {
        this.fobValue = fobValue;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    public String getSelectedInOut() {
        return selectedInOut;
    }

    public void setSelectedInOut(String selectedInOut) {
        this.selectedInOut = selectedInOut;
    }

    public List<EVVCallInfo> getCallInfo() {
        return callInfo;
    }

    public void setCallInfo(List<EVVCallInfo> callInfo) {
        this.callInfo = callInfo;
    }


}
