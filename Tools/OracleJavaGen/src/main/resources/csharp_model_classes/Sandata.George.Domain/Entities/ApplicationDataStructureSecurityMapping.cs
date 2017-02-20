//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 03:44:22 PM EDT 
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
 * <p>Java class for Application_Data_Structure_Security_Mapping complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Data_Structure_Security_Mapping">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Application_Data_Structure_Security_Mapping_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Data_Security_Compliance_Type_Name" use="required" type="{}Data_Security_Compliance_Type_Name" />
 *       &lt;attribute name="Data_Security_Classification_ID" use="required" type="{}Data_Security_Classification_ID" />
 *       &lt;attribute name="Application_Data_Structure_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Data_Structure_Read_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Data_Structure_Write_Indicator" type="{}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppDataStrucSecMap",
        updateMethod = "updateAppDataStrucSecMap",
        deleteMethod = "deleteAppDataStrucSecMap",
        getMethod = "getAppDataStrucSecMap",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppDataStrucSecMapT",
        primaryKeys = {})
namespace Sandata.George.Domain.Entities
{
	[DataContract]
	public class ApplicationDataStructureSecurityMapping
	{




			[DataMember(IsRequired = true)]
			public = "getAppDataStrucSecMapSk", setter = "setAppDataStrucSecMapSk", type = "java.math.BigDecimal", index = ApplicationDataStructureSecurityMappingSK { get; set; }

    protected BigInteger applicationDataStructureSecurityMappingSK;
			[DataMember(IsRequired = true)]
			public = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = RecordCreateTimestamp { get; set; }

    protected Date recordCreateTimestamp;
			[DataMember(IsRequired = true)]
			public = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = RecordUpdateTimestamp { get; set; }

    protected Date recordUpdateTimestamp;
			[DataMember(IsRequired = true)]
			public = "getDataSecCompTypName", setter = "setDataSecCompTypName", type = "String", index = DataSecurityComplianceTypeName { get; set; }

    protected DataSecurityComplianceTypeName dataSecurityComplianceTypeName;
			[DataMember(IsRequired = true)]
			public = "getDataSecClasId", setter = "setDataSecClasId", type = "String", index = DataSecurityClassificationID { get; set; }

    protected String dataSecurityClassificationID;
			[DataMember(IsRequired = true)]
			public = "getAppDataStrucSk", setter = "setAppDataStrucSk", type = "java.math.BigDecimal", index = ApplicationDataStructureSK { get; set; }

    protected BigInteger applicationDataStructureSK;
			[DataMember]
			public = "getDataStrucReadInd", setter = "setDataStrucReadInd", type = "java.math.BigDecimal", index = DataStructureReadIndicator { get; set; }

    protected Boolean dataStructureReadIndicator;
			[DataMember]
			public = "getDataStrucWriteInd", setter = "setDataStrucWriteInd", type = "java.math.BigDecimal", index = DataStructureWriteIndicator { get; set; }

    protected Boolean dataStructureWriteIndicator;

	}
}
