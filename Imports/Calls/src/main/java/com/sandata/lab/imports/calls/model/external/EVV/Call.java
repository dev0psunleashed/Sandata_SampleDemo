package com.sandata.lab.imports.calls.model.external.EVV;

import com.sandata.lab.data.model.base.BaseObject;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

public class Call extends BaseObject {

    private static final long serialVersionUID = 1L;

    String callExpKey;

    String event_type;
    String account;
    String exportKey;

    //Unique identifier for call
    String sid;

    String callKey;

    //Time if call in local time
    String call_dtime;



    DateTime call_dtimeUTC;

    //Phone number of where call was made from
    String ani;

    String dnis;

    int duration;

    String n800num;

    String stx_id;

    String client_id;

    String parentcallKey;

    boolean gpsFlag;

    BigDecimal latitude;

    BigDecimal longitude;

    boolean callerRecordFlag;

    String callerRecordFileName;

    boolean speakerVFlag;

    String inOutFlag;

    //Offset of hours of call time from UTC (Can be used to convert call_dtime to UTC)
    int hours_offset;

    String fobFlag;

    String fobValue;



    String tzName;

    String selectedInOut;

    List<CallInfo> callInfos;

    public String getCallExpKey() {
        return callExpKey;
    }

    public void setCallExpKey(String callExpKey) {
        this.callExpKey = callExpKey;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
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

    public String getCall_dtime() {
        return call_dtime;
    }

    public void setCall_dtime(String call_dtime) {
        this.call_dtime = call_dtime;
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

    public String getN800num() {
        return n800num;
    }

    public void setN800num(String n800num) {
        this.n800num = n800num;
    }

    public String getStx_id() {
        return stx_id;
    }

    public void setStx_id(String stx_id) {
        this.stx_id = stx_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getParentcallKey() {
        return parentcallKey;
    }

    public void setParentcallKey(String parentcallKey) {
        this.parentcallKey = parentcallKey;
    }

    public boolean getGpsFlag() {
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

    public boolean getCallerRecordFlag() {
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

    public boolean getSpeakerVFlag() {
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

    public int getHours_offset() {
        return hours_offset;
    }

    public void setHours_offset(int hours_offset) {
        this.hours_offset = hours_offset;
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

    public String getSelectedInOut() {
        return selectedInOut;
    }

    public void setSelectedInOut(String selectedInOut) {
        this.selectedInOut = selectedInOut;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<CallInfo> getCallInfos() {
        return callInfos;
    }

    public void setCallInfos(List<CallInfo> callInfos) {
        this.callInfos = callInfos;
    }

    public DateTime getCall_dtimeUTC() {
        return call_dtimeUTC;
    }

    public void setCall_dtimeUTC(DateTime call_dtimeUTC) {
        this.call_dtimeUTC = call_dtimeUTC;
    }

    public String getTzName() {
        return tzName;
    }

    public void setTzName(String tzName) {
        this.tzName = tzName;
    }
}
