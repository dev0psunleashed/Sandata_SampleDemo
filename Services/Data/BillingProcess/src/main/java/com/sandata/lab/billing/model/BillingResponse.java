package com.sandata.lab.billing.model;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Billing;
import com.sandata.lab.data.model.dl.model.BillingDetail;

import java.math.BigDecimal;
import java.util.Date;

public class BillingResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Billing billing;
    private BillingDetail billingDetail;

    private BigDecimal actualVisitSeconds;
    private BigDecimal adjustedVisitSeconds;

    private Date actualVisitStartDate;
    private Date actualVisitEndDate;
    private Date adjustedVisitStartDate;
    private Date adjustedVisitEndDate;

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }

    public BigDecimal getActualVisitSeconds() {
        return actualVisitSeconds;
    }

    public void setActualVisitSeconds(BigDecimal actualVisitSeconds) {
        this.actualVisitSeconds = actualVisitSeconds;
    }

    public BigDecimal getAdjustedVisitSeconds() {
        return adjustedVisitSeconds;
    }

    public void setAdjustedVisitSeconds(BigDecimal adjustedVisitSeconds) {
        this.adjustedVisitSeconds = adjustedVisitSeconds;
    }

    public Date getActualVisitStartDate() {
        return actualVisitStartDate;
    }

    public void setActualVisitStartDate(Date actualVisitStartDate) {
        this.actualVisitStartDate = actualVisitStartDate;
    }

    public Date getActualVisitEndDate() {
        return actualVisitEndDate;
    }

    public void setActualVisitEndDate(Date actualVisitEndDate) {
        this.actualVisitEndDate = actualVisitEndDate;
    }

    public Date getAdjustedVisitStartDate() {
        return adjustedVisitStartDate;
    }

    public void setAdjustedVisitStartDate(Date adjustedVisitStartDate) {
        this.adjustedVisitStartDate = adjustedVisitStartDate;
    }

    public Date getAdjustedVisitEndDate() {
        return adjustedVisitEndDate;
    }

    public void setAdjustedVisitEndDate(Date adjustedVisitEndDate) {
        this.adjustedVisitEndDate = adjustedVisitEndDate;
    }

    public void initBillingDates(SandataLogger logger) throws SandataRuntimeException {

        logger.trace("START: initBillingDates");

        if (billing == null) {
            String errMsg = String.format("%s: ERROR: initBillingDates: billing == null", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        if (adjustedVisitStartDate != null && adjustedVisitEndDate != null) {

            logger.info("initBillingDates: Using adjusted visit dates!");

            billing.setBillingStartDate(adjustedVisitStartDate);
            billing.setBillingEndDate(adjustedVisitEndDate);
        }
        else if (actualVisitStartDate != null && actualVisitEndDate != null) {

            logger.info("initBillingDates: Using actual visit dates!");

            billing.setBillingStartDate(actualVisitStartDate);
            billing.setBillingEndDate(actualVisitEndDate);
        }
        else {
            String errMsg = String.format("%s: ERROR: initBillingDates: Can not set billing start/end dates!", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        logger.trace("END: initBillingDates");
    }

    public void initBillingTotalUnits(SandataLogger logger) {

        logger.trace("START: initBillingTotalUnits");

        if (billing == null) {
            String errMsg = String.format("%s: ERROR: initBillingTotalUnits: billing == null", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        if (billingDetail == null) {
            String errMsg = String.format("%s: ERROR: initBillingTotalUnits: billingDetail == null", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        if (adjustedVisitSeconds != null && adjustedVisitSeconds.longValue() > 0) {

            logger.info(String.format("initBillingTotalUnits: Using adjusted hours in seconds: [%d]", adjustedVisitSeconds.longValue()));

            billing.setBillingTotalUnit(adjustedVisitSeconds);
            billingDetail.setBillingDetailTotalUnit(adjustedVisitSeconds);
        }
        else if (actualVisitSeconds != null && actualVisitSeconds.longValue() > 0) {

            logger.info(String.format("initBillingTotalUnits: Using actual hours in seconds: [%d]", actualVisitSeconds.longValue()));

            billing.setBillingTotalUnit(actualVisitSeconds);
            billingDetail.setBillingDetailTotalUnit(actualVisitSeconds);
        }
        else {
            String errMsg = String.format("%s: ERROR: initBillingTotalUnits: Can not set billing total units because actual/adjusted hours is null or zero!", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        logger.trace("END: initBillingTotalUnits");
    }

    public void initBillingTotalAmount(SandataLogger logger) {

        logger.trace("START: initBillingTotalAmount");

        if (billing == null) {
            String errMsg = String.format("%s: ERROR: initBillingTotalAmount: billing == null", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        if (billingDetail == null) {
            String errMsg = String.format("%s: ERROR: initBillingTotalAmount: billingDetail == null", getClass().getName());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg);
        }

        if (billingDetail.getBillingDetailRateAmount() == null) {

            String errMsg = String.format("%s: ERROR: initBillingTotalAmount: billingDetail.getBillingDetailRateAmount() == null: SET FAKE: 198.99", getClass().getName());
            logger.error(errMsg);
            billing.setBillingTotalAmount(BigDecimal.valueOf(198.99));
        }
        else {

            float hours = Math.round(billing.getBillingTotalUnit().longValue() / 3600f);

            logger.trace(String.format("initBillingTotalAmount: [HOURS=%.2f]", hours));

            BigDecimal billingTotalAmout = BigDecimal.valueOf(hours * billingDetail.getBillingDetailRateAmount().doubleValue());
            billing.setBillingTotalAmount(billingTotalAmout);
            billingDetail.setBillingDetailTotalAmount(billingTotalAmout);

            logger.trace(String.format("initBillingTotalAmount: [BillingTotalAmount=%.2f]", billing.getBillingTotalAmount()));
        }

        logger.trace("END: initBillingTotalAmount");
    }
}
