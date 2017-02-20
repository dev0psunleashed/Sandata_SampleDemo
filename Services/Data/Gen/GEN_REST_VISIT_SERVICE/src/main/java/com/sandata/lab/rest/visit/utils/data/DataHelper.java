package com.sandata.lab.rest.visit.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.visit.impl.OracleDataService;

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

    public static String getBusinessEntityID(
            final Connection connection,
            final long businessEntitySk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT BE_ID FROM BE WHERE BE_SK = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index, businessEntitySk);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("BE_ID");
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
        }
    }
}
