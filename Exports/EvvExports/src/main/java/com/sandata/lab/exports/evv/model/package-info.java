/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/12/2016
 * Time: 7:21 AM
 */
@javax.xml.bind.annotation.XmlSchema(xmlns = {
        @javax.xml.bind.annotation.XmlNs(prefix = "soap", namespaceURI = "http://schemas.xmlsoap.org/soap/envelope/"),
        @javax.xml.bind.annotation.XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
        @javax.xml.bind.annotation.XmlNs(prefix = "xsd", namespaceURI = "http://www.w3.org/2001/XMLSchema")

},
        namespace = "http://schemas.xmlsoap.org/soap/envelope/",
        elementFormDefault = XmlNsForm.UNQUALIFIED,
        attributeFormDefault = XmlNsForm.UNSET
)
package com.sandata.lab.exports.evv.model;

import javax.xml.bind.annotation.XmlNsForm;

