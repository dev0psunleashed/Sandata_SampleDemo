package com.sandata.lab.imports.payroll.impl;


import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.imports.payroll.model.Check;
import com.sandata.lab.imports.payroll.model.Deduction;
import com.sandata.lab.imports.payroll.model.Earning;
import com.sandata.lab.imports.payroll.model.Tax;
import org.apache.camel.Exchange;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PRInputTransformer {

    /**
     * @param exchange
     */
    public void checkToPRInput(Exchange exchange) {

        try {

            List<Check> checks = (ArrayList<Check>) exchange.getIn().getBody();
            String businessEntityID = (String) exchange.getIn().getHeader("bsnEntID");
            String staffID = (String) exchange.getIn().getHeader("staffID");
            String interfaceID = (String) exchange.getIn().getHeader("interfaceID");

            List<PayrollInput> payrollInputs = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();

            for (Check check : checks) {
                PayrollInput payrollInput = new PayrollInput();

                payrollInput.setBusinessEntityID(businessEntityID);
                payrollInput.setInterfaceID(interfaceID);
                payrollInput.setStaffID(staffID);

                payrollInput.setPayrollInputID(java.util.UUID.randomUUID().toString());
                payrollInput.setCheckDate(check.getCheckDate());
                payrollInput.setCheckNumber(check.getCheckNumber());
                payrollInput.setElectronicFundsTransferTransactionIdentifier(check.getVoucherNumber());
                payrollInput.setGrossPayAmount(check.getGrossAmount());
                payrollInput.setNetPayAmount(check.getNetAmount());
                payrollInput.setWeekEndDate(check.getPeriodEnd());
                payrollInput.setRecordCreateTimestamp(date);
                payrollInput.setRecordUpdateTimestamp(date);
                payrollInput.setRecordEffectiveTimestamp(date);
                payrollInput.setChangeVersionID(new BigInteger("1"));

                try {

                    Date termDate = sdf.parse("12/31/9999");
                    payrollInput.setRecordTerminationTimestamp(termDate);
                } catch (java.lang.Exception e) {
                    e.printStackTrace();
                }

                payrollInput.setCurrentRecordIndicator(true);
                payrollInput.setRecordCreatedBy("Payroll Import");
                payrollInput.setRecordUpdatedBy("Payroll Import");
                payrollInputs.add(payrollInput);

                if (check.getDeductions() != null) {

                    List<PayrollInputDeductionDetail> payrollInputDeductionDetails = new ArrayList<>();

                    for (Deduction deduction : check.getDeductions()) {

                        PayrollInputDeductionDetail payrollInputDeductionDetail = new PayrollInputDeductionDetail();
                        payrollInputDeductionDetail.setRecordCreateTimestamp(date);
                        payrollInputDeductionDetail.setRecordUpdateTimestamp(date);
                        payrollInputDeductionDetail.setChangeVersionID(new BigInteger("1"));
                        payrollInputDeductionDetail.setBusinessEntityID(businessEntityID);
                        payrollInputDeductionDetail.setDeductionAmount(deduction.getRateAmount());
                        payrollInputDeductionDetail.setDeductionCode(deduction.getRateType());

                        payrollInputDeductionDetails.add(payrollInputDeductionDetail);
                    }

                    payrollInput.getPayrollInputDeductionDetail().addAll(payrollInputDeductionDetails);
                }

                if (check.getEarnings() != null) {

                    List<PayrollInputEarningDetail> payrollInputEarningDetails = new ArrayList<>();

                    for (Earning earning : check.getEarnings()) {

                        PayrollInputEarningDetail payrollInputEarningDetail = new PayrollInputEarningDetail();
                        payrollInputEarningDetail.setRecordCreateTimestamp(date);
                        payrollInputEarningDetail.setRecordUpdateTimestamp(date);
                        payrollInputEarningDetail.setBusinessEntityID(businessEntityID);

                        payrollInputEarningDetail.setChangeVersionID(new BigInteger("1"));
                        payrollInputEarningDetail.setEarningCode(earning.getRateType());
                        payrollInputEarningDetail.setEarningAmount(earning.getRateAmount());
                        payrollInputEarningDetail.setDateOfService(earning.getDateOfService());

                        try {
                            RateTypeName rateTypeName = RateTypeName.fromValue(earning.getRate());

                            payrollInputEarningDetail.setRateTypeName(rateTypeName.value());

                        } catch (java.lang.Exception e) {

                            e.printStackTrace();
                        }
                        payrollInputEarningDetail.setEarningHours(earning.getHours());
                        payrollInputEarningDetails.add(payrollInputEarningDetail);
                    }

                    payrollInput.getPayrollInputEarningDetail().addAll(payrollInputEarningDetails);
                }

                if (check.getTaxes() != null) {

                    List<PayrollInputTaxDetail> payrollInputTaxDetails = new ArrayList<>();

                    for (Tax tax : check.getTaxes()) {

                        PayrollInputTaxDetail payrollInputTaxDetail = new PayrollInputTaxDetail();
                        payrollInputTaxDetail.setRecordCreateTimestamp(date);
                        payrollInputTaxDetail.setRecordUpdateTimestamp(date);
                        payrollInputTaxDetail.setChangeVersionID(new BigInteger("1"));
                        payrollInputTaxDetail.setBusinessEntityID(businessEntityID);
                        payrollInputTaxDetail.setTaxCode(tax.getCode());
                        payrollInputTaxDetail.setTaxAmount(tax.getAmount());
                        payrollInputTaxDetails.add(payrollInputTaxDetail);
                    }

                    payrollInput.getPayrollInputTaxDetail().addAll(payrollInputTaxDetails);
                }
            }

            exchange.getIn().setBody(payrollInputs);
        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage());
        }
    }
}
