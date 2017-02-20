package com.sandata.lab.dl.db.pool.test.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.test.utils.log.ConnectionPoolDataServiceTestLogger;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import org.apache.camel.Exchange;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
public class SandataConnectionPoolDataServiceTest {

    private ConnectionPoolDataService connectionPoolDataService;

    public void testCoreDataConnection(Exchange exchange) {

        SandataLogger logger = ConnectionPoolDataServiceTestLogger.CreateLogger(exchange);
        logger.start();

        logger.info("**** TEST ((COREDATA)): START ****");

        try {
            if (connectionPoolDataService != null) {

                logger.info(">>> connectionPoolDataService != null <<<");

                Connection connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);

                if (connection != null) {
                    logger.info(">>> connection != null <<<");

                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    try {
                        connection.setAutoCommit(false);

                        String sql = "SELECT COUNT(*) AS VAL FROM SERVICE";

                        int index = 1;
                        preparedStatement = connection.prepareStatement(sql);

                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {

                            BigDecimal val = resultSet.getBigDecimal("VAL");

                            logger.info(String.format("((((((( *** COREDATA = %d ***  ))))))", val.intValue()));
                        }
                        else {
                            logger.info(String.format("((((((( ERROR: VAL = %d  ))))))", 0));
                        }

                        connection.commit();

                    } catch (Exception e) {

                        try {
                            connection.rollback();
                        }
                        catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }

                        throw new SandataRuntimeException(String.format("%s: %s",
                                e.getClass().getName(), e.getMessage()));

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

                        try {
                            connection.close();
                        }
                        catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                }
                else {
                    logger.info(">>> connection == null <<<");
                }

            } else {
                logger.info(">>> connectionPoolDataService == null <<<");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("**** TEST ((COREDATA)): STOP ****");

        logger.stop();
    }

    public void testLobDataConnection(Exchange exchange) {

        SandataLogger logger = ConnectionPoolDataServiceTestLogger.CreateLogger(exchange);
        logger.start();

        logger.info("**** TEST ((LOBDATA)): START ****");

        try {
            if (connectionPoolDataService != null) {

                logger.info(">>> connectionPoolDataService != null <<<");

                Connection connection = connectionPoolDataService.getConnection(ConnectionType.LOBDATA);

                if (connection != null) {
                    logger.info(">>> connection != null <<<");

                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    try {
                        connection.setAutoCommit(false);

                        String sql = "SELECT COUNT(*) AS VAL FROM DOC_TYP_LKUP";

                        int index = 1;
                        preparedStatement = connection.prepareStatement(sql);

                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {

                            BigDecimal val = resultSet.getBigDecimal("VAL");

                            logger.info(String.format("((((((( *** LOBDATA = %d *** ))))))", val.intValue()));
                        }
                        else {
                            logger.info(String.format("((((((( ERROR: VAL = %d  ))))))", 0));
                        }

                        connection.commit();

                    } catch (Exception e) {

                        try {
                            connection.rollback();
                        }
                        catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }

                        throw new SandataRuntimeException(String.format("%s: %s",
                                e.getClass().getName(), e.getMessage()));

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

                        try {
                            connection.close();
                        }
                        catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                }
                else {
                    logger.info(">>> connection == null <<<");
                }

            } else {
                logger.info(">>> connectionPoolDataService == null <<<");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("**** TEST ((LOBDATA)): STOP ****");

        logger.stop();
    }

    public void testMetaDataConnection(Exchange exchange) {

        SandataLogger logger = ConnectionPoolDataServiceTestLogger.CreateLogger(exchange);
        logger.start();

        logger.info("**** TEST ((METADATA)): START ****");

        try {
            if (connectionPoolDataService != null) {

                logger.info(">>> connectionPoolDataService != null <<<");

                Connection connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);

                if (connection != null) {
                    logger.info(">>> connection != null <<<");

                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    try {
                        connection.setAutoCommit(false);

                        String sql = "SELECT COUNT(*) AS VAL FROM APP_LOG";

                        int index = 1;
                        preparedStatement = connection.prepareStatement(sql);

                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {

                            BigDecimal val = resultSet.getBigDecimal("VAL");

                            logger.info(String.format("((((((( *** METADATA = %d *** ))))))", val.intValue()));
                        }
                        else {
                            logger.info(String.format("((((((( ERROR: VAL = %d  ))))))", 0));
                        }

                        connection.commit();

                    } catch (Exception e) {

                        try {
                            connection.rollback();
                        }
                        catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }

                        throw new SandataRuntimeException(String.format("%s: %s",
                                e.getClass().getName(), e.getMessage()));

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

                        try {
                            connection.close();
                        }
                        catch (SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                }
                else {
                    logger.info(">>> connection == null <<<");
                }

            } else {
                logger.info(">>> connectionPoolDataService == null <<<");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("**** TEST ((METADATA)): STOP ****");

        logger.stop();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }
}
