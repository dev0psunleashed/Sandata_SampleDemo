package com.sandata.lab.data.model.dl.model.find.enums;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "Invoice_Find_Type")
@XmlEnum
public enum ARPostStatus {

    @SerializedName("Posted")
    POSTED("POSTED"),
    @SerializedName("Open")
    OPEN("Open");

    private final String value;

    ARPostStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ARPostStatus fromValue(String v) {
        for (ARPostStatus c: ARPostStatus.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }

        return valueOf(v);
    }
}
