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
 * Defines a many-to-many relationship between the Authorization and Service entities.
 * 
 * <p>Java class for Authorization_Service complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Authorization_Service">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Authorization_Service_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Authorization_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Service_Name" use="required" type="{}Service_Name" />
 *       &lt;attribute name="Billing_Code" type="{}Billing_Code" />
 *       &lt;attribute name="Rate_Type_Name" type="{}Rate_Type_Name" />
 *       &lt;attribute name="Rate_Qualifier_Code" type="{}Rate_Qualifier_Code" />
 *       &lt;attribute name="Authorization_Service_Rate_Amount" type="{}Money" />
 *       &lt;attribute name="Authorization_Service_Start_Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Authorization_Service_End_Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Authorization_Limit_Type_Name" type="{}Authorization_Limit_Type_Name" />
 *       &lt;attribute name="Authorization_Service_Unit_Name" use="required" type="{}Authorization_Service_Unit_Name" />
 *       &lt;attribute name="Authorization_Service_Limit" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_1" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_1" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_1" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_2" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_2" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_2" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_3" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_3" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_3" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_4" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_4" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_4" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_5" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_5" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_5" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_6" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_6" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_6" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_Day_7" type="{}Authorization_Limit" />
 *       &lt;attribute name="Authorization_Service_Limit_Start_Time_Day_7" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Authorization_Service_Limit_End_Time_Day_7" type="{}Authorization_Limit_Time" />
 *       &lt;attribute name="Payer_ID" type="{}ID" />
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
	public class AuthorizationService
	{

			[DataMember(IsRequired = true)]
			public long AuthorizationServiceSK { get; set; }

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

			[DataMember(IsRequired = true)]
			public long ChangeVersionID { get; set; }

			[DataMember(IsRequired = true)]
			public long AuthorizationSK { get; set; }

			[DataMember(IsRequired = true)]
			public String BusinessEntityID { get; set; }

			[DataMember(IsRequired = true)]
			public ServiceName ServiceName { get; set; }

			[DataMember]
			public String BillingCode { get; set; }

			[DataMember]
			public String RateTypeName { get; set; }

			[DataMember]
			public RateQualifierCode RateQualifierCode { get; set; }

			[DataMember]
			public decimal? AuthorizationServiceRateAmount { get; set; }

			[IgnoreDataMember]
			public DateTime AuthorizationServiceStartDate { get; set; }

			[DataMember(Name = "AuthorizationServiceStartDate", IsRequired = true)]
			public string AuthorizationServiceStartDateStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceStartDate);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceStartDate = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public DateTime AuthorizationServiceEndDate { get; set; }

			[DataMember(Name = "AuthorizationServiceEndDate", IsRequired = true)]
			public string AuthorizationServiceEndDateStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceEndDate);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceEndDate = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public AuthorizationLimitTypeName AuthorizationLimitTypeName { get; set; }

			[DataMember(IsRequired = true)]
			public AuthorizationServiceUnitName AuthorizationServiceUnitName { get; set; }

			[DataMember]
			public decimal? AuthorizationServiceLimit { get; set; }

			[DataMember]
			public decimal? AuthorizationServiceLimitDay1 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay1 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay1")]
			public string AuthorizationServiceLimitStartTimeDay1Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay1);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay1 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay1 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay1")]
			public string AuthorizationServiceLimitEndTimeDay1Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay1);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay1 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public decimal? AuthorizationServiceLimitDay2 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay2 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay2")]
			public string AuthorizationServiceLimitStartTimeDay2Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay2);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay2 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay2 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay2")]
			public string AuthorizationServiceLimitEndTimeDay2Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay2);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay2 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public decimal? AuthorizationServiceLimitDay3 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay3 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay3")]
			public string AuthorizationServiceLimitStartTimeDay3Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay3);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay3 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay3 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay3")]
			public string AuthorizationServiceLimitEndTimeDay3Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay3);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay3 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public decimal? AuthorizationServiceLimitDay4 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay4 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay4")]
			public string AuthorizationServiceLimitStartTimeDay4Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay4);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay4 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay4 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay4")]
			public string AuthorizationServiceLimitEndTimeDay4Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay4);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay4 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public decimal? AuthorizationServiceLimitDay5 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay5 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay5")]
			public string AuthorizationServiceLimitStartTimeDay5Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay5);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay5 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay5 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay5")]
			public string AuthorizationServiceLimitEndTimeDay5Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay5);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay5 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public decimal? AuthorizationServiceLimitDay6 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay6 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay6")]
			public string AuthorizationServiceLimitStartTimeDay6Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay6);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay6 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay6 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay6")]
			public string AuthorizationServiceLimitEndTimeDay6Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay6);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay6 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public decimal? AuthorizationServiceLimitDay7 { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitStartTimeDay7 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitStartTimeDay7")]
			public string AuthorizationServiceLimitStartTimeDay7Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitStartTimeDay7);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitStartTimeDay7 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[IgnoreDataMember]
			public Nullable<DateTime> AuthorizationServiceLimitEndTimeDay7 { get; set; }

			[DataMember(Name = "AuthorizationServiceLimitEndTimeDay7")]
			public string AuthorizationServiceLimitEndTimeDay7Str
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(AuthorizationServiceLimitEndTimeDay7);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						AuthorizationServiceLimitEndTimeDay7 = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public String PayerID { get; set; }


	}
}