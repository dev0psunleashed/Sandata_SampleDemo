package com.sandata.lab.rest.patient.handler;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.AdministrativeStaffPatient;
import com.sandata.lab.data.model.result.SequenceKeyValueResult;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.patient.impl.OracleDataService;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * Handles the data request for staff specifically related to administrative methods.
 * <p/>
 *
 * @author David Rutgos
 */
public class AdminDataHandler {

    private final OracleDataService oracleDataService;

    public AdminDataHandler(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    /**
     * Insert an existing admin into the ADMIN_STAFF_PT_XWALK.
     */
    public long insertAdmin(Connection connection, String bsnEntId, String patientId, String adminId, String roleName) throws SandataRuntimeException {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date termDate = dateFormat.parse("9999-12-31 00:00:00");

            AdministrativeStaffPatient administrativeStaffPatient = createAdministrativeStaffPatientCrosswalk("insertAdmin");

            administrativeStaffPatient.setBusinessEntityID(bsnEntId);
            administrativeStaffPatient.setPatientID(patientId);
            administrativeStaffPatient.setAdministrativeStaffID(adminId);
            administrativeStaffPatient.setAdministrativeStaffPatientEffectiveDate(new Date());
            administrativeStaffPatient.setAdministrativeStaffPatientTerminationDate(termDate);
            administrativeStaffPatient.setAdministrativeStaffRoleName(roleName);
            administrativeStaffPatient.setAdministrativeStaffRoleEffectiveDate(new Date());
            administrativeStaffPatient.setAdministrativeStaffRoleTerminationDate(termDate);

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(administrativeStaffPatient);
            Object jpubType = new DataMapper().map(administrativeStaffPatient);

            return oracleDataService.execute(
                    connection,
                    ConnectionType.COREDATA,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType
            );

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    public long insertCoordinator(Connection connection, String bsnEntId, String patientId, String coordinatorId) throws SandataRuntimeException {
        if (!adminExists(connection, bsnEntId, coordinatorId, "COORDINATOR")) {
            throw new SandataRuntimeException(String.format("insertCoordinator: [ADMIN_STAFF_ID=%s] does not exist " +
                    "and/or is not a coordinator!", coordinatorId));
        }

        long returnVal = insertAdmin(connection, bsnEntId, patientId, coordinatorId, "Coordinator");

        if (returnVal <= 0) {
            throw new SandataRuntimeException(String.format("insertCoordinator: adminDataHandler.insertAdmin: " +
                    "Insert was not successful! [RETURN_VAL=%d]", returnVal));
        }

        return returnVal;
    }

    public long insertNurse(Connection connection, String bsnEntId, String patientId, String nurseId) throws SandataRuntimeException {
        if (!adminExists(connection, bsnEntId, nurseId, "NURSE")) {
            throw new SandataRuntimeException(String.format("insertNurse: [ADMIN_STAFF_ID=%s] does not exist " +
                    "and/or is not a nurse!", nurseId));
        }

        long returnVal = insertAdmin(connection, bsnEntId, patientId, nurseId, "Nurse");

        if (returnVal <= 0) {
            throw new SandataRuntimeException(String.format("insertNurse: adminDataHandler.insertAdmin: " +
                    "Insert was not successful! [RETURN_VAL=%d]", returnVal));
        }

        return returnVal;
    }

    public boolean adminExists(Connection connection, String bsnEntId, String adminId, String type) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                    "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME " +
                    "  FROM ADMIN_STAFF T1 " +
                    "INNER JOIN (SELECT ADMIN_STAFF_ID,ADMIN_STAFF_ROLE_XREF_SK,ADMIN_STAFF_ROLE_NAME,REC_TERM_TMSTP,CURR_REC_IND, " +
                    "              ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE " +
                    "  FROM ADMIN_STAFF_ROLE_XREF " +
                    "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    "      BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                    "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID " +
                    "WHERE T1.BE_ID = ? AND T1.ADMIN_STAFF_ID = ? " +
                    "  AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, adminId);
            preparedStatement.setString(index++, type.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // True: Coordinator Exists

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
        }
    }

    public List<AdministrativeStaffPatient> getAdminStaffPatientXwalk(String patientId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM ADMIN_STAFF_PT " +
                    "WHERE PT_ID = ? AND BE_ID = ?" +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<AdministrativeStaffPatient> resultList =
                    (List<AdministrativeStaffPatient>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaffPatient");

            return resultList;

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public AdministrativeStaffPatient getAdminStaffPatientXwalk(Connection connection, BigInteger adminStaffPTXwalkSK) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM ADMIN_STAFF_PT " +
                    "WHERE ADMIN_STAFF_PT_SK = ?" +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, adminStaffPTXwalkSK.longValue());

            resultSet = preparedStatement.executeQuery();

            List<AdministrativeStaffPatient> resultList =
                    (List<AdministrativeStaffPatient>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaffPatient");

            if (resultList.size() == 1) {
                return resultList.get(0);
            }

            return null;

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
        }
    }

    public long deleteAdminStaffPatientXwalk(AdministrativeStaffPatient administrativeStaffPatient) throws SandataRuntimeException {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(administrativeStaffPatient);

        return oracleDataService.execute(
                oracleMetadata.packageName(),
                oracleMetadata.deleteMethod(),
                administrativeStaffPatient.getAdministrativeStaffPatientSK().intValue());
    }

    public void deleteAdminStaffPatientXwalkForList(Connection connection, List<SequenceKeyValueResult> list, SandataLogger logger) throws SandataRuntimeException {

        for (SequenceKeyValueResult skv : list) {
            deleteAdminStaffPatientXwalk(connection, skv, logger);
        }
    }

    public void deleteAdminStaffPatientXwalk(Connection connection, SequenceKeyValueResult skv, SandataLogger logger) throws SandataRuntimeException {

        AdministrativeStaffPatient administrativeStaffPatient =
                getAdminStaffPatientXwalk(connection, skv.getSequenceKey());

        long returnVal = deleteAdminStaffPatientXwalk(administrativeStaffPatient);
        logger.warn(String.format("deleteAdminStaffPatientXwalk: DELETED: [SK=%d]: [ADMIN_STAFF_ID=%s]: [RESULT=%d]",
                skv.getSequenceKey().intValue(),
                skv.getValue(),
                returnVal
        ));
    }

    public List<SequenceKeyValueResult> getAdminIdForPatientId(Connection connection, String bsnEntId, String patientId, String type) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                    "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME,T3.PT_ID,T3.ADMIN_STAFF_PT_SK " +
                    "  FROM ADMIN_STAFF T1 " +
                    " " +
                    "INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_ROLE_XREF_SK,ADMIN_STAFF_ROLE_NAME,REC_TERM_TMSTP,CURR_REC_IND, " +
                    "              ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE " +
                    "  FROM ADMIN_STAFF_ROLE_XREF " +
                    "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    "      BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                    "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID AND T1.BE_ID = T2.BE_ID" +
                    " " +
                    "INNER JOIN (SELECT ADMIN_STAFF_PT_SK,BE_ID,ADMIN_STAFF_ID,PT_ID,REC_TERM_TMSTP,CURR_REC_IND, " +
                    "              ADMIN_STAFF_PT_EFF_DATE,ADMIN_STAFF_PT_TERM_DATE " +
                    "  FROM ADMIN_STAFF_PT " +
                    "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    "      BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                    "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                    "ON T2.ADMIN_STAFF_ID = T3.ADMIN_STAFF_ID AND T2.BE_ID = T3.BE_ID " +
                    " " +
                    "WHERE T1.BE_ID = ? AND T3.PT_ID = ? " +
                    "  AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, type.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<SequenceKeyValueResult> adminIdList = new ArrayList<>();
            while (resultSet.next()) {

                SequenceKeyValueResult sequenceKeyValueResult = new SequenceKeyValueResult();
                sequenceKeyValueResult.setSequenceKey(resultSet.getBigDecimal("ADMIN_STAFF_PT_SK").toBigInteger());
                sequenceKeyValueResult.setValue(resultSet.getString("ADMIN_STAFF_ID"));
                adminIdList.add(sequenceKeyValueResult);
            }

            return adminIdList;

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
        }
    }

    public String getCoordinatorIdForPatientId(String bsnEntId, String patientId) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            List<SequenceKeyValueResult> skv = getAdminIdForPatientId(connection, bsnEntId, patientId, "Coordinator");
            if (skv.size() > 0) {
                return skv.get(0).getValue();
            }

            return null;

        } catch (Exception e) {
            // Rollback
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

    public String getNurseIdForPatientId(String bsnEntId, String patientId) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            List<SequenceKeyValueResult> skv = getAdminIdForPatientId(connection, bsnEntId, patientId, "Nurse");
            if (skv.size() > 0) {
                return skv.get(0).getValue();
            }

            return null;

        } catch (Exception e) {
            // Rollback
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

    private AdministrativeStaffPatient createAdministrativeStaffPatientCrosswalk(String changeReasonMemo) throws ParseException {

        Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

        AdministrativeStaffPatient administrativeStaffPatient = new AdministrativeStaffPatient();

        administrativeStaffPatient.setRecordCreateTimestamp(new Date());
        administrativeStaffPatient.setRecordUpdateTimestamp(new Date());
        administrativeStaffPatient.setRecordEffectiveTimestamp(new Date());
        administrativeStaffPatient.setRecordTerminationTimestamp(termDate);
        administrativeStaffPatient.setCurrentRecordIndicator(true);
        administrativeStaffPatient.setChangeVersionID(BigInteger.ZERO);

        administrativeStaffPatient.setRecordCreatedBy("Middleware Service");
        administrativeStaffPatient.setChangeReasonMemo(changeReasonMemo);

        return administrativeStaffPatient;
    }
}
