//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


package com.sandata.lab.rest.oracle.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A definitive piece of work assigned to a staff member during a scheduled visit
 * 
 * <p>Java class for Task complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Task">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Plan_Of_Care_Task_List" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Service_Task" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Schedule_Task_List" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Visit_Task_List" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Task_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Task_ID" use="required" type="{}Task_ID" />
 *       &lt;attribute name="Business_Entity_Task_ID" use="required" type="{}Business_Entity_Task_ID" />
 *       &lt;attribute name="Business_Entity_Task_Name" type="{}Task_Name" />
 *       &lt;attribute name="Task_Description" type="{}Description_Short" />
 *       &lt;attribute name="Task_Reading_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Task_Reading_Validation_Rule">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="100"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Critical_Task_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Task_Effective_Date" type="{}Attribute_Effective_Date" />
 *       &lt;attribute name="Task_Termination_Date" type="{}Attribute_Termination_Date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Task", propOrder = {
    "planOfCareTaskList",
    "serviceTask",
    "scheduleTaskList",
    "visitTaskList"
})
@OracleMetadata(
        packageName = "PKG_TASKS",
        insertMethod = "insertTask",
        updateMethod = "updateTask",
        deleteMethod = "deleteTask",
        getMethod = "getTask",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.TaskT",
        primaryKeys = {})
public class Task extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Plan_Of_Care_Task_List")
    @SerializedName("PlanOfCareTaskList")
    protected List<PlanOfCareTaskList> planOfCareTaskList;
    @XmlElement(name = "Service_Task")
    @SerializedName("ServiceTask")
    protected List<ServiceTask> serviceTask;
    @XmlElement(name = "Schedule_Task_List")
    @SerializedName("ScheduleTaskList")
    protected List<ScheduleTaskList> scheduleTaskList;
    @XmlElement(name = "Visit_Task_List")
    @SerializedName("VisitTaskList")
    protected List<VisitTaskList> visitTaskList;
    @XmlAttribute(name = "Task_SK", required = true)
    @SerializedName("TaskSK")
	@Mapping(getter = "getTaskSk", setter = "setTaskSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger taskSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecTermTmstp", setter = "setRecTermTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordTerminationTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
	@Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 5)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
	@Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 6)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
	@Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 7)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Current_Record_Indicator", required = true)
    @SerializedName("CurrentRecordIndicator")
	@Mapping(getter = "getCurrRecInd", setter = "setCurrRecInd", type = "java.math.BigDecimal", index = 8)
    protected boolean currentRecordIndicator;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 9)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
	@Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 10)
    protected String businessEntityID;
    @XmlAttribute(name = "Task_ID", required = true)
    @SerializedName("TaskID")
	@Mapping(getter = "getTaskId", setter = "setTaskId", type = "String", index = 11)
    protected String taskID;
    @XmlAttribute(name = "Business_Entity_Task_ID", required = true)
    @SerializedName("BusinessEntityTaskID")
	@Mapping(getter = "getBeTaskId", setter = "setBeTaskId", type = "String", index = 12)
    protected String businessEntityTaskID;
    @XmlAttribute(name = "Business_Entity_Task_Name")
    @SerializedName("BusinessEntityTaskName")
	@Mapping(getter = "getBeTaskName", setter = "setBeTaskName", type = "String", index = 13)
    protected String businessEntityTaskName;
    @XmlAttribute(name = "Task_Description")
    @SerializedName("TaskDescription")
	@Mapping(getter = "getTaskDesc", setter = "setTaskDesc", type = "String", index = 14)
    protected String taskDescription;
    @XmlAttribute(name = "Task_Reading_Indicator")
    @SerializedName("TaskReadingIndicator")
	@Mapping(getter = "getTaskRdngInd", setter = "setTaskRdngInd", type = "java.math.BigDecimal", index = 15)
    protected Boolean taskReadingIndicator;
    @XmlAttribute(name = "Task_Reading_Validation_Rule")
    @SerializedName("TaskReadingValidationRule")
	@Mapping(getter = "getTaskRdngValidRule", setter = "setTaskRdngValidRule", type = "String", index = 16)
    protected String taskReadingValidationRule;
    @XmlAttribute(name = "Critical_Task_Indicator")
    @SerializedName("CriticalTaskIndicator")
	@Mapping(getter = "getCriticalTaskInd", setter = "setCriticalTaskInd", type = "java.math.BigDecimal", index = 17)
    protected Boolean criticalTaskIndicator;
    @XmlAttribute(name = "Task_Effective_Date")
    @SerializedName("TaskEffectiveDate")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getTaskEffDate", setter = "setTaskEffDate", type = "java.sql.Timestamp", index = 18)
    protected Date taskEffectiveDate;
    @XmlAttribute(name = "Task_Termination_Date")
    @SerializedName("TaskTerminationDate")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getTaskTermDate", setter = "setTaskTermDate", type = "java.sql.Timestamp", index = 19)
    protected Date taskTerminationDate;

    /**
     * Gets the value of the planOfCareTaskList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planOfCareTaskList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanOfCareTaskList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanOfCareTaskList }
     * 
     * 
     */
    public List<PlanOfCareTaskList> getPlanOfCareTaskList() {
        if (planOfCareTaskList == null) {
            planOfCareTaskList = new ArrayList<PlanOfCareTaskList>();
        }
        return this.planOfCareTaskList;
    }

    /**
     * Gets the value of the serviceTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceTask }
     * 
     * 
     */
    public List<ServiceTask> getServiceTask() {
        if (serviceTask == null) {
            serviceTask = new ArrayList<ServiceTask>();
        }
        return this.serviceTask;
    }

    /**
     * Gets the value of the scheduleTaskList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduleTaskList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduleTaskList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduleTaskList }
     * 
     * 
     */
    public List<ScheduleTaskList> getScheduleTaskList() {
        if (scheduleTaskList == null) {
            scheduleTaskList = new ArrayList<ScheduleTaskList>();
        }
        return this.scheduleTaskList;
    }

    /**
     * Gets the value of the visitTaskList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the visitTaskList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVisitTaskList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VisitTaskList }
     * 
     * 
     */
    public List<VisitTaskList> getVisitTaskList() {
        if (visitTaskList == null) {
            visitTaskList = new ArrayList<VisitTaskList>();
        }
        return this.visitTaskList;
    }

    /**
     * Gets the value of the taskSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTaskSK() {
        return taskSK;
    }

    /**
     * Sets the value of the taskSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTaskSK(BigInteger value) {
        this.taskSK = value;
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
     * Gets the value of the recordEffectiveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordEffectiveTimestamp() {
        return recordEffectiveTimestamp;
    }

    /**
     * Sets the value of the recordEffectiveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordEffectiveTimestamp(Date value) {
        this.recordEffectiveTimestamp = value;
    }

    /**
     * Gets the value of the recordTerminationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordTerminationTimestamp() {
        return recordTerminationTimestamp;
    }

    /**
     * Sets the value of the recordTerminationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordTerminationTimestamp(Date value) {
        this.recordTerminationTimestamp = value;
    }

    /**
     * Gets the value of the recordCreatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordCreatedBy() {
        return recordCreatedBy;
    }

    /**
     * Sets the value of the recordCreatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreatedBy(String value) {
        this.recordCreatedBy = value;
    }

    /**
     * Gets the value of the recordUpdatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordUpdatedBy() {
        return recordUpdatedBy;
    }

    /**
     * Sets the value of the recordUpdatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdatedBy(String value) {
        this.recordUpdatedBy = value;
    }

    /**
     * Gets the value of the changeReasonMemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeReasonMemo() {
        return changeReasonMemo;
    }

    /**
     * Sets the value of the changeReasonMemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeReasonMemo(String value) {
        this.changeReasonMemo = value;
    }

    /**
     * Gets the value of the currentRecordIndicator property.
     * 
     */
    public boolean isCurrentRecordIndicator() {
        return currentRecordIndicator;
    }

    /**
     * Sets the value of the currentRecordIndicator property.
     * 
     */
    public void setCurrentRecordIndicator(boolean value) {
        this.currentRecordIndicator = value;
    }

    /**
     * Gets the value of the changeVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getChangeVersionID() {
        return changeVersionID;
    }

    /**
     * Sets the value of the changeVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setChangeVersionID(BigInteger value) {
        this.changeVersionID = value;
    }

    /**
     * Gets the value of the businessEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityID() {
        return businessEntityID;
    }

    /**
     * Sets the value of the businessEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityID(String value) {
        this.businessEntityID = value;
    }

    /**
     * Gets the value of the taskID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * Sets the value of the taskID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskID(String value) {
        this.taskID = value;
    }

    /**
     * Gets the value of the businessEntityTaskID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityTaskID() {
        return businessEntityTaskID;
    }

    /**
     * Sets the value of the businessEntityTaskID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityTaskID(String value) {
        this.businessEntityTaskID = value;
    }

    /**
     * Gets the value of the businessEntityTaskName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityTaskName() {
        return businessEntityTaskName;
    }

    /**
     * Sets the value of the businessEntityTaskName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityTaskName(String value) {
        this.businessEntityTaskName = value;
    }

    /**
     * Gets the value of the taskDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets the value of the taskDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskDescription(String value) {
        this.taskDescription = value;
    }

    /**
     * Gets the value of the taskReadingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaskReadingIndicator() {
        return taskReadingIndicator;
    }

    /**
     * Sets the value of the taskReadingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaskReadingIndicator(Boolean value) {
        this.taskReadingIndicator = value;
    }

    /**
     * Gets the value of the taskReadingValidationRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskReadingValidationRule() {
        return taskReadingValidationRule;
    }

    /**
     * Sets the value of the taskReadingValidationRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskReadingValidationRule(String value) {
        this.taskReadingValidationRule = value;
    }

    /**
     * Gets the value of the criticalTaskIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCriticalTaskIndicator() {
        return criticalTaskIndicator;
    }

    /**
     * Sets the value of the criticalTaskIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCriticalTaskIndicator(Boolean value) {
        this.criticalTaskIndicator = value;
    }

    /**
     * Gets the value of the taskEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTaskEffectiveDate() {
        return taskEffectiveDate;
    }

    /**
     * Sets the value of the taskEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskEffectiveDate(Date value) {
        this.taskEffectiveDate = value;
    }

    /**
     * Gets the value of the taskTerminationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTaskTerminationDate() {
        return taskTerminationDate;
    }

    /**
     * Sets the value of the taskTerminationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskTerminationDate(Date value) {
        this.taskTerminationDate = value;
    }

}
