CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedEvntSvc" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedEvntSvc {

	private static String TABLE_NAME = "SCHED_EVNT_SVC";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_EVNT_SVC_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_EVNT_SVC(SCHED_EVNT_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_UPDATED_BY,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,SCHED_EVNT_SK,SVC_SK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getSchedEvntSvc(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntSvc: getSchedEvntSvc: ");

			try {

					String selectPattern = "SELECT SCHED_EVNT_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_UPDATED_BY,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,SCHED_EVNT_SK,SVC_SK FROM SCHED_EVNT_SVC %s";

					String whereClause = "WHERE SCHED_EVNT_SK=? AND SVC_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedEvntSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntSvc: getSchedEvntSvc: ");

			try {

					String sql = String.format("SELECT SCHED_EVNT_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_UPDATED_BY,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,SCHED_EVNT_SK,SVC_SK FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedEvntSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntSvc: insertSchedEvntSvc: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedEvntSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntSvc: updateSchedEvntSvc: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedEvntSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvntSvc: deleteSchedEvntSvc: ");

			try {

					return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}



}
;
/
