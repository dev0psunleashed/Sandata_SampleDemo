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
 * Establishes a many-to-many relationship between the Admission Period and Diagnosis Code entities.
 * 
 * <p>Java class for Admission_Period_Diagnosis complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Admission_Period_Diagnosis">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Admission_Period_Diagnosis_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Admission_Period_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="ICD_Diagnosis_Rank" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="ICD_Diagnosis_First_Onset_Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="ICD_Diagnosis_Code" use="required" type="{}ICD_Diagnosis_Code" />
 *       &lt;attribute name="ICD_Diagnosis_Code_Revision_Qualifier" use="required" type="{}ICD_Diagnosis_Code_Revision_Qualifier" />
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
	public class AdmissionPeriodDiagnosis
	{

			[DataMember(IsRequired = true)]
			public long AdmissionPeriodDiagnosisSK { get; set; }

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
			public long AdmissionPeriodSK { get; set; }

			[DataMember(IsRequired = true)]
			public long ICDDiagnosisRank { get; set; }

			[IgnoreDataMember]
			public Nullable<DateTime> ICDDiagnosisFirstOnsetDate { get; set; }

			[DataMember(Name = "ICDDiagnosisFirstOnsetDate")]
			public string ICDDiagnosisFirstOnsetDateStr
			{
				get
				{
					return DateTimeHelper.ToDateTimeString(ICDDiagnosisFirstOnsetDate);
				}
				set
				{
					if (!String.IsNullOrEmpty(value))
					{
						ICDDiagnosisFirstOnsetDate = DateTimeHelper.ToDateTime(value);
					}
				}
			}

			[DataMember(IsRequired = true)]
			public String ICDDiagnosisCode { get; set; }

			[DataMember(IsRequired = true)]
			public ICDDiagnosisCodeRevisionQualifier ICDDiagnosisCodeRevisionQualifier { get; set; }


	}
}
