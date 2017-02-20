package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>InvoiceFindType</p>
 * <p>Date: 7/27/2016</p>
 * <p>Time: 3:00 PM</p>
 *
 * @author jasonscott
 */
@XmlType(name = "Invoice_Find_Type")
@XmlEnum
public enum InvoiceFindType {

    @SerializedName("Billable")
    BILLABLE("Billable"),
    @SerializedName("Unbillable")
    UNBILLABLE("Unbillable"),
    @SerializedName("Deleted")
    DELETED("Deleted");

    private final String value;

    InvoiceFindType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvoiceFindType fromValue(String v) {
        for (InvoiceFindType c: InvoiceFindType.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }

        return valueOf(v);
    }
}
