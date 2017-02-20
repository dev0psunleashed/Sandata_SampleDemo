package com.sandata.lab.rest.staff.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.CurrentStaffRate;
import com.sandata.lab.data.model.dl.model.extended.payroll.StaffRateSource;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffRateExchange;
import com.sandata.lab.data.model.jpub.model.StaffAssocRateT;
import com.sandata.lab.data.model.jpub.model.StaffRateT;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.staff.model.StaffAssocRateExt;
import com.sandata.lab.rest.staff.model.StaffAssociatedRateExt;
import com.sandata.lab.rest.staff.model.StaffRateExt;
import com.sandata.lab.rest.staff.model.StaffRateResultExt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Oracle data methods specific to Rates.
 * <p/>
 *
 * @author David Rutgos
 */
public class RatesOracleDataService extends OracleDataService {

    public long addStaffRate(
            Connection connection,
            StaffRateExchange staffRateExchange,
            String type,
            String code,
            SandataLogger logger) throws SandataRuntimeException {

        validateStaffRateExchange(staffRateExchange);

        if (!StringUtil.IsNullOrEmpty(staffRateExchange.getPatientID())) {
            return insertStaffAssociatedRate(connection, staffRateExchange, type, code, logger);
        }

        return insertStaffRate(connection, staffRateExchange, type, code, logger);
    }

    public StaffRateResultExt getStaffAssocRate(String staffId, String bsnEntId, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            //dmr--SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //dmr--String currentDate = dateFormat.format(new Date());

            StaffRateResultExt staffRateResult = new StaffRateResultExt();

            List<StaffAssociatedRateExt> staffAssociatedRateExtList = new ArrayList<>();

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT T4.SVC_UNIT_NAME,T4.BE_RATE_SK,T4.BE_RATE_AMT,T4.BE_RATE_EFF_DATE,T4.BE_RATE_TERM_DATE, " +
                    "       T3.STAFF_RATE_SK,T3.STAFF_RATE_AMT,T3.STAFF_RATE_EFF_DATE,T3.STAFF_RATE_TERM_DATE, " +
                    "       T2.PT_FIRST_NAME,T2.PT_LAST_NAME,T1.* " +
                    "  FROM STAFF_ASSOC_RATE T1 " +
                    " " +
                    "INNER JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND " +
                    "  FROM PT WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.PT_ID = T2.PT_ID " +
                    " " +
                    "LEFT JOIN (SELECT STAFF_RATE_SK,BE_ID,STAFF_ID,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE, " +
                    "            STAFF_RATE_AMT,STAFF_RATE_EFF_DATE,STAFF_RATE_TERM_DATE " +
                    "  FROM STAFF_RATE " +
                    "    WHERE TRUNC(CURRENT_TIMESTAMP) " +
                    "      BETWEEN STAFF_RATE_EFF_DATE AND STAFF_RATE_TERM_DATE) T3 " +
                    "ON T1.BE_ID = T3.BE_ID AND T1.STAFF_ID = T3.STAFF_ID " +
                    "  AND T1.RATE_QLFR_CODE = T3.RATE_QLFR_CODE AND T1.SVC_NAME = T3.SVC_NAME " +
                    "  AND T1.RATE_SUB_TYP_NAME = T3.RATE_SUB_TYP_NAME AND UPPER(T1.RATE_TYP_NAME) = UPPER(T3.RATE_TYP_NAME) " +
                    " " +
                    "LEFT JOIN (SELECT BE_RATE_SK,BE_ID,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE, " +
                    "            BE_RATE_AMT,BE_RATE_EFF_DATE,BE_RATE_TERM_DATE,SVC_UNIT_NAME " +
                    "  FROM BE_RATE " +
                    "    WHERE TRUNC(CURRENT_TIMESTAMP) " +
                    "      BETWEEN BE_RATE_EFF_DATE AND BE_RATE_TERM_DATE) T4 " +
                    "ON T1.BE_ID = T4.BE_ID " +
                    "  AND T1.RATE_QLFR_CODE = T4.RATE_QLFR_CODE AND T1.SVC_NAME = T4.SVC_NAME " +
                    "  AND T1.RATE_SUB_TYP_NAME = T4.RATE_SUB_TYP_NAME AND UPPER(T1.RATE_TYP_NAME) = UPPER(T4.RATE_TYP_NAME) " +
                    " " +
                    "WHERE T1.STAFF_ID = ? AND T1.BE_ID = ? AND T1.RATE_QLFR_CODE = 'PAY' AND BE_RATE_SK IS NOT NULL " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            //dmr--preparedStatement.setString(index++, currentDate);
            //dmr--preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                StaffAssociatedRate staffAssociatedRate = (StaffAssociatedRate) new DataMapper().mapWithOffsetEntityNext(
                        resultSet,
                        "com.sandata.lab.data.model.dl.model.StaffAssociatedRate",

                        // offset skips columns that are not part of the StaffAssociatedRate entity
                        // these columns must be before the StaffAssociatedRate entity columns
                        11);

                StaffAssocRateExt staffAssocRateExt = new StaffAssocRateExt(staffAssociatedRate);
                staffAssocRateExt.setServiceUnitName(ServiceUnitName.fromValue(resultSet.getString("SVC_UNIT_NAME")));

                StaffAssociatedRateExt staffAssociatedRateExt = new StaffAssociatedRateExt(staffAssocRateExt);
                staffAssociatedRateExt.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                staffAssociatedRateExt.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                staffAssociatedRateExt.setCurrentStaffRate(createCurrentStaffRate(resultSet, logger));

                staffAssociatedRateExtList.add(staffAssociatedRateExt);
            }

            staffRateResult.setStaffAssociatedRateExtList(staffAssociatedRateExtList);

            preparedStatement.close();

            // Get Staff Rates
            sql = "SELECT T2.SVC_UNIT_NAME,T1.* " +
                    "  FROM STAFF_RATE T1 " +
                    "  INNER JOIN (SELECT BE_RATE_SK,BE_ID,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE, " +
                    "            BE_RATE_AMT,BE_RATE_EFF_DATE,BE_RATE_TERM_DATE,SVC_UNIT_NAME " +
                    "    FROM BE_RATE " +
                    "      WHERE TRUNC(CURRENT_TIMESTAMP) " +
                    "        BETWEEN BE_RATE_EFF_DATE AND BE_RATE_TERM_DATE) T2 " +
                    "  ON T1.BE_ID = T2.BE_ID " +
                    "    AND T1.RATE_QLFR_CODE = T2.RATE_QLFR_CODE AND T1.SVC_NAME = T2.SVC_NAME " +
                    "    AND T1.RATE_SUB_TYP_NAME = T2.RATE_SUB_TYP_NAME AND UPPER(T1.RATE_TYP_NAME) = UPPER(T2.RATE_TYP_NAME) " +
                    " " +
                    "  WHERE T1.STAFF_ID = ? AND T1.BE_ID = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) ";

            preparedStatement = connection.prepareStatement(sql);

            index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<StaffRateExt> staffRateList = new ArrayList<>();
            while (resultSet.next()) {
                StaffRate staffRate = (StaffRate) new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.StaffRate", 1);
                StaffRateExt staffRateExt = new StaffRateExt(staffRate);
                staffRateExt.setServiceUnitName(ServiceUnitName.fromValue(resultSet.getString("SVC_UNIT_NAME")));
                staffRateList.add(staffRateExt);
            }

            staffRateResult.setStaffRateList(staffRateList);

            return staffRateResult;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private boolean staffAssocRateExist(StaffAssociatedRate staffAssociatedRate) {

        // If the effective and term dates for the given StaffAssociatedRate do NOT overlap with any existing rates, then return false.
        // Otherwise return true because the StaffAssocRate already exists for the given dates and duplicate are not allowed!
        return !(countStaffAssocRateForDate(staffAssociatedRate, staffAssociatedRate.getStaffAssociatedRateEffectiveDate()) == 0
                    && countStaffAssocRateForDate(staffAssociatedRate, staffAssociatedRate.getStaffAssociatedRateTerminationDate()) == 0);
    }

    private boolean staffRateExist(StaffRate staffRate) {

        // If the effective and term dates for the given StaffRate do NOT overlap with any existing rates, then return false.
        // Otherwise return true because the StaffAssocRate already exists for the given dates and duplicate are not allowed!
        return !(countStaffRateForDate(staffRate, staffRate.getStaffRateEffectiveDate()) == 0
                && countStaffRateForDate(staffRate, staffRate.getStaffRateTerminationDate()) == 0);
    }

    private int countStaffAssocRateForDate(StaffAssociatedRate staffAssociatedRate, Date date) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = format.format(date);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT COUNT(*) AS TOTAL " +
                    "  FROM STAFF_ASSOC_RATE " +
                    "WHERE BE_ID = ? AND STAFF_ID = ? AND PT_ID = ? " +
                    "  AND SVC_NAME = ? AND RATE_SUB_TYP_NAME = ? AND RATE_QLFR_CODE = ? " +
                    "  AND RATE_TYP_NAME = ? " +
                    "  AND (TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    "    BETWEEN STAFF_ASSOC_RATE_EFF_DATE AND STAFF_ASSOC_RATE_TERM_DATE) " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffAssociatedRate.getBusinessEntityID());
            preparedStatement.setString(index++, staffAssociatedRate.getStaffID());
            preparedStatement.setString(index++, staffAssociatedRate.getPatientID());
            preparedStatement.setString(index++, staffAssociatedRate.getServiceName().value());
            preparedStatement.setString(index++, staffAssociatedRate.getRateSubTypeName().value());
            preparedStatement.setString(index++, staffAssociatedRate.getRateQualifierCode().value());
            preparedStatement.setString(index++, staffAssociatedRate.getRateTypeName().toUpperCase());
            preparedStatement.setString(index++, dateString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TOTAL");
            }

            return 0;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private int countStaffRateForDate(StaffRate staffRate, Date date) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = format.format(date);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT COUNT(*) AS TOTAL " +
                    "  FROM STAFF_RATE " +
                    "WHERE BE_ID = ? AND STAFF_ID = ? " +
                    "  AND SVC_NAME = ? AND RATE_SUB_TYP_NAME = ? AND RATE_QLFR_CODE = ? " +
                    "  AND RATE_TYP_NAME = ? " +
                    "  AND (TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    "    BETWEEN STAFF_RATE_EFF_DATE AND STAFF_RATE_TERM_DATE) " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffRate.getBusinessEntityID());
            preparedStatement.setString(index++, staffRate.getStaffID());
            preparedStatement.setString(index++, staffRate.getServiceName().value());
            preparedStatement.setString(index++, staffRate.getRateSubTypeName().value());
            preparedStatement.setString(index++, staffRate.getRateQualifierCode().value());
            preparedStatement.setString(index++, staffRate.getRateTypeName().toUpperCase());
            preparedStatement.setString(index++, dateString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TOTAL");
            }

            return 0;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private long insertStaffAssociatedRate(
            Connection connection,
            StaffRateExchange staffRateExchange,
            String type,
            String code,
            SandataLogger logger) throws SandataRuntimeException {

        try {
            logger.info(String.format("%s: addStaffAssociatedRate: [BE_ID:%s]: [STAFF_ID=%s]: [PT_ID=%s]: %s",
                    getClass().getName(),
                    staffRateExchange.getBusinessEntityID(),
                    staffRateExchange.getStaffID(),
                    staffRateExchange.getPatientID(),
                    staffRateExchange.toString()));

            Date termDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("9999-12-31 00:00:00");

            StaffAssociatedRate staffAssociatedRate = new StaffAssociatedRate();

            staffAssociatedRate.setRecordCreateTimestamp(new Date());
            staffAssociatedRate.setRecordUpdateTimestamp(new Date());
            staffAssociatedRate.setRecordEffectiveTimestamp(new Date());
            staffAssociatedRate.setRecordTerminationTimestamp(termDate);
            staffAssociatedRate.setCurrentRecordIndicator(true);
            staffAssociatedRate.setChangeVersionID(BigInteger.ZERO);

            staffAssociatedRate.setRecordCreatedBy("MW");
            staffAssociatedRate.setChangeReasonMemo("Staff: OracleDataService: insertStaffAssociatedRate");

            staffAssociatedRate.setBusinessEntityID(staffRateExchange.getBusinessEntityID());
            staffAssociatedRate.setStaffID(staffRateExchange.getStaffID());
            staffAssociatedRate.setPatientID(staffRateExchange.getPatientID());
            staffAssociatedRate.setStaffAssociatedRateEffectiveDate(staffRateExchange.getEffectiveDate());

            if (!DateUtil.IsNull(staffRateExchange.getTerminationDate())) {
                staffAssociatedRate.setStaffAssociatedRateTerminationDate(staffRateExchange.getTerminationDate());
            } else {
                // If a date was not provided by the caller set it to the open-ended termination date
                staffAssociatedRate.setStaffAssociatedRateTerminationDate(termDate);
            }

            staffAssociatedRate.setServiceName(staffRateExchange.getServiceName());
            String rateTypeName = (staffRateExchange.getRateTypeName() != null) ? staffRateExchange.getRateTypeName().toUpperCase() : null;
            staffAssociatedRate.setRateTypeName(rateTypeName);
            staffAssociatedRate.setStaffAssociatedRateAmount(staffRateExchange.getStaffRateAmount());
            staffAssociatedRate.setRateChangeQualifier(RateChangeQualifier.DELTA);

            switch (type.toUpperCase()) {
                case "REGULAR":
                    staffAssociatedRate.setRateSubTypeName(RateSubTypeName.REGULAR);
                    break;
                case "WEEKEND":
                    staffAssociatedRate.setRateSubTypeName(RateSubTypeName.WEEKEND);
                    break;
                case "HOLIDAY":
                    staffAssociatedRate.setRateSubTypeName(RateSubTypeName.HOLIDAY);
                    break;
                default:
                    throw new SandataRuntimeException(String.format("insertStaffAssociatedRate: [TYPE=%s]: UNKNOWN!", type));
            }

            switch (code.toUpperCase()) {
                case "PAY":
                    staffAssociatedRate.setRateQualifierCode(RateQualifierCode.PAY);
                    break;
                case "BILL":
                    staffAssociatedRate.setRateQualifierCode(RateQualifierCode.BILL);
                    break;
                default:
                    throw new SandataRuntimeException(String.format("insertStaffAssociatedRate: [CODE=%s]: UNKNOWN!", code));
            }

            if (staffAssocRateExist(staffAssociatedRate)) {
                throw new SandataRuntimeException("insertStaffAssociatedRate: ERROR: Staff Associated Rate Exists!");
            }

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffAssociatedRate);
            StaffAssocRateT jpubObj = (StaffAssocRateT) new DataMapper().map(staffAssociatedRate);

            // Execute Insert
            return execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    private long insertStaffRate(
            Connection connection,
            StaffRateExchange staffRateExchange,
            String type,
            String code,
            SandataLogger logger) throws SandataRuntimeException {

        try {
            logger.info(String.format("%s: insertStaffRate: [BE_ID:%s]: [STAFF_ID=%s]: [PT_ID=%s]: %s",
                    getClass().getName(),
                    staffRateExchange.getBusinessEntityID(),
                    staffRateExchange.getStaffID(),
                    staffRateExchange.getPatientID(),
                    staffRateExchange.toString()));

            Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

            StaffRate staffRate = new StaffRate();

            staffRate.setRecordCreateTimestamp(new Date());
            staffRate.setRecordUpdateTimestamp(new Date());
            staffRate.setRecordEffectiveTimestamp(new Date());
            staffRate.setRecordTerminationTimestamp(termDate);
            staffRate.setCurrentRecordIndicator(true);
            staffRate.setChangeVersionID(BigInteger.ZERO);

            staffRate.setRecordCreatedBy("Middleware Service");
            staffRate.setChangeReasonMemo("Payer: OracleDataService: insertStaffRate");

            staffRate.setBusinessEntityID(staffRateExchange.getBusinessEntityID());
            staffRate.setStaffID(staffRateExchange.getStaffID());
            staffRate.setStaffRateEffectiveDate(staffRateExchange.getEffectiveDate());

            if (staffRateExchange.getTerminationDate() != null) {
                staffRate.setStaffRateTerminationDate(staffRateExchange.getTerminationDate());
            } else {
                // If a date wasn't provided by the caller set it to the open-ended termination date
                staffRate.setStaffRateTerminationDate(termDate);
            }

            staffRate.setServiceName(staffRateExchange.getServiceName());

            String rateTypeName = (staffRateExchange.getRateTypeName() != null) ? staffRateExchange.getRateTypeName().toUpperCase() : null;
            staffRate.setRateTypeName(rateTypeName);
            staffRate.setStaffRateAmount(staffRateExchange.getStaffRateAmount());

            switch (type.toUpperCase()) {
                case "REGULAR":
                    staffRate.setRateSubTypeName(RateSubTypeName.REGULAR);
                    break;
                case "WEEKEND":
                    staffRate.setRateSubTypeName(RateSubTypeName.WEEKEND);
                    break;
                case "HOLIDAY":
                    staffRate.setRateSubTypeName(RateSubTypeName.HOLIDAY);
                    break;
                default:
                    throw new SandataRuntimeException(String.format("insertStaffRate: [TYPE=%s]: UNKNOWN!", type));
            }

            switch (code.toUpperCase()) {
                case "PAY":
                    staffRate.setRateQualifierCode(RateQualifierCode.PAY);
                    break;
                case "BILL":
                    staffRate.setRateQualifierCode(RateQualifierCode.BILL);
                    break;
                default:
                    throw new SandataRuntimeException(String.format("insertStaffRate: [CODE=%s]: UNKNOWN!", code));
            }

            if (staffRateExist(staffRate)) {
                throw new SandataRuntimeException("insertStaffRate: ERROR: Staff Rate Exists!");
            }

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffRate);
            StaffRateT jpubObj = (StaffRateT) new DataMapper().map(staffRate);

            // Execute Insert
            return execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    private void validateStaffRateExchange(StaffRateExchange staffRateExchange) throws SandataRuntimeException {

        try {
            if (StringUtil.IsNullOrEmpty(staffRateExchange.getBusinessEntityID())) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getBusinessEntityID() is NULL or EMPTY!");
            }

            if (StringUtil.IsNullOrEmpty(staffRateExchange.getStaffID())) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getStaffID() is NULL or EMPTY!");
            }

            if (DateUtil.IsNull(staffRateExchange.getEffectiveDate())) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getEffectiveDate() is NULL!");
            }

            if (StringUtil.IsNullOrEmpty(staffRateExchange.getStaffID())) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getStaffID() is NULL or EMPTY!");
            }

            if (staffRateExchange.getServiceName() == null) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getServiceName() is NULL!");
            }

            if (staffRateExchange.getStaffRateAmount() == null) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getStaffSateAmount() is NULL!");
            }

            if (StringUtil.IsNullOrEmpty(staffRateExchange.getRateTypeName())) {
                throw new SandataRuntimeException("validateStaffRateExchange: staffRateExchange.getRateTypeName() is NULL or EMPTY!");
            }

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    private CurrentStaffRate createCurrentStaffRate(ResultSet resultSet, SandataLogger logger) throws SandataRuntimeException {

        try {
            CurrentStaffRate currentStaffRate = new CurrentStaffRate();

            // Staff Rate will override the Agency Rate
            BigDecimal staffRateSK = resultSet.getBigDecimal("STAFF_RATE_SK");
            if (staffRateSK != null) {
                currentStaffRate.setStaffRateSource(StaffRateSource.Staff);
                currentStaffRate.setStaffRateSK(staffRateSK.toBigInteger());
                currentStaffRate.setStaffRateAmount(resultSet.getBigDecimal("STAFF_RATE_AMT"));

                Timestamp effDate = resultSet.getTimestamp("STAFF_RATE_EFF_DATE");
                if (effDate == null) {
                    throw new SandataRuntimeException(String.format("createCurrentStaffRate: [STAFF_RATE_SK=%d]: " +
                            "ERROR: effDate == null", staffRateSK.intValue()));
                }

                currentStaffRate.setStaffRateEffectiveDate(new Date(effDate.getTime()));

                Timestamp termDate = resultSet.getTimestamp("STAFF_RATE_TERM_DATE");
                if (termDate == null) {
                    throw new SandataRuntimeException(String.format("createCurrentStaffRate: [STAFF_RATE_SK=%d]: " +
                            "ERROR: termDate == null", staffRateSK.intValue()));
                }

                currentStaffRate.setStaffRateTerminationDate(new Date(termDate.getTime()));
            } else {
                BigDecimal agencyRateSK = resultSet.getBigDecimal("BE_RATE_SK");
                if (agencyRateSK != null) {
                    currentStaffRate.setStaffRateSource(StaffRateSource.BusinessEntity);
                    currentStaffRate.setStaffRateSK(agencyRateSK.toBigInteger());
                    currentStaffRate.setStaffRateAmount(resultSet.getBigDecimal("BE_RATE_AMT"));

                    Timestamp effDate = resultSet.getTimestamp("BE_RATE_EFF_DATE");
                    if (effDate == null) {
                        throw new SandataRuntimeException(String.format("createCurrentStaffRate: [BE_RATE_SK=%d]: " +
                                "ERROR: effDate == null", agencyRateSK.intValue()));
                    }

                    currentStaffRate.setStaffRateEffectiveDate(new Date(effDate.getTime()));

                    Timestamp termDate = resultSet.getTimestamp("BE_RATE_TERM_DATE");
                    if (termDate == null) {
                        throw new SandataRuntimeException(String.format("createCurrentStaffRate: [BE_RATE_SK=%d]: " +
                                "ERROR: termDate == null", agencyRateSK.intValue()));
                    }

                    currentStaffRate.setStaffRateTerminationDate(new Date(termDate.getTime()));

                } else {
                    throw new SandataRuntimeException("createCurrentStaffRate: ERROR: BE_RATE_SK == null");
                }
            }

            String serviceUnitNameString = resultSet.getString("SVC_UNIT_NAME");
            if (!StringUtil.IsNullOrEmpty(serviceUnitNameString)) {

                try {

                    currentStaffRate.setServiceUnitName(ServiceUnitName.fromValue(serviceUnitNameString));

                } catch (Exception e) {
                    logger.error(String.format("%s: createCurrentStaffRate: EXCEPTION: [SVC_UNIT_NAME=%s]: %s",
                            getClass().getName(), serviceUnitNameString, e.getMessage()));
                }

            } else {
                logger.warn(String.format("%s: createCurrentStaffRate: [SVC_UNIT_NAME=NULL]", getClass().getName()));
            }

            return currentStaffRate;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }
}
