//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.27 at 10:25:43 PM EDT 
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
 * <p>Java class for Report_Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Report_Request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Report_Request_Attribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Report_Request_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Report_ID" use="required" type="{}ID" />
 *       &lt;attribute name="User_Globally_Unique_Identifier" type="{}Computing_Globally_Unique_Identifier" />
 *       &lt;attribute name="Application_Session_SK" type="{}Surrogate_Key" />
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
	public class ReportRequest
	{




			[DataMember]
			public List<ReportRequestAttribute> ReportRequestAttributeCollection { get; set; }

			[DataMember(IsRequired = true)]
			public long ReportRequestSK { get; set; }

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
			public String ReportID { get; set; }

			[DataMember]
			public String UserGloballyUniqueIdentifier { get; set; }

			[DataMember]
			public long? ApplicationSessionSK { get; set; }


	}
}
