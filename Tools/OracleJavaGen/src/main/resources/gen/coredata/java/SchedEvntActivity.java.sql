CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedEvntActivity" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedEvntActivity {

	private static String TABLE_NAME = "SCHED_EVNT_ACTIVITY";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_EVNT_ACTIVITY_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_EVNT_ACTIVITY(SCHED_EVNT_ACTIVITY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_EVNT_SK,ACTIVITY_SK) VALUES (?,?,?,?,?,?)";

	public static ResultSet getSchedEvntActivity(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntActivity: getSchedEvntActivity: ");

			try {

					String selectPattern = "SELECT SCHED_EVNT_ACTIVITY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_EVNT_SK,ACTIVITY_SK FROM SCHED_EVNT_ACTIVITY %s";

					String whereClause = "WHERE SCHED_EVNT_SK=? AND ACTIVITY_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedEvntActivity(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntActivity: getSchedEvntActivity: ");

			try {

					String sql = String.format("SELECT SCHED_EVNT_ACTIVITY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_EVNT_SK,ACTIVITY_SK FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedEvntActivity(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntActivity: insertSchedEvntActivity: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedEvntActivity(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntActivity: updateSchedEvntActivity: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedEvntActivity(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntActivity: deleteSchedEvntActivity: ");

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
