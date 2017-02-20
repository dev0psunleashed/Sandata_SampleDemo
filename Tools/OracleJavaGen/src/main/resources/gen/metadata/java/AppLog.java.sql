CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AppLog" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppLog {

	private static String TABLE_NAME = "APP_LOG";

	private static String SEQUENCE_KEY_COLUMN_NAME = "APP_LOG_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO APP_LOG(APP_LOG_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_SESS_SK,LOG_HOST,LOG_PROCESS,LOG_PID,LOG_THREAD,LOG_LVL,LOG_MSG) VALUES (?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAppLog(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppLog: getAppLog: ");

			try {

					String sql = String.format("SELECT APP_LOG_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_SESS_SK,LOG_HOST,LOG_PROCESS,LOG_PID,LOG_THREAD,LOG_LVL,LOG_MSG FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAppLog(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppLog: insertAppLog: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAppLog(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppLog: updateAppLog: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAppLog(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppLog: deleteAppLog: ");

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
