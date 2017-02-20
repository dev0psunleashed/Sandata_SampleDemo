package com.sandata.lab.rest.staff.impl;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffRateExchange;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.sandata.lab.rest.staff.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public class OSGIDataService {

    private RatesOracleDataService ratesOracleDataService;
    private OracleFindDataService oracleFindDataService;
    private OracleDataService oracleDataService;

    public void getStaffIDByTIN(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String staffTIN = (String) exchange.getIn().getHeader("staffSSN");
            String externalBsnEntID = (String) exchange.getIn().getHeader("bsnEntID");

            String staffID = oracleFindDataService.getStaffIDByTIN(staffTIN, externalBsnEntID);

            exchange.getIn().setBody(staffID);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void staffExists(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String businessEntityID = (String) exchange.getIn().getHeader("bsn_ent_id");
            String firstName = (String) exchange.getIn().getHeader("first_name");
            String lastName = (String) exchange.getIn().getHeader("last_name");
            String staffID = (String) exchange.getIn().getHeader("staff_id");

            boolean result = oracleFindDataService.staffExists(businessEntityID, firstName, lastName, staffID);

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffByBusinessEntityID(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {


            String businessEntityID = (String) exchange.getIn().getHeader("bsnEntID");

            logger.info(String.format("Getting all staff for business entity id: %s", businessEntityID));

            // StaffNotes
            List<Staff> result =
                oracleFindDataService.getStaffByBusinessEntityID(businessEntityID);

            logger.info(String.format("Retrieve %d staff for business entity id: %s", result.size(), businessEntityID));

            exchange.getIn().setBody(result);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void addStaffRate(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            String type = (String) exchange.getIn().getHeader("type");
            String code = (String) exchange.getIn().getHeader("code");
            StaffRateExchange staffRateExchange = exchange.getIn().getBody(StaffRateExchange.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(ratesOracleDataService.addStaffRate(connection, staffRateExchange, type, code, logger));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void getStaffAssocRatePK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException("StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(ratesOracleDataService.getStaffAssocRate(staffId, bsnEntId, logger));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void findClassesForStaff(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            // Extract parameters and null check.
            String staffId = (String) exchange.getIn().getHeader("staff_id");
            if (staffId == null
                || staffId.isEmpty()) {
                throw new SandataRuntimeException("Staff ID (staff_id) required!");
            }

            DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);

            String fromDateTimeString = (String) exchange.getIn().getHeader("from_date_time");
            Date fromDateTime = null;
            if (StringUtil.IsNullOrEmpty(fromDateTimeString)) {
                throw new SandataRuntimeException("From Date Time (from_date_time) required!");
            }

            try {
                fromDateTime = format.parse(fromDateTimeString);
            } catch (ParseException e) {
                throw new SandataRuntimeException("Staff training start time must be in format yyyy-MM-dd !");
            }

            String toDateTimeString = (String) exchange.getIn().getHeader("to_date_time");
            Date toDateTime = null;
            if (StringUtil.IsNullOrEmpty(toDateTimeString)) {
                throw new SandataRuntimeException("To Date Time (to_date_time) required!");
            }

            try {
                toDateTime = format.parse(toDateTimeString);
            } catch (ParseException e) {
                throw new SandataRuntimeException("Staff training end time must be in format yyyy-MM-dd !");
            }

            // Defaulted parameters.
            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleFindDataService.findStaffClassEnrollment(
                staffId, bsnEntId, fromDateTime, toDateTime, page, pageSize, direction, sortOn);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);
            exchange.getIn().setHeader("ORDER_BY_COLUMN", sortOn);
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", direction);

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }

    }

    public void setRatesOracleDataService(RatesOracleDataService ratesOracleDataService) {
        this.ratesOracleDataService = ratesOracleDataService;
    }

    public void setOracleFindDataService(OracleFindDataService oracleFindDataService) {
        this.oracleFindDataService = oracleFindDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
