package com.sandata.lab.rules.data.service.impl;

import static java.lang.String.format;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList;
import com.sandata.lab.data.model.dl.model.ContractExceptionList;
import com.sandata.lab.data.model.dl.model.PatientPayer;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.dl.model.extended.exception.ContractExceptionListExt;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rules.vv.fact.ScheduleEventFact;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.fact.VisitFact;
import com.sandata.lab.rules.vv.fact.PatientPhoneNumbersFact;
import com.sandata.lab.rules.vv.fact.VisitServiceValidationFact;

import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

/**
 * <p>Database services for visit verification bundles.</p>
 *
 * @author thanhxle
 */
public class VisitVerificationExceptionDataService extends VisitVerificationDataService {

	private Logger visitVerificationLogger = LoggerFactory.getLogger(VisitVerificationExceptionDataService.class);


	/**
	 *
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
	public void getVisitBySk (final Exchange exchange) throws SandataRuntimeException {

		try {

			String visitSk =  exchange.getIn().getBody(String.class);
			if (visitSk != null) {
				OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(new Visit());
				List<Visit> result =  (List<Visit>) executeGet(
						oracleMetadata.packageName(),
						oracleMetadata.getMethod(),
						"com.sandata.lab.data.model.dl.model.Visit",
						Long.valueOf(visitSk)
						);

				VisitFact visitFact = null;
				if (result != null && result.size() > 0) {
					visitFact = new VisitFact();
					visitFact.setData(result.get(0));
				}

				exchange.getIn().setBody(visitFact);
				if (visitFact != null && visitFact.getSchedEvntSk() != null) {
					exchange.getIn().setHeader("IS_PLANNED_VISIT", true);
				}

			}
		} catch (Exception e) {

			String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
			visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
					, this.getClass(), "getVisitBySk", errMsg));
			throw new SandataRuntimeException(errMsg,e);

		} 

	}

	/**
	 * Get ScheduleEvent from Visit (VISIT.SCHED_EVNT_SK)
	 *
	 * @param exchange
	 */
	public void getScheduleEventFactByVisit(final Exchange exchange) throws Exception {
		try {

			List objects = exchange.getIn().getBody(List.class);
			VisitFact visitFact = findVisitFactInEnrichedExchange(exchange);

			if (null == visitFact || null == visitFact.getSchedEvntSk()) {
				exchange.getIn().setBody(null);
				return;
			}

			List<ScheduleEvent> schedEventList = (List<ScheduleEvent>)getEntitiesForId(
					ConnectionType.COREDATA,
					"SELECT * " +
							"FROM SCHED_EVNT " +
							"WHERE SCHED_EVNT_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
							"com.sandata.lab.data.model.dl.model.ScheduleEvent",
							new Object[]{visitFact.getSchedEvntSk()});

			ScheduleEventFact schedEvntFact = null;
			if (schedEventList != null && schedEventList.size() > 0) {
				ScheduleEvent schedEvnt = schedEventList.get(0);

				schedEvntFact = new ScheduleEventFact();
				schedEvntFact.setScheduleSK(schedEvnt.getScheduleSK());
				schedEvntFact.setScheduleEventSK(schedEvnt.getScheduleEventSK());
				schedEvntFact.setScheduleEventStartDatetime(schedEvnt.getScheduleEventStartDatetime());
				schedEvntFact.setScheduleEventEndDatetime(schedEvnt.getScheduleEventEndDatetime());

			}
			exchange.getIn().setBody(schedEvntFact);

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);
		}

	}

	/**
	 * Get VisitEvent that is a Call-In (VISIT_EVNT.CALL_IN_IND = 1) from a Visit
	 *
	 * @param exchange
	 * @throws Exception
	 */
	public void getVisitEventFactByVisit(final Exchange exchange) throws Exception {
		try {

			List objects = exchange.getIn().getBody(List.class);
			VisitFact visitFact = findVisitFactInEnrichedExchange(exchange);

			if (null == visitFact || null == visitFact.getVisitSk()) {
				exchange.getIn().setBody(null);
				return;
			}

			//get latest visit event , sort by created date time descending
			List<VisitEvent> visitEventList = (List<VisitEvent>)getEntitiesForId(
					ConnectionType.COREDATA,
					"SELECT * " +
							"FROM VISIT_EVNT " +
							"WHERE VISIT_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ORDER BY REC_CREATE_TMSTP DESC ",
							"com.sandata.lab.data.model.dl.model.VisitEvent",
							new Object[]{visitFact.getVisitSk()}
					);


			VisitEventFact visitEventFact = null;
			if (visitEventList != null && visitEventList.size() > 0) {
				VisitEvent visitEvent = visitEventList.get(0);
				visitEventFact = new VisitEventFact();
				visitEventFact.setVisitEventSK(visitEvent.getVisitEventSK());
				visitEventFact.setVisitSK(visitEvent.getVisitSK());
				visitEventFact.setBusinessEntityId(visitFact.getBusinessEntityId());
				visitEventFact.setVisitEventTypeCode(visitEvent.getVisitEventTypeCode());
				visitEventFact.setCallInTime(visitEvent.getVisitEventDatetime());
				visitEventFact.setAni(visitEvent.getAutomaticNumberIdentification());
				visitEventFact.setDnis(visitEvent.getDialedNumberIdentificationService());
				visitEventFact.setStaffID(visitEvent.getStaffID());
				visitEventFact.setPatientID(visitEvent.getStaffID());
				visitEventFact.setCallInIndicator(visitEvent.isCallInIndicator());
				visitEventFact.setCallOutIndicator(visitEvent.isCallOutIndicator());

			}

			exchange.getIn().setBody(visitEventFact);

		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * @param exchange
	 * @throws SandataRuntimeException
	 */
	public void getBeExcpLst(final Exchange exchange) throws SandataRuntimeException {

		try {

			VisitFact visitFact = exchange.getIn().getHeader("visitFact", VisitFact.class);	
			if (null == visitFact || null == visitFact.getVisitSk()) {
				return;
			}

			if (StringUtil.IsNullOrEmpty(visitFact.getBusinessEntityId())) {
				throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
			}

			Object[] params = new Object[]{visitFact.getBusinessEntityId()};
			StringBuilder whereClause = new StringBuilder().append("WHERE BE_ID = ? ");


			String sql = "SELECT * FROM BE_EXCP_LST " + whereClause.toString()
			+ "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

			ArrayList<BusinessEntityExceptionList> result =
					(ArrayList<BusinessEntityExceptionList>) getEntitiesForId(ConnectionType.COREDATA, sql,
							"com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList", params);

			// update AdditionalSettings of BusinessEntityExceptionList from APP_TENANT_KEY_CONF
			ArrayList<BusinessEntityExceptionListExt> beExcpLstExts = new ArrayList<BusinessEntityExceptionListExt>();
			if (result != null && result.size() > 0) {
				for (BusinessEntityExceptionList beExcpLst : result) {
					BusinessEntityExceptionListExt beExcpLstExt = new BusinessEntityExceptionListExt(beExcpLst);
					beExcpLstExt.setAdditionalSettings(getBeExcpLstSetting(beExcpLstExt.getExceptionID().toString(), beExcpLstExt.getBusinessEntityID()));
					beExcpLstExts.add(beExcpLstExt);
				}
			}

			exchange.getIn().setBody(beExcpLstExts);

		} catch (Exception e) {
			String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
			throw new SandataRuntimeException(errMsg, e);

		}
	}


	/**
	 * @param beExcpId
	 * @param bsnEntId
	 * @return Additional settings for beExcpId
	 * @throws SandataRuntimeException
	 */
	private String getBeExcpLstSetting(String beExcpId, String bsnEntId) throws SandataRuntimeException {

		Connection connection = null;
		CallableStatement callableStatement = null;

		try {

			connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
			connection.setAutoCommit(true);

			String callMethod = "{?=call PKG_APP_UTIL.GET_BE_EXCP_LIST_SETTING(?,?)}";
			callableStatement = connection.prepareCall(callMethod);
			callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);

			int index = 2;
			callableStatement.setString(index++, beExcpId);
			callableStatement.setString(index++, bsnEntId);

			callableStatement.execute();
			return callableStatement.getString(1);

		} catch (Exception e) {

			// Rollback
			if (connection != null) {

				try {
					connection.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			throw new SandataRuntimeException(String.format("[PKG_APP_UTIL.GET_BE_EXCP_LIST_SETTING]: beExcpId=%s, bsnEntId=%s)",
					beExcpId, bsnEntId,
					e.getClass().getName(), e.getMessage()), e);

		} finally {

			// Close the Statement
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			// Close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}


	private Object getEntitiesForId(final ConnectionType connectionType, final String sql, final String className, final Object... params) throws SandataRuntimeException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = connectionPoolDataService.getConnection(connectionType);

			preparedStatement = connection.prepareStatement(sql);

			int index = 1;
			for (Object object : params) {
				preparedStatement.setObject(index++, object);
			}

			resultSet = preparedStatement.executeQuery();

			Object result = new DataMapper().map(resultSet, className);

			return result;

		} catch (Exception e) {

			throw new SandataRuntimeException(String.format("%s: %s",
					e.getClass().getName(), e.getMessage()), e);

		} finally {

			// Close the ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			// Close the statement
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			// Close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param exchange
	 */
	public void getContractExceptionListSetting(final Exchange exchange) {


		ArrayList<ContractExceptionList> result = null;
		Connection metaDataConn = null;
		CallableStatement callableStatement = null;
		ArrayList<ContractExceptionListExt> extResult = null;

		try {

			PatientPayer patientPayer = exchange.getIn().getBody(PatientPayer.class);
			if (patientPayer == null) {
				String errMsg = "Unexpected exception, patientPayer = null while getting the contract excp setting";
				visitVerificationLogger.warn(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, this.getClass(), "getContractExceptionListSetting", errMsg));
				exchange.getIn().setBody(null);
				return;
			}

			if (StringUtil.IsNullOrEmpty(patientPayer.getBusinessEntityID())) {
				String errMsg = "Bussiness entity ID (bsnt_id) is required!";
				visitVerificationLogger.warn(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, this.getClass(), "getContractExceptionListSetting", errMsg));
				exchange.getIn().setBody(null);
				return;
			}

			if (StringUtil.IsNullOrEmpty(patientPayer.getPayerID())) {

				String errMsg = "PayerID (payer_id) is required!";
				visitVerificationLogger.warn(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, this.getClass(), "getContractExceptionListSetting", errMsg));
				exchange.getIn().setBody(null);
				return;
			}

			if (StringUtil.IsNullOrEmpty(patientPayer.getContractID())) {
				String errMsg = "ContractID (contract_id) is required!";
				visitVerificationLogger.warn(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, this.getClass(), "getContractExceptionListSetting", errMsg));
				exchange.getIn().setBody(null);
				return;
			}

			result = (ArrayList) executeGet(
					ConnectionType.COREDATA,
					"PKG_AM",
					"getContrExcpLst",
					"com.sandata.lab.data.model.dl.model.ContractExceptionList",
					new String[]{patientPayer.getPayerID(), patientPayer.getContractID()}
					);


			if (result != null && result.size() > 0) {
				metaDataConn = getOracleConnection(ConnectionType.METADATA);
				String callMethod = "{?=call PKG_APP_UTIL.GET_CONTR_EXCP_LST_SETTINGS(?,?,?,?)}";

				extResult = new ArrayList<>();
				for (ContractExceptionList contractExceptionList : result) {
					ContractExceptionListExt ext = new ContractExceptionListExt(contractExceptionList);

					callableStatement = metaDataConn.prepareCall(callMethod);
					callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);

					int index = 2;
					callableStatement.setString(index++, patientPayer.getBusinessEntityID());
					callableStatement.setString(index++, ext.getPayerID());
					callableStatement.setString(index++, ext.getContractID());
					callableStatement.setString(index++, String.valueOf(ext.getExceptionID()));

					callableStatement.execute();

					ext.setAdditionalSettings(callableStatement.getString(1));
					callableStatement.close();

					extResult.add(ext);
				}

			}

			exchange.getIn().setBody(extResult);

		} catch (Exception e) {

			if (metaDataConn != null) {

				try {
					metaDataConn.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			String errMsg = format("%s: getContrExcpLst: %s", getClass().getName(), e.getMessage());
			visitVerificationLogger.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, this.getClass(), "getContractExceptionListSetting", errMsg));
			throw new SandataRuntimeException(errMsg, e);

		} finally {
			// Close the Statement
			if (callableStatement != null) {
				try {
					if (!callableStatement.isClosed()) {
						callableStatement.close();
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			// Close the connection
			if (metaDataConn != null) {
				try {
					metaDataConn.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}	


	/**
	 * Get the list of contract of a specific payer by payer id
	 * @param exchange
	 */
	public void getContractForPayer(final Exchange exchange) {

		try {

			VisitFact visitFact = findVisitFactInEnrichedExchange(exchange);

			if (visitFact != null) {
				if (StringUtil.IsNullOrEmpty(visitFact.getPatientId())) {
					visitVerificationLogger.warn(LoggingUtils.getErrorLogMessageForProcessor(
							LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD,
							this.getClass(),
							"getContractForPayer",
							"Visit without patient id, cannot proceed to get contract level excp setting"));
					exchange.getIn().setBody(null);
					return;
				}

				String sql = "SELECT T1.* "
						+ "FROM PT_PAYER T1 "
						+ "INNER JOIN "
						+ "  (SELECT * "
						+ "  FROM SCHED_EVNT "
						+ "WHERE BE_ID                                = '%s' "
						+ "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' "
						+ "  AND CURR_REC_IND                           = 1) "
						+ "  ) T2 "
						+ "ON T1.PAYER_ID = T2.PAYER_ID "
						+ "INNER JOIN "
						+ "  (SELECT * FROM VISIT WHERE VISIT_SK = %s AND BE_ID = '%s' "
						+ "  ) T3 "
						+ "ON T2.SCHED_EVNT_SK                           = T3.SCHED_EVNT_SK "
						+ "WHERE T1.BE_ID                                ='%s' "
						+ "AND T1.PT_ID                                  ='%s' "
						+ "AND T1.PAYER_RANK_NAME                        = 'PRIMARY' "
						+ "AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' "
						+ "AND T1.CURR_REC_IND                           = 1) ";


				sql = String.format(sql,visitFact.getBusinessEntityId(), visitFact.getVisitSk(),visitFact.getBusinessEntityId(), visitFact.getBusinessEntityId(),  visitFact.getPatientId());

				ArrayList<PatientPayer> result =
						(ArrayList<PatientPayer>) getEntitiesForId(ConnectionType.COREDATA, sql,
								"com.sandata.lab.data.model.dl.model.PatientPayer");

				if (result != null && result.size() > 0) {
					exchange.getIn().setBody(result.get(0));
				}

			}
		} catch (Exception ex) {
			String errMsg = format("%s: getContractListForPayer: %s", getClass().getName(), ex.getMessage());
			visitVerificationLogger.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, this.getClass(), "getContractForPayer", errMsg));
			throw new SandataRuntimeException(errMsg, ex);
		}

	}



	private Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params)
			throws SandataRuntimeException {

		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {

			connection = connectionPoolDataService.getConnection(connectionType);
			connection.setAutoCommit(false);

			ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
			ARRAY primaryKeysArray = new ARRAY(des, connection, params);

			String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
			callableStatement = connection.prepareCall(callMethod);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.setArray(2, primaryKeysArray);
			callableStatement.execute();

			resultSet = (ResultSet) callableStatement.getObject(1);

			Object result = new DataMapper().map(resultSet, className);

			connection.commit();

			return result;

		} catch (Exception e) {

			// Rollback
			if (connection != null) {

				try {
					connection.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
					packageName, methodName, className,
					e.getClass().getName(), e.getMessage()), e);

		} finally {

			// Close the ResultSet
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			// Close the Statement
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

			// Close the connection
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}



	public void getPhoneNumbersByPatientId (final Exchange exchange) {
		PatientPhoneNumbersFact patientPhoneNumbersFact = new PatientPhoneNumbersFact();

		VisitFact visitFact = findVisitFactInEnrichedExchange(exchange);
		if (null == visitFact || null == visitFact.getVisitSk()) {
			return;
		}

		if (StringUtil.IsNullOrEmpty(visitFact.getPatientId())) {
			visitVerificationLogger.info("In getPhoneNumbersByPatientId Patient Id (patientId) is required!");
			return;
		}        

		if (visitFact.getPatientId() != null) {

			String sql = "SELECT PT_PHONE FROM PT_CONT_PHONE WHERE PT_ID = ? " +
					" AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
					" AND CURR_REC_IND = 1)";
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = getOracleConnection();
				connection.setAutoCommit(true);
				preparedStatement = connection.prepareStatement(sql);
				int index = 1;
				preparedStatement.setString(index++, visitFact.getPatientId());
				resultSet = preparedStatement.executeQuery();

				ArrayList<String> patientPhoneNumbersList = new ArrayList<String>(); 
				if(resultSet != null) {
					while (resultSet.next()) {     
						patientPhoneNumbersList.add(resultSet.getString(1));
					}
					if (patientPhoneNumbersList != null && !patientPhoneNumbersList.isEmpty()) {
						patientPhoneNumbersFact.setPatientPhoneNumbersList(patientPhoneNumbersList);
					}
				}

			} catch (Exception e) {

				// Rollback
				if (connection != null) {

					try {
						connection.rollback();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}

				String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
				visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
						, this.getClass(), "getPhoneNumbersByPatientId", errMsg));
				throw new SandataRuntimeException(errMsg,e);


			} finally {
				// Close the ResultSet
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}
				// Close the statement
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}
				// Close the connection
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}
			}
		}

		exchange.getIn().setBody(patientPhoneNumbersFact);
	}    


	public void getVisitServiceByVisitSk (final Exchange exchange) {
		VisitServiceValidationFact visitServiceValidationFact = new VisitServiceValidationFact();

		VisitFact visitFact = findVisitFactInEnrichedExchange(exchange);
		if (null == visitFact || null == visitFact.getVisitSk()) {
			return;
		}

		if (visitFact.getVisitSk() == null) {
			visitVerificationLogger.info("In getVisitServiceByVisitSk() Visit SK (visitSk) is required!");
			return;

		}        

		if (visitFact.getVisitSk() != null) {

			String sql = "SELECT SVC_NAME FROM VISIT_SVC WHERE VISIT_SK = ? " +
					" AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
					" AND CURR_REC_IND = 1)";
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = getOracleConnection();
				connection.setAutoCommit(true);
				preparedStatement = connection.prepareStatement(sql);
				int index = 1;
				preparedStatement.setLong(index++, visitFact.getVisitSk().longValue());
				resultSet = preparedStatement.executeQuery();

				ArrayList<String> visitServicesList = new ArrayList<String>(); 
				if(resultSet != null) {
					while (resultSet.next()) {     
						visitServicesList.add(resultSet.getString(1));
					}
					if (visitServicesList != null && !visitServicesList.isEmpty()) {
						visitServiceValidationFact.setServicesList(visitServicesList);
					}
				}

			} catch (Exception e) {

				// Rollback
				if (connection != null) {

					try {
						connection.rollback();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}

				String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
				visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
						, this.getClass(), "getVisitServiceByVisitSk", errMsg));
				throw new SandataRuntimeException(errMsg,e);


			} finally {
				// Close the ResultSet
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}
				// Close the statement
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}
				// Close the connection
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						visitVerificationLogger.info(sqle.getMessage());
					}
				}
			}
		}

		exchange.getIn().setBody(visitServiceValidationFact);
	}        

}
