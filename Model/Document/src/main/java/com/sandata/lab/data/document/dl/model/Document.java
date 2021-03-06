//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.10 at 06:16:03 PM EDT 
//

package com.sandata.lab.data.document.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document")
public class Document extends BaseObject {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Document_ID", required = true)
    @SerializedName("DocumentID")
    protected String documentID;

    @XmlAttribute(name = "Document_SK", required = true)
    @SerializedName("DocumentSK")
    protected BigInteger documentSK;

    @XmlAttribute(name = "Document_Type_Name", required = true)
    @SerializedName("DocumentTypeName")
    protected String documentTypeName;

    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date recordCreateTimestamp;

    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date recordUpdateTimestamp;

    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
    protected String businessEntityID;

    @XmlAttribute(name = "Patient_ID", required = true)
    @SerializedName("PatientID")
    protected String patientID;

    @XmlAttribute(name = "Staff_ID", required = true)
    @SerializedName("StaffID")
    protected String staffID;

    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
    protected String recordCreatedBy;

    @XmlAttribute(name = "Change_Reason_Memo", required = true)
    @SerializedName("ChangeReasonMemo")
    protected String changeReasonMemo;

    @XmlAttribute(name = "Document_Details", required = true)
    @SerializedName("DocumentDetails")
    protected List<DocumentDetail> documentDetails;

    @XmlAttribute(name = "Document_Other", required = true)
    @SerializedName("DocumentOther")
    protected byte[] docOther;

    @XmlAttribute(name = "Visit_SK", required = true)
    @SerializedName("VisitSK")
    protected BigInteger visitSK;


    @XmlAttribute(name = "Document_Class_Name", required = true)
    @SerializedName("DocumentClassName")
    protected String docClassName;


    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public BigInteger getDocumentSK() {
        return documentSK;
    }

    public void setDocumentSK(BigInteger documentSK) {
        this.documentSK = documentSK;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    public void setRecordUpdateTimestamp(Date recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }

    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    public void setRecordCreateTimestamp(Date recordCreateTimestamp) {
        this.recordCreateTimestamp = recordCreateTimestamp;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getRecordCreatedBy() {
        return recordCreatedBy;
    }

    public void setRecordCreatedBy(String recordCreatedBy) {
        this.recordCreatedBy = recordCreatedBy;
    }

    public String getChangeReasonMemo() {
        return changeReasonMemo;
    }

    public void setChangeReasonMemo(String changeReasonMemo) {
        this.changeReasonMemo = changeReasonMemo;
    }

    public List<DocumentDetail> getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(List<DocumentDetail> documentDetails) {
        this.documentDetails = documentDetails;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public byte[] getDocOther() {
        return docOther;
    }

    public void setDocOther(byte[] docOther) {
        this.docOther = docOther;
    }

    public BigInteger getVisitSK() {
        return visitSK;
    }

    public void setVisitSK(BigInteger visitSK) {
        this.visitSK = visitSK;
    }

    public String getDocClassName() {
        return docClassName;
    }

    public void setDocClassName(String docClassName) {
        this.docClassName = docClassName;
    }
}
