package com.sandata.lab.rest.patient.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;


import java.sql.*;


import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class OracleFindDataService extends OracleDataService {

    public boolean patientExists(String businessEntityId, String firstName, String lastName, String staffID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT 'TRUE' AS PT_EXISTS FROM DUAL WHERE EXISTS (SELECT * FROM COREDATA.PT WHERE UPPER(PT_FIRST_NAME) = ? " +
                    " AND UPPER(PT_LAST_NAME) = ? " +
                    " AND UPPER(PT_ID) = ?" +
                    " AND BE_ID =? AND CURR_REC_IND = 1 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') )");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, firstName.toUpperCase());
            preparedStatement.setString(2, lastName.toUpperCase());
            preparedStatement.setString(3, staffID.toUpperCase());
            preparedStatement.setString(4, businessEntityId);

            resultSet = preparedStatement.executeQuery();

            boolean result = false;

            while (resultSet.next()) {
                result = resultSet.getString("PT_EXISTS").equals("TRUE") ? true : false;
            }

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
}
