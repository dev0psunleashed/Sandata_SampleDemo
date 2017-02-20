package com.sandata.one.aggregator.visit.review.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.exception.SandataValidationException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.one.aggregator.visit.review.api.DataService;
import com.sandata.one.aggregator.visit.review.model.ReviewVisitRequest;
import com.sandata.one.aggregator.visit.review.model.ReviewVisitStaffResult;
import com.sandata.one.aggregator.visit.review.utils.DateUtils;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static com.sandata.one.aggregator.visit.review.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class RestDataService implements DataService {


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private OracleDataService oracleDataService;

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void reviewVisit(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            ReviewVisitRequest reviewVisitRequest = new ReviewVisitRequest();

            LocalDateTime now = LocalDateTime.now();

            String toTime = (String) exchange.getIn().getHeader("to_time");
            if (StringUtils.isEmpty(toTime)) {
                toTime = timeFormatter.format(now);
            }

            String toDate = (String) exchange.getIn().getHeader("to_date");
            if (StringUtils.isEmpty(toDate)) {
                toDate = dateFormatter.format(now);
            }

            // The Default date range if the user does not select it is 24 hours back
            LocalDateTime back24Hours = now.minusHours(24);

            String fromTime = (String) exchange.getIn().getHeader("from_time");
            if (StringUtils.isEmpty(fromTime)) {
                fromTime = timeFormatter.format(back24Hours);
            }

            String fromDate = (String) exchange.getIn().getHeader("from_date");
            if (StringUtils.isEmpty(fromDate)) {
                fromDate = dateFormatter.format(back24Hours);
            }

            LocalDateTime fromDateTime = getDateTime(fromDate, fromTime, true);
            LocalDateTime toDateTime = getDateTime(toDate, toTime, false);

            // FromDateTime must be before ToDateTime
            long secondDiff = fromDateTime.until(toDateTime, ChronoUnit.SECONDS);
            if (secondDiff <= 0) {
                throw new SandataValidationException(
                        format("From DateTime (from_date & from_time) must be before To DateTime (to_date & to_time). From DateTime (from_date & from_time) = %s, To DateTime (to_date & to_time) = %s",
                                fromDateTime.format(dateTimeFormatter), toDateTime.format(dateTimeFormatter)));
            }

            // The Date Range must be limited to 180 days
            long daysDiff = fromDateTime.until(toDateTime, ChronoUnit.DAYS);
            if (daysDiff > 180) {
                throw new SandataValidationException(
                        format("Date Range must be limited to 180 days. From DateTime (from_date & from_time) = %s, To DateTime (to_date & to_time) = %s",
                                fromDateTime.format(dateTimeFormatter), toDateTime.format(dateTimeFormatter)));
            }

            reviewVisitRequest.setFromDate(Date.from(fromDateTime.toInstant(ZoneOffset.UTC)));
            reviewVisitRequest.setToDate(Date.from(toDateTime.toInstant(ZoneOffset.UTC)));

            reviewVisitRequest.setPayerId((String) exchange.getIn().getHeader("payer_id"));
            reviewVisitRequest.setContractId((String) exchange.getIn().getHeader("contract_id"));
            reviewVisitRequest.setServiceName((String) exchange.getIn().getHeader("service_name"));
            reviewVisitRequest.setBusinessEntityId((String) exchange.getIn().getHeader("bsn_ent_id"));
            reviewVisitRequest.setPatientLastName((String) exchange.getIn().getHeader("patient_last_name"));
            reviewVisitRequest.setPatientFirstName((String) exchange.getIn().getHeader("patient_first_name"));
            reviewVisitRequest.setPatientName((String) exchange.getIn().getHeader("patient_name"));
            reviewVisitRequest.setPatientId((String) exchange.getIn().getHeader("patient_id"));
            reviewVisitRequest.setMedicaidId((String) exchange.getIn().getHeader("medicaid_id"));
            reviewVisitRequest.setStaffLastName((String) exchange.getIn().getHeader("staff_last_name"));
            reviewVisitRequest.setStaffFirstName((String) exchange.getIn().getHeader("staff_first_name"));
            reviewVisitRequest.setStaffName((String) exchange.getIn().getHeader("staff_name"));
            reviewVisitRequest.setStaffId((String) exchange.getIn().getHeader("staff_id"));
            reviewVisitRequest.setStaffSsn((String) exchange.getIn().getHeader("staff_snn"));
            reviewVisitRequest.setVisitStatus((String) exchange.getIn().getHeader("visit_status"));
            reviewVisitRequest.setExceptionsList((List) exchange.getIn().getHeader("exceptions_list"));
            reviewVisitRequest.setCallType((String) exchange.getIn().getHeader("call_type"));
            reviewVisitRequest.setCoordinatorName((String) exchange.getIn().getHeader("coordinator_name"));
            reviewVisitRequest.setCoordinatorId((String) exchange.getIn().getHeader("coordinator_id"));
            reviewVisitRequest.setManagerName((String) exchange.getIn().getHeader("manager_name"));
            reviewVisitRequest.setPage((Integer) exchange.getIn().getHeader("page"));
            reviewVisitRequest.setPageSize((Integer) exchange.getIn().getHeader("page_size"));
            reviewVisitRequest.setSortOn((String) exchange.getIn().getHeader("sort_on"));
            reviewVisitRequest.setDirection((String) exchange.getIn().getHeader("direction"));

            Response response = oracleDataService.reviewVisit(reviewVisitRequest);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (SandataValidationException e) {
            throw e;
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private LocalDateTime getDateTime(String dateString, String timeString, boolean isFromDate) {
        String dateTimeString = null;
        try {
            String secondString = isFromDate ? "00" : "59";
            dateTimeString = format("%s %s:%s", dateString, timeString, secondString);
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);

        } catch (DateTimeParseException dtpe) {
            String dateType = isFromDate ? "From" : "To";
            throw new SandataValidationException(format("%s DateTime: [%s]: Is NOT a valid date time and/or the format is incorrect!", dateType, dateTimeString));
        }
    }

    public void getVisitHistory(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long visitSk = (long) exchange.getIn().getHeader("visit_sk");

            if (visitSk <= 0) {
                throw new SandataValidationException(format("VisitSK (visit_sk) must be greater than zero. Input visit_sk = %d", visitSk));
            }

            int page = (int) exchange.getIn().getHeader("page");
            if (page <= 0) {
                page = 1;
            }

            int pageSize = (int) exchange.getIn().getHeader("page_size");
            if (pageSize <= 0) {
                pageSize = 10;
            }

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            if (StringUtils.isEmpty(sortOn)) {
                sortOn = "ChangedBy";
            }

            String direction = (String) exchange.getIn().getHeader("direction");
            if (StringUtils.isEmpty(direction)) {
                direction = "DESC";
            }

            Response response = oracleDataService.getVisitHistory(visitSk, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (SandataValidationException e) {
            throw e;
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getVisitPatientDetails(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long visitSk = (long) exchange.getIn().getHeader("visit_sk");

            if (visitSk <= 0) {
                throw new SandataValidationException(format("VisitSK (visit_sk) must be greater than zero. Input visit_sk = %d", visitSk));
            }

            exchange.getIn().setBody(oracleDataService.getVisitPatientDetails(visitSk));

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getVisitNotes(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long visitSk = (long) exchange.getIn().getHeader("visit_sk");

            if (visitSk <= 0) {
                throw new SandataValidationException(format("VisitSK (visit_sk) must be greater than zero. Input visit_sk = %d", visitSk));

            }

            Response response = oracleDataService.getVisitNotes(visitSk);

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }


    /**
     * get visit exception details for a specific visit sk
     * @param exchange including visit_sk
     */
    public void getVisitExcpForReview(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        //excplknm - default sort_on column
        Integer page = (Integer) exchange.getIn().getHeader("page");
        Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");

        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String  sortDirection = (String) exchange.getIn().getHeader("direction");

        try {

            if (exchange.getIn().getHeader("sequence_key") == null) {
                throw new SandataValidationException("Missing required info : visit_sk");
            }

            long visitSk = (long) exchange.getIn().getHeader("sequence_key");
            Response response = oracleDataService.getVisitExcpForReview(visitSk,page, pageSize, sortOn, sortDirection);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection().toUpperCase());

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getVisitTasks(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long visitSk = (long) exchange.getIn().getHeader("visit_sk");

            if (visitSk <= 0) {
                throw new SandataValidationException(format("VisitSK (visit_sk) must be greater than zero. Input visit_sk = %d", visitSk));
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getVisitTasks(visitSk, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     *
     * @param exchange
     */
    public void getStaffDetail(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            if (exchange.getIn().getHeader("sequence_key") == null) {
                throw new SandataValidationException("Missing required info : visit_sk");
            }
            long visitSk = (long) exchange.getIn().getHeader("sequence_key");

            List<ReviewVisitStaffResult>  result = oracleDataService.getStaffDetail(visitSk);

            Response response = new Response();

            if (result != null && result.size() == 1) {
                response.setData(result.get(0));
            } else if(result.size() > 1) {
                //but not for unlanned visit
                ReviewVisitStaffResult visitStaffResult = result.get(0);
                if (visitStaffResult.getSchedEvntSk() != null ) {
                    // handle for planned visit , compare
                    visitStaffResult.setHireDate(DateUtils.sortAndGetHireDateClosestBeforeVisitDate(result));
                    visitStaffResult.setTermDate(DateUtils.sortAndGetTermDateClosestAfterVisitDate(result));
                } else {
                    //TODO: as David comments, visit date implied SCHED_EVNT_START_DTIME , this only appliable to planned visit
                    //for unplanned visit , by default get the latest date of hire date and term date
                    visitStaffResult.setHireDate(DateUtils.getLatestHiredDate(result));
                    visitStaffResult.setTermDate(DateUtils.getLatestTermDate(result));
                }

                response.setData(visitStaffResult);
            }

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     *
     * @param exchange
     */
    public void getCallLogs(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        //excplknm - default sort_on column
        Integer page = (Integer) exchange.getIn().getHeader("page");
        Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");

        String sortOn = (String) exchange.getIn().getHeader("sort_on");
        String  sortDirection = (String) exchange.getIn().getHeader("direction");

        try {

            if (exchange.getIn().getHeader("visit_sk") == null) {
                throw new SandataValidationException("Missing required info : visit_sk");
            }

            long visitSk = (long) exchange.getIn().getHeader("visit_sk");
            Response response = oracleDataService.getCallLogs(visitSk,page, pageSize, sortOn, sortDirection);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection().toUpperCase());

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getVisitDetail(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            long visitSk = (long) exchange.getIn().getHeader("visit_sk");

            if (visitSk <= 0) {
                throw new SandataValidationException(format("VisitSK (visit_sk) must be greater than zero. Input visit_sk = %d", visitSk));
            }

            Response response = oracleDataService.getVisitDetail(visitSk);
            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
