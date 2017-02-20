package com.sandata.lab.rest.staff.handler;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.AdministrativeStaffStaffCrossReference;
import com.sandata.lab.data.model.result.SequenceKeyValueResult;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.staff.impl.OracleDataService;

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
     * Insert an existing admin into the ADMIN_STAFF_STAFF_XREF. GEOR-5728
     */
    public long insertAdmin(Connection connection, String bsnEntId, String staffId, String adminId, String roleName) throws SandataRuntimeException {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date termDate = dateFormat.parse("9999-12-31 00:00:00");

            AdministrativeStaffStaffCrossReference administrativeStaffStaffCrossReference = createAdministrativeStaffStaffCrossReference("insertAdmin");

            administrativeStaffStaffCrossReference.setBusinessEntityID(bsnEntId);
            administrativeStaffStaffCrossReference.setStaffID(staffId);
            administrativeStaffStaffCrossReference.setAdministrativeStaffID(adminId);
            administrativeStaffStaffCrossReference.setAdministrativeStaffStaffEffectiveDate(new Date());
            administrativeStaffStaffCrossReference.setAdministrativeStaffStaffTerminationDate(termDate);
            administrativeStaffStaffCrossReference.setAdministrativeStaffRoleName(roleName);
            administrativeStaffStaffCrossReference.setAdministrativeStaffRoleEffectiveDate(new Date());
            administrativeStaffStaffCrossReference.setAdministrativeStaffRoleTerminationDate(termDate);

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(administrativeStaffStaffCrossReference);
            Object jpubType = new DataMapper().map(administrativeStaffStaffCrossReference);

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

    public long insertCoordinator(Connection connection, String bsnEntId, String staffId, String coordinatorId) throws SandataRuntimeException {
        String roleName = "COORDINATOR";

        if (!adminExists(connection, bsnEntId, coordinatorId, roleName)) {
            throw new SandataRuntimeException(String.format("insertCoordinator: [ADMIN_STAFF_ID=%s] does not exist " +
                    "and/or is not a coordinator!", coordinatorId));
        }

        long returnVal = insertAdmin(connection, bsnEntId, staffId, coordinatorId, roleName);

        if (returnVal <= 0) {
            throw new SandataRuntimeException(String.format("insertCoordinator: adminDataHandler.insertAdmin: " +
                    "Insert was not successful! [RETURN_VAL=%d]", returnVal));
        }

        return returnVal;
    }

    public long insertNurse(Connection connection, String bsnEntId, String staffId, String nurseId) throws SandataRuntimeException {
        String roleName = "NURSE";

        if (!adminExists(connection, bsnEntId, nurseId, roleName)) {
            throw new SandataRuntimeException(String.format("insertNurse: [ADMIN_STAFF_ID=%s] does not exist " +
                    "and/or is not a nurse!", nurseId));
        }

        long returnVal = insertAdmin(connection, bsnEntId, staffId, nurseId, roleName);

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
                    "INNER JOIN (SELECT ADMIN_STAFF_ROLE_XREF_SK, REC_TERM_TMSTP, CURR_REC_IND, " +
                    "              ADMIN_STAFF_ID, ADMIN_STAFF_ROLE_NAME, ADMIN_STAFF_ROLE_EFF_DATE, ADMIN_STAFF_ROLE_TERM_DATE " +
                    "          FROM ADMIN_STAFF_ROLE_XREF " +
                    "          WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    "            BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                    "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
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

    public AdministrativeStaffStaffCrossReference getAdminStaffXref(Connection connection, BigInteger adminStaffXrefSK) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM ADMIN_STAFF_STAFF_XREF " +
                    "WHERE ADMIN_STAFF_STAFF_XREF_SK = ?" +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, adminStaffXrefSK.longValue());

            resultSet = preparedStatement.executeQuery();

            List<AdministrativeStaffStaffCrossReference> resultList =
                    (List<AdministrativeStaffStaffCrossReference>) new DataMapper().map(resultSet, 
                            "com.sandata.lab.data.model.dl.model.AdministrativeStaffStaffCrossReference");

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

    public long deleteAdminStaffXref(AdministrativeStaffStaffCrossReference administrativeStaffStaffCrossReference) throws SandataRuntimeException {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(administrativeStaffStaffCrossReference);

        return oracleDataService.execute(
                oracleMetadata.packageName(),
                oracleMetadata.deleteMethod(),
                administrativeStaffStaffCrossReference.getAdministrativeStaffStaffCrossReferenceSK().intValue());
    }

    public void deleteAdminStaffXrefForList(Connection connection, List<SequenceKeyValueResult> list, SandataLogger logger) throws SandataRuntimeException {

        for (SequenceKeyValueResult skv : list) {
            deleteAdminStaffXref(connection, skv, logger);
        }
    }

    public void deleteAdminStaffXref(Connection connection, SequenceKeyValueResult skv, SandataLogger logger) throws SandataRuntimeException {

        AdministrativeStaffStaffCrossReference administrativeStaffStaffCrossReference =
                getAdminStaffXref(connection, skv.getSequenceKey());

        long returnVal = deleteAdminStaffXref(administrativeStaffStaffCrossReference);
        logger.warn(String.format("deleteAdminStaffXrefForList: DELETED: [SK=%d]: [ADMIN_STAFF_ID=%s]: [RESULT=%d]",
                skv.getSequenceKey().intValue(),
                skv.getValue(),
                returnVal
        ));
    }

    // GEOR-5728
    public List<SequenceKeyValueResult> getAdminIdForStaffId(Connection connection, String bsnEntId, String staffId, String type) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                    "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME,T3.STAFF_ID,T3.ADMIN_STAFF_STAFF_XREF_SK " +
                    "  FROM ADMIN_STAFF T1 " +
                    "      INNER JOIN " +
                    "          ( " +
                    "          SELECT ADMIN_STAFF_ROLE_XREF_SK, REC_TERM_TMSTP, CURR_REC_IND, " +
                    "              ADMIN_STAFF_ID, ADMIN_STAFF_ROLE_NAME, ADMIN_STAFF_ROLE_EFF_DATE, ADMIN_STAFF_ROLE_TERM_DATE " +
                    "          FROM ADMIN_STAFF_ROLE_XREF " +
                    "          WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                    "              AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "          ) T2 ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID " +
                    "" +
                    "      INNER JOIN " +
                    "          ( " +
                    "          SELECT ADMIN_STAFF_STAFF_XREF_SK,BE_ID,ADMIN_STAFF_ID,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND, " +
                    "              ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE " +
                    "          FROM ADMIN_STAFF_STAFF_XREF " +
                    "          WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                    "              AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "          ) T3 ON T2.ADMIN_STAFF_ID = T3.ADMIN_STAFF_ID " +
                    "  WHERE T1.BE_ID = ? AND T3.STAFF_ID = ? " +
                    "      AND T1.BE_ID = T3.BE_ID " +
                    "      AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                    "      AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "  ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, type.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<SequenceKeyValueResult> adminIdList = new ArrayList<>();
            while (resultSet.next()) {

                SequenceKeyValueResult sequenceKeyValueResult = new SequenceKeyValueResult();
                sequenceKeyValueResult.setSequenceKey(resultSet.getBigDecimal("ADMIN_STAFF_STAFF_XREF_SK").toBigInteger());
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

    public String getCoordinatorIdForStaffId(String bsnEntId, String staffId) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            List<SequenceKeyValueResult> skv = getAdminIdForStaffId(connection, bsnEntId, staffId, "Coordinator");
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
            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    public String getNurseIdForStaffId(String bsnEntId, String staffId) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            List<SequenceKeyValueResult> skv = getAdminIdForStaffId(connection, bsnEntId, staffId, "Nurse");
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
            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    private AdministrativeStaffStaffCrossReference createAdministrativeStaffStaffCrossReference(String changeReasonMemo) throws ParseException {

        Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

        AdministrativeStaffStaffCrossReference administrativeStaffStaffCrossReference = new AdministrativeStaffStaffCrossReference();

        administrativeStaffStaffCrossReference.setRecordCreateTimestamp(new Date());
        administrativeStaffStaffCrossReference.setRecordUpdateTimestamp(new Date());
        administrativeStaffStaffCrossReference.setRecordEffectiveTimestamp(new Date());
        administrativeStaffStaffCrossReference.setRecordTerminationTimestamp(termDate);
        administrativeStaffStaffCrossReference.setCurrentRecordIndicator(true);
        administrativeStaffStaffCrossReference.setChangeVersionID(BigInteger.ZERO);

        administrativeStaffStaffCrossReference.setRecordCreatedBy("Middleware Service");
        administrativeStaffStaffCrossReference.setChangeReasonMemo(changeReasonMemo);

        return administrativeStaffStaffCrossReference;
    }
}
