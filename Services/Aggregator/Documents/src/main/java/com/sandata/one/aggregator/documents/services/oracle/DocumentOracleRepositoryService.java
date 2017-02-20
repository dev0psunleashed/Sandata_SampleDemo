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

package com.sandata.one.aggregator.documents.services.oracle;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.document.dl.model.Document;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.one.aggregator.documents.api.DocumentStatusUpdate;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocDetProptyLkupT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocDetT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocT;
import com.sandata.one.aggregator.documents.impl.data.model.jpub.DocTypLkupT;
import com.sandata.one.aggregator.documents.utils.log.DocumentDataLogger;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.BLOB;

import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository class for handling document database access.
 * <p/>
 *
 * @author Ralph Sylvain
 */

public class DocumentOracleRepositoryService extends OracleRepositoryService {

    private final String TABLE_NAME = "DOC";

    /**
     * Retrieves the InputStream for the BLOB column in the DOC table.
     *
     * @param docId     The DOC_ID of the BLOB that we want to retrieve.
     * @param cachePath The path to where the cached Files are stored.
     * @return Returns the File that points to the document.
     * @throws SandataRuntimeException
     */
    public File getDocumentFile(final String docId, final String cachePath, final SandataLogger logger, String dataColumn) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        InputStream inputStream = null;
        OutputStream outputStream = null;

        BLOB blob = null;

        try {

            File cachedFile = new File(String.format("%s/%s", cachePath, docId + dataColumn));

            /*   if (cachedFile.exists()) {

                BufferedReader br = new BufferedReader(new FileReader(cachedFile));
                if (br.readLine() != null) {
                    logger.info(String.format("getDocumentFile: [CACHE_FILE:%s]: [DOC_ID=%s]: cachedFile.exists()", cachedFile.getPath(), docId + dataColumn));
                    return cachedFile;
                }
            }
           */

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT %s FROM %s.DOC WHERE DOC_ID = ?", dataColumn, ConnectionType.LOBDATA);

            logger.info(String.format("getDocumentFile: [DOC_ID=%s]: [SQL=%s]", docId, sql));

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            preparedStatement.setString(i, docId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blob = (BLOB) resultSet.getBlob(dataColumn);

                logger.info(String.format("getDocumentFile: [DOC_ID=%s]: [BLOB_SIZE=%d]", docId, blob.length()));

                inputStream = blob.binaryStreamValue();
                outputStream = new FileOutputStream(cachedFile);
                int readBytes = 0;
                byte[] bytes = new byte[1024];
                while ((readBytes = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, readBytes);
                }

                outputStream.flush();
                logger.info(String.format("getDocumentFile: [CACHE_FILE:%s]: [DOC_ID=%s]: Cached file created!", cachedFile.getPath(), docId));
                return cachedFile;
            } else {
                logger.warn(String.format("getDocumentFile: WARNING: [DOC_ID=%s]: [SQL=%s]: No result found for give document ID.", docId, sql));
            }

            return null;
        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("saveFile: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        } finally {

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

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
            connectionPoolDataService.close(connection);
        }
    }

    /**
     * The @file is save as a BLOB to the document storage database. Lock the BLOB column FOR UPDATE first before
     * attempting to save the file. The save occurs via streaming.
     *
     * @param file  Is the full path to the file that will be saved.
     * @param docId Is the DOC_ID for the document record we are updating.
     * @return Returns TRUE if the file was saved successfully.
     * @throws SandataRuntimeException
     */
    public boolean saveFileToBlob(final File file, final String docId, final String docStatusId,
                                  DocumentStatusUpdate documentStatusUpdate, String dataColumnName) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger();

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        boolean bResult = false;

        if (file == null) {
            throw new SandataRuntimeException(String.format("saveFileToBlob: %s: File == null",
                    getClass().getName()));
        }

        if (!file.exists()) {
            throw new SandataRuntimeException(String.format("saveFileToBlob: %s: [FILE_PATH=%s]: File does NOT exist!",
                    getClass().getName(), file.getPath()));
        }

        if (docId == null) {
            throw new SandataRuntimeException(String.format("saveFileToBlob: %s: DOC_ID == null",
                    getClass().getName()));
        }

        logger.info(String.format("saveFileToBlob: Saving file to BLOB: [FILE_PATH=%s] [DOC_ID=%s] ...", file.getPath(), docId));

        try {

            final long totalBytes = file.length();

            documentStatusUpdate.updateStatus(0L, totalBytes, "Starting", docStatusId, logger);

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_DOC_UTIL.UPDATE_DOC_BLOB(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

            int index = 2;
            callableStatement.setString(index++, dataColumnName);
            callableStatement.setString(index++, docId);

            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            if (resultSet.next()) {

                logger.info(String.format("saveFileToBlob: [DOC_ID=%s]: Locked!", docId));
                documentStatusUpdate.updateStatus(0L, totalBytes, "Uploading", docStatusId, logger);

                BLOB blob = (BLOB) resultSet.getBlob(dataColumnName);

                OutputStream outputStream = blob.setBinaryStream(0L);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[blob.getBufferSize()];
                int bytesread;
                long totalBytesRead = 0;
                while ((bytesread = fis.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesread);
                    totalBytesRead += bytesread;
                    documentStatusUpdate.updateStatus(totalBytesRead, totalBytes, "Uploading", docStatusId, logger);
                }

                outputStream.flush();
                outputStream.close();
                fis.close();
                bResult = true;

                logger.info(String.format("saveFileToBlob: [DOC_ID=%s]: BLOB record was successfully saved!", docId));
                documentStatusUpdate.updateStatus(totalBytesRead, totalBytes, "Complete", docStatusId, logger);
            } else {
                String errMsg = String.format("saveFileToBlob: ERROR: [DOC_ID=%s]: BLOB record not found!", docId);
                logger.error(errMsg);
                documentStatusUpdate.errorMessage(errMsg, "Error", docStatusId, logger);
            }

            connection.commit();

            logger.info(String.format("saveFileToBlob: [DOC_ID=%s]: Commit executed!", docId));

            return bResult;
        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("saveFile: %s: %s: [ERROR_MSG=%s]",
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

            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            connectionPoolDataService.close(connection);

            logger.stop();
        }
    }

    public List<Document> getDocuments(List<String> docIDs, String type) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT T1.DOC_SK,T1.DOC_ID,T1.DOC_TYP_NAME,T1.REC_CREATE_TMSTP,T1.REC_UPDATE_TMSTP,T2.* FROM "
                    + ConnectionType.LOBDATA
                    + ".DOC T1"
                    + " LEFT OUTER JOIN (SELECT * FROM "
                    + ConnectionType.LOBDATA
                    + ".DOC_DET) T2"
                    + " ON T1.DOC_SK = T2.DOC_SK"
                    + " WHERE T1.DOC_ID IN (?)";

            StringBuilder stringBuilder = new StringBuilder();

            int docIDSize = docIDs.size();
            for (int i = 0; i < docIDSize; i++) {
                stringBuilder.append('?');

                if (i < docIDSize - 1) {
                    stringBuilder.append(',');
                }
            }

            sql = sql.replace("(?)", "(" + stringBuilder.toString() + ")");

            if (!StringUtil.IsNullOrEmpty(type)) {

                sql = sql.concat(" and T2.DOC_DET_PROPTY_KEY_VAL = ?");
            }

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            for (String docId : docIDs) {
                preparedStatement.setString(i, docId);
                i++;
            }

            if (!StringUtil.IsNullOrEmpty(type)) {

                preparedStatement.setString(i, type);
            }

            resultSet = preparedStatement.executeQuery();

            Map<BigInteger, Document> documentMap = new HashMap<>();

            while (resultSet.next()) {
                resultSetTransformer.transformResultSetToDocument(resultSet, documentMap);
            }

            return new ArrayList<Document>(documentMap.values());
        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("getDocuments: %s: %s: [ERROR_MSG=%s]",
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

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            connectionPoolDataService.close(connection);

        }
    }

    public Document getDocumentForID(final String docID) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM "
                    + ConnectionType.LOBDATA
                    + ".DOC "
                    + "LEFT OUTER JOIN DOC_DET "
                    + "ON DOC.DOC_SK = DOC_DET.DOC_SK "
                    + "WHERE DOC.DOC_ID=?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, docID);

            resultSet = preparedStatement.executeQuery();

            Map<BigInteger, Document> documentMap = new HashMap<>();

            while (resultSet.next()) {
                resultSetTransformer.transformResultSetToDocument(resultSet, documentMap);
            }

            Document document = documentMap.entrySet().iterator().next().getValue();

            return document;
        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("getDocumentForSK: %s: %s: [ERROR_MSG=%s]",
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

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            connectionPoolDataService.close(connection);

        }
    }

    public List<DocTypLkupT> getDocTypLkupTyp() throws SandataRuntimeException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.LOBDATA
                    + ".DOC_TYP_LKUP";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            List<DocTypLkupT> docTypLkupTypList = new ArrayList<>();

            while (resultSet.next()) {
                docTypLkupTypList.add(resultSetTransformer.transformResultSetToDocTypLkupTyp(resultSet));
            }

            return docTypLkupTypList;
        } catch (Exception e) {

            e.printStackTrace();

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("getDocTypLkupTyp: %s: %s: [ERROR_MSG=%s]",
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

            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            connectionPoolDataService.close(connection);
        }
    }

    public int updateDocDetails(List<DocDetT> docDetTypList) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        int resultVal = 0;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.LOBDATA + "." + "DOC_DET_TAB", connection);
            ARRAY docDetTypArray = new ARRAY(arrayDes, connection, docDetTypList.toArray());
            
            String callMethod = "{?=call PKG_DOC_UTIL.UPDATE_DOC_DET_PROPTY(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            
            callableStatement.setArray(2, docDetTypArray);
            
            callableStatement.execute();
            resultVal = (Integer)callableStatement.getObject(1);

            connection.commit();


            return resultVal;
        } catch (Exception e) {

            e.printStackTrace();

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("updateDocDetails: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            connectionPoolDataService.close(connection);
        }
    }

    public List<DocDetProptyLkupT> getDocDetailLookup() throws SandataRuntimeException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.LOBDATA
                    + ".DOC_DET_PROPTY_LKUP";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            List<DocDetProptyLkupT> docDetProptyLkupTypList = new ArrayList<>();

            while (resultSet.next()) {
                docDetProptyLkupTypList.add(resultSetTransformer.transformResultSetToDocDetProptyLkupT(resultSet));
            }

            return docDetProptyLkupTypList;
        } catch (Exception e) {

            e.printStackTrace();

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("getDocDetailLookup: %s: %s: [ERROR_MSG=%s]",
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

            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            connectionPoolDataService.close(connection);
        }
    }

    public Response getDocsByDocDet(String detlID, String detlValue, int page, int pageSize, String orderBy, String direction) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM (SELECT ROWNUM ROW_NUMBER,COUNT(*) OVER() TOTAL_ROWS,r1.* FROM";

            StringBuilder stringBuilder = new StringBuilder(sql);
            stringBuilder = stringBuilder.append("(SELECT DOC.DOC_SK,DOC.DOC_ID,DOC.DOC_TYP_NAME,DOC.REC_CREATE_TMSTP,DOC.REC_UPDATE_TMSTP,T2.DOC_DET_SK,T2.DOC_DET_PROPTY_KEY_NAME, T2.DOC_DET_PROPTY_KEY_VAL " +
                    "FROM "
                    + ConnectionType.LOBDATA
                    + ".DOC_DET " +
                    "JOIN "
                    + ConnectionType.LOBDATA
                    + ".DOC  on DOC.DOC_SK = DOC_DET.DOC_SK  " +
                    "JOIN "
                    + ConnectionType.LOBDATA
                    + ".DOC_DET T2 on DOC.DOC_SK = T2.DOC_SK " +
                    "where DOC_DET.DOC_DET_PROPTY_KEY_NAME = ? and DOC_DET.DOC_DET_PROPTY_KEY_VAL = ? ");


            stringBuilder = stringBuilder.append(String.format("ORDER BY %s %s) r1)"
                    + "WHERE ROW_NUMBER BETWEEN ? AND ?", orderBy, direction));

            preparedStatement = connection.prepareStatement(stringBuilder.toString());


            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            preparedStatement.setObject(1, detlID);
            preparedStatement.setObject(2, detlValue);
            preparedStatement.setInt(3, fromRow);
            preparedStatement.setInt(4, toRow);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderBy);
            response.setOrderByDirection(direction);

            List<DocT> docTypList = new ArrayList<>();

            Map<BigInteger, Document> documentMap = new HashMap<>();

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                resultSetTransformer.transformResultSetToDocument(resultSet, documentMap);

            }

            response.setData(new ArrayList<Document>(documentMap.values()));

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
            connectionPoolDataService.close(connection);
        }
    }

    public String getDocIdForVisit(final long visitSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DOC_ID FROM VISIT_DOC_XWALK WHERE VISIT_SK = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("DOC_ID");
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

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

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

    @Override
    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(ConnectionType.LOBDATA);
    }
}
