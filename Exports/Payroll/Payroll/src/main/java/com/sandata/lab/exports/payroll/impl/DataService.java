package com.sandata.lab.exports.payroll.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.BusinessEntity;
import com.sandata.lab.data.model.dl.model.WeekEndDay;
import com.sandata.lab.exports.payroll.utils.PayrollProcessUtil;
import com.sandata.lab.exports.payroll.utils.log.PayrollExportLogger;
import org.apache.camel.Exchange;

import java.util.Date;
import java.util.logging.Logger;

public class DataService {

    public void getPayRollEndDate(Exchange exchange) throws Exception{

        try {

            SandataLogger logger = PayrollExportLogger.CreateLogger();

            BusinessEntity businessEntity = (BusinessEntity) exchange.getIn().getHeader("bsnEnt");

            WeekEndDay payrollWeekEndDay = businessEntity.getBusinessEntityPayrollWeekEndDay();

            if (payrollWeekEndDay == null) {

                throw new SandataRuntimeException(String.format("There is no Payroll Week End value for business entity" +
                        "'%s'", businessEntity));

            }

            String businessEntityTimeZone = businessEntity.getTimezoneName();

            if(StringUtil.IsNullOrEmpty(businessEntityTimeZone)){

                throw new SandataRuntimeException(String.format("There is no timezone value for business entity" +
                        "'%s'", businessEntity));

            }

            logger.info(String.format("Calculating payroll end date for business entity '%s' with timezone '%s" +
                    " and week end day '%s' ", businessEntity.getBusinessEntityID(), businessEntityTimeZone, payrollWeekEndDay.value()));

            Date payrollEndDate = PayrollProcessUtil.PayrollWeekEndDate
                    (payrollWeekEndDay.value(), businessEntityTimeZone);


            exchange.getIn().setHeader("prEndTime", payrollEndDate.getTime());
        }catch (Exception e){

            e.printStackTrace();
            throw e;
        }
    }
}
