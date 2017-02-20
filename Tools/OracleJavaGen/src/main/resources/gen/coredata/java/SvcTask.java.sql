CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SvcTask" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SvcTask {

	private static String TABLE_NAME = "SVC_TASK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SVC_TASK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SVC_TASK(SVC_TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SVC_SK,BE_ID,BE_TASK_ID) VALUES (?,?,?,?,?,?,?)";

	public static ResultSet getSvcTask(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcTask: getSvcTask: ");

			try {

					String sql = String.format("SELECT SVC_TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SVC_SK,BE_ID,BE_TASK_ID FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSvcTask(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcTask: getSvcTask: ");

			try {

					String selectPattern = "SELECT SVC_TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SVC_SK,BE_ID,BE_TASK_ID FROM SVC_TASK %s";

					String whereClause = "WHERE SVC_SK=? AND BE_TASK_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSvcTask(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcTask: getSvcTask: ");

			try {

					String sql = String.format("SELECT SVC_TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SVC_SK,BE_ID,BE_TASK_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSvcTask(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcTask: insertSvcTask: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSvcTask(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcTask: updateSvcTask: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSvcTask(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcTask: deleteSvcTask: ");

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