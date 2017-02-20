CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffPrDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffPrDet {

	private static String TABLE_NAME = "STAFF_PR_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_PR_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_PR_DET(STAFF_PR_DET_SK,STAFF_PR_DET_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_EMPLOYEE_PR_TYP_NAME,STAFF_PAY_FREQ,STAFF_PR_FEDERAL_EXEMPTIONS,STAFF_FEDERAL_MRTL_STATUS_NAME,STAFF_PR_STATE_EXEMPTIONS,STAFF_STATE_MRTL_STATUS_NAME,STAFF_STATE_OF_RESIDENCE,STAFF_EIN,STAFF_STATE_TAX_NUM,CITY_TAX_1,CITY_TAX_2,STATE_TAX,STAFF_HOURLY_RATE_IND,STAFF_DAILY_RATE_IND,STAFF_OVERRIDE_RATE,HOLIDAY_RATE_BYPASS_IND,STAFF_PR_BANK_ROUTING,STAFF_PR_NOTES) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffPrDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPrDet: getStaffPrDet: ");

			try {

					String selectPattern = "SELECT STAFF_PR_DET_SK,STAFF_PR_DET_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_EMPLOYEE_PR_TYP_NAME,STAFF_PAY_FREQ,STAFF_PR_FEDERAL_EXEMPTIONS,STAFF_FEDERAL_MRTL_STATUS_NAME,STAFF_PR_STATE_EXEMPTIONS,STAFF_STATE_MRTL_STATUS_NAME,STAFF_STATE_OF_RESIDENCE,STAFF_EIN,STAFF_STATE_TAX_NUM,CITY_TAX_1,CITY_TAX_2,STATE_TAX,STAFF_HOURLY_RATE_IND,STAFF_DAILY_RATE_IND,STAFF_OVERRIDE_RATE,HOLIDAY_RATE_BYPASS_IND,STAFF_PR_BANK_ROUTING,STAFF_PR_NOTES FROM STAFF_PR_DET %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffPrDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPrDet: getStaffPrDet: ");

			try {

					String sql = String.format("SELECT STAFF_PR_DET_SK,STAFF_PR_DET_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_EMPLOYEE_PR_TYP_NAME,STAFF_PAY_FREQ,STAFF_PR_FEDERAL_EXEMPTIONS,STAFF_FEDERAL_MRTL_STATUS_NAME,STAFF_PR_STATE_EXEMPTIONS,STAFF_STATE_MRTL_STATUS_NAME,STAFF_STATE_OF_RESIDENCE,STAFF_EIN,STAFF_STATE_TAX_NUM,CITY_TAX_1,CITY_TAX_2,STATE_TAX,STAFF_HOURLY_RATE_IND,STAFF_DAILY_RATE_IND,STAFF_OVERRIDE_RATE,HOLIDAY_RATE_BYPASS_IND,STAFF_PR_BANK_ROUTING,STAFF_PR_NOTES FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffPrDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPrDet: insertStaffPrDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffPrDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPrDet: updateStaffPrDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffPrDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPrDet: deleteStaffPrDet: ");

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
