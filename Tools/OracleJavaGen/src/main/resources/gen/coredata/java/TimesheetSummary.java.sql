CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "TimesheetSummary" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TimesheetSummary {

	private static String TABLE_NAME = "TIMESHEET_SUMMARY";

	private static String SEQUENCE_KEY_COLUMN_NAME = "TIMESHEET_SUMMARY_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 4;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO TIMESHEET_SUMMARY(TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,TIMESHEET_SUMMARY_ID,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,STAFF_ID,BE_CALENDAR_LKUP_SK,TIMESHEET_WEEK_START_DATE,TIMESHEET_WEEK_END_DATE,TIMESHEET_OT_HRS,TIMESHEET_WEEK_TOTAL_HRS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getTimesheetSummary(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetSummary: getTimesheetSummary: ");

			try {

					String sql = String.format("SELECT TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,TIMESHEET_SUMMARY_ID,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,STAFF_ID,BE_CALENDAR_LKUP_SK,TIMESHEET_WEEK_START_DATE,TIMESHEET_WEEK_END_DATE,TIMESHEET_OT_HRS,TIMESHEET_WEEK_TOTAL_HRS FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getTimesheetSummary(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetSummary: getTimesheetSummary: ");

			try {

					String selectPattern = "SELECT TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,TIMESHEET_SUMMARY_ID,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,STAFF_ID,BE_CALENDAR_LKUP_SK,TIMESHEET_WEEK_START_DATE,TIMESHEET_WEEK_END_DATE,TIMESHEET_OT_HRS,TIMESHEET_WEEK_TOTAL_HRS FROM TIMESHEET_SUMMARY %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getTimesheetSummary(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetSummary: getTimesheetSummary: ");

			try {

					String sql = String.format("SELECT TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,TIMESHEET_SUMMARY_ID,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,STAFF_ID,BE_CALENDAR_LKUP_SK,TIMESHEET_WEEK_START_DATE,TIMESHEET_WEEK_END_DATE,TIMESHEET_OT_HRS,TIMESHEET_WEEK_TOTAL_HRS FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertTimesheetSummary(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetSummary: insertTimesheetSummary: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateTimesheetSummary(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetSummary: updateTimesheetSummary: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteTimesheetSummary(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetSummary: deleteTimesheetSummary: ");

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
