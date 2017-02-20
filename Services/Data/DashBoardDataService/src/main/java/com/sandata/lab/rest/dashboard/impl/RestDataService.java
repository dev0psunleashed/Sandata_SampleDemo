package com.sandata.lab.rest.dashboard.impl;

import static com.sandata.lab.rest.dashboard.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.sandata.lab.rest.dashboard.model.*;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.rest.dashboard.api.DataService;
import com.sandata.lab.rest.dashboard.model.enums.Columns;
import com.sandata.lab.rest.dashboard.model.enums.Tables;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void getTotalScheduledHoursDetails(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_SCHED.getValue();
            String columnTotalName = Columns.MV_SCHEDULE_SCHEDULE_TOTAL_HOURS.getValue();
            String dateColumn = Columns.MV_SCHEDULE_SCHEDULE_DATE.getValue();

           /* List<PatientDetailsResponse> patientDetailsResponseList = (ArrayList)oracleDataService.getDashboardDetailsData(bsnEntId,bsnEntLvl,fromDate,toDate,
                    tableName, columnTotalName, dateColumn, PatientDetailsResponse.class);
*/
            List<PatientDetailsResponse> patientDetailsResponseList = (ArrayList)oracleDataService.getPatientStaffDetail(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,
                    tableName, columnTotalName, dateColumn, PatientDetailsResponse.class);

            exchange.getIn().setBody(patientDetailsResponseList);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void getTotalScheduledHours(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_SCHED.getValue();
            String columnTotalName = Columns.MV_SCHEDULE_SCHEDULE_TOTAL_HOURS.getValue();
            String dateColumn = Columns.MV_SCHEDULE_SCHEDULE_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,tableName, columnTotalName, dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void getVerifiedHours(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_VISIT_VERIF.getValue();
            String columnTotalName = Columns.MV_VISIT_TOTAL_HOURS.getValue();
            String dateColumn = Columns.MV_VISIT_END_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,tableName, columnTotalName, dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void getVerifiedHoursDetails(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_VISIT_VERIF.getValue();
            String columnTotalName = Columns.MV_VISIT_TOTAL_HOURS.getValue();
            String dateColumn = Columns.MV_VISIT_END_DATE.getValue();

            List<PatientDetailsResponse> patientDetailsResponseList = (ArrayList)oracleDataService.getPatientStaffDetail(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,
                    tableName, columnTotalName, dateColumn, PatientDetailsResponse.class);

            exchange.getIn().setBody(patientDetailsResponseList);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }



    /**
     * Serves for the following operation names:
     * <ul>
     * 	<li>PKG_DASHBOARD_getTotalPayrollHours_LocationResponse</li>
     *  <li>PKG_DASHBOARD_getTotalPayrollHoursDetail_LocationResponse</li>
     *  <li>PKG_DASHBOARD_getTotalPayrollDollars_LocationResponse</li>
     *  <li>PKG_DASHBOARD_getTotalPayrollDollarsDetail_LocationResponse</li>
     *  <li>PKG_DASHBOARD_getTotalOverTimeHours_LocationResponse</li>
     *  <li>PKG_DASHBOARD_getTotalOverTimeHoursDetail_LocationResponse</li>
     * </ul>
     * 
     * @param exchange
     * @param operationName
     */
    public void getTotalOrPendingValues(Exchange exchange, final String operationName) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String businessEntityId = (String) mcl.get(0);
            if (businessEntityId == null || businessEntityId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityId (bns_ent_id) is required!");
            }

            int bsnEntityLevel = ((Integer)mcl.get(1)).intValue();

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);
            boolean pending = false;
            if(mcl.size() > 4) {
                String pendingStr = (String) mcl.get(4);
                if (pendingStr != null && pendingStr.equals("pending")) {
                    pending = true;
                }
            }
            
            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            Columns valColName = getValColName(operationName);
            Tables table = getPRTable(operationName, pending);
            Columns dateColumn = getPRDateColumn(table);
            
            if (operationName.contains("TotalPayrollHoursDetail") 
            		|| operationName.contains("TotalPayrollDollarsDetail") 
            		|| operationName.contains("TotalOverTimeHoursDetail")) {
            	List<StaffDetailsResponse> staffDetailResponseList = (ArrayList)oracleDataService.getPatientStaffDetail(businessEntityId, bsnEntityLevel, fromDateTimeString, toDateTimeString,
            			table.getValue(), valColName.getValue(), dateColumn.getValue(), StaffDetailsResponse.class);
            	exchange.getIn().setBody(staffDetailResponseList);
            	
            } else {
            	LocationResponse locationResponse = oracleDataService.getDashboardLocationData(businessEntityId, bsnEntityLevel, fromDateTimeString, toDateTimeString, 
            			table.getValue(), valColName.getValue(), dateColumn.getValue());
                exchange.getIn().setBody(locationResponse);
            }

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    public void getTotalPayrollDollars(Exchange exchange, final String operationName) {
    }

    public void getTotalOvertimeHours(Exchange exchange, final String operationName) {
    }

    private Date getDate(final String dateString) throws SandataRuntimeException {
        Date date = null;
        if (dateString != null && dateString.length() != 0) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            try {
                date = format.parse(dateString);
            } catch (ParseException pe) {
                throw new SandataRuntimeException(
                        format("To Date: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                dateString));
            }
        } else {
            throw new SandataRuntimeException("From/To Dates are required!");
        }
        return date;
    }

    /**
     * @author thanh.le
     * @param exchange
     * 
     * Get the expired authentication details / patient details
     * 
     */
    public void getExpiredAuthDetails(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_AUTH_EXPIRE.getValue();
            String columnTotalName = Columns.PT_ID.getValue();
            String dateColumn = Columns.AUTH_SERVICE_END_DATE.getValue();

            List<PatientDetailsResponse> patientDetailsResponseList = (ArrayList)oracleDataService.getPatientStaffDetail(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,
                    tableName, columnTotalName, dateColumn, PatientDetailsResponse.class);

            exchange.getIn().setBody(patientDetailsResponseList);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
        
    }

    /**
     * @author thanh.le
     * @param exchange
     * 
     * Get the expired authentication summary
     * 
     */
    public void getExpiredAuth(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_AUTH_EXPIRE.getValue();
            String columnTotalName = Columns.AUTH_ID.getValue();
            String dateColumn = Columns.AUTH_SERVICE_END_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,tableName, columnTotalName, dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
        
    }


    /**
     * Get value column based on operationName
     * 
     * @param operationName
     * @return
     */
    private Columns getValColName(String operationName) {
        if (operationName.contains("TotalPayrollHours") || operationName.contains("TotalOverTimeHours")) {
            return Columns.MV_PR_PR_HRS;
        }
        else if (operationName.contains("TotalPayrollDollars") ) {
            return Columns.MV_PR_PR_AMT;
        }
        return null;
    }
    
    /**
     * Get PayRoll table(i.e. MV_PR, MV_PR_OT, MV_PR_PENDING) based on operation name and pending flag
     * 
     * @param operationName
     * @param pending
     * @return
     */
    private Tables getPRTable(String operationName, boolean pending) {
        if( operationName.contains("TotalPayrollHours") || operationName.contains("TotalPayrollDollars") )  {
            if( pending ) {
                return Tables.MV_PR_PENDING;
            }
            else {
                return Tables.MV_PR;
            }
            
        } else if(operationName.contains("TotalOverTimeHours")) {
            return Tables.MV_PR_OT;
        }
        
        return null;
    }
    
    /**
     * Get PayRoll date column based on table name
     * 
     * @param table
     * @return
     */
    private Columns getPRDateColumn(Tables table) {
    	switch (table) {
	    	case MV_PR:
	    		return Columns.MV_PR_PR_EXPORT_DATE;
	    		
	    	case MV_PR_OT:
	    		return Columns.MV_PR_PR_EXPORT_DATE;
	    		
	    	case MV_PR_PENDING:
	    		return Columns.MV_PR_PR_EXPORT_DATE;
	    		
	    	default:
	    		return Columns.MV_PR_PR_EXPORT_DATE;
    	}
    }



    /**
     * 
     * @param exchange
     * 
     * get the visit exception break down
     *
     */
    public void getVisitExcpBreakdown(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");
            
            List<StaffDetailsResponse> visitExcpRespone =  oracleDataService.getVisiExceptionBreakdown(bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString);
            
            exchange.getIn().setBody(visitExcpRespone);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
        
    }

    /**
     * @param exchange
     * 
     * Total Number of Visit Exceptions for a Business Entity
     * 
     */
    public void getVisitExceptions(final Exchange exchange) {
        
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_VISIT_EXCP.getValue();
            String columnTotalName = Columns.VISIT_EXCEPTION_SK.getValue();
            String dateColumn = Columns.VISIT_START_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(bsnEntId,bsnEntLvl,fromDateTimeString,toDateTimeString,tableName, columnTotalName, dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    /**
     * @param exchange
     * 
     */
    public void getStaffOrPatientsExceptions(final Exchange exchange,final String operationName) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            getDate(fromDate);
            getDate(toDate);

            String tableName = Tables.MV_VISIT_EXCP.getValue();
            String columnTotalName = Columns.VISIT_SK.getValue();
            String dateColumn = Columns.VISIT_START_DATE.getValue();

            if(operationName.contains("getStaffsExceptions")){
                List<StaffDetailsResponse> staffDetailsResponseList = (ArrayList)oracleDataService.getVisiExceptionBreakdown(bsnEntId,bsnEntLvl,fromDate,toDate);
                    exchange.getIn().setBody(staffDetailsResponseList);
            }

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
        
    }

    /**
     *
     * @param exchange
     *
     * get the visit exception break down
     *
     */
    public void getStaffVisitExceptions(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);
            String staffID = (String) mcl.get(4);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            List<VisitException> visitExcpRespone =  oracleDataService.getStaffVisitExceptions(bsnEntId,bsnEntLvl,staffID,fromDateTimeString,toDateTimeString);

            exchange.getIn().setBody(visitExcpRespone);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    /**
     * Get total open order by location / business entity
     * 
     * @param exchange
     */
    public void getOpenOrder(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            // Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_OPEN_ORDER.getValue();
            String columnTotalName = Columns.OPEN_ORD_COUNT.getValue();
            String dateColumn = Columns.OPEN_ORD_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString, tableName, columnTotalName,
                    dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    /**
     * get the list of staff/coordinator with total count of patients
     * 
     * @param exchange
     */
    public void getListCoordinators(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            // Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            List<CoordinatorsResponse> coordinatorsList = oracleDataService.getListCoordinators(bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString);

            exchange.getIn().setBody(coordinatorsList);

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    /**
     * get list of patients by staff/coordinator id
     * 
     * @param exchange
     */
    public void getListPatients(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            String bsnEntId = (String) mcl.get(1);

            if (staffId == null || staffId.length() == 0) {
          //      throw new SandataRuntimeException("Coordinator (staffId) is required!");
            }

            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(2);

            String fromDate = (String) mcl.get(3);
            String toDate = (String) mcl.get(4);

            // Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            List<Patient> patientList = oracleDataService.getListPatients(staffId, bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString);

            exchange.getIn().setBody(patientList);

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    /**
     * Get Out of Item Compliance
     * 
     * @param exchange
     */
    public void getOutOfCompliance(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            // Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_COMP.getValue();
            String columnTotalName = Columns.OUT_OF_COMPLIANCE_COUNT.getValue();
            String dateColumn = Columns.OUT_OF_COMPLIANCE_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString,
                    tableName, columnTotalName, dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    /**
     * Get Non-Eligible Patients
     *
     * @param exchange
     */
    public void getNonEligiblePatients(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_NONELIGIB.getValue();
            String columnTotalName = Columns.PT_NON_ELIGIB_COUNT.getValue();
            String dateColumn = Columns.PT_NON_ELIGIB_DATE.getValue();

            LocationResponse locationResponse = oracleDataService.getDashboardLocationData(
                    bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString, tableName, columnTotalName, dateColumn);

            exchange.getIn().setBody(locationResponse);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    /**
     *
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getNonEligiblePatientDetails(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");

            String tableName = Tables.MV_NONELIGIB.getValue();
            String columnTotalName = Columns.PT_NON_ELIGIB_COUNT.getValue();
            String dateColumn = Columns.PT_NON_ELIGIB_DATE.getValue();

            List<PatientDetailsResponse> patientDetailsResponseList = (ArrayList)oracleDataService.getPatientStaffDetail(bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString,
                    tableName, columnTotalName, dateColumn, PatientDetailsResponse.class);

            exchange.getIn().setBody(patientDetailsResponseList);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }
	
	/**
	 * 
	 * 
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
    public void getOutOfComplianceDetails(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList)exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            int bsnEntLvl = (int) mcl.get(1);

            String fromDate = (String) mcl.get(2);
            String toDate = (String) mcl.get(3);

            //Validate the dates
            String fromDateTimeString = DateUtil.convertStringUTCtoStringDateTime(fromDate, "from_date");
            String toDateTimeString = DateUtil.convertStringUTCtoStringDateTime(toDate, "to_date");
            
            String tableName = Tables.MV_COMP.getValue();
            String columnTotalName = Columns.OUT_OF_COMPLIANCE_COUNT.getValue();
            String dateColumn = Columns.OUT_OF_COMPLIANCE_DATE.getValue();

            List<StaffDetailsResponse> staffDetailsResponseList = (ArrayList)oracleDataService.getPatientStaffDetail(bsnEntId, bsnEntLvl, fromDateTimeString, toDateTimeString,
                    tableName, columnTotalName, dateColumn, StaffDetailsResponse.class);

            exchange.getIn().setBody(staffDetailsResponseList);

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }
}
