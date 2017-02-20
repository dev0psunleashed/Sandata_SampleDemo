package com.sandata.lab.rest.am.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleFindDataService extends OracleDataService {

    /**
     * Get a list of Tenant Configuration by business entity ids.
     *
     * @param bsnEntIDs
     * @return
     */
    public HashMap<String, Map> getTenantConfigsByBEIds(List<String> bsnEntIDs) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM METADATA.APP_TENANT_BE_XWALK T1" +
                    " JOIN METADATA.APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_SK = T2.APP_TENANT_SK " +
                    " WHERE T1.BE_ID IN (?) AND UPPER(KEY_NAME) = 'AGNCY_MGMT_PAT_STAFF_GEN'";

            StringBuilder stringBuilder = new StringBuilder();

            int listSize = bsnEntIDs.size();
            for (int i = 0; i < listSize; i++) {
                stringBuilder.append('?');

                if (i < listSize - 1) {
                    stringBuilder.append(',');
                }
            }

            sql = sql.replace("(?)", "(" + stringBuilder.toString() + ")");

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            for (String bsnEntID : bsnEntIDs) {
                preparedStatement.setString(i, bsnEntID);
                i++;
            }


            resultSet = preparedStatement.executeQuery();

            HashMap<String, Map> beResult = new HashMap<>();

            while (resultSet.next()) {

                HashMap<String, String> result = new HashMap<>();
                result.put("tenantSK", resultSet.getString("APP_TENANT_SK"));
                result.put("config", resultSet.getString("TENANT_KEY_CONF_VAL"));

                beResult.put(resultSet.getString("BE_ID"), result);
            }

            return beResult;

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

    public Object getTenantConfigByKey(String businessEntityID, String keyName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM METADATA.APP_TENANT_BE_XWALK T1" +
                    " JOIN METADATA.APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_SK = T2.APP_TENANT_SK " +
                    " WHERE T1.BE_ID = ? AND UPPER(T2.KEY_NAME) = ?";

            StringBuilder stringBuilder = new StringBuilder();


            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            preparedStatement.setString(i++, businessEntityID);
            preparedStatement.setString(i++, keyName);


            resultSet = preparedStatement.executeQuery();

            Object result = null;

            while (resultSet.next()) {

                result = resultSet.getObject("TENANT_KEY_CONF_VAL");
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
