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
@XmlType(name = "DocumentDetailPropertyLookup")
public class DocumentDetailPropertyLookUp extends BaseObject {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Document_Detail_Property_Lookup_SK", required = true)
    @SerializedName("DocumentDetailPropertyLookupSK")
    protected BigInteger documentDetailPropertyLookupSK;

    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date recordCreateTimestamp;

    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date recordUpdateTimestamp;

    @XmlAttribute(name = "Document_Detail_Property_Name")
    @SerializedName("DocumentDetailPropertyName")
    protected String documentDetailPropertyName;

    @XmlAttribute(name = "Document_Detail_Property_DESC")
    @SerializedName("DocumentDetailPropertyDesc")
    protected String documentDetailPropertyDesc;

    public BigInteger getDocumentDetailPropertyLookupSK() {
        return documentDetailPropertyLookupSK;
    }

    public void setDocumentDetailPropertyLookupSK(BigInteger documentDetailPropertyLookupSK) {
        this.documentDetailPropertyLookupSK = documentDetailPropertyLookupSK;
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

    public String getDocumentDetailPropertyName() {
        return documentDetailPropertyName;
    }

    public void setDocumentDetailPropertyName(String documentDetailPropertyName) {
        this.documentDetailPropertyName = documentDetailPropertyName;
    }

    public String getDocumentDetailPropertyDesc() {
        return documentDetailPropertyDesc;
    }

    public void setDocumentDetailPropertyDesc(String documentDetailPropertyDesc) {
        this.documentDetailPropertyDesc = documentDetailPropertyDesc;
    }
}
