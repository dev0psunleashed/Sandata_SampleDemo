CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "DocDet" AS 

import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import com.sandata.lab.db.oracle.common.util.StringUtil;
import oracle.jdbc.OracleConnection;
import oracle.sql.STRUCT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements DOC Oracle stored procedures.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class DocDet {

    private static String TABLE_NAME = "DOC_DET";

    private static String SEQUENCE_KEY_COLUMN_NAME = "DOC_DET_SK";

    private static int PRIMARY_KEY_INDEX = 1;

    private static int REC_TERM_TMSTP_INDEX = -1;

    private static int CURR_REC_IND_INDEX = -1;

    private static String INSERT_STATEMENT = "INSERT INTO DOC_DET(DOC_DET_SK, REC_CREATE_TMSTP, REC_UPDATE_TMSTP, DOC_SK, DOC_DET_PROPTY_KEY_NAME, DOC_DET_PROPTY_KEY_VAL) VALUES (?,?,?,?,?,?)";



    public static int insertDocDet(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocDet: insertDocDet: ");

        try {

            return  new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
    public static int updateDocDet(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocDet: updateDocDet: ");

        try {

            return  new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
    public static int deleteDocDet(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocDet: deleteDocDet: ");

        try {

            return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
}

/
