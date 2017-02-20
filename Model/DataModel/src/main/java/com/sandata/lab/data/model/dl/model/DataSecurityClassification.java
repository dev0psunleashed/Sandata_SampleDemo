//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 03:44:22 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The Data Security Classification entity  is used to store data security classifications.
 * 
 * 
 * 
 * <p>Java class for Data_Security_Classification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Data_Security_Classification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Application_Audit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Data_Structure_Security_Mapping" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Data_Security_Classification_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Data_Security_Compliance_Type_Name" use="required" type="{}Data_Security_Compliance_Type_Name" />
 *       &lt;attribute name="Data_Security_Classification_ID" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *             &lt;enumeration value="HIPAA-PHI-01"/>
 *             &lt;enumeration value="HIPAA-PHI-02"/>
 *             &lt;enumeration value="HIPAA-PHI-03"/>
 *             &lt;enumeration value="HIPAA-PHI-04"/>
 *             &lt;enumeration value="HIPAA-PHI-05"/>
 *             &lt;enumeration value="HIPAA-PHI-06"/>
 *             &lt;enumeration value="HIPAA-PHI-07"/>
 *             &lt;enumeration value="HIPAA-PHI-08"/>
 *             &lt;enumeration value="HIPAA-PHI-09"/>
 *             &lt;enumeration value="HIPAA-PHI-10"/>
 *             &lt;enumeration value="HIPAA-PHI-11"/>
 *             &lt;enumeration value="HIPAA-PHI-12"/>
 *             &lt;enumeration value="HIPAA-PHI-13"/>
 *             &lt;enumeration value="HIPAA-PHI-14"/>
 *             &lt;enumeration value="HIPAA-PHI-15"/>
 *             &lt;enumeration value="HIPAA-PHI-16"/>
 *             &lt;enumeration value="HIPAA-PHI-17"/>
 *             &lt;enumeration value="HIPAA-PHI-18"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Data_Security_Classification_Description" type="{}Description_Long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Data_Security_Classification", propOrder = {
    "applicationAudit",
    "applicationDataStructureSecurityMapping"
})
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertDataSecClas",
        updateMethod = "updateDataSecClas",
        deleteMethod = "deleteDataSecClas",
        getMethod = "getDataSecClas",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.DataSecClasT",
        primaryKeys = {})
public class DataSecurityClassification extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Application_Audit")
    @SerializedName("ApplicationAudit")
    protected List<ApplicationAudit> applicationAudit;
    @XmlElement(name = "Application_Data_Structure_Security_Mapping")
    @SerializedName("ApplicationDataStructureSecurityMapping")
    protected List<ApplicationDataStructureSecurityMapping> applicationDataStructureSecurityMapping;
    @XmlAttribute(name = "Data_Security_Classification_SK", required = true)
    @SerializedName("DataSecurityClassificationSK")
	@Mapping(getter = "getDataSecClasSk", setter = "setDataSecClasSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger dataSecurityClassificationSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Data_Security_Compliance_Type_Name", required = true)
    @SerializedName("DataSecurityComplianceTypeName")
	@Mapping(getter = "getDataSecCompTypName", setter = "setDataSecCompTypName", type = "String", index = 3)
    protected DataSecurityComplianceTypeName dataSecurityComplianceTypeName;
    @XmlAttribute(name = "Data_Security_Classification_ID", required = true)
    @SerializedName("DataSecurityClassificationID")
	@Mapping(getter = "getDataSecClasId", setter = "setDataSecClasId", type = "String", index = 4)
    protected String dataSecurityClassificationID;
    @XmlAttribute(name = "Data_Security_Classification_Description")
    @SerializedName("DataSecurityClassificationDescription")
	@Mapping(getter = "getDataSecClasDesc", setter = "setDataSecClasDesc", type = "String", index = 5)
    protected String dataSecurityClassificationDescription;

    /**
     * Gets the value of the applicationAudit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationAudit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationAudit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationAudit }
     * 
     * 
     */
    public List<ApplicationAudit> getApplicationAudit() {
        if (applicationAudit == null) {
            applicationAudit = new ArrayList<ApplicationAudit>();
        }
        return this.applicationAudit;
    }

    /**
     * Gets the value of the applicationDataStructureSecurityMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationDataStructureSecurityMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationDataStructureSecurityMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationDataStructureSecurityMapping }
     * 
     * 
     */
    public List<ApplicationDataStructureSecurityMapping> getApplicationDataStructureSecurityMapping() {
        if (applicationDataStructureSecurityMapping == null) {
            applicationDataStructureSecurityMapping = new ArrayList<ApplicationDataStructureSecurityMapping>();
        }
        return this.applicationDataStructureSecurityMapping;
    }

    /**
     * Gets the value of the dataSecurityClassificationSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataSecurityClassificationSK() {
        return dataSecurityClassificationSK;
    }

    /**
     * Sets the value of the dataSecurityClassificationSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataSecurityClassificationSK(BigInteger value) {
        this.dataSecurityClassificationSK = value;
    }

    /**
     * Gets the value of the recordCreateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    /**
     * Sets the value of the recordCreateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreateTimestamp(Date value) {
        this.recordCreateTimestamp = value;
    }

    /**
     * Gets the value of the recordUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    /**
     * Sets the value of the recordUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdateTimestamp(Date value) {
        this.recordUpdateTimestamp = value;
    }

    /**
     * Gets the value of the dataSecurityComplianceTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link DataSecurityComplianceTypeName }
     *     
     */
    public DataSecurityComplianceTypeName getDataSecurityComplianceTypeName() {
        return dataSecurityComplianceTypeName;
    }

    /**
     * Sets the value of the dataSecurityComplianceTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSecurityComplianceTypeName }
     *     
     */
    public void setDataSecurityComplianceTypeName(DataSecurityComplianceTypeName value) {
        this.dataSecurityComplianceTypeName = value;
    }

    /**
     * Gets the value of the dataSecurityClassificationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSecurityClassificationID() {
        return dataSecurityClassificationID;
    }

    /**
     * Sets the value of the dataSecurityClassificationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSecurityClassificationID(String value) {
        this.dataSecurityClassificationID = value;
    }

    /**
     * Gets the value of the dataSecurityClassificationDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSecurityClassificationDescription() {
        return dataSecurityClassificationDescription;
    }

    /**
     * Sets the value of the dataSecurityClassificationDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSecurityClassificationDescription(String value) {
        this.dataSecurityClassificationDescription = value;
    }

}
