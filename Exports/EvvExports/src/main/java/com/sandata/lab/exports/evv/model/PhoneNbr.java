package com.sandata.lab.exports.evv.model;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 7:38 AM
 */

import javax.xml.bind.annotation.*;

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
 *         &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhoneNbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PHONE-ACCT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "recipientID",
        "phoneNbr",
        "phoneacct",
        "vendorCode"
})
public class PhoneNbr {

    @XmlElement(name = "RecipientID", required = true)
    protected String recipientID;
    @XmlElement(name = "PhoneNbr", required = true)
    protected String phoneNbr;
    @XmlElement(name = "PHONE-ACCT", required = true)
    protected String phoneacct;
    @XmlElement(name = "VendorCode")
    protected String vendorCode;
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "rowOrder")
    private String rowOrder;

    /**
     * Gets the value of the recipientID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecipientID() {
        return recipientID;
    }

    /**
     * Sets the value of the recipientID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecipientID(String value) {
        this.recipientID = value;
    }

    /**
     * Gets the value of the phoneNbr property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPhoneNbr() {
        return phoneNbr;
    }

    /**
     * Sets the value of the phoneNbr property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPhoneNbr(String value) {
        this.phoneNbr = value;
    }

    /**
     * Gets the value of the phoneacct property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPHONEACCT() {
        return phoneacct;
    }

    /**
     * Sets the value of the phoneacct property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPHONEACCT(String value) {
        this.phoneacct = value;
    }

    /**
     * Gets the value of the vendorCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * Sets the value of the vendorCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVendorCode(String value) {
        this.vendorCode = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowOrder() {
        return rowOrder;
    }

    public void setRowOrder(String rowOrder) {
        this.rowOrder = rowOrder;
    }
}


