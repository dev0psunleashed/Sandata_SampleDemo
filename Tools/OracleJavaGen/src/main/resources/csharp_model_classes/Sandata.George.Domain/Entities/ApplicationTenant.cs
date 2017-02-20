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
 * Identification of the tenancy within the system.
 * 
 * Entity Purpose: Identifies tenants within the system and/or application.
 * 
 * 
 * 
 * <p>Java class for Application_Tenant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Tenant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Application_User_Role" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Base_Application_Function" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Secure_Element" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Data_Content" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Privilege" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Role" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Tenant_Key_Configuration" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Tenant_Business_Entity_Crosswalk" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Application_Tenant_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Tenant_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Tenant_Type_Name" type="{}Type_Name" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppTenant",
        updateMethod = "updateAppTenant",
        deleteMethod = "deleteAppTenant",
        getMethod = "getAppTenant",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppTenantT",
        primaryKeys = {})
namespace Sandata.George.Domain.Entities
{
	[DataContract]
	public class ApplicationTenant
	{




			[DataMember]
			public List<ApplicationUserRole> ApplicationUserRoleCollection { get; set; }

			[DataMember]
			public List<BaseApplicationFunction> BaseApplicationFunctionCollection { get; set; }

			[DataMember]
			public List<ApplicationSecureElement> ApplicationSecureElementCollection { get; set; }

			[DataMember]
			public List<ApplicationDataContent> ApplicationDataContentCollection { get; set; }

			[DataMember]
			public List<ApplicationPrivilege> ApplicationPrivilegeCollection { get; set; }

			[DataMember]
			public List<ApplicationRole> ApplicationRoleCollection { get; set; }

			[DataMember]
			public List<ApplicationTenantKeyConfiguration> ApplicationTenantKeyConfigurationCollection { get; set; }

			[DataMember]
			public List<ApplicationTenantBusinessEntityCrosswalk> ApplicationTenantBusinessEntityCrosswalkCollection { get; set; }

			[DataMember(IsRequired = true)]
			public = "getAppTenantSk", setter = "setAppTenantSk", type = "java.math.BigDecimal", index = ApplicationTenantSK { get; set; }

    protected BigInteger applicationTenantSK;
			[DataMember(IsRequired = true)]
			public = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = RecordCreateTimestamp { get; set; }

    protected Date recordCreateTimestamp;
			[DataMember(IsRequired = true)]
			public = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = RecordUpdateTimestamp { get; set; }

    protected Date recordUpdateTimestamp;
			[DataMember]
			public = "getTenantName", setter = "setTenantName", type = "String", index = TenantName { get; set; }

    protected String tenantName;
			[DataMember]
			public = "getTenantTypName", setter = "setTenantTypName", type = "String", index = TenantTypeName { get; set; }

    protected String tenantTypeName;

	}
}
