CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedEvntAuth" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedEvntAuth {

	private static String TABLE_NAME = "SCHED_EVNT_AUTH";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_EVNT_AUTH_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_EVNT_AUTH(SCHED_EVNT_AUTH_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,SCHED_EVNT_SK,AUTH_SK,AUTH_QLFR) VALUES (?,?,?,?,?,?,?)";

	public static ResultSet getSchedEvntAuth(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntAuth: getSchedEvntAuth: ");

			try {

					String selectPattern = "SELECT SCHED_EVNT_AUTH_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,SCHED_EVNT_SK,AUTH_SK,AUTH_QLFR FROM SCHED_EVNT_AUTH %s";

					String whereClause = "WHERE SCHED_EVNT_SK=? AND AUTH_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedEvntAuth(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntAuth: getSchedEvntAuth: ");

			try {

					String sql = String.format("SELECT SCHED_EVNT_AUTH_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,SCHED_EVNT_SK,AUTH_SK,AUTH_QLFR FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedEvntAuth(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntAuth: insertSchedEvntAuth: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedEvntAuth(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntAuth: updateSchedEvntAuth: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedEvntAuth(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntAuth: deleteSchedEvntAuth: ");

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
