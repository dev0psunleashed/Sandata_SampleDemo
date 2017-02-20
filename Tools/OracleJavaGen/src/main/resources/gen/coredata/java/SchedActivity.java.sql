CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedActivity" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedActivity {

	private static String TABLE_NAME = "SCHED_ACTIVITY";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_ACTIVITY_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_ACTIVITY(SCHED_ACTIVITY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,ACTIVITY_SK) VALUES (?,?,?,?,?,?)";

	public static ResultSet getSchedActivity(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedActivity: getSchedActivity: ");

			try {

					String selectPattern = "SELECT SCHED_ACTIVITY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,ACTIVITY_SK FROM SCHED_ACTIVITY %s";

					String whereClause = "WHERE SCHED_SK=? AND ACTIVITY_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedActivity(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedActivity: getSchedActivity: ");

			try {

					String sql = String.format("SELECT SCHED_ACTIVITY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,ACTIVITY_SK FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedActivity(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedActivity: insertSchedActivity: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedActivity(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedActivity: updateSchedActivity: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedActivity(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedActivity: deleteSchedActivity: ");

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
