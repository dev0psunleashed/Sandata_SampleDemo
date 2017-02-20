CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "RprtReq" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RprtReq {

	private static String TABLE_NAME = "RPRT_REQ";

	private static String SEQUENCE_KEY_COLUMN_NAME = "RPRT_REQ_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO RPRT_REQ(RPRT_REQ_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,RPRT_ID,USER_GUID,APP_SESS_SK) VALUES (?,?,?,?,?,?)";

	public static ResultSet getRprtReq(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReq: getRprtReq: ");

			try {

					String sql = String.format("SELECT RPRT_REQ_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,RPRT_ID,USER_GUID,APP_SESS_SK FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertRprtReq(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReq: insertRprtReq: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateRprtReq(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReq: updateRprtReq: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteRprtReq(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("RprtReq: deleteRprtReq: ");

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
