package com.sandata.lab.rest.bsn_ent.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.BusinessEntityRateExchange;
import com.sandata.lab.data.model.jpub.model.BeRateT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.bsn_ent.api.OracleService;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigInteger;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, Object... params)
        throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            long result =  callableStatement.getLong(1);

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                packageName, methodName,
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                packageName, methodName,
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = (Long) callableStatement.getObject(1);
            return result;
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                packageName, methodName,
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
        }
    }

    public Response getBsnEntLocation(final String businessEntityParentID, String locationName) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String filterByLocationQuery = "";
            String filterByLocationValue = "";

            if(!StringUtil.IsNullOrEmpty(locationName)) {
                filterByLocationQuery = "  AND T1.BE_NAME LIKE ? ";
                filterByLocationValue = "%" + locationName.trim() + "%";
            }

            String sql = "SELECT DISTINCT T2.BE_PAR_ID,T1.BE_ID,T1.BE_NAME " +
                    "  FROM BE T1 " +
                    "INNER JOIN BE_REL T2 " +
                    "ON T1.BE_ID = T2.BE_ID " +
                    "  AND TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1 " +
                    "WHERE T2.BE_PAR_ID = ? " +
                    "  AND T2.BE_REL_TYP = 'BE_LOCATION' " +
                    "  AND T2.BE_REL_STATUS = 'ACTIVE' " +
                    filterByLocationQuery +
                    "START WITH T2.BE_PAR_ID IS NULL " +
                    "CONNECT BY NOCYCLE T2.BE_PAR_ID = PRIOR T1.BE_ID " +
                    "ORDER BY BE_NAME";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, businessEntityParentID);

            if(!StringUtil.IsNullOrEmpty(locationName)) {
                preparedStatement.setString(index++, filterByLocationValue);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();

            List<BusinessEntityLocation> businessEntityLocations = new ArrayList<>();
            response.setData(businessEntityLocations);

            while (resultSet.next()) {

                BusinessEntityLocation location = new BusinessEntityLocation();
                location.setBusinessEntityParentID(resultSet.getString("BE_PAR_ID"));
                location.setBusinessEntityID(resultSet.getString("BE_ID"));
                location.setLocation(resultSet.getString("BE_NAME"));
                businessEntityLocations.add(location);
            }

            return response;
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

    public Object getEntitiesForId(final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

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

    public List<BusinessEntityRelationship> getBusinessEntityRelationshipForPK(String bsnEntId, Long bsnEntLevelSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                + ConnectionType.COREDATA
                + ".BE_REL WHERE BE_ID = ? AND BE_LVL_SK = ?"
                + " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setLong(index, bsnEntLevelSk);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityRelationship> resultList =
                (List<BusinessEntityRelationship>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRelationship");

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public BusinessEntityRateExchange getBeRateExchange(
                String bsnEntId,
                ServiceName serviceName,
                String rateTypeName,
                SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_RATE " +
                    "WHERE BE_ID = ? " +
                    "  AND SVC_NAME = ? " +
                    "  AND UPPER(RATE_TYP_NAME) = ? " +
                    "  AND RATE_QLFR_CODE = 'PAY' " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, serviceName.value());
            preparedStatement.setString(index++, rateTypeName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityRate> resultList =
                    (List<BusinessEntityRate>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRate");

            // There are no records that match the query
            if (resultList.size() == 0) {
                logger.info(String.format("getBeRateExchange: No data found! [SQL=%s]", sql));
                return null;
            }

            if (resultList.size() != 3) {
                throw new SandataRuntimeException(String.format("getBeRateExchange: List<BusinessEntityRate> Size=%d: Expected Size=3", resultList.size()));
            }

            BusinessEntityRateExchange businessEntityRateExchange = new BusinessEntityRateExchange();
            boolean init = false;
            for (BusinessEntityRate businessEntityRate : resultList) {
                switch(businessEntityRate.getRateSubTypeName()) {
                    case REGULAR:
                        businessEntityRateExchange.setRegularPayrollRateSK(businessEntityRate.getBusinessEntityRateSK());
                        businessEntityRateExchange.setRegularChangeVersionID(businessEntityRate.getChangeVersionID());
                        businessEntityRateExchange.setRegularRateAmount(businessEntityRate.getBusinessEntityRateAmount());
                        break;
                    case WEEKEND:
                        businessEntityRateExchange.setWeekendPayrollRateSK(businessEntityRate.getBusinessEntityRateSK());
                        businessEntityRateExchange.setWeekendChangeVersionID(businessEntityRate.getChangeVersionID());
                        businessEntityRateExchange.setWeekendRateAmount(businessEntityRate.getBusinessEntityRateAmount());
                        break;
                    case HOLIDAY:
                        businessEntityRateExchange.setHolidayPayrollRateSK(businessEntityRate.getBusinessEntityRateSK());
                        businessEntityRateExchange.setHolidayChangeVersionID(businessEntityRate.getChangeVersionID());
                        businessEntityRateExchange.setHolidayRateAmount(businessEntityRate.getBusinessEntityRateAmount());
                        break;
                }

                // Init is run once for common properties
                if (!init) {
                    init = true;

                    businessEntityRateExchange.setBusinessEntityID(businessEntityRate.getBusinessEntityID());
                    businessEntityRateExchange.setBusinessEntityLineofBusinessID(businessEntityRate.getBusinessEntityLineOfBusinessID());
                    businessEntityRateExchange.setBusinessEntityLocationID(businessEntityRate.getBusinessEntityLocationID());
                    businessEntityRateExchange.setEffectiveDate(businessEntityRate.getBusinessEntityRateEffectiveDate());
                    businessEntityRateExchange.setServiceName(businessEntityRate.getServiceName());
                    businessEntityRateExchange.setRateTypeName(businessEntityRate.getRateTypeName());
                    businessEntityRateExchange.setServiceUnitName(businessEntityRate.getServiceUnitName());
                }
            }

            return businessEntityRateExchange;

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

    public long deleteBeRateExchange(
            String bsnEntId,
            ServiceName serviceName,
            String rateTypeName) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String callMethod = "{?=call PKG_BSN_UTIL.DELETE_BE_RATE(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            
            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, serviceName.value());
            callableStatement.setString(index++, rateTypeName.toUpperCase());
            
            callableStatement.execute();
            
            return callableStatement.getLong(1);

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

            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public long[] updateBeRateExchange(
            Connection connection,
            BusinessEntityRateExchange businessEntityRateExchange,
            SandataLogger logger) throws SandataRuntimeException {

        validateBusinessEntityRateExchange(businessEntityRateExchange);

        long[] result = new long[3];

        try {
            logger.info(String.format("%s: updateBeRateExchange: %s",
                    getClass().getName(),
                    businessEntityRateExchange.toString()));

            List<BusinessEntityRate> businessEntityRateList = getBusinessEntityRateList(
                    connection,
                    businessEntityRateExchange.getBusinessEntityID(),
                    businessEntityRateExchange.getServiceName(),
                    businessEntityRateExchange.getRateTypeName());

            if (businessEntityRateList.size() != 3) {
                logger.warn(String.format("updateBeRateExchange: businessEntityRateExchange does not exist!: [%s]", businessEntityRateExchange.toString()));
                return new long[0];
            }

            int index = 0;
            for (BusinessEntityRate businessEntityRate : businessEntityRateList) {
                businessEntityRate.setBusinessEntityID(businessEntityRateExchange.getBusinessEntityID());
                businessEntityRate.setBusinessEntityLineOfBusinessID(businessEntityRateExchange.getBusinessEntityLineofBusinessID());
                businessEntityRate.setBusinessEntityLocationID(businessEntityRateExchange.getBusinessEntityLocationID());
                businessEntityRate.setBusinessEntityRateEffectiveDate(businessEntityRateExchange.getEffectiveDate());
                businessEntityRate.setServiceName(businessEntityRateExchange.getServiceName());
                businessEntityRate.setRateTypeName(businessEntityRateExchange.getRateTypeName());
                businessEntityRate.setServiceUnitName(businessEntityRateExchange.getServiceUnitName());

                if (businessEntityRate.getRateSubTypeName() == RateSubTypeName.REGULAR) {
                    businessEntityRate.setBusinessEntityRateAmount(businessEntityRateExchange.getRegularRateAmount());

                } else if (businessEntityRate.getRateSubTypeName() == RateSubTypeName.WEEKEND) {
                    businessEntityRate.setBusinessEntityRateAmount(businessEntityRateExchange.getWeekendRateAmount());

                } else if (businessEntityRate.getRateSubTypeName() == RateSubTypeName.HOLIDAY) {
                    businessEntityRate.setBusinessEntityRateAmount(businessEntityRateExchange.getHolidayRateAmount());
                }

                result[index++] = updateBeRate(connection, businessEntityRate);
            }
        }
        catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }

        return result;
    }

    private long updateBeRate(Connection connection, BusinessEntityRate businessEntityRate) throws SandataRuntimeException {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(businessEntityRate);
        BeRateT jpubObj = (BeRateT)new DataMapper().map(businessEntityRate);
        // Execute Update
        long result = execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.updateMethod(), jpubObj);
        if (result < 0) {
            throw new SandataRuntimeException("updateBeRate: ERROR: result < 0");
        }

        return businessEntityRate.getBusinessEntityRateSK().longValue();
    }

    public long[] insertBeRateExchange(
                Connection connection,
                BusinessEntityRateExchange businessEntityRateExchanges,
                SandataLogger logger) throws SandataRuntimeException {

        validateBusinessEntityRateExchange(businessEntityRateExchanges);

        int count = getBusinessEntityRateExchangeCount(
                connection,
                businessEntityRateExchanges.getBusinessEntityID(),
                businessEntityRateExchanges.getServiceName(),
                businessEntityRateExchanges.getRateTypeName()
        );

        if (count == 3) {
            throw new SandataRuntimeException(String.format("insertBeRateExchange: BusinessEntityRateExchange already exists!: [%s]", businessEntityRateExchanges.toString()));
        }

        if (count < 0 || count > 3) {
            throw new SandataRuntimeException(String.format("insertBeRateExchange: BusinessEntityRateExchange: ILLEGAL BusinessRate [Count=%d]: [%s]", count, businessEntityRateExchanges.toString()));
        }

        long[] result = new long[3];

        try {
            logger.info(String.format("%s: insertBeRateExchange: %s",
                    getClass().getName(), businessEntityRateExchanges.toString()));

            // REGULAR
            BusinessEntityRate regularBusinessEntityRate = createBusinessEntityRate(businessEntityRateExchanges,
                    "BSN: OracleDataService: insertBeRateExchange: REGULAR");
            regularBusinessEntityRate.setRateSubTypeName(RateSubTypeName.REGULAR);
            regularBusinessEntityRate.setBusinessEntityRateAmount(businessEntityRateExchanges.getRegularRateAmount());
            result[0] = insertBusinessEntityRate(connection, regularBusinessEntityRate);

            // WEEKEND
            BusinessEntityRate weekendBusinessEntityRate = createBusinessEntityRate(businessEntityRateExchanges,
                    "BSN: OracleDataService: insertBeRateExchange: WEEKEND");
            weekendBusinessEntityRate.setRateSubTypeName(RateSubTypeName.WEEKEND);
            weekendBusinessEntityRate.setBusinessEntityRateAmount(businessEntityRateExchanges.getWeekendRateAmount());
            result[1] = insertBusinessEntityRate(connection, weekendBusinessEntityRate);

            // HOLIDAY
            BusinessEntityRate holidayBusinessEntityRate = createBusinessEntityRate(businessEntityRateExchanges,
                    "BSN: OracleDataService: insertBeRateExchange: HOLIDAY");
            holidayBusinessEntityRate.setRateSubTypeName(RateSubTypeName.HOLIDAY);
            holidayBusinessEntityRate.setBusinessEntityRateAmount(businessEntityRateExchanges.getHolidayRateAmount());
            result[2] = insertBusinessEntityRate(connection, holidayBusinessEntityRate);
        }
        catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }

        return result;
    }

    private long insertBusinessEntityRate(Connection connection, BusinessEntityRate businessEntity) throws SandataRuntimeException {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(businessEntity);
        BeRateT jpubObj = (BeRateT)new DataMapper().map(businessEntity);
        // Execute Insert
        return execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
    }

    private void validateBusinessEntityRateExchange(BusinessEntityRateExchange businessEntityRateExchange) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(businessEntityRateExchange.getBusinessEntityID())) {
            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getBusinessEntityID() is NULL or EMPTY!");
        }

        //TODO: LOB and Location do not exist in the model currently for BE_RATE table
        /*if (StringUtil.IsNullOrEmpty(businessEntityRateExchange.getBusinessEntityLineOfBusiness())) {
            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getBusinessEntityLineOfBusiness() is NULL or EMPTY!");
        }

        if (StringUtil.IsNullOrEmpty(businessEntityRateExchange.getBusinessEntityLocationID())) {
            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getBusinessEntityLocationID() is NULL or EMPTY!");
        }*/

        if (businessEntityRateExchange.getEffectiveDate() == null) {
            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getEffectiveDate() is NULL!");
        }

        if (businessEntityRateExchange.getServiceName() == null) {
            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getServiceName() is NULL!");
        }

        if (businessEntityRateExchange.getRateTypeName() == null) {
            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getRateTypeName() is NULL!");
        }

        // REGULAR
        if (businessEntityRateExchange.getRegularRateAmount() == null
                || businessEntityRateExchange.getRegularRateAmount().doubleValue() <= 0) {

            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getRegularRateAmount() is NULL or <= 0");
        }

        // WEEKEND
        if (businessEntityRateExchange.getWeekendRateAmount() == null
                || businessEntityRateExchange.getWeekendRateAmount().doubleValue() <= 0) {

            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getWeekendRateAmount() is NULL or <= 0");
        }

        // HOLIDAY
        if (businessEntityRateExchange.getHolidayRateAmount() == null
                || businessEntityRateExchange.getHolidayRateAmount().doubleValue() <= 0) {

            throw new SandataRuntimeException("validateBusinessEntityRateExchange: businessEntityRateExchange.getHolidayRateAmount() is NULL or <= 0");
        }
    }

    private List<BusinessEntityRate> getBusinessEntityRateList(
            Connection connection, String bsnEntId, ServiceName serviceName,
            String rateTypeName) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM BE_RATE " +
                    "WHERE BE_ID = ? " +
                    "  AND SVC_NAME = ? " +
                    "  AND UPPER(RATE_TYP_NAME) = ? " +
                    "  AND RATE_QLFR_CODE = 'PAY' " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, serviceName.value());
            preparedStatement.setString(index++, rateTypeName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            return (List<BusinessEntityRate>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRate");

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

    private BusinessEntityRate createBusinessEntityRate(
                    BusinessEntityRateExchange businessEntityRateExchange,
                    String changReasonMemo) throws ParseException {

        Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

        BusinessEntityRate businessEntityRate = new BusinessEntityRate();

        businessEntityRate.setRecordCreateTimestamp(new Date());
        businessEntityRate.setRecordUpdateTimestamp(new Date());
        businessEntityRate.setRecordEffectiveTimestamp(new Date());
        businessEntityRate.setRecordTerminationTimestamp(termDate);
        businessEntityRate.setCurrentRecordIndicator(true);
        businessEntityRate.setChangeVersionID(BigInteger.ZERO);

        businessEntityRate.setRecordCreatedBy("Middleware Service");
        businessEntityRate.setChangeReasonMemo(changReasonMemo);

        businessEntityRate.setBusinessEntityID(businessEntityRateExchange.getBusinessEntityID());
        businessEntityRate.setBusinessEntityLineOfBusinessID(businessEntityRateExchange.getBusinessEntityLineofBusinessID());
        businessEntityRate.setBusinessEntityRateEffectiveDate(businessEntityRateExchange.getEffectiveDate());
        businessEntityRate.setServiceName(businessEntityRateExchange.getServiceName());
        businessEntityRate.setRateTypeName(businessEntityRateExchange.getRateTypeName());
        businessEntityRate.setBusinessEntityLocationID(businessEntityRateExchange.getBusinessEntityLocationID());
        businessEntityRate.setRateQualifierCode(RateQualifierCode.PAY);
        businessEntityRate.setBusinessEntityRateTerminationDate(termDate);
        businessEntityRate.setServiceUnitName(businessEntityRateExchange.getServiceUnitName());

        return businessEntityRate;
    }

    private int getBusinessEntityRateExchangeCount(
            Connection connection, String bsnEntId, ServiceName serviceName,
            String rateTypeName) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT COUNT(*) AS TOTAL FROM BE_RATE " +
                    "WHERE BE_ID = ? " +
                    "  AND SVC_NAME = ? " +
                    "  AND UPPER(RATE_TYP_NAME) = ? " +
                    "  AND RATE_QLFR_CODE = 'PAY' " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, serviceName.value());
            preparedStatement.setString(index++, rateTypeName.toUpperCase());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TOTAL");
            }

            return 0;

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

    public List<BusinessEntityRateExchange> getBeRateForBsnEntID(String bsnEntId, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_RATE WHERE BE_ID = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "ORDER BY SVC_NAME, UPPER(RATE_TYP_NAME), RATE_QLFR_CODE";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityRate> businessEntityRateList =
                    (List<BusinessEntityRate>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRate");


            List<BusinessEntityRateExchange> resultList = new ArrayList<>();

            HashMap<String,BusinessEntityRateExchange> map = new HashMap<>();
            for (BusinessEntityRate businessEntityRate : businessEntityRateList) {

                String key = String.format("%s:%s:%s:",
                                businessEntityRate.getServiceName().value(),
                                businessEntityRate.getRateTypeName(),
                                businessEntityRate.getRateQualifierCode());

                BusinessEntityRateExchange businessEntityRateExchange;
                if (map.containsKey(key)) {
                    businessEntityRateExchange = map.get(key);
                } else {
                    businessEntityRateExchange = new BusinessEntityRateExchange();
                    map.put(key, businessEntityRateExchange);

                    businessEntityRateExchange.setBusinessEntityID(businessEntityRate.getBusinessEntityID());
                    businessEntityRateExchange.setBusinessEntityLineofBusinessID(businessEntityRate.getBusinessEntityLineOfBusinessID());
                    businessEntityRateExchange.setBusinessEntityLocationID(businessEntityRate.getBusinessEntityLocationID());
                    businessEntityRateExchange.setEffectiveDate(businessEntityRate.getBusinessEntityRateEffectiveDate());
                    businessEntityRateExchange.setServiceName(businessEntityRate.getServiceName());
                    businessEntityRateExchange.setRateTypeName(businessEntityRate.getRateTypeName());
                    businessEntityRateExchange.setServiceUnitName(businessEntityRate.getServiceUnitName());
                }

                switch(businessEntityRate.getRateSubTypeName()) {
                    case REGULAR:
                        businessEntityRateExchange.setRegularPayrollRateSK(businessEntityRate.getBusinessEntityRateSK());
                        businessEntityRateExchange.setRegularChangeVersionID(businessEntityRate.getChangeVersionID());
                        businessEntityRateExchange.setRegularRateAmount(businessEntityRate.getBusinessEntityRateAmount());
                        break;
                    case WEEKEND:
                        businessEntityRateExchange.setWeekendPayrollRateSK(businessEntityRate.getBusinessEntityRateSK());
                        businessEntityRateExchange.setWeekendChangeVersionID(businessEntityRate.getChangeVersionID());
                        businessEntityRateExchange.setWeekendRateAmount(businessEntityRate.getBusinessEntityRateAmount());
                        break;
                    case HOLIDAY:
                        businessEntityRateExchange.setHolidayPayrollRateSK(businessEntityRate.getBusinessEntityRateSK());
                        businessEntityRateExchange.setHolidayChangeVersionID(businessEntityRate.getChangeVersionID());
                        businessEntityRateExchange.setHolidayRateAmount(businessEntityRate.getBusinessEntityRateAmount());
                        break;
                }
            }

            Iterator itr = map.entrySet().iterator();
            while(itr.hasNext()) {

                Map.Entry pair = (Map.Entry)itr.next();
                BusinessEntityRateExchange businessEntityRateExchange = (BusinessEntityRateExchange)pair.getValue();
                if (businessEntityRateExchange.getRegularPayrollRateSK() != null
                        && businessEntityRateExchange.getWeekendPayrollRateSK() != null
                        && businessEntityRateExchange.getHolidayPayrollRateSK() != null) {

                    resultList.add(businessEntityRateExchange);

                } else {
                    logger.error(String.format("getBeRateForBsnEntID: ERROR: BusinessEntityRateExchange: %s: Null SK Value!", businessEntityRateExchange.toString()));
                }
            }

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public List<BusinessEntityComplianceRelationshipDetail> getBeCompRelDetPK(Long compRelSk, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_COMP_REL_DET WHERE BE_COMP_REL_SK = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, compRelSk);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityComplianceRelationshipDetail> resultList =
                    (List<BusinessEntityComplianceRelationshipDetail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceRelationshipDetail");

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
            this.connectionPoolDataService.close(connection);
        }
    }
}
