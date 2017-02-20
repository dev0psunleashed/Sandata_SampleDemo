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
@XmlType(name = "DocumentDetail")
public class DocumentDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Document_Detail_SK", required = true)
    @SerializedName("DocumentDetailSK")
    protected BigInteger documentDetailSK;

    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date recordCreateTimestamp;

    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date recordUpdateTimestamp;

    @XmlAttribute(name = "Document_SK")
    @SerializedName("DocumentSK")
    protected BigInteger documentSK;

    @XmlAttribute(name = "Document_Detail_Property_Name")
    @SerializedName("DocumentDetailPropertyName")
    protected String documentDetailPropertyName;

    @XmlAttribute(name = "Document_Detail_Property_Val")
    @SerializedName("DocumentDetailPropertyVal")
    protected String documentDetailPropertyVal;

    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    public void setRecordCreateTimestamp(Date recordCreateTimestamp) {
        this.recordCreateTimestamp = recordCreateTimestamp;
    }

    public BigInteger getDocumentDetailSK() {
        return documentDetailSK;
    }

    public void setDocumentDetailSK(BigInteger documentDetailSK) {
        this.documentDetailSK = documentDetailSK;
    }

    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    public void setRecordUpdateTimestamp(Date recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }

    public BigInteger getDocumentSK() {
        return documentSK;
    }

    public void setDocumentSK(BigInteger documentSK) {
        this.documentSK = documentSK;
    }

    public String getDocumentDetailPropertyName() {
        return documentDetailPropertyName;
    }

    public void setDocumentDetailPropertyName(String documentDetailPropertyName) {
        this.documentDetailPropertyName = documentDetailPropertyName;
    }

    public String getDocumentDetailPropertyVal() {
        return documentDetailPropertyVal;
    }

    public void setDocumentDetailPropertyVal(String documentDetailPropertyVal) {
        this.documentDetailPropertyVal = documentDetailPropertyVal;
    }
}
