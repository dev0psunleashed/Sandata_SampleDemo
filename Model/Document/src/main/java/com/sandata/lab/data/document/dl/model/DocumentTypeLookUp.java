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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentTypeLookup")
public class DocumentTypeLookUp extends BaseObject {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Document_TYPE_LOOKUP_SK", required = true)
    @SerializedName("DocumentTypeLookupSK")
    protected BigInteger documentTypeLookupSK;

    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date recordCreateTimestamp;

    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date recordUpdateTimestamp;

    @XmlAttribute(name = "Document_TYPE_Name")
    @SerializedName("DocumentTypeName")
    protected String documentTypeName;

    @XmlAttribute(name = "Document_TYPE_DESC")
    @SerializedName("DocumentTypeDesc")
    protected String documentTypeDesc;

    public BigInteger getDocumentTypeLookupSK() {
        return documentTypeLookupSK;
    }

    public void setDocumentTypeLookupSK(BigInteger documentTypeLookupSK) {
        this.documentTypeLookupSK = documentTypeLookupSK;
    }

    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    public void setRecordCreateTimestamp(Date recordCreateTimestamp) {
        this.recordCreateTimestamp = recordCreateTimestamp;
    }

    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    public void setRecordUpdateTimestamp(Date recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getDocumentTypeDesc() {
        return documentTypeDesc;
    }

    public void setDocumentTypeDesc(String documentTypeDesc) {
        this.documentTypeDesc = documentTypeDesc;
    }
}
