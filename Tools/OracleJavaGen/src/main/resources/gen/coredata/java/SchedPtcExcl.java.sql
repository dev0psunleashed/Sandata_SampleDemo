CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedPtcExcl" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedPtcExcl {

	private static String TABLE_NAME = "SCHED_PTC_EXCL";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_PTC_EXCL_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_PTC_EXCL(SCHED_PTC_EXCL_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_REASON_MEMO,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_PTC_EXCL_QLFR,SCHED_PERM_QLFR,SCHED_PTC_EXCL_NOTE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getSchedPtcExcl(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedPtcExcl: getSchedPtcExcl: ");

			try {

					String selectPattern = "SELECT SCHED_PTC_EXCL_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_REASON_MEMO,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_PTC_EXCL_QLFR,SCHED_PERM_QLFR,SCHED_PTC_EXCL_NOTE FROM SCHED_PTC_EXCL %s";

					String whereClause = "WHERE PT_ID=? AND STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedPtcExcl(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedPtcExcl: getSchedPtcExcl: ");

			try {

					String sql = String.format("SELECT SCHED_PTC_EXCL_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_REASON_MEMO,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_PTC_EXCL_QLFR,SCHED_PERM_QLFR,SCHED_PTC_EXCL_NOTE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedPtcExcl(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedPtcExcl: insertSchedPtcExcl: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedPtcExcl(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedPtcExcl: updateSchedPtcExcl: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedPtcExcl(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedPtcExcl: deleteSchedPtcExcl: ");

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
