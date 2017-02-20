package com.sandata.lab.eligibility.model.response;

import java.math.BigDecimal;

import com.sandata.lab.eligibility.model.response.enums.HSDCalendarPatternCodeType;
import com.sandata.lab.eligibility.model.response.enums.HSDMeasurementCodeType;
import com.sandata.lab.eligibility.model.response.enums.HSDPatternTimeCodeType;
import com.sandata.lab.eligibility.model.response.enums.HSDQuantityCodeType;
import com.sandata.lab.eligibility.model.response.enums.HSDTimePeriodCodeType;

public class BenefitHealthCareServicesDeliveryRestriction {
    private long id;
    private long benefitId;

    private HSDQuantityCodeType quantityType;
    private BigDecimal quantity;
    private HSDMeasurementCodeType measurementBasis;
    private BigDecimal selectionModulus;
    private HSDTimePeriodCodeType timePeriod;
    private Long periodCount;
    private HSDCalendarPatternCodeType calendarPatternType;
    private HSDPatternTimeCodeType timePatternType;

    private Benefit benefit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(long benefitId) {
        this.benefitId = benefitId;
    }

    public HSDQuantityCodeType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(HSDQuantityCodeType quantityType) {
        this.quantityType = quantityType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public HSDMeasurementCodeType getMeasurementBasis() {
        return measurementBasis;
    }

    public void setMeasurementBasis(HSDMeasurementCodeType measurementBasis) {
        this.measurementBasis = measurementBasis;
    }

    public BigDecimal getSelectionModulus() {
        return selectionModulus;
    }

    public void setSelectionModulus(BigDecimal selectionModulus) {
        this.selectionModulus = selectionModulus;
    }

    public HSDTimePeriodCodeType getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(HSDTimePeriodCodeType timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Long getPeriodCount() {
        return periodCount;
    }

    public void setPeriodCount(Long periodCount) {
        this.periodCount = periodCount;
    }

    public HSDCalendarPatternCodeType getCalendarPatternType() {
        return calendarPatternType;
    }

    public void setCalendarPatternType(HSDCalendarPatternCodeType calendarPatternType) {
        this.calendarPatternType = calendarPatternType;
    }

    public HSDPatternTimeCodeType getTimePatternType() {
        return timePatternType;
    }

    public void setTimePatternType(HSDPatternTimeCodeType timePatternType) {
        this.timePatternType = timePatternType;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }
}