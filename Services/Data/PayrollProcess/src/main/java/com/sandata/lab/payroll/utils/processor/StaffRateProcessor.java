package com.sandata.lab.payroll.utils.processor;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.RateChangeQualifier;
import com.sandata.lab.payroll.model.StaffPayrollRates;

import java.math.BigDecimal;

/**
 * Apply Staff Rate business rules as per GEOR-3679 "Payroll Hierarchy Breakdown".
 *
 * EPIC: http://jira.sandata.com/browse/GEOR-3679
 *
 * ATTACHMENT: Payroll Structure v2.pdf (5/20/2016 6:57 PM
 *
 * Acceptance Criteria:
 *
 * 1.	As a User, I must be able to view the payroll breakdown structure so that I may see the rate being used for payroll
 * 1.1.	When I view the payroll rate breakdown structure, the hierarchy rates are setup like so, Staff, Contract, and Agency
 * 1.2.	The payroll hierarchy process must be followed to determine the pay rate
 *
 * RULES (Payroll Structure v2.pdf):
 *
 * 1. (Lowest Priority)(Must be defined!!) Agency Baseline Rates: Hourly/$10/Unit - LiveIn/$130/Visit - Mutual/$11/Unit
 *    NOTE: Each agency baseline rate MUST have a detailed rate (Weekend/Holiday/Regular)
 *
 * 2. (Third Priority) Contract Baseline Rate: Hourly/$10/Unit - LiveIn/$130/Visit - Mutual/$11/Unit
 *    NOTE: Each contract baseline rate MUST have a detailed rate (Weekend/Holiday/Regular)
 *
 * 3. (Second Priority) Staff Specific Payroll Rate
 *    NOTE: Staff specific payroll add on/rate MUST be added to the Agency Baseline Payroll Rate.
 *          They MUST also be defined with detailed rate (Weekend/Holiday/Regular)
 *
 * 4. (Highest Priority) Staff to Patient Associated Rate
 *    NOTE: The system must also provide the ability for the Staff to have a specific rate for a specific patient.
 *          Staff associated rate must be added to the Agency baseline payroll rate unless the Staff has the Staff
 *          specific payroll rate. They MUST also be defined with the detailed rate (Weekend/Holiday/Regular)
 *
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffRateProcessor {

    public BigDecimal getStaffRateAmount(StaffPayrollRates staffPayrollRates, SandataLogger logger) throws SandataRuntimeException {

        validate(staffPayrollRates);

        // Lowest Priority
        BigDecimal baselineRate = staffPayrollRates.getBusinessEntityRate().getBusinessEntityRateAmount();

        logger.info(String.format("%s: getStaffRateAmount: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                "[RATE=%.2f]: Set agency baseline rate ...",
                getClass().getName(),
                staffPayrollRates.getBusinessEntityID(),
                staffPayrollRates.getBusinessEntityLocationID(),
                staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                staffPayrollRates.getStaffID(),
                baselineRate
        ));

        // Third Priority
        if (staffPayrollRates.getPayrollRateMatrix() != null) {

            baselineRate = staffPayrollRates.getPayrollRateMatrix().getRateAmount();

            logger.info(String.format("%s: getStaffRateAmount: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                            "[RATE=%.2f]: Set contract [CONTR_ID=%s] baseline rate ...",
                    getClass().getName(),
                    staffPayrollRates.getBusinessEntityID(),
                    staffPayrollRates.getBusinessEntityLocationID(),
                    staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                    staffPayrollRates.getStaffID(),
                    baselineRate,
                    staffPayrollRates.getPayrollRateMatrix().getContractID()
            ));
        }

        // Second Priority
        if (staffPayrollRates.getStaffRate() != null) {

            baselineRate = staffPayrollRates.getStaffRate().getStaffRateAmount();

            logger.info(String.format("%s: getStaffRateAmount: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                            "[RATE=%.2f]: Set staff baseline rate ...",
                    getClass().getName(),
                    staffPayrollRates.getBusinessEntityID(),
                    staffPayrollRates.getBusinessEntityLocationID(),
                    staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                    staffPayrollRates.getStaffID(),
                    baselineRate
            ));
        }

        // Supplemental
        if (staffPayrollRates.getStaffSupplementalRate() != null) {

            RateChangeQualifier rateChangeQualifier = RateChangeQualifier.NEW_RATE;
            if (staffPayrollRates.getStaffSupplementalRate().getStaffSupplementalRateChangeQualifier() == null) {
                logger.warn(String.format("%s: getStaffRateAmount: StaffSupplementalRate: RateChangeQualifier == NULL: DEFAULT: NEW_RATE", getClass().getName()));
            } else {
                rateChangeQualifier = staffPayrollRates.getStaffSupplementalRate().getStaffSupplementalRateChangeQualifier();
            }

            switch (rateChangeQualifier) {
                case DELTA:
                    baselineRate = baselineRate.add(staffPayrollRates.getStaffSupplementalRate().getStaffSupplementalRate());

                    logger.info(String.format("%s: getStaffRateAmount: StaffSupplementalRate: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                                    "[RATE=%.2f]: Set DELTA staff supplemental baseline rate ...",
                            getClass().getName(),
                            staffPayrollRates.getBusinessEntityID(),
                            staffPayrollRates.getBusinessEntityLocationID(),
                            staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                            staffPayrollRates.getStaffID(),
                            baselineRate
                    ));
                    break;

                case NEW_RATE:
                    baselineRate = staffPayrollRates.getStaffSupplementalRate().getStaffSupplementalRate();

                    logger.info(String.format("%s: getStaffRateAmount: StaffSupplementalRate: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                                    "[RATE=%.2f]: Set NEW_RATE staff supplemental baseline rate ...",
                            getClass().getName(),
                            staffPayrollRates.getBusinessEntityID(),
                            staffPayrollRates.getBusinessEntityLocationID(),
                            staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                            staffPayrollRates.getStaffID(),
                            baselineRate
                    ));
                    break;

                default:
                    logger.warn(String.format("%s: getStaffRateAmount: StaffSupplementalRate: UNDEFINED: RateChangeQualifier == %s: " +
                            "DEFAULT: NEW_RATE", getClass().getName(), rateChangeQualifier.value()));

                    baselineRate = staffPayrollRates.getStaffSupplementalRate().getStaffSupplementalRate();

                    logger.info(String.format("%s: getStaffRateAmount: StaffSupplementalRate: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                                    "[RATE=%.2f]: Set NEW_RATE (DEFAULT!) staff supplemental baseline rate ...",
                            getClass().getName(),
                            staffPayrollRates.getBusinessEntityID(),
                            staffPayrollRates.getBusinessEntityLocationID(),
                            staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                            staffPayrollRates.getStaffID(),
                            baselineRate
                    ));
            }
        }

        // Highest Priority
        if (staffPayrollRates.getStaffAssociatedRate() != null) {

            RateChangeQualifier rateChangeQualifier = RateChangeQualifier.NEW_RATE;
            if (staffPayrollRates.getStaffAssociatedRate().getRateChangeQualifier() == null) {
                logger.warn(String.format("%s: getStaffRateAmount: StaffAssociatedRate: RateChangeQualifier == NULL: DEFAULT: NEW_RATE", getClass().getName()));
            } else {
                rateChangeQualifier = staffPayrollRates.getStaffAssociatedRate().getRateChangeQualifier();
            }

            switch (rateChangeQualifier) {
                case DELTA:
                    baselineRate = baselineRate.add(staffPayrollRates.getStaffAssociatedRate().getStaffAssociatedRateAmount());

                    logger.info(String.format("%s: getStaffRateAmount: StaffAssociatedRate: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                                    "[RATE=%.2f]: Set DELTA staff associated baseline rate for [PT_ID=%s] ...",
                            getClass().getName(),
                            staffPayrollRates.getBusinessEntityID(),
                            staffPayrollRates.getBusinessEntityLocationID(),
                            staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                            staffPayrollRates.getStaffID(),
                            baselineRate,
                            staffPayrollRates.getStaffAssociatedRate().getPatientID()
                    ));
                    break;

                case NEW_RATE:
                    baselineRate = staffPayrollRates.getStaffAssociatedRate().getStaffAssociatedRateAmount();

                    logger.info(String.format("%s: getStaffRateAmount: StaffAssociatedRate: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                                    "[RATE=%.2f]: Set NEW_RATE staff associated baseline rate for [PT_ID=%s] ...",
                            getClass().getName(),
                            staffPayrollRates.getBusinessEntityID(),
                            staffPayrollRates.getBusinessEntityLocationID(),
                            staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                            staffPayrollRates.getStaffID(),
                            baselineRate,
                            staffPayrollRates.getStaffAssociatedRate().getPatientID()
                    ));
                    break;

                default:
                    logger.warn(String.format("%s: getStaffRateAmount: StaffAssociatedRate: UNDEFINED: RateChangeQualifier == %s: " +
                                    "DEFAULT: NEW_RATE", getClass().getName(), rateChangeQualifier.value()));

                    baselineRate = staffPayrollRates.getStaffAssociatedRate().getStaffAssociatedRateAmount();

                    logger.info(String.format("%s: getStaffRateAmount: StaffAssociatedRate: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: [STAFF_ID=%s]: " +
                                    "[RATE=%.2f]: Set NEW_RATE (DEFAULT!) staff associated baseline rate for [PT_ID=%s] ...",
                            getClass().getName(),
                            staffPayrollRates.getBusinessEntityID(),
                            staffPayrollRates.getBusinessEntityLocationID(),
                            staffPayrollRates.getBusinessEntityLineOfBusinessID(),
                            staffPayrollRates.getStaffID(),
                            baselineRate,
                            staffPayrollRates.getStaffAssociatedRate().getPatientID()
                    ));

            }
        }

        return baselineRate;
    }

    private void validate(StaffPayrollRates staffPayrollRates) throws SandataRuntimeException {

        if (staffPayrollRates == null) {
            throw new SandataRuntimeException(String.format("%s: validate: StaffPayrollRates == NULL", getClass().getName()));
        }

        if (staffPayrollRates.getBusinessEntityRate() == null) {
            throw new SandataRuntimeException(String.format("%s: [BE_ID=%s]: [BE_LOC_ID=%s]: [BE_LOB_ID=%s]: validate: " +
                    "BusinessEntityRate MUST be defined! BusinessEntityRate == NULL",
                    getClass().getName(),
                    staffPayrollRates.getBusinessEntityID(),
                    staffPayrollRates.getBusinessEntityLocationID(),
                    staffPayrollRates.getBusinessEntityLineOfBusinessID()));
        }
    }
}
