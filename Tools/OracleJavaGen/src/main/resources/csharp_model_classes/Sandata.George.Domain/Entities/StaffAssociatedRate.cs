//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


using Sandata.George.Common;
using Sandata.George.Common.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;





/**
 * Stores information on the various pay rates that are associated with a Staff member
 * 
 * <p>Java class for Staff_Associated_Rate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Staff_Associated_Rate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Staff_Associated_Rate_SK" use="required" type="{}Surrogate_Key" />
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
 *       &lt;attribute name="Staff_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Patient_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Staff_Associated_Rate_Effective_Date" use="required" type="{}Attribute_Effective_Date" />
 *       &lt;attribute name="Staff_Associated_Rate_Termination_Date" use="required" type="{}Attribute_Termination_Date" />
 *       &lt;attribute name="Service_Name" use="required" type="{}Service_Name" />
 *       &lt;attribute name="Rate_Sub_Type_Name" use="required" type="{}Rate_Sub_Type_Name" />
 *       &lt;attribute name="Rate_Type_Name" use="required" type="{}Rate_Type_Name" />
 *       &lt;attribute name="Rate_Qualifier_Code" use="required" type="{}Rate_Qualifier_Code" />
 *       &lt;attribute name="Rate_Change_Qualifier" use="required" type="{}Rate_Change_Qualifier" />
 *       &lt;attribute name="Staff_Associated_Rate_Amount" use="required" type="{}Money" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
namespace Sandata.George.Domain.Entities
{
	[DataContract]
	public class StaffAssociatedRate
	{

			[DataMember(IsRequired = true)]
			public long StaffAssociatedRateSK { get; set; }

			[IgnoreDataMember]
			public DateTime RecordCreateTimestamp { get; set; }

			[DataMember(Name = "RecordCreateTimestamp", IsRequired = true)]
			public string RecordCreateTimestampStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(RecordCreateTimestamp);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						RecordCreateTimestamp = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public DateTime RecordUpdateTimestamp { get; set; }

			[DataMember(Name = "RecordUpdateTimestamp", IsRequired = true)]
			public string RecordUpdateTimestampStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(RecordUpdateTimestamp);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						RecordUpdateTimestamp = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public DateTime RecordEffectiveTimestamp { get; set; }

			[DataMember(Name = "RecordEffectiveTimestamp", IsRequired = true)]
			public string RecordEffectiveTimestampStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(RecordEffectiveTimestamp);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						RecordEffectiveTimestamp = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public DateTime RecordTerminationTimestamp { get; set; }

			[DataMember(Name = "RecordTerminationTimestamp", IsRequired = true)]
			public string RecordTerminationTimestampStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(RecordTerminationTimestamp);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						RecordTerminationTimestamp = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public String RecordCreatedBy { get; set; }

			[DataMember]
			public String RecordUpdatedBy { get; set; }

			[DataMember]
			public String ChangeReasonMemo { get; set; }

			[DataMember(IsRequired = true)]
			public bool CurrentRecordIndicator { get; set; }

			[DataMember(IsRequired = true)]
			public long ChangeVersionID { get; set; }

			[DataMember(IsRequired = true)]
			public String BusinessEntityID { get; set; }

			[DataMember(IsRequired = true)]
			public String StaffID { get; set; }

			[DataMember(IsRequired = true)]
			public String PatientID { get; set; }

			[IgnoreDataMember]
			public DateTime StaffAssociatedRateEffectiveDate { get; set; }

			[DataMember(Name = "StaffAssociatedRateEffectiveDate", IsRequired = true)]
			public string StaffAssociatedRateEffectiveDateStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(StaffAssociatedRateEffectiveDate);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						StaffAssociatedRateEffectiveDate = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public DateTime StaffAssociatedRateTerminationDate { get; set; }

			[DataMember(Name = "StaffAssociatedRateTerminationDate", IsRequired = true)]
			public string StaffAssociatedRateTerminationDateStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(StaffAssociatedRateTerminationDate);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						StaffAssociatedRateTerminationDate = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember(IsRequired = true)]
			public ServiceName ServiceName { get; set; }

			[DataMember(IsRequired = true)]
			public RateSubTypeName RateSubTypeName { get; set; }

			[DataMember(IsRequired = true)]
			public String RateTypeName { get; set; }

			[DataMember(IsRequired = true)]
			public RateQualifierCode RateQualifierCode { get; set; }

			[DataMember(IsRequired = true)]
			public RateChangeQualifier RateChangeQualifier { get; set; }

			[DataMember(IsRequired = true)]
			public decimal StaffAssociatedRateAmount { get; set; }


	}
}
