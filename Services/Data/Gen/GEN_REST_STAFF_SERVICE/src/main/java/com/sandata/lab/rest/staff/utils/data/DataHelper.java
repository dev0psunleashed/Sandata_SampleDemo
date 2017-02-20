package com.sandata.lab.rest.staff.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.staff.impl.OracleDataService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 11/29/15
 * Time: 11:29 PM
 */

public class DataHelper {

    // TODO: Refactor
    // If the table is a house keeping table, append check for active record
    public static String houseKeeping(String sql, String tableName) {

        String result = String.format(sql, tableName);

        if (
                tableName.equals("STAFF_RATE")
                || tableName.equals("STAFF_AVAIL")
           ) {
            return result;

        }

        return result + " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";
    }

    public static String tableName(String[] methodParts, int startIndex) {

        if (methodParts == null) {
            return null;
        }

        if (methodParts.length <= startIndex) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (int index = startIndex; index < methodParts.length - 1; index++) {
            result.append(methodParts[index]);
            result.append("_");
        }

        result.append(methodParts[methodParts.length - 1]);

        return result.toString();
    }

    public static BigDecimal getBusinessEntitySK(
            final String businessEntityId,
            final OracleDataService oracleDataService) throws SandataRuntimeException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT BE_SK FROM BE WHERE BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index, businessEntityId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getBigDecimal("BE_SK");
            }

            return null;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
