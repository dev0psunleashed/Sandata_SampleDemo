/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.dl.doc.services.oracle;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.jpub.model.BeDocXwalkT;
import com.sandata.lab.data.model.jpub.model.PtDocXwalkT;
import com.sandata.lab.data.model.jpub.model.StaffDocXwalkT;
import com.sandata.lab.data.model.jpub.model.VisitDocXwalkT;
import com.sandata.lab.data.model.response.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */


public class MainOracleRepositoryService extends OracleRepositoryService {

    private final String CURR_IND = "CURR_REC_IND";
    private final String REC_TERM = "REC_TERM_TMSTP";


    public Response getDocXwalkByKey(String tableName, Map<String, Object> params, int page, int pageSize, String orderBy, String direction) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM (SELECT ROWNUM ROW_NUMBER,COUNT(*) OVER() TOTAL_ROWS,r1.* FROM";
         /*           "(SELECT * FROM STAFF_DOC_XWALK WHERE STAFF_ID = ? AND BE_ID = ?" +
                    "AND (TO_CHAR(%S, 'YYYY-MM-DD')) = '9999-12-31' AND %S = 1 ORDER BY %s %s) r1)" +
                    "WHERE ROW_NUMBER BETWEEN ? AND ?", REC_TERM, CURR_IND, orderBy, direction);
            */
            StringBuilder stringBuilder = new StringBuilder(sql);
            stringBuilder = stringBuilder.append(String.format("(SELECT * from %s where ", tableName));
            
           /* preparedStatement.setString(1, StaffId);
            preparedStatement.setString(2,BeId);
*/
            for (String key : params.keySet()) {
                stringBuilder = stringBuilder.append(key + " = ? and ");
            }

            stringBuilder = stringBuilder.append(String.format("TO_CHAR(%S, 'YYYY-MM-DD') = '9999-12-31' AND %S = 1 ORDER BY %s %s) r1)"
                    + "WHERE ROW_NUMBER BETWEEN ? AND ?", REC_TERM, CURR_IND, orderBy, direction));

            preparedStatement = connection.prepareStatement(stringBuilder.toString());

            int i = 1;
            for (Object value : params.values()) {
                preparedStatement.setObject(i, value);
                i++;
            }

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            preparedStatement.setInt(i, fromRow);
            preparedStatement.setInt(i + 1, toRow);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderBy);
            response.setOrderByDirection(direction);

            if (tableName.equalsIgnoreCase("BE_DOC_XWALK")) {
                response.setData(getBeXwalkByBeID(resultSet, response));
            } else if (tableName.equalsIgnoreCase("PT_DOC_XWALK")) {
                response.setData(getPtXwalkByPtID(resultSet, response));
            } else if (tableName.equalsIgnoreCase("STAFF_DOC_XWALK")) {
                response.setData(getStaffXwalkByStaffID(resultSet, response));
            } else if (tableName.equalsIgnoreCase("VISIT_DOC_XWALK")) {
                response.setData(getVisitXwalkByVisitSK(resultSet, response));
            }

            return response;

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("getStaffXwalkByStaffID: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {

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
            this.closeOracleConnection(connection);
        }
    }

    public List<PtDocXwalkT> getPtXwalkByPtID(ResultSet resultSet, Response response) throws SandataRuntimeException {
        try {

            List<PtDocXwalkT> PtDocXwalkTypList = new ArrayList<>();

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                PtDocXwalkT PtDocXwalkTyp = resultSetTransformer.transformResultSetToPtDocXwalkTyp(resultSet);
                PtDocXwalkTypList.add(PtDocXwalkTyp);
            }

            return PtDocXwalkTypList;
        } catch (Exception e) {

            e.printStackTrace();

            throw new SandataRuntimeException(String.format("getPtXwalkByPtID: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public List<VisitDocXwalkT> getVisitXwalkByVisitSK(ResultSet resultSet, Response response) throws SandataRuntimeException {
        try {

            List<VisitDocXwalkT> visitDocXwalkTypList = new ArrayList<>();

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                VisitDocXwalkT visitDocXwalkTyp = resultSetTransformer.transformResultSetToVisitDocXwalkTyp(resultSet);
                visitDocXwalkTypList.add(visitDocXwalkTyp);
            }

            return visitDocXwalkTypList;
        } catch (Exception e) {

            e.printStackTrace();

            throw new SandataRuntimeException(String.format("getVisitXwalkByVisitSK: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public List<StaffDocXwalkT> getStaffXwalkByStaffID(ResultSet resultSet, Response response) throws SandataRuntimeException {

        try {

            List<StaffDocXwalkT> staffDocXwalkTypList = new ArrayList<>();

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                StaffDocXwalkT staffDocXwalkTyp = resultSetTransformer.transformResultSetToStaffDocXwalkTyp(resultSet);
                staffDocXwalkTypList.add(staffDocXwalkTyp);
            }

            return staffDocXwalkTypList;
        } catch (Exception e) {

            e.printStackTrace();

            throw new SandataRuntimeException(String.format("getStaffXwalkByStaffID: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }


    public List<BeDocXwalkT> getBeXwalkByBeID(ResultSet resultSet, Response response) throws SandataRuntimeException {

        try {

            List<BeDocXwalkT> BeDocXwalkTypList = new ArrayList<>();

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                BeDocXwalkT BeDocXwalkTyp = resultSetTransformer.transformResultSetToBeDocXwalkTyp(resultSet);
                BeDocXwalkTypList.add(BeDocXwalkTyp);
            }

            return BeDocXwalkTypList;
        } catch (Exception e) {

            e.printStackTrace();

            throw new SandataRuntimeException(String.format("getStaffXwalkByStaffID: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public PtDocXwalkT getPtDocXwalk(ResultSet resultSet) {
        PtDocXwalkT PtDocXwalkTyp = null;

        try {

            while (resultSet.next()) {
                PtDocXwalkTyp = resultSetTransformer.transformResultSetToPtDocXwalkTyp(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return PtDocXwalkTyp;
    }

    public BeDocXwalkT getBeDocXwalk(ResultSet resultSet) {
        BeDocXwalkT BeDocXwalkTyp = null;

        try {

            while (resultSet.next()) {
                BeDocXwalkTyp = resultSetTransformer.transformResultSetToBeDocXwalkTyp(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return BeDocXwalkTyp;
    }


    public StaffDocXwalkT getStaffDocXwalk(ResultSet resultSet) {
        StaffDocXwalkT staffDocXwalkTyp = null;

        try {

            while (resultSet.next()) {
                staffDocXwalkTyp = resultSetTransformer.transformResultSetToStaffDocXwalkTyp(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return staffDocXwalkTyp;
    }

    public VisitDocXwalkT getVisitDocXwalk(ResultSet resultSet) {
        VisitDocXwalkT visitDocXwalkTyp = null;

        try {

            while (resultSet.next()) {
                visitDocXwalkTyp = resultSetTransformer.transformResultSetToVisitDocXwalkTyp(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return visitDocXwalkTyp;
    }

    public Object getDocXwalk(String table, Map<String, Object> params) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("Select * from %s ", table);
            //where DOC_ID = ?" +
            //"and (TO_CHAR(%s, 'YYYY-MM-DD')) = '9999-12-31' and %s = 1", table, REC_TERM, CURR_IND);


            StringBuilder stringBuilder = new StringBuilder(sql);

            stringBuilder = stringBuilder.append(" where ");

            for (String key : params.keySet()) {
                stringBuilder = stringBuilder.append(key + "= ? and ");
            }

            stringBuilder = stringBuilder.append(String.format("(TO_CHAR(%s, 'YYYY-MM-DD')) = '9999-12-31' and %s = 1", REC_TERM, CURR_IND));

            PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());

            int i = 1;
            for (Object value : params.values()) {
                preparedStatement.setObject(i, value);
                i++;
            }


            resultSet = preparedStatement.executeQuery();


            if (table.equalsIgnoreCase("BE_DOC_XWALK")) {
                return getBeDocXwalk(resultSet);
            } else if (table.equalsIgnoreCase("PT_DOC_XWALK")) {
                return getPtDocXwalk(resultSet);
            } else if (table.equalsIgnoreCase("STAFF_DOC_XWALK")) {
                return getStaffDocXwalk(resultSet);
            } else if (table.equalsIgnoreCase("VISIT_DOC_XWALK")) {
                return getVisitDocXwalk(resultSet);
            }

            return resultSet;
        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }

                throw new SandataRuntimeException(String.format("%s: %s",
                        e.getClass().getName(), e.getMessage()), e);
            }
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
            this.closeOracleConnection(connection);

        }

        return null;
    }
}
