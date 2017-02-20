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
 * Stores Business Entity specific information on Physicians
 * 
 * <p>Java class for Provider complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Provider">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Patient_Intake" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Claim_Line" type="{}Claim_Line" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Provider_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Provider_ID" use="required" type="{}ID" />
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
 *       &lt;attribute name="Provider_First_Name" type="{}Name" />
 *       &lt;attribute name="Provider_Middle_Name" type="{}Name" />
 *       &lt;attribute name="Provider_Last_Name" type="{}Name" />
 *       &lt;attribute name="Provider_Organization_Name" type="{}Organization_Name" />
 *       &lt;attribute name="Provider_Unique_Physician_Identification_Number" type="{}Unique_Physician_Identification_Number" />
 *       &lt;attribute name="Provider_National_Provider_Identifier" type="{}National_Provider_Identifier" />
 *       &lt;attribute name="Provider_License_Number" type="{}ID" />
 *       &lt;attribute name="Provider_License_State" type="{}State_Code" />
 *       &lt;attribute name="Provider_License_Expiration" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Provider_Address_Line_1" type="{}Address_Line" />
 *       &lt;attribute name="Provider_Address_Line_2" type="{}Address_Line" />
 *       &lt;attribute name="Provider_City" type="{}City" />
 *       &lt;attribute name="Provider_State" type="{}State_Code" />
 *       &lt;attribute name="Provider_Postal_Code" type="{}Postal_Code" />
 *       &lt;attribute name="Provider_Zip4" type="{}Zip4" />
 *       &lt;attribute name="Provider_Phone" type="{}Phone" />
 *       &lt;attribute name="Provider_Fax" type="{}Phone" />
 *       &lt;attribute name="Provider_Contact_Name" type="{}Name" />
 *       &lt;attribute name="Provider_Signature_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Provider_Taxonomy_Code" type="{}Taxonomy_Code" />
 *       &lt;attribute name="Medicare_Assignment_Code" type="{}Code" />
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
	public class Provider
	{

			[DataMember]
			public List<PatientIntake> PatientIntakeCollection { get; set; }

			[DataMember]
			public List<ClaimLine> ClaimLineCollection { get; set; }

			[DataMember(IsRequired = true)]
			public long ProviderSK { get; set; }

			[DataMember(IsRequired = true)]
			public String ProviderID { get; set; }

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

			[DataMember]
			public String ProviderFirstName { get; set; }

			[DataMember]
			public String ProviderMiddleName { get; set; }

			[DataMember]
			public String ProviderLastName { get; set; }

			[DataMember]
			public String ProviderOrganizationName { get; set; }

			[DataMember]
			public String ProviderUniquePhysicianIdentificationNumber { get; set; }

			[DataMember]
			public String ProviderNationalProviderIdentifier { get; set; }

			[DataMember]
			public String ProviderLicenseNumber { get; set; }

			[DataMember]
			public StateCode ProviderLicenseState { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> ProviderLicenseExpiration { get; set; }

			[DataMember(Name = "ProviderLicenseExpiration")]
			public string ProviderLicenseExpirationStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(ProviderLicenseExpiration);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						ProviderLicenseExpiration = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember]
			public String ProviderAddressLine1 { get; set; }

			[DataMember]
			public String ProviderAddressLine2 { get; set; }

			[DataMember]
			public String ProviderCity { get; set; }

			[DataMember]
			public StateCode ProviderState { get; set; }

			[DataMember]
			public String ProviderPostalCode { get; set; }

			[DataMember]
			public String ProviderZip4 { get; set; }

			[DataMember]
			public String ProviderPhone { get; set; }

			[DataMember]
			public String ProviderFax { get; set; }

			[DataMember]
			public String ProviderContactName { get; set; }

			[DataMember]
			public bool? ProviderSignatureIndicator { get; set; }

			[DataMember]
			public String ProviderTaxonomyCode { get; set; }

			[DataMember]
			public String MedicareAssignmentCode { get; set; }


	}
}
