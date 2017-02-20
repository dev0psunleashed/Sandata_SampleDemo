package com.sandata.lab.data.model.dl.model.extended.ar;

import com.google.gson.annotations.SerializedName;

/**
 * BAR-246:
 *
 * Ari's Description:
 *
 * 1. 0-20
 * 2. 21-50
 * 3. 51-100
 * 4. 101-200
 * 5. 201-300
 * 6. 301+
 *
 * <p/>
 *
 * @author David Rutgos
 */
public enum OpenBalance {

    @SerializedName("0-20")
    ZERO_TO_TWENTY("0-20"),
    @SerializedName("21-50")
    TWENTY_ONE_TO_FIFTY("21-50"),
    @SerializedName("51-100")
    FIFTY_ONE_TO_ONE_HUNDRED("51-100"),
    @SerializedName("101-200")
    ONE_HUNDRED_ONE_TO_TWO_HUNDRED("101-200"),
    @SerializedName("201-300")
    TWO_HUNDRED_ONE_TO_THREE_HUNDRED("201-300"),
    @SerializedName("301+")
    THREE_HUNDRED_PLUS("301+");

    private final String value;

    OpenBalance(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OpenBalance fromValue(String v) {
        for (OpenBalance c: OpenBalance.values()) {
            if (c.value.equalsIgnoreCase(v)) {
                return c;
            }
        }
        return valueOf(v);
    }
}
