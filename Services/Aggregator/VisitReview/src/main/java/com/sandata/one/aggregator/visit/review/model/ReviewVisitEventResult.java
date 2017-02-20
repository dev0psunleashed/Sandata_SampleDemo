package com.sandata.one.aggregator.visit.review.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.model.Visit;

import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;
import java.util.Date;

public class ReviewVisitEventResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("CallDate")
    private Date callDate;

    @SerializedName("CallTime")
    private String callTime;

    //call in/call out / extra call
    @SerializedName("CallInIndicator")
    private Boolean callInIndicator;

    @SerializedName("CallOutIndicator")
    private Boolean callOutIndicator;

    @SerializedName("CallSource")
    private String callSource;

    @SerializedName("CallType")
    private String callType;

    @SerializedName("ServiceName")
    private String serviceName;

    //for Mobile only - CallTYpe is mobile

    @SerializedName("Latitude")
    private String latitude;

    @SerializedName("Longitude")
    private String longitude;

    @SerializedName("MVVUserName")
    private String mvvUserName;

    //For Telephony Only including FVV:

    @SerializedName("ANI")
    private String ani;

    @SerializedName("FVVUserName")
    private String fvvUserName;

    @SerializedName("PatientID")
    private String patientID;

    @SerializedName("VisitEventSK")
    private Long visitEventSK;


    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public Boolean getCallInIndicator() {
        return callInIndicator;
    }

    public void setCallInIndicator(Boolean callInIndicator) {
        this.callInIndicator = callInIndicator;
    }

    public Boolean getCallOutIndicator() {
        return callOutIndicator;
    }

    public void setCallOutIndicator(Boolean callOutIndicator) {
        this.callOutIndicator = callOutIndicator;
    }


    public String getCallSource() {
        return callSource;
    }

    public void setCallSource(String callSource) {
        this.callSource = callSource;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMvvUserName() {
        return mvvUserName;
    }

    public void setMvvUserName(String mvvUserName) {
        this.mvvUserName = mvvUserName;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getFvvUserName() {
        return fvvUserName;
    }

    public void setFvvUserName(String fvvUserName) {
        this.fvvUserName = fvvUserName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Long getVisitEventSK() {
        return visitEventSK;
    }

    public void setVisitEventSK(Long visitEventSK) {
        this.visitEventSK = visitEventSK;
    }
}
