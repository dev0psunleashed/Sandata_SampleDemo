package com.sandata.lab.rest.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by khangle on 09/03/2016.
 */
public class UnappliedBalanceRangeLookup extends EnumLookup {

    @SerializedName("from")
    private BigDecimal balanceFrom;

    @SerializedName("to")
    private BigDecimal balanceTo;

    /**
     * Constructor
     *
     * @param id
     * @param type
     * @param balanceFrom
     * @param balanceTo
     */
    public UnappliedBalanceRangeLookup(int id, String type, BigDecimal balanceFrom, BigDecimal balanceTo) {
        this.id = id;
        this.type = type;
        this.balanceFrom = balanceFrom;
        this.balanceTo = balanceTo;

        recordCreated = new Date();
        recordUpdated = new Date();
    }

    public BigDecimal getBalanceFrom() {
        return balanceFrom;
    }

    public void setBalanceFrom(BigDecimal balanceFrom) {
        this.balanceFrom = balanceFrom;
    }

    public BigDecimal getBalanceTo() {
        return balanceTo;
    }

    public void setBalanceTo(BigDecimal balanceTo) {
        this.balanceTo = balanceTo;
    }
}
