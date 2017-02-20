//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 01:27:57 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.Adapter2;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Lookup table for calendar information.
 * 
 * Note: The entity is Business Entitiy specific.
 * 
 * <p>Java class for Business_Entity_Calendar_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Business_Entity_Calendar_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Holiday_Calendar" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Timesheet_Summary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Schedule_Event" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Timesheet_Detail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Business_Entity_Calendar_Lookup_SK" use="required" type="{}Surrogate_Key" />
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
 *       &lt;attribute name="Calendar_Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Day_Of_Week">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;totalDigits value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Day_In_Year">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;totalDigits value="3"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Weekday_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Weekend_Indicator" type="{}Indicator" />
 *       &lt;attribute name="National_Holiday_Indicator" type="{}Indicator" />
 *       &lt;attribute name="State_Holiday_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Location_Holiday_Indicator" type="{}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Business_Entity_Calendar_Lookup", propOrder = {
    "holidayCalendar",
    "timesheetSummary",
    "scheduleEvent",
    "timesheetDetail"
})
@OracleMetadata(
        packageName = "PKG_AM",
        insertMethod = "insertBeCalendarLkup",
        updateMethod = "updateBeCalendarLkup",
        deleteMethod = "deleteBeCalendarLkup",
        getMethod = "getBeCalendarLkup",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.BeCalendarLkupT",
        primaryKeys = {})
public class BusinessEntityCalendarLookup extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Holiday_Calendar")
    @SerializedName("HolidayCalendar")
    protected List<HolidayCalendar> holidayCalendar;
    @XmlElement(name = "Timesheet_Summary")
    @SerializedName("TimesheetSummary")
    protected List<TimesheetSummary> timesheetSummary;
    @XmlElement(name = "Schedule_Event")
    @SerializedName("ScheduleEvent")
    protected List<ScheduleEvent> scheduleEvent;
    @XmlElement(name = "Timesheet_Detail")
    @SerializedName("TimesheetDetail")
    protected List<TimesheetDetail> timesheetDetail;
    @XmlAttribute(name = "Business_Entity_Calendar_Lookup_SK", required = true)
    @SerializedName("BusinessEntityCalendarLookupSK")
	@Mapping(getter = "getBeCalendarLkupSk", setter = "setBeCalendarLkupSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger businessEntityCalendarLookupSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
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
    @XmlAttribute(name = "Calendar_Date")
    @SerializedName("CalendarDate")
    @XmlJavaTypeAdapter(Adapter2.class)
    @XmlSchemaType(name = "date")
	@Mapping(getter = "getCalendarDate", setter = "setCalendarDate", type = "java.sql.Timestamp", index = 11)
    protected Date calendarDate;
    @XmlAttribute(name = "Day_Of_Week")
    @SerializedName("DayOfWeek")
	@Mapping(getter = "getDayOfWeek", setter = "setDayOfWeek", type = "java.math.BigDecimal", index = 12)
    protected BigDecimal dayOfWeek;
    @XmlAttribute(name = "Day_In_Year")
    @SerializedName("DayInYear")
	@Mapping(getter = "getDayInYear", setter = "setDayInYear", type = "java.math.BigDecimal", index = 13)
    protected BigDecimal dayInYear;
    @XmlAttribute(name = "Weekday_Indicator")
    @SerializedName("WeekdayIndicator")
	@Mapping(getter = "getWeekdayInd", setter = "setWeekdayInd", type = "java.math.BigDecimal", index = 14)
    protected Boolean weekdayIndicator;
    @XmlAttribute(name = "Weekend_Indicator")
    @SerializedName("WeekendIndicator")
	@Mapping(getter = "getWeekendInd", setter = "setWeekendInd", type = "java.math.BigDecimal", index = 15)
    protected Boolean weekendIndicator;
    @XmlAttribute(name = "National_Holiday_Indicator")
    @SerializedName("NationalHolidayIndicator")
	@Mapping(getter = "getNationalHolidayInd", setter = "setNationalHolidayInd", type = "java.math.BigDecimal", index = 16)
    protected Boolean nationalHolidayIndicator;
    @XmlAttribute(name = "State_Holiday_Indicator")
    @SerializedName("StateHolidayIndicator")
	@Mapping(getter = "getStateHolidayInd", setter = "setStateHolidayInd", type = "java.math.BigDecimal", index = 17)
    protected Boolean stateHolidayIndicator;
    @XmlAttribute(name = "Location_Holiday_Indicator")
    @SerializedName("LocationHolidayIndicator")
	@Mapping(getter = "getLocHolidayInd", setter = "setLocHolidayInd", type = "java.math.BigDecimal", index = 18)
    protected Boolean locationHolidayIndicator;

    /**
     * Gets the value of the holidayCalendar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the holidayCalendar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHolidayCalendar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HolidayCalendar }
     * 
     * 
     */
    public List<HolidayCalendar> getHolidayCalendar() {
        if (holidayCalendar == null) {
            holidayCalendar = new ArrayList<HolidayCalendar>();
        }
        return this.holidayCalendar;
    }

    /**
     * Gets the value of the timesheetSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timesheetSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimesheetSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimesheetSummary }
     * 
     * 
     */
    public List<TimesheetSummary> getTimesheetSummary() {
        if (timesheetSummary == null) {
            timesheetSummary = new ArrayList<TimesheetSummary>();
        }
        return this.timesheetSummary;
    }

    /**
     * Gets the value of the scheduleEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduleEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduleEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduleEvent }
     * 
     * 
     */
    public List<ScheduleEvent> getScheduleEvent() {
        if (scheduleEvent == null) {
            scheduleEvent = new ArrayList<ScheduleEvent>();
        }
        return this.scheduleEvent;
    }

    /**
     * Gets the value of the timesheetDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timesheetDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimesheetDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimesheetDetail }
     * 
     * 
     */
    public List<TimesheetDetail> getTimesheetDetail() {
        if (timesheetDetail == null) {
            timesheetDetail = new ArrayList<TimesheetDetail>();
        }
        return this.timesheetDetail;
    }

    /**
     * Gets the value of the businessEntityCalendarLookupSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBusinessEntityCalendarLookupSK() {
        return businessEntityCalendarLookupSK;
    }

    /**
     * Sets the value of the businessEntityCalendarLookupSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBusinessEntityCalendarLookupSK(BigInteger value) {
        this.businessEntityCalendarLookupSK = value;
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
     * Gets the value of the calendarDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCalendarDate() {
        return calendarDate;
    }

    /**
     * Sets the value of the calendarDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarDate(Date value) {
        this.calendarDate = value;
    }

    /**
     * Gets the value of the dayOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the value of the dayOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDayOfWeek(BigDecimal value) {
        this.dayOfWeek = value;
    }

    /**
     * Gets the value of the dayInYear property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDayInYear() {
        return dayInYear;
    }

    /**
     * Sets the value of the dayInYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDayInYear(BigDecimal value) {
        this.dayInYear = value;
    }

    /**
     * Gets the value of the weekdayIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWeekdayIndicator() {
        return weekdayIndicator;
    }

    /**
     * Sets the value of the weekdayIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWeekdayIndicator(Boolean value) {
        this.weekdayIndicator = value;
    }

    /**
     * Gets the value of the weekendIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWeekendIndicator() {
        return weekendIndicator;
    }

    /**
     * Sets the value of the weekendIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWeekendIndicator(Boolean value) {
        this.weekendIndicator = value;
    }

    /**
     * Gets the value of the nationalHolidayIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNationalHolidayIndicator() {
        return nationalHolidayIndicator;
    }

    /**
     * Sets the value of the nationalHolidayIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNationalHolidayIndicator(Boolean value) {
        this.nationalHolidayIndicator = value;
    }

    /**
     * Gets the value of the stateHolidayIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStateHolidayIndicator() {
        return stateHolidayIndicator;
    }

    /**
     * Sets the value of the stateHolidayIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStateHolidayIndicator(Boolean value) {
        this.stateHolidayIndicator = value;
    }

    /**
     * Gets the value of the locationHolidayIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLocationHolidayIndicator() {
        return locationHolidayIndicator;
    }

    /**
     * Sets the value of the locationHolidayIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLocationHolidayIndicator(Boolean value) {
        this.locationHolidayIndicator = value;
    }

}