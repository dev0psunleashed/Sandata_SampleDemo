package com.sandata.lab.exports.evv.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 7:33 AM
 */
/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ComplianceElement " type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ComplianceValue " type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "complianceElement0020",
        "complianceValue0020",
        "status"
})
public class Compliance {
    @XmlElement(name = "ComplianceElement ")
    protected String complianceElement0020;
    @XmlElement(name = "ComplianceValue ", required = true)
    protected String complianceValue0020;
    @XmlElement(name = "Status")
    protected String status;

    /**
     * Gets the value of the complianceElement0020 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getComplianceElement_0020() {
        return complianceElement0020;
    }

    /**
     * Sets the value of the complianceElement0020 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setComplianceElement_0020(String value) {
        this.complianceElement0020 = value;
    }

    /**
     * Gets the value of the complianceValue0020 property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getComplianceValue_0020() {
        return complianceValue0020;
    }

    /**
     * Sets the value of the complianceValue0020 property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setComplianceValue_0020(String value) {
        this.complianceValue0020 = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
