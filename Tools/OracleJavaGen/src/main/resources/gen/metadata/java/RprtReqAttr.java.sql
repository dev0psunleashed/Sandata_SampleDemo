CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "RprtReqAttr" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RprtReqAttr {

	private static String TABLE_NAME = "RPRT_REQ_ATTR";

	private static String SEQUENCE_KEY_COLUMN_NAME = "RPRT_REQ_ATTR_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO RPRT_REQ_ATTR(RPRT_REQ_ATTR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,RPRT_ID,RPRT_REQ_SK,RPRT_REQ_ATTR_KEY_NAME,RPRT_REQ_ATTR_KEY_VAL) VALUES (?,?,?,?,?,?,?)";

	public static ResultSet getRprtReqAttr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReqAttr: getRprtReqAttr: ");

			try {

					String sql = String.format("SELECT RPRT_REQ_ATTR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,RPRT_ID,RPRT_REQ_SK,RPRT_REQ_ATTR_KEY_NAME,RPRT_REQ_ATTR_KEY_VAL FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertRprtReqAttr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReqAttr: insertRprtReqAttr: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateRprtReqAttr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReqAttr: updateRprtReqAttr: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteRprtReqAttr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReqAttr: deleteRprtReqAttr: ");

			try {

					return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, false);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}



}
;
/
