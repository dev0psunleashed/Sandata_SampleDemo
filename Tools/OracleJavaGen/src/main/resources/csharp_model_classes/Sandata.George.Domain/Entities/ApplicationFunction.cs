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
 * Identification of functionality to the system
 * 
 * Entity Purpose: The Application Function entity registers functionality within the application. This is intended to be aggregate to a process, page, or other level of functionality that can be decomposed into Functional Elements
 * 
 * 
 * <p>Java class for Application_Function complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Application_Function">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Application_Secure_Element" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Application_Function_Element" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Application_Function_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Application_Module_SK" type="{}Surrogate_Key" />
 *       &lt;attribute name="Function_Name" type="{}Name_Generic" />
 *       &lt;attribute name="Function_Type_Name" type="{}Type_Name" />
 *       &lt;attribute name="Function_Area" type="{}Name_Generic" />
 *       &lt;attribute name="Function_Scope" type="{}Name_Generic" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@OracleMetadata(
        packageName = "PKG_APP",
        insertMethod = "insertAppFun",
        updateMethod = "updateAppFun",
        deleteMethod = "deleteAppFun",
        getMethod = "getAppFun",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AppFunT",
        primaryKeys = {})
namespace Sandata.George.Domain.Entities
{
	[DataContract]
	public class ApplicationFunction
	{




			[DataMember]
			public List<ApplicationSecureElement> ApplicationSecureElementCollection { get; set; }

			[DataMember]
			public List<ApplicationFunctionElement> ApplicationFunctionElementCollection { get; set; }

			[DataMember(IsRequired = true)]
			public = "getAppFunSk", setter = "setAppFunSk", type = "java.math.BigDecimal", index = ApplicationFunctionSK { get; set; }

    protected BigInteger applicationFunctionSK;
			[DataMember(IsRequired = true)]
			public = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = RecordCreateTimestamp { get; set; }

    protected Date recordCreateTimestamp;
			[DataMember(IsRequired = true)]
			public = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = RecordUpdateTimestamp { get; set; }

    protected Date recordUpdateTimestamp;
			[DataMember]
			public = "getAppModSk", setter = "setAppModSk", type = "java.math.BigDecimal", index = ApplicationModuleSK { get; set; }

    protected BigInteger applicationModuleSK;
			[DataMember]
			public = "getFunName", setter = "setFunName", type = "String", index = FunctionName { get; set; }

    protected String functionName;
			[DataMember]
			public = "getFunTypName", setter = "setFunTypName", type = "String", index = FunctionTypeName { get; set; }

    protected String functionTypeName;
			[DataMember]
			public = "getFunArea", setter = "setFunArea", type = "String", index = FunctionArea { get; set; }

    protected String functionArea;
			[DataMember]
			public = "getFunScope", setter = "setFunScope", type = "String", index = FunctionScope { get; set; }

    protected String functionScope;

	}
}
