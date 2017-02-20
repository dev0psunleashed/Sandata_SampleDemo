CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedEvnt" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedEvnt {

	private static String TABLE_NAME = "SCHED_EVNT";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_EVNT_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 11;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 12;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_EVNT(SCHED_EVNT_SK,SCHED_EVNT_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,AUTH_SK,AUTH_QLFR,STAFF_ID,PAYER_ID,RFRL_SK,BE_CALENDAR_LKUP_SK,SCHED_SK,POC_SK,TZ_NAME,SCHED_EVNT_BILL_RATE,MASTER_RATE_ID,DAY_OF_MONTH,SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME,SCHED_EVNT_TOTAL_HRS,SCHED_EVNT_STATUS,SCHED_EVNT_PAY_RATE,SCHED_EVNT_UNIT,SCHED_EVNT_RESTRICTION,SCHED_EVNT_COMMENT,SCHED_EVNT_MANUAL_OVERRIDE_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getSchedEvnt(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvnt: getSchedEvnt: ");

			try {

					String selectPattern = "SELECT SCHED_EVNT_SK,SCHED_EVNT_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,AUTH_SK,AUTH_QLFR,STAFF_ID,PAYER_ID,RFRL_SK,BE_CALENDAR_LKUP_SK,SCHED_SK,POC_SK,TZ_NAME,SCHED_EVNT_BILL_RATE,MASTER_RATE_ID,DAY_OF_MONTH,SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME,SCHED_EVNT_TOTAL_HRS,SCHED_EVNT_STATUS,SCHED_EVNT_PAY_RATE,SCHED_EVNT_UNIT,SCHED_EVNT_RESTRICTION,SCHED_EVNT_COMMENT,SCHED_EVNT_MANUAL_OVERRIDE_IND FROM SCHED_EVNT %s";

					String whereClause = "WHERE SCHED_SK=? AND BE_ID=? AND PT_ID=? AND STAFF_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedEvnt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvnt: getSchedEvnt: ");

			try {

					String sql = String.format("SELECT SCHED_EVNT_SK,SCHED_EVNT_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,AUTH_SK,AUTH_QLFR,STAFF_ID,PAYER_ID,RFRL_SK,BE_CALENDAR_LKUP_SK,SCHED_SK,POC_SK,TZ_NAME,SCHED_EVNT_BILL_RATE,MASTER_RATE_ID,DAY_OF_MONTH,SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME,SCHED_EVNT_TOTAL_HRS,SCHED_EVNT_STATUS,SCHED_EVNT_PAY_RATE,SCHED_EVNT_UNIT,SCHED_EVNT_RESTRICTION,SCHED_EVNT_COMMENT,SCHED_EVNT_MANUAL_OVERRIDE_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedEvnt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvnt: insertSchedEvnt: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedEvnt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvnt: updateSchedEvnt: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedEvnt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedEvnt: deleteSchedEvnt: ");

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
