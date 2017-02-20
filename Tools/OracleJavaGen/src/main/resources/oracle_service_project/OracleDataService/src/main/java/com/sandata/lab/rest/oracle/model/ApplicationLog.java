//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 03:44:22 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Application_Log complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Log">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Application_Log_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Application_Session_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Log_Host" type="{}Computing_Host" />
 *       &lt;attribute name="Log_Process" type="{}Computing_Process" />
 *       &lt;attribute name="Log_Process_ID" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Log_Thread" type="{}Computing_Thread" />
 *       &lt;attribute name="Log_Level">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Log_Message" type="{}Message_Long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application_Log")
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppLog",
        updateMethod = "updateAppLog",
        deleteMethod = "deleteAppLog",
        getMethod = "getAppLog",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppLogT",
        primaryKeys = {})
public class ApplicationLog extends BaseObject extends BaseObject extends BaseObject extends BaseObject {

	private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Application_Log_SK", required = true)
    @SerializedName("ApplicationLogSK")
    @SerializedName("ApplicationLogSK")
    @SerializedName("ApplicationLogSK")
    @SerializedName("ApplicationLogSK")
	@Mapping(getter = "getAppLogSk", setter = "setAppLogSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger applicationLogSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @SerializedName("RecordCreateTimestamp")
    @SerializedName("RecordCreateTimestamp")
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @SerializedName("RecordUpdateTimestamp")
    @SerializedName("RecordUpdateTimestamp")
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Application_Session_SK")
    @SerializedName("ApplicationSessionSK")
    @SerializedName("ApplicationSessionSK")
    @SerializedName("ApplicationSessionSK")
    @SerializedName("ApplicationSessionSK")
	@Mapping(getter = "getAppSessSk", setter = "setAppSessSk", type = "java.math.BigDecimal", index = 3)
    protected BigInteger applicationSessionSK;
    @XmlAttribute(name = "Log_Host")
    @SerializedName("LogHost")
    @SerializedName("LogHost")
    @SerializedName("LogHost")
    @SerializedName("LogHost")
	@Mapping(getter = "getLogHost", setter = "setLogHost", type = "String", index = 4)
    protected String logHost;
    @XmlAttribute(name = "Log_Process")
    @SerializedName("LogProcess")
    @SerializedName("LogProcess")
    @SerializedName("LogProcess")
    @SerializedName("LogProcess")
	@Mapping(getter = "getLogProcess", setter = "setLogProcess", type = "String", index = 5)
    protected String logProcess;
    @XmlAttribute(name = "Log_Process_ID")
    @SerializedName("LogProcessID")
    @SerializedName("LogProcessID")
    @SerializedName("LogProcessID")
    @SerializedName("LogProcessID")
	@Mapping(getter = "getLogPid", setter = "setLogPid", type = "java.math.BigDecimal", index = 6)
    protected BigInteger logProcessID;
    @XmlAttribute(name = "Log_Thread")
    @SerializedName("LogThread")
    @SerializedName("LogThread")
    @SerializedName("LogThread")
    @SerializedName("LogThread")
	@Mapping(getter = "getLogThread", setter = "setLogThread", type = "java.math.BigDecimal", index = 7)
    protected BigInteger logThread;
    @XmlAttribute(name = "Log_Level")
    @SerializedName("LogLevel")
    @SerializedName("LogLevel")
    @SerializedName("LogLevel")
    @SerializedName("LogLevel")
	@Mapping(getter = "getLogLvl", setter = "setLogLvl", type = "String", index = 8)
    protected String logLevel;
    @XmlAttribute(name = "Log_Message")
    @SerializedName("LogMessage")
    @SerializedName("LogMessage")
    @SerializedName("LogMessage")
    @SerializedName("LogMessage")
	@Mapping(getter = "getLogMsg", setter = "setLogMsg", type = "String", index = 9)
    protected String logMessage;

    /**
     * Gets the value of the applicationLogSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationLogSK() {
        return applicationLogSK;
    }

    /**
     * Sets the value of the applicationLogSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationLogSK(BigInteger value) {
        this.applicationLogSK = value;
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
     * Gets the value of the applicationSessionSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getApplicationSessionSK() {
        return applicationSessionSK;
    }

    /**
     * Sets the value of the applicationSessionSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setApplicationSessionSK(BigInteger value) {
        this.applicationSessionSK = value;
    }

    /**
     * Gets the value of the logHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogHost() {
        return logHost;
    }

    /**
     * Sets the value of the logHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogHost(String value) {
        this.logHost = value;
    }

    /**
     * Gets the value of the logProcess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogProcess() {
        return logProcess;
    }

    /**
     * Sets the value of the logProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogProcess(String value) {
        this.logProcess = value;
    }

    /**
     * Gets the value of the logProcessID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLogProcessID() {
        return logProcessID;
    }

    /**
     * Sets the value of the logProcessID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLogProcessID(BigInteger value) {
        this.logProcessID = value;
    }

    /**
     * Gets the value of the logThread property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLogThread() {
        return logThread;
    }

    /**
     * Sets the value of the logThread property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLogThread(BigInteger value) {
        this.logThread = value;
    }

    /**
     * Gets the value of the logLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * Sets the value of the logLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogLevel(String value) {
        this.logLevel = value;
    }

    /**
     * Gets the value of the logMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogMessage() {
        return logMessage;
    }

    /**
     * Sets the value of the logMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogMessage(String value) {
        this.logMessage = value;
    }

}
