package com.sandata.lab.exports.payroll.impl;

import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.BusinessEntity;
import com.sandata.lab.data.model.dl.model.BusinessEntityLineOfBusinessLookup;
import com.sandata.lab.data.model.dl.model.ServiceName;
import com.sandata.lab.data.model.dl.model.StateCode;
import com.sandata.lab.data.model.dl.model.extended.PayrollOutputExt;
import com.sandata.lab.exports.payroll.model.paypro.*;
import com.sandata.lab.exports.payroll.utils.log.PayrollExportLogger;
import org.apache.camel.Exchange;

import java.math.BigInteger;
import java.util.*;

public class PayProExportTransformer {

    public void prOutPutToPayProTime(Exchange exchange) throws Exception {

        try {

            SandataLogger logger = PayrollExportLogger.CreateLogger();

            BusinessEntity businessEntity = (BusinessEntity) exchange.getIn().getHeader("bsnEnt");
            List<PayrollOutputExt> payrollOutputExt = (List<PayrollOutputExt>) exchange.getIn().getBody();

            if(payrollOutputExt == null || payrollOutputExt.size() < 1){

                logger.info(String.format("No payroll records to export for business entity '%s'", businessEntity.getBusinessEntityID()));

            }

            logger.info(String.format("Retrieved %d payroll records for business entity '%s'", payrollOutputExt.size(),businessEntity.getBusinessEntityID()));

            HashMap<String, String> businessEntityInfo = (HashMap<String, String>) exchange.getIn().getHeader("bsnEntInfo");

            List<Time> times = new ArrayList<>();

            List<BigInteger> prOutputSks = new ArrayList<>();

            DetCodes detCodes = new DetCodes();


            HashMap<String, DetCode> detCodeMap = new HashMap<>();
            Summary summary = new Summary();

            HashSet<String> staffIDList = new HashSet();

            logger.info("Exporting ");


            for (PayrollOutputExt payrollOutputExtObj : payrollOutputExt) {

                prOutputSks.add(payrollOutputExtObj.getPayrollOutputSK());

                Time time = new Time();

                time.setAmount(payrollOutputExtObj.getPayrollAmount());
                time.setHours(payrollOutputExtObj.getPayrollHours());
                time.setHourlyRate(payrollOutputExtObj.getPayrollRate());
                time.setSsn(payrollOutputExtObj.getStaffTaxpayerIdentificationNumber());
                time.setEarningCode(payrollOutputExtObj.getPayrollCode());
                time.setEarning("E");
                time.setBaseRate(payrollOutputExtObj.getStaffRateAmount());

                time.setBeginDate(payrollOutputExtObj.getWeekEndDate());
                time.setEndDate(payrollOutputExtObj.getWeekEndDate());

                List<BusinessEntityLineOfBusinessLookup> businessEntityLineOfBusinessList =
                        businessEntity.getBusinessEntityLineOfBusinessLookup();

                time.setCc1(payrollOutputExtObj.getLineOfBusiness());

                StateCode primaryState = businessEntity.getBusinessEntityPrimaryState();

                if (primaryState != null) {
                    time.setCc2(primaryState.value());
                }

                time.setCc3(businessEntityInfo.get("vendorBEID"));

                ServiceName staffPosition = payrollOutputExtObj.getStaffPositionName();

                if (staffPosition != null) {
                    time.settCode4(staffPosition.value());
                }


                String comment = payrollOutputExtObj.getStaffLastName() + ", " + payrollOutputExtObj.getStaffFirstName();
                time.setComment(comment);

                times.add(time);

                staffIDList.add(time.getSsn());
                updateSummary(summary, payrollOutputExtObj);
                updateDetCodeSummary(detCodes, detCodeMap, time);
            }

            logger.info(String.format("Created %d time records for payroll export for business enity '%s'", times.size(), businessEntity.getBusinessEntityID()));

            detCodes.setDetCodes(new ArrayList<DetCode>(detCodeMap.values()));

            summary.setDetCodes(detCodes);
            summary.setEmployeeCount(staffIDList.size());

            Transaction transaction = new Transaction();
            transaction.setTimes(times);
            transaction.setCompany(businessEntityInfo.get("vendorParentBEID"));
            transaction.setSummary(summary);

            PayPro payPro = new PayPro();
            payPro.setTransaction(transaction);

            exchange.getIn().setHeader("prOutputSks", prOutputSks);
            exchange.getIn().setHeader("prExportTimestamp", new Date().getTime());
            exchange.getIn().setBody(payPro);
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    private void updateSummary(Summary summary, PayrollOutputExt payrollOutputExt) {

        setBeginEndDateRange(payrollOutputExt, summary);

        summary.incrementTimeCount();
    }

    private void setBeginEndDateRange(PayrollOutputExt payrollOutputExt, Summary summary) {

        Date weekEndDate = payrollOutputExt.getWeekEndDate();

        if (summary.getBeginDate() == null) {

            summary.setBeginDate(new Date(weekEndDate.getTime()));
            summary.setEndDate(new Date(weekEndDate.getTime()));
        } else {

            if (summary.getBeginDate().after(weekEndDate)) {

                summary.setEndDate(new Date(summary.getBeginDate().getTime()));
                summary.setBeginDate(new Date(weekEndDate.getTime()));

            } else if (summary.getEndDate().before(weekEndDate)) {

                summary.setEndDate(new Date(weekEndDate.getTime()));

            }

        }

    }

    private void updateDetCodeSummary(DetCodes detCodes, HashMap<String, DetCode> detCodeMap, Time time) {

        String code = time.getEarningCode();

        DetCode detCode = detCodeMap.get(code);

        if (detCode == null) {

            detCode = new DetCode();
            detCode.setCode(code);
        }

        detCode.addAmount(time.getAmount());
        detCode.addHours(time.getHours());
        detCode.incrementCount();

         detCodeMap.put(code, detCode);

        detCodes.addAmount(time.getAmount());
        detCodes.addHours(time.getHours());
        detCodes.incrementCount();
    }
}
