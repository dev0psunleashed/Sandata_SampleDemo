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
 * The lookup entitiy that contains the list of all the attributes to describe report request characteristics. 
 * 
 * <p>Java class for Report_Request_Attribute_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Report_Request_Attribute_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Report_Request_Attribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Report_Request_Attribute_Lookup_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Report_Request_Attribute_Key_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *             &lt;enumeration value="PATIENT_NAME"/>
 *             &lt;enumeration value="PATIENT_ID"/>
 *             &lt;enumeration value="STAFF_NAME"/>
 *             &lt;enumeration value="STAFF_ID"/>
 *             &lt;enumeration value="PAYER_TYPE"/>
 *             &lt;enumeration value="PAYER"/>
 *             &lt;enumeration value="CONTRACT"/>
 *             &lt;enumeration value="LOCATION"/>
 *             &lt;enumeration value="COORDINATOR"/>
 *             &lt;enumeration value="SERVICE"/>
 *             &lt;enumeration value="PROGRAM"/>
 *             &lt;enumeration value="CITY"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Report_Request_Attribute_Key_Description" type="{}Description_Long" />
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
	public class ReportRequestAttributeLookup
	{




			[DataMember]
			public List<ReportRequestAttribute> ReportRequestAttributePropertyCollection { get; set; }

			[DataMember(IsRequired = true)]
			public long ReportRequestAttributeLookupSK { get; set; }

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

			[DataMember]
			public String ReportRequestAttributeKeyName { get; set; }

			[DataMember]
			public String ReportRequestAttributeKeyDescription { get; set; }


	}
}