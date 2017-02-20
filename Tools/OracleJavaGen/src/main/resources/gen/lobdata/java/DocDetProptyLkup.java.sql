CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "DocDetProptyLkup" AS 
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
public class DocDetProptyLkup {

    private static String TABLE_NAME = "DOC_DET_PROPTY_LKUP";

    private static String SEQUENCE_KEY_COLUMN_NAME = "DOC_DET_PROPTY_LKUP_SK";

    private static int PRIMARY_KEY_INDEX = 1;

    private static int REC_TERM_TMSTP_INDEX = -1;

    private static int CURR_REC_IND_INDEX = -1;

    private static String INSERT_STATEMENT = "INSERT INTO DOC_DET_PROPTY_LKUP(DOC_DET_PROPTY_LKUP_SK, REC_CREATE_TMSTP, REC_UPDATE_TMSTP, DOC_DET_PROPTY_KEY_NAME, DOC_DET_PROPTY_KEY_DESC) VALUES (?,?,?,?,?)";



    public static int insertDoc(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocDetProptyLkup: insertDocDetProptyLkup: ");

        try {

            return  new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
    public static int updateDoc(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocDetProptyLkup: updateDocDetProptyLkup: ");

        try {

            return  new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
    public static int deleteDoc(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocDetProptyLkup: deleteDocDetProptyLkup: ");

        try {

            return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
}

/
