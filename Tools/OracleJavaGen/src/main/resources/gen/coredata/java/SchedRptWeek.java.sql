CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedRptWeek" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedRptWeek {

	private static String TABLE_NAME = "SCHED_RPT_WEEK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_RPT_WEEK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_RPT_WEEK(SCHED_RPT_WEEK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,WEEK_OF_MONTH_NUM,SCHED_SK,SCHED_START_TIME,SCHED_END_TIME) VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet getSchedRptWeek(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedRptWeek: getSchedRptWeek: ");

			try {

					String selectPattern = "SELECT SCHED_RPT_WEEK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,WEEK_OF_MONTH_NUM,SCHED_SK,SCHED_START_TIME,SCHED_END_TIME FROM SCHED_RPT_WEEK %s";

					String whereClause = "WHERE SCHED_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedRptWeek(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedRptWeek: getSchedRptWeek: ");

			try {

					String sql = String.format("SELECT SCHED_RPT_WEEK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,WEEK_OF_MONTH_NUM,SCHED_SK,SCHED_START_TIME,SCHED_END_TIME FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedRptWeek(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedRptWeek: insertSchedRptWeek: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedRptWeek(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedRptWeek: updateSchedRptWeek: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedRptWeek(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedRptWeek: deleteSchedRptWeek: ");

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
