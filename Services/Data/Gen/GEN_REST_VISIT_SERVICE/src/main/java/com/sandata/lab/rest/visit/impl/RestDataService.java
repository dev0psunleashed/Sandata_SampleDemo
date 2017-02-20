package com.sandata.lab.rest.visit.impl;

import static com.sandata.lab.rest.visit.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.constants.audit.Audit;
import com.sandata.lab.data.model.data.Compare;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ActionCode;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitActivity;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitEventTypeCode;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.data.model.dl.model.VisitHistory;
import com.sandata.lab.data.model.dl.model.VisitNote;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.dl.model.extended.visit.VisitExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.visit.api.DataService;
import com.sandata.lab.rest.visit.model.AuditVisit;
import com.sandata.lab.rest.visit.model.FindVisitDetailsResult;
import com.sandata.lab.rest.visit.utils.log.OracleDataLogger;
import com.sandata.lab.rest.visit.utils.templates.ProducerVisitHandler;

import oracle.jdbc.OracleTypes;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

	private OracleDataService oracleDataService;
	private ProducerVisitHandler producerVisitHandler;

	private final static String _PACKAGE_NAME = "PKG_VISIT";

	public void getVisitHistDetail(final Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

		logger.start();

		try {

			Long visitSk = (Long) exchange.getIn().getHeader("visit_sk");
			if (visitSk == null || visitSk <= 0) {
				throw new SandataRuntimeException("VisitSK (visit_sk) is required!");
			}

			int page = (int) exchange.getIn().getHeader("page");
			int pageSize = (int) exchange.getIn().getHeader("page_size");
			String sortOn = (String) exchange.getIn().getHeader("sort_on");
			String direction = (String) exchange.getIn().getHeader("direction");

			Response response = oracleDataService.getVisitHistDetail(visitSk, page, pageSize, sortOn, direction);
			exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
			exchange.getIn().setHeader("PAGE", page);
			exchange.getIn().setHeader("PAGE_SIZE", pageSize);
			exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
			exchange.getIn().setHeader("ORDER_BY_DIRECTION", direction.toUpperCase());

			exchange.getIn().setBody(response.getData());

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);

		} finally {
			logger.stop();
		}
	}

	public void acceptCalls(final Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {

			List<VisitEvent> visitEvents = (List<VisitEvent>) exchange.getIn().getBody();

			ProducerVisitHandler producerVisitHandler = new ProducerVisitHandler(exchange);
			long result = oracleDataService.acceptCalls(visitEvents, producerVisitHandler);

			exchange.getIn().setBody(result);

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	public void getVisitsForPatient(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {

			MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

			String patientId = (String) mcl.get(0);
			if (patientId == null || patientId.length() == 0) {
				throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
			}

			String bsnEntId = (String) mcl.get(1);
			if (bsnEntId == null || bsnEntId.length() == 0) {
				throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
			}

			String sortOn = (String) mcl.get(2);
			if (sortOn == null || sortOn.length() == 0) {
				sortOn = "REC_CREATE_TMSTP";
			}

			String direction = (String) mcl.get(3);

			int page = (int) mcl.get(4);
			int pageSize = (int) mcl.get(5);


			DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);

			// Validate schedule from/to date time
			String fromDateTimeString = (String) mcl.get(6);
			Date fromDateTime = null;
			Date toDateTime = null;
			if (!StringUtil.IsNullOrEmpty(fromDateTimeString)) {
				try {
					fromDateTime = format.parse(fromDateTimeString);
				} catch (ParseException pe) {
					throw new SandataRuntimeException(exchange,
							format("Schedule From Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
									fromDateTimeString));
				}

				// Validate schedule to date time
				String toDateTimeString = (String) mcl.get(7);
				if (!StringUtil.IsNullOrEmpty(toDateTimeString)) {
					try {
						toDateTime = format.parse(toDateTimeString);
					} catch (ParseException pe) {
						throw new SandataRuntimeException(exchange,
								format("Schedule To Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
										toDateTimeString));
					}
				} else {
					throw new SandataRuntimeException(exchange, "To Date Time (to_date_time) is required!");
				}

				if (toDateTime.before(fromDateTime)) {
					throw new SandataRuntimeException(exchange,
							format("To Date Time (to_date_time) [%s] IS LESS THAN From Date Time (from_date_time) [%s]",
									toDateTimeString, fromDateTimeString));
				}
			}

			Response response = oracleDataService.getVisitsForPatient(
					patientId,
					bsnEntId,
					fromDateTime,
					toDateTime,
					sortOn,
					direction,
					page,
					pageSize);

			exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
			exchange.getIn().setHeader("PAGE", response.getPage());
			exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
			exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
			exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

			exchange.getIn().setBody(response.getData());
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	@Override
	public void findVisit(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {

			MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

			boolean bHasAtLeastOneParam = false;

			String patientFirstName = (String) params.get(0);
			if (patientFirstName != null && patientFirstName.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String patientLastName = (String) params.get(1);
			if (patientLastName != null && patientLastName.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String patientId = (String) params.get(2);
			if (patientId != null && patientId.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String patientCoordinator = (String) params.get(16);
			if (patientCoordinator != null && patientCoordinator.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String staffFirstName = (String) params.get(3);
			if (staffFirstName != null && staffFirstName.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String staffLastName = (String) params.get(4);
			if (staffLastName != null && staffLastName.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String staffId = (String) params.get(5);
			if (staffId != null && staffId.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String staffCoordinator = (String) params.get(17);
			if (staffCoordinator != null && staffCoordinator.length() != 0) {
				bHasAtLeastOneParam = true;
			}

			String fromDateTimeStr = (String) params.get(6);
			String toDateTimeStr = (String) params.get(7);
			DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);

			// Validating fromDateTime
			Date fromDateTime;
			if (!StringUtil.IsNullOrEmpty(fromDateTimeStr)) {

				try {
					fromDateTime = format.parse(fromDateTimeStr);
				} catch (ParseException pe) {
					throw new SandataRuntimeException(exchange,
							format("From Date Time: [%s]: Is NOT a valid date time and/or the format is incorrect!",
									fromDateTimeStr));
				}
			} else {
				throw new SandataRuntimeException(exchange, "From/To Dates are required!");
			}

			// Validating toDateTime
			Date toDateTime;
			if (!StringUtil.IsNullOrEmpty(toDateTimeStr)) {

				try {
					toDateTime = format.parse(toDateTimeStr);
				} catch (ParseException pe) {
					throw new SandataRuntimeException(exchange,
							format("To Date Time: [%s]: Is NOT a valid date time and/or the format is incorrect!",
									toDateTimeStr));
				}
			} else {
				throw new SandataRuntimeException(exchange, "From/To Dates are required!");
			}

			if (fromDateTime != null && toDateTime != null) {
				bHasAtLeastOneParam = true;
			}

			if (!bHasAtLeastOneParam) {
				throw new SandataRuntimeException(exchange, "At least one parameter is required!");
			}

			int page = (Integer) params.get(8);
			int page_size = (Integer) params.get(9);

			String orderByColumn = (String) params.get(10);
			String orderByDirection = (String) params.get(11);

			String bsnEntId = (String) params.get(12);
			if (bsnEntId == null || bsnEntId.length() == 0) {

				throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
			}

			String status = (String) params.get(13);

			Double scheduledHours = (Double) params.get(14);

			Long visitSK = (Long) params.get(15);

			List<BigInteger> exceptionIdList = (List<BigInteger>) params.get(18);

			Boolean excpOnlyFlag = (Boolean) params.get(19);

			Response response = oracleDataService.findVisit(patientFirstName, patientLastName, patientId, patientCoordinator,
					staffFirstName, staffLastName, staffId, staffCoordinator, bsnEntId, fromDateTime, toDateTime,
					orderByColumn, orderByDirection, status, scheduledHours, page, page_size, visitSK, exceptionIdList, excpOnlyFlag);

			exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
			//dmr--exchange.getIn().setHeader("PAGE", response.getPage());
			//dmr--exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
			exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
			exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

			exchange.getIn().setBody(response.getData());
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	/**
	 * Find Visit details by visitSk
	 *
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
	public void findVisitDetails(final Exchange exchange) throws SandataRuntimeException {
		SandataLogger logger = CreateLogger(exchange);

		logger.start();
		try {
			String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
			if (StringUtil.IsNullOrEmpty(bsnEntId)) {
				throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) is required!");
			}

			Long visitSk = exchange.getIn().getHeader("visit_sk", Long.class);

			int page = exchange.getIn().getHeader("page", Integer.class);
			int pageSize = exchange.getIn().getHeader("page_size", Integer.class);
			String orderByColumn = exchange.getIn().getHeader("sort_on", String.class);
			String orderByDirection = exchange.getIn().getHeader("direction", String.class);

			Response response = oracleDataService.findVisitDetails(bsnEntId, visitSk, orderByColumn, orderByDirection, page, pageSize);

			exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
			exchange.getIn().setHeader("PAGE", page);
			exchange.getIn().setHeader("PAGE_SIZE", pageSize);
			exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
			exchange.getIn().setHeader("ORDER_BY_DIRECTION", orderByDirection.toUpperCase());

			exchange.getIn().setBody(response.getData());

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	/**
	 * Update Visit Details (Service, Adjusted In, Adusted Out, Bill Hours, )
	 *
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
	public void updateVisitDetails(final Exchange exchange) throws SandataRuntimeException {
		SandataLogger logger = CreateLogger(exchange);

		logger.start();
		try {
			FindVisitDetailsResult visitDetails = exchange.getIn().getBody(FindVisitDetailsResult.class);
			if (visitDetails == null) {
				throw new SandataRuntimeException("Visit Details cannot be null");
			}

			if (visitDetails.getVisitSk() == null) {
				throw new SandataRuntimeException("VisitSk cannot be null");
			}

			AuditVisit visitBeforeUpdate = oracleDataService.getAuditVisit(visitDetails.getVisitSk().longValue());

			long result = oracleDataService.updateVisitDetails(visitDetails);

			AuditVisit visitAfterUpdate = oracleDataService.getAuditVisit(visitDetails.getVisitSk().longValue());
			// oracleDataService.insertAuditVisitHistory((String)exchange.getIn().getHeader("SandataGUID"), visitBeforeUpdate, visitAfterUpdate);

			exchange.getIn().setBody(result);

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	@Override
	public void get(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {
			String operationName = (String) exchange.getIn().getHeader("operationName");

			String[] methodParts = operationName.split("_");

			String packageName = methodParts[0] + "_" + methodParts[1];
			String methodName = methodParts[2];
			String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

			Object result;
			Object body = exchange.getIn().getBody();

			if (exchange.getIn().getHeader("sequence_key") != null) {
				long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

				result = oracleDataService.executeGet(
						packageName,
						methodName,
						className,
						sequenceKey
						);

				ArrayList result2 = (ArrayList<Object>) result;

				if (result2 != null && result2.size() > 0) {
					result = result2.get(0);

					if (methodParts[3].equals("Visit")) {

						exchange.getIn().setBody(getRelatedVisitData((Visit) result));
					} else if (methodParts[3].equals("VisitTaskList")) {

						//dmr GEOR-1034: Extended VisitTaskList to logically determine if the task is accepted.
						VisitTaskListExt visitTaskListExt = new VisitTaskListExt((VisitTaskList) result);
						exchange.getIn().setBody(visitTaskListExt);
					} else {
						exchange.getIn().setBody(result);
					}
				} else {
					exchange.getIn().setBody(null);
				}
			} else {
				MessageContentsList mcl = (MessageContentsList) body;

				String[] params = new String[mcl.size()];

				for (int index = 0; index < mcl.size(); index++) {
					params[index] = (String) mcl.get(index);
				}

				result = oracleDataService.executeGet(
						packageName,
						methodName,
						className,
						params
						);

				ArrayList result2 = (ArrayList<Object>) result;
				//GEOR-4294: Thanh Le 5/31/2016: Visit GET returns Note Object instead of collection of objects
				if (methodParts.length >= 5 && methodParts[4].equals("List")) {
					exchange.getIn().setBody(result2);
				} else if (result2 != null && result2.size() > 0) {
					result = result2.get(0);

					//dmr GEOR-1034: Extended VisitTaskList to logically determine if the task is accepted.
					if (methodParts[3].equals("VisitTaskList")) {
						List<VisitTaskListExt> visitTaskListExtList = new ArrayList<>();
						List<VisitTaskList> visitTaskLists = null;
						if (result instanceof List) {
							visitTaskLists = (List<VisitTaskList>) result;
							for (VisitTaskList visitTaskList : visitTaskLists) {

								VisitTaskListExt visitTaskListExt = new VisitTaskListExt(visitTaskList);
								visitTaskListExtList.add(visitTaskListExt);
							}
						} else if (result instanceof VisitTaskList) {
							//handle non list return
							VisitTaskListExt visitTaskListExt = new VisitTaskListExt((VisitTaskList) result);
							visitTaskListExtList.add(visitTaskListExt);
						}

						exchange.getIn().setBody(visitTaskListExtList);
					} else if (methodParts[3].equals("Visit")) {
						exchange.getIn().setBody(getRelatedVisitData((Visit) result));
					} else if (
							methodParts[3].equals("VisitException")
							|| methodParts[3].equals("VisitEvent")
							) {
						exchange.getIn().setBody(result2);
					} else {
						exchange.getIn().setBody(result);
					}
				} else {
					exchange.getIn().setBody(null);
				}
			}
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);
		} finally {
			logger.stop();
		}
	}

	private VisitExt getRelatedVisitData(final Visit visit) {

		List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
				"PKG_VISIT",
				"getVisitEvnt",
				"com.sandata.lab.data.model.dl.model.VisitEvent",
				visit.getVisitSK());

		for (VisitEvent item : visitEvents) {
			visit.getVisitEvent().add(item);
		}

		List<VisitNote> visitNotesList = (List<VisitNote>) oracleDataService.getEntitiesForId(
				"SELECT * FROM VISIT_NOTE " +
						"WHERE VISIT_SK = ? AND BE_ID = ? " +
						"  AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
						"ORDER BY REC_CREATE_TMSTP ASC,VISIT_NOTE_SK ASC",
						"com.sandata.lab.data.model.dl.model.VisitNote",
						visit.getVisitSK(),
						visit.getBusinessEntityID());

		for (VisitNote item : visitNotesList) {
			visit.getVisitNote().add(item);
		}

		List<VisitException> visitExceptionList = (List<VisitException>) oracleDataService.executeGet(
				"PKG_VISIT",
				"getVisitExcp",
				"com.sandata.lab.data.model.dl.model.VisitException",
				visit.getVisitSK());

		// converts a list of VisitException to a list of VisitExceptionExt
		// to add exception name and description
		visit.getVisitException().addAll(oracleDataService.getVisitExceptionExts(visitExceptionList));

		List<VisitActivity> visitActivityList = (List<VisitActivity>) oracleDataService.executeGet(
				"PKG_VISIT",
				"getVisitActivity",
				"com.sandata.lab.data.model.dl.model.VisitActivity",
				visit.getVisitSK());

		for (VisitActivity item : visitActivityList) {
			visit.getVisitActivity().add(item);
		}

		//TODO: Need to fix autogen of model to handle extended objects.
		//dmr--REMINDER: GEOR-1053: Need to extend Visit model to support extended VisitTaskList. When generating the Visit Model
		//                          make sure not to overwrite this extended object.
		//thanh.le change the package name
		List<VisitTaskList> visitTaskList = (List<VisitTaskList>) oracleDataService.executeGet(
				"PKG_VISIT",
				"getVisitTaskLst",
				"com.sandata.lab.data.model.dl.model.VisitTaskList",
				visit.getVisitSK());

		for (VisitTaskList item : visitTaskList) {

			//dmr--REMINDER: GEOR-1053:
			VisitTaskListExt visitTaskListExt = new VisitTaskListExt(item);
			visit.getVisitTaskList().add(visitTaskListExt);
		}

		// get the timezone from schedule event
		VisitExt visitExt = new VisitExt(visit);
		String sqlGetSchedEvent = "SELECT * FROM SCHED_EVNT WHERE SCHED_EVNT_SK = ? ";
		List<ScheduleEvent> shedEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(
				sqlGetSchedEvent,
				"com.sandata.lab.data.model.dl.model.ScheduleEvent",
				visitExt.getScheduleEventSK());
		if (shedEventList != null && shedEventList.size() > 0) {
			visitExt.setTimezoneName(shedEventList.get(0).getTimezoneName());
		}

		return visitExt;
	}

	public void insertVisitTaskDetailsForVisit(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {
			List<VisitTaskList> visitTaskLists = (ArrayList<VisitTaskList>) exchange.getIn().getBody();

			Object result = execute(_PACKAGE_NAME, "insertVisitTaskLst", visitTaskLists, true);

			exchange.getIn().setBody(1);
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	public void updateVisitTaskDetailsForVisit(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {
			List<VisitTaskList> visitTaskLists = (ArrayList<VisitTaskList>) exchange.getIn().getBody();

			Object result = execute(_PACKAGE_NAME, "updateVisitTaskLst", visitTaskLists, false);

			exchange.getIn().setBody(1);
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}


	public void getVisitTaskDetailsForVisit(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {
			BigInteger visitSk = (BigInteger) exchange.getIn().getHeader("visit_sk");

			Object result = oracleDataService.getVisitTaskList(visitSk);

			exchange.getIn().setBody(result);
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	public void deleteVisitTasks(Exchange exchange){

		SandataLogger logger = CreateLogger(exchange);

		try{

			List<Long> sks = (ArrayList)exchange.getIn().getHeader("visit_task_lst_sk");

			int result = oracleDataService.deleteVisitTasks(sks);

			exchange.getIn().setBody(result);

		}catch (Exception e){

			e.printStackTrace();
			throw new SandataRuntimeException(e.getMessage());
		}
	}

	@Override
	public void delete(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {
			String operationName = (String) exchange.getIn().getHeader("operationName");

			String[] methodParts = operationName.split("_");

			String packageName = methodParts[0] + "_" + methodParts[1];
			String methodName = methodParts[2];
			String typeName = methodParts[3];

			long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

			// getting audit data before deleting Exception, Note and Task
			AuditVisit visitBeforeDelete = getAuditVisitBeforeDelete(typeName, sequenceKey);

			long result = oracleDataService.execute(
					packageName,
					methodName,
					sequenceKey
					);

			// TODO: should use ActiveMQ for inserting Audit Log
			// inserting audit data after deleting, Exception, Note and Task
			if (visitBeforeDelete != null) {
				AuditVisit visitAfterDelete = getAuditVisitAfterDelete(typeName, visitBeforeDelete.getVisitSk());
				// oracleDataService.insertAuditVisitHistory((String) exchange.getIn().getHeader("SandataGUID"), visitBeforeDelete, visitAfterDelete);
			}

			exchange.getIn().setBody(result);
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);

		} finally {
			logger.stop();
		}
	}

	@Override
	public void update(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		Connection connection = null;

		logger.start();

		try {
			Object data = exchange.getIn().getBody();

			connection = oracleDataService.getOracleConnection();
			connection.setAutoCommit(false);

			if (data instanceof VisitHistory) {
				VisitHistory visitHistory = (VisitHistory) data;
				if (visitHistory.getRecordCreateTimestampHistory() == null) {
					visitHistory.setRecordCreateTimestampHistory(new Date());
				}

				if (visitHistory.getActionCode() == null) {
					//As this is an Update method
					visitHistory.setActionCode(ActionCode.U);
				}
			}

			if (data instanceof VisitException) {
				VisitException visitException = (VisitException) data;

				if (visitException.getVisitExceptionSK() == null) {
					throw new SandataRuntimeException("VisitExceptionSK is required on update.");
				}

				if (visitException.getExceptionID() == null) {
					throw new SandataRuntimeException("ExceptionID is required.");
				}

				if (visitException.getVisitSK() == null) {
					throw new SandataRuntimeException("VisitSK is required.");
				}

				long visitExceptionSK = visitException.getVisitExceptionSK().longValue();
				long excpId = visitException.getExceptionID().longValue();
				long visitSk = visitException.getVisitSK().longValue();

				String sql =
						"SELECT * FROM VISIT_EXCP "
								+ "WHERE EXCP_ID = ? AND VISIT_SK = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) "
								+ "    AND VISIT_EXCP_SK <> ?";

				if (hasActiveVisitExceptionRecords(sql, excpId, visitSk, visitExceptionSK)) {
					throw new SandataRuntimeException(String.format(
							"There is active record of exceptionID = %d and visitSK = %d in the system. Update is not allowed.",
							excpId, visitSk));
				}
			}

			// getting audit data before updating Visit, Exception, Note and Task
			AuditVisit visitBeforeUpdate = getAuditVisit(data, 0);

			long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999, null, exchange, logger);
			if (returnVal > 0) {
				if (data instanceof Visit) {
					Visit visit = (Visit) data;
					BigInteger visitSk = visit.getVisitSK();
					if (visitSk == null) {
						logger.error("VISIT_SK WAS NULL ON UPDATE!");
					} else {
						if (producerVisitHandler == null) {
							producerVisitHandler = new ProducerVisitHandler(exchange);
						}
						producerVisitHandler.sendVisit(visit);
					}
				} else if (data instanceof VisitEvent) {
					int resultVisitEvent = updateVisitAccutalTimesFromUI(connection, (VisitEvent) data);
					if (resultVisitEvent < 0) {
						throw new SandataRuntimeException("Update Visit acctual time was not successful");
					}
				}

				connection.commit();

				// inserting audit data after updating Visit, Exception, Note and Task
				AuditVisit visitAfterUpdate = getAuditVisit(data, returnVal);
				// oracleDataService.insertAuditVisitHistory((String) exchange.getIn().getHeader("SandataGUID"), visitBeforeUpdate, visitAfterUpdate);

				exchange.getIn().setBody(returnVal);
			} else {
				throw new SandataRuntimeException(exchange, "Update was not successful!");
			}
		} catch (Exception e) {

			if (connection != null) {

				try {
					connection.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);
		} finally {

			this.oracleDataService.closeOracleConnection(connection);

			logger.stop();
		}
	}


	@Override
	public void insert(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		Connection connection = null;

		logger.start();

		try {
			Object data = exchange.getIn().getBody();

			connection = oracleDataService.getOracleConnection();
			connection.setAutoCommit(false);

			if (data instanceof VisitHistory) {
				VisitHistory visitHistory = (VisitHistory) data;
				if (visitHistory.getRecordCreateTimestampHistory() == null) {
					visitHistory.setRecordCreateTimestampHistory(new Date());
				}

				if (visitHistory.getActionCode() == null) {
					//As this is an insert method
					visitHistory.setActionCode(ActionCode.I);
				}
			}

			if (data instanceof VisitException) {
				VisitException visitException = (VisitException) data;

				if (visitException.getExceptionID() == null) {
					throw new SandataRuntimeException("ExceptionID is required.");
				}

				if (visitException.getVisitSK() == null) {
					throw new SandataRuntimeException("VisitSK is required.");
				}

				long excpId = visitException.getExceptionID().longValue();
				long visitSk = visitException.getVisitSK().longValue();

				String sql =
						"SELECT * FROM VISIT_EXCP "
								+ "WHERE EXCP_ID = ? AND VISIT_SK = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

				if (hasActiveVisitExceptionRecords(sql, excpId, visitSk)) {
					throw new SandataRuntimeException(String.format(
							"There is active record of exceptionID = %d and visitSK = %d in the system. Insert is not allowed.",
							excpId, visitSk));
				}
			}

			// getting audit data before inserting Visit, Exception, Note and Task
			AuditVisit visitBeforeInsert = getAuditVisit(data, -1);

			long returnVal = executeRecursive(connection, data, true, -999, null, exchange, logger);
			if (returnVal > 0) {
				if (data instanceof Visit) {
					Visit visit = (Visit) data;
					BigInteger visitSk = BigInteger.valueOf(returnVal);
					visit.setVisitSK(visitSk);

					if (producerVisitHandler == null) {
						producerVisitHandler = new ProducerVisitHandler(exchange);
					}

					producerVisitHandler.sendVisit(visit);

				} else if (data instanceof VisitEvent) {
					int resultVisitEvent = updateVisitAccutalTimesFromUI(connection, (VisitEvent) data);
					if (resultVisitEvent < 0) {
						throw new SandataRuntimeException("Update Visit acctual time was not successful");
					}
				}

				connection.commit();

				// TODO: should use ActiveMQ for inserting Audit Log
				// inserting audit data after inserting Visit, Exception, Note and Task
				AuditVisit visitAfterInsert = getAuditVisit(data, returnVal);
				// oracleDataService.insertAuditVisitHistory((String) exchange.getIn().getHeader("SandataGUID"), visitBeforeInsert, visitAfterInsert);

				exchange.getIn().setBody(returnVal);
			} else {
				throw new SandataRuntimeException(exchange, "Insert was not successful!");
			}
		} catch (Exception e) {

			if (connection != null) {

				try {
					connection.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg, e);
		} finally {

			this.oracleDataService.closeOracleConnection(connection);

			logger.stop();
		}
	}

	private AuditVisit getAuditVisitBeforeDelete(String typeName, long sequenceKey) {

		switch (typeName) {
		case "VisitException":
			return getAuditVisitByVisitExceptionSk(sequenceKey);

		case "VisitNote":
			return getAuditVisitByVisitNoteSk(sequenceKey);

		case "VisitTaskList":
			return getAuditVisitByVisitTaskListSk(sequenceKey);
		}

		return null;
	}

	private AuditVisit getAuditVisitByVisitExceptionSk(long visitExceptionSk) {
		List<VisitException> result = (ArrayList<VisitException>) oracleDataService.executeGet(
				"PKG_VISIT", "getVisitExcp", "com.sandata.lab.data.model.dl.model.VisitException", visitExceptionSk);

		if (result != null && result.size() > 0) {
			return oracleDataService.getAuditVisitForExceptions(result.get(0).getVisitSK().longValue());
		}

		return null;
	}

	private AuditVisit getAuditVisitByVisitNoteSk(long visitNoteSk) {
		List<VisitNote> result = (ArrayList<VisitNote>) oracleDataService.executeGet(
				"PKG_VISIT", "getVisitNote", "com.sandata.lab.data.model.dl.model.VisitNote", visitNoteSk);

		if (result != null && result.size() > 0) {
			return oracleDataService.getAuditVisitForNotes(result.get(0).getVisitSK().longValue());
		}

		return null;
	}

	private AuditVisit getAuditVisitByVisitTaskListSk(long visitTaskListSk) {
		List<VisitTaskList> result = (ArrayList<VisitTaskList>) oracleDataService.executeGet(
				"PKG_VISIT", "getVisitTaskLst", "com.sandata.lab.data.model.dl.model.VisitTaskList", visitTaskListSk);

		if (result != null && result.size() > 0) {
			return oracleDataService.getAuditVisitForTasks(result.get(0).getVisitSK().longValue());
		}

		return null;
	}

	private AuditVisit getAuditVisitAfterDelete(String typeName, long visitSk) {
		switch (typeName) {
		case "VisitException":
			return oracleDataService.getAuditVisitForExceptions(visitSk);

		case "VisitNote":
			return oracleDataService.getAuditVisitForNotes(visitSk);

		case "VisitTaskList":
			return oracleDataService.getAuditVisitForTasks(visitSk);
		}

		return null;
	}

	private AuditVisit getAuditVisit(Object data, long visitSk) {
		AuditVisit auditVisit = null;
		if (data instanceof Visit) {
			if (visitSk > 0) {
				// after insert / update visit
				auditVisit = oracleDataService.getAuditVisit(visitSk);
			} else if (visitSk == 0) {
				// before update existing visit
				Visit visit = (Visit) data;
				auditVisit = oracleDataService.getAuditVisit(visit.getVisitSK().longValue());
			}
			// return null if before insert Visit

		} else if (data instanceof VisitException) {
			VisitException visitException = (VisitException) data;
			auditVisit = oracleDataService.getAuditVisitForExceptions(visitException.getVisitSK().longValue());

		} else if (data instanceof VisitNote) {
			VisitNote visitNote = (VisitNote) data;
			auditVisit = oracleDataService.getAuditVisitForNotes(visitNote.getVisitSK().longValue());

		} else if (data instanceof VisitTaskList) {
			VisitTaskList visitTaskList = (VisitTaskList) data;
			auditVisit = oracleDataService.getAuditVisitForTasks(visitTaskList.getVisitSK().longValue());
		}

		return auditVisit;
	}

	private boolean hasActiveVisitExceptionRecords(String sql, Object... params) {
		List<VisitException> visitExceptions = (List<VisitException>) oracleDataService.getEntitiesForId(sql,
				"com.sandata.lab.data.model.dl.model.VisitException", params);

		return visitExceptions != null && visitExceptions.size() > 0;
	}

	private void setSk(final Object jpubType, long sequenceKey, String setSkMethodName) throws Exception {

		if (sequenceKey <= 0) {
			return;
		}

		try {

			Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
			method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal,
			String bsnEntId, Exchange exchange, SandataLogger logger)
					throws SandataRuntimeException {

		try {
			// GEOR-6612
			String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
			if (timezoneFieldErrMsg.length() > 0) {
				throw new SandataRuntimeException(timezoneFieldErrMsg);
			}

			OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

			Object jpubType = new DataMapper().map(data);

			setSk(jpubType, returnVal, "setVisitSk");

            setTimeStamp(jpubType, "setRecEffTmstp");
            setTimeStamp(jpubType, "setRecUpdateTmstp");

			long result = 0;

			if (bShouldInsert) {

                setTimeStamp(jpubType, "setRecCreateTmstp");

				result = oracleDataService.execute(
						connection,
						oracleMetadata.packageName(),
						oracleMetadata.insertMethod(),
						jpubType
						);
			} else {

				BigInteger skValue = DataMapper.GetSK(data);
             

				if (skValue == null) {
					throw new SandataRuntimeException(String.format("%s: executeRecursive: skValue == NULL: [Type: %s]",
							getClass().getName(), data.getClass().getName()));
				}

				// Since this is a recursive call, the first data element will have the bsnEntId, the children may not but they are related by the same parent SK
				if (StringUtil.IsNullOrEmpty(bsnEntId)) {
					// Init once
					bsnEntId = DataMapper.GetBusinessEntityID(data);
				}

				returnVal = skValue.intValue();

				Compare compare = null;
				// bsnEntId is require, so only process if it exists!
				if (!StringUtil.IsNullOrEmpty(bsnEntId)) {
					//TODO: Need to consider different approach since this may slow the service down
					//dmr--For audit reasons, we need to get the current record before doing an update to it.
					// NOTE: Visit records are NOT historical. Making them historical, we maybe able to use a different approach.
					List currentStateList = (List) oracleDataService.executeGet(
							oracleMetadata.packageName(),
							oracleMetadata.getMethod(),
							data.getClass().getName(),
							skValue.intValue()
							);

					if (currentStateList.size() < 1) {
						// Since this is an update, this should not happen!
						throw new SandataRuntimeException(String.format("%s: executeRecursive: currentStateList is EMPTY!", getClass().getName()));
					}

					compare = new Compare();
					compare.setOriginal(currentStateList.get(0));
					compare.setUpdated(data);

				} else {
					logger.warn(String.format("%s: executeRecursive: bsnEntId is NULL or EMPTY!", data.getClass().getName()));
				}

				// UPDATE
				result = oracleDataService.execute(
						connection,
						oracleMetadata.packageName(),
						oracleMetadata.updateMethod(),
						jpubType
						);

				if (result > 0 && compare != null) {

					exchange.getIn().setHeader("AuditBsnEntID", bsnEntId);
					exchange.getIn().setHeader("AuditSkValue", skValue.longValue());

					String queueName = Messaging.Names.COMPONENT_TYPE_QUEUE + Audit.EVENT.AUDIT_CHANGED_EVENT.toString();

					logger.trace(String.format("%s: executeRecursive: [Result: %d]: [Queue: %s]: Sending audit message ...",
							getClass().getName(), result, queueName));

					if (producerVisitHandler == null) {
						producerVisitHandler = new ProducerVisitHandler(exchange);
					}
					producerVisitHandler.queueData(compare, queueName, exchange.getIn().getHeaders(), logger);
				}
			}

			if (result > 0) {

				if (returnVal == -999) {
					returnVal = result;
				}

				// Check if there are any lists that need to be inserted
				for (Field field : data.getClass().getDeclaredFields()) {

					field.setAccessible(true);

					Object property = field.get(data);
					if (property != null && property instanceof List) {

						List list = (List) property;
						for (Object object : list) {

							// Try to insert the object!

							// WARNING: RECURSIVE!!!!
							long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal, bsnEntId, exchange, logger);
							if (insertResponse == -1) {
								if (bShouldInsert) {
									throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
											object.getClass().getName()));
								} else {
									throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
											object.getClass().getName()));
								}
							}
						}
					}
				}

				// SUCCESS
				return returnVal;

			} // if (result > 0)

			// FAILED
			return -1;
		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);
		}
	}

	private int updateVisitAccutalTimesFromUI(Connection connection, VisitEvent visitEvent) {
		CallableStatement callableStatement = null;
		try {
			if (visitEvent != null && visitEvent.getVisitSK() != null
					&& visitEvent.getVisitEventDatetime() != null
					&& VisitEventTypeCode.UI.equals(visitEvent.getVisitEventTypeCode())) {

				BigDecimal visitSk = BigDecimal.valueOf(visitEvent.getVisitSK().longValue());
				Date visitEventDateTime = visitEvent.getVisitEventDatetime();

				String sqlGetScheduleEvent = "SELECT SE.* "
						+ "FROM SCHED_EVNT SE "
						+ "    INNER JOIN VISIT V ON SE.SCHED_EVNT_SK = V.SCHED_EVNT_SK AND SE.BE_ID = V.BE_ID "
						+ "WHERE (TO_CHAR(SE.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SE.CURR_REC_IND = '1') "
						+ "    AND V.VISIT_SK = ? ";
				List<ScheduleEvent> schedEvents = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(sqlGetScheduleEvent,
						connection,
						"com.sandata.lab.data.model.dl.model.ScheduleEvent",
						visitSk);

				if (schedEvents != null && !schedEvents.isEmpty()) {
					ScheduleEvent schedEvent = schedEvents.get(0);
					if (schedEvent.getTimezoneName() != null && visitEvent.getTimezoneName() != null
							&& !schedEvent.getTimezoneName().equals(visitEvent.getTimezoneName())) {
						// convert from visit event timezone to Visit's timezone
						visitEventDateTime = new LocalDateTime(visitEventDateTime.getTime())
								.toDateTime(DateTimeZone.forID(visitEvent.getTimezoneName()))
								.toDateTime(DateTimeZone.forID(schedEvent.getTimezoneName()))
								.toLocalDateTime().toDate();
					}
				}

				String updateMethod = null;
				if (visitEvent.isCallInIndicator() != null && visitEvent.isCallInIndicator()
						&& visitEvent.isCallOutIndicator() != null && visitEvent.isCallOutIndicator()) {
					throw new SandataRuntimeException("A VisitEvent cannot be both call in and call out");
				} else if (visitEvent.isCallInIndicator() != null && visitEvent.isCallInIndicator()) {
					updateMethod = "UPDATE_VISIT_ACT_START_TMSTP";
				} else if (visitEvent.isCallOutIndicator() != null && visitEvent.isCallOutIndicator()) {
					updateMethod = "UPDATE_VISIT_ACT_END_TMSTP";
				} else {
					// stop updating Visit acutal time due to neither call in nor call out
					return 0;
				}

				String callMethod = String.format("{?=call PKG_VISIT_UTIL.%s(?,?)}", updateMethod);
				callableStatement = connection.prepareCall(callMethod);
				callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

				int index = 2;
				callableStatement.setTimestamp(index++, new Timestamp(visitEventDateTime.getTime()));
				callableStatement.setBigDecimal(index++, BigDecimal.valueOf(visitEvent.getVisitSK().longValue()));

				callableStatement.execute();

				return (Integer) callableStatement.getObject(1);
			}

			return 0;
		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public OracleDataService getOracleDataService() {
		return oracleDataService;
	}

	public void setOracleDataService(OracleDataService oracleDataService) {
		this.oracleDataService = oracleDataService;
	}

	public void getVisitNoteList(Exchange exchange) throws SandataRuntimeException {
		SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

		logger.start();

		try {


			Long visitSk = Long.valueOf(exchange.getIn().getHeader("visit_sk").toString());
			if (visitSk == null || visitSk <= 0) {
				throw new SandataRuntimeException("VisitSK (visit_sk) is required!");
			}

			String bsntId = (String) exchange.getIn().getHeader("bsn_ent_id");

			if (visitSk == null) {
				throw new SandataRuntimeException("Bussiness Entity ID (bsn_ent_id) is required!");
			}

			Object response = oracleDataService.getVisitNoteList(visitSk, bsntId);

			exchange.getIn().setBody(response);

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);

		} finally {
			logger.stop();
		}

	}

	public void updateVVBillableHrs(Exchange exchange) {
		SandataLogger logger = OracleDataLogger.CreateLogger(exchange);
		logger.start();
		try {
			Long visitSk = Long.valueOf(exchange.getIn().getHeader("visit_sk").toString());
			if (visitSk == null || visitSk <= 0) {
				throw new SandataRuntimeException("VisitSK (visit_sk) is required!");
			}
			long response = oracleDataService.getVisitWithVVBillableHrs(visitSk);
			exchange.getIn().setBody(response);
		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);
		} finally {
			logger.stop();
		}
	}


	public long execute(String packageName, String methodName, Object data, boolean isInsert) throws Exception {

		SandataLogger logger = OracleDataLogger.CreateLogger();

		Connection connection = null;

		try {

			connection = oracleDataService.getOracleConnection();
			connection.setAutoCommit(false);

			long returnVal = 0;

			if ((data instanceof List)) {

				for (Object object : (List) data) {
					returnVal = executeRecursive(connection, object, isInsert, -999);
					if (returnVal < 0) {
						throw new SandataRuntimeException("Insert was not successful!");
					}
				}
			} else {

				returnVal = executeRecursive(connection, data, isInsert, -999);
			}

			if (returnVal > 0) {
				connection.commit();
			} else {
				throw new SandataRuntimeException("Insert was not successful!");
			}

			return returnVal;
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

			connection.close();

			logger.stop();
		}
	}

	/**
	 * 
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
	public void getPatientsForSearch(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {

            String beId = (String)exchange.getIn().getHeader("bsn_ent_id");
			String lastName = (String)exchange.getIn().getHeader("last_name");
			String firstName = (String)exchange.getIn().getHeader("first_name");
			String patientId = (String)exchange.getIn().getHeader("patient_id");
			//        	String serviceName = (String)exchange.getIn().getHeader("svc_name");
			List<String> serviceNameList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("svc_name"));

			String visitDateTimeString = (String)exchange.getIn().getHeader("visit_date_time");
			int page = (int)exchange.getIn().getHeader("page");
			int pageSize = (int)exchange.getIn().getHeader("page_size");
			String sortOn = (String)exchange.getIn().getHeader("sort_on");
			String direction = (String)exchange.getIn().getHeader("direction");

			Date visitDateTime = null;

			DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);
			if (!StringUtil.IsNullOrEmpty(visitDateTimeString)) {
				try {
					visitDateTime = format.parse(visitDateTimeString);
				} catch (ParseException pe) {
					throw new SandataRuntimeException(exchange,
							format("Visit Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
									visitDateTimeString));
				}
			}

			if (StringUtil.IsNullOrEmpty(beId)) {
				throw new SandataRuntimeException("Business Entity ID is required!");
			}


			Response response = oracleDataService.findPatientsForSearch(
					beId,
					lastName,
					firstName,
					patientId,
					serviceNameList,
					visitDateTimeString,
					page,
					pageSize,
					sortOn,
					direction
					);

			exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
			exchange.getIn().setHeader("PAGE", response.getPage());
			exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
			exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
			exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

			exchange.getIn().setBody(response.getData());
			
		} catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg);

		} finally {
			logger.stop();
		}
	}    

	/**
	 * 
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
	public void getStaffsForSearch(Exchange exchange) throws SandataRuntimeException {

		SandataLogger logger = CreateLogger(exchange);

		logger.start();

		try {

            String beId = (String)exchange.getIn().getHeader("bsn_ent_id");
			String lastName = (String)exchange.getIn().getHeader("last_name");
			String firstName = (String)exchange.getIn().getHeader("first_name");
			String staffId = (String)exchange.getIn().getHeader("staff_id");
			//        	String staffPositionName = (String)exchange.getIn().getHeader("staff_position_name");
			List<String> staffPositionNameList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("staff_position_name"));
			String visitDateTimeString = (String)exchange.getIn().getHeader("visit_date_time");
			int page = (int)exchange.getIn().getHeader("page");
			int pageSize = (int)exchange.getIn().getHeader("page_size");
			String sortOn = (String)exchange.getIn().getHeader("sort_on");
			String direction = (String)exchange.getIn().getHeader("direction");

			Date visitDateTime = null;

			DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);
			if (!StringUtil.IsNullOrEmpty(visitDateTimeString)) {
				try {
					visitDateTime = format.parse(visitDateTimeString);
				} catch (ParseException pe) {
					throw new SandataRuntimeException(exchange,
							format("Visit Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
									visitDateTimeString));
				}
			}

			if (StringUtil.IsNullOrEmpty(beId)) {
				throw new SandataRuntimeException("Business Entity ID is required!");
			}


			Response response = oracleDataService.findStaffsForSearch(
					beId,
					lastName,
					firstName,
					staffId,
					staffPositionNameList,
					visitDateTimeString,
					page,
					pageSize,
					sortOn,
					direction                    
					);

			exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
			exchange.getIn().setHeader("PAGE", response.getPage());
			exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
			exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
			exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

			exchange.getIn().setBody(response.getData());
		}
		catch (Exception e) {

			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(exchange, errMsg);

		} finally {
			logger.stop();
		}
	}        

	private long executeRecursive(final Connection connection, final Object data, boolean bShouldInsert, long returnVal)
			throws SandataRuntimeException {

		try {
			OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

			Object jpubType = new DataMapper().map(data);


			long result = 0;

			setTimeStamp(jpubType, "setRecEffTmstp");
            setTimeStamp(jpubType, "setRecUpdateTmstp");


			if (bShouldInsert) {

				setTimeStamp(jpubType, "setRecCreateTmstp");

				result = oracleDataService.execute(
						connection,
						oracleMetadata.packageName(),
						oracleMetadata.insertMethod(),
						jpubType
						);
			} else {

				// UPDATE
				result = oracleDataService.execute(
						connection,
						oracleMetadata.packageName(),
						oracleMetadata.updateMethod(),
						jpubType
						);
			}

			if (result > 0) {

				if (returnVal == -999) {
					returnVal = result;
				}

				// Check if there are any lists that need to be inserted
				for (Field field : data.getClass().getDeclaredFields()) {

					field.setAccessible(true);

					Object property = field.get(data);
					if (property != null && property instanceof List) {

						List list = (List) property;
						for (Object object : list) {

							// Try to insert the object!

							// WARNING: RECURSIVE!!!!
							long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal);
							if (insertResponse == -1) {
								if (bShouldInsert) {
									throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
											object.getClass().getName()));
								} else {
									throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
											object.getClass().getName()));
								}
							}
						}
					}
				}

				// SUCCESS
				return returnVal;

			} // if (result > 0)

			// FAILED
			return -1;
		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);
		}
	}

	private void setTimeStamp(final Object jpubType, String setTimeStampMethodName) throws Exception {


		try {

			Method method = jpubType.getClass().getDeclaredMethod(setTimeStampMethodName, java.sql.Timestamp.class);
			method.invoke(jpubType, new java.sql.Timestamp(new Date().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
