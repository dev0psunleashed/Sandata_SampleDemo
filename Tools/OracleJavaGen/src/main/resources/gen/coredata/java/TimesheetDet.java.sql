CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "TimesheetDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TimesheetDet {

	private static String TABLE_NAME = "TIMESHEET_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "TIMESHEET_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 9;

	private static String INSERT_STATEMENT = "INSERT INTO TIMESHEET_DET(TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,VISIT_SK,BE_CALENDAR_LKUP_SK,SVC_ACTIVITY_BILLING_CODE_SK,PR_CODE,TIMESHEET_DATE,PR_RATE_AMT,PR_HRS,PR_AMT,RELEASE_TO_BILL_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getTimesheetDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetDet: getTimesheetDet: ");

			try {

					String sql = String.format("SELECT TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,VISIT_SK,BE_CALENDAR_LKUP_SK,SVC_ACTIVITY_BILLING_CODE_SK,PR_CODE,TIMESHEET_DATE,PR_RATE_AMT,PR_HRS,PR_AMT,RELEASE_TO_BILL_IND FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getTimesheetDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetDet: getTimesheetDet: ");

			try {

					String selectPattern = "SELECT TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,VISIT_SK,BE_CALENDAR_LKUP_SK,SVC_ACTIVITY_BILLING_CODE_SK,PR_CODE,TIMESHEET_DATE,PR_RATE_AMT,PR_HRS,PR_AMT,RELEASE_TO_BILL_IND FROM TIMESHEET_DET %s";

					String whereClause = "WHERE VISIT_SK=? AND PT_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getTimesheetDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetDet: getTimesheetDet: ");

			try {

					String sql = String.format("SELECT TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,VISIT_SK,BE_CALENDAR_LKUP_SK,SVC_ACTIVITY_BILLING_CODE_SK,PR_CODE,TIMESHEET_DATE,PR_RATE_AMT,PR_HRS,PR_AMT,RELEASE_TO_BILL_IND FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertTimesheetDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetDet: insertTimesheetDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateTimesheetDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetDet: updateTimesheetDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteTimesheetDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("TimesheetDet: deleteTimesheetDet: ");

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
