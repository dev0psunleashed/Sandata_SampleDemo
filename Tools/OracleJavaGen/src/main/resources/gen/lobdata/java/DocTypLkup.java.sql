CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "DocTypLkup" AS 
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
public class DocTypLkup {

    private static String TABLE_NAME = "DOC_TYP_LKUP";

    private static String SEQUENCE_KEY_COLUMN_NAME = "DOC_TYP_LKUP_SK";

    private static int PRIMARY_KEY_INDEX = 1;

    private static int REC_TERM_TMSTP_INDEX = -1;

    private static int CURR_REC_IND_INDEX = -1;

    private static String INSERT_STATEMENT = "INSERT INTO DOC_TYP_LKUP(DOC_TYP_LKUP_SK, REC_CREATE_TMSTP, REC_UPDATE_TMSTP,DOC_TYP_NAME,DOC_TYP_DESC) VALUES (?,?,?,?,?)";



    public static int insertDocTypLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocTypLkup: insertDocTypLkup: ");

        try {

            return  new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
    public static int updateDocTypLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocTypLkup: updateDoc: ");

        try {

            return  new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
    public static int deleteDocTypLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("DocTypLkup: deleteDocTypLkup: ");

        try {

            return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
}

/
