CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BillingDetHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BillingDetHist {

	private static String TABLE_NAME = "BILLING_DET_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BILLING_DET_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO BILLING_DET_HIST(BILLING_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,CLAIM_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS,BILLING_DET_SUBM_STATUS,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,PAYER_ID,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,RATE_AMT,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,USER_NAME,USER_GUID,MEMO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBillingDetHist(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDetHist: getBillingDetHist: ");

			try {

					String sql = String.format("SELECT BILLING_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,CLAIM_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS,BILLING_DET_SUBM_STATUS,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,PAYER_ID,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,RATE_AMT,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,USER_NAME,USER_GUID,MEMO FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBillingDetHist(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDetHist: getBillingDetHist: ");

			try {

					String selectPattern = "SELECT BILLING_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,CLAIM_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS,BILLING_DET_SUBM_STATUS,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,PAYER_ID,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,RATE_AMT,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,USER_NAME,USER_GUID,MEMO FROM BILLING_DET_HIST %s";

					String whereClause = "WHERE SVC_NAME=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBillingDetHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDetHist: getBillingDetHist: ");

			try {

					String sql = String.format("SELECT BILLING_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,CLAIM_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS,BILLING_DET_SUBM_STATUS,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,PAYER_ID,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,RATE_AMT,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,USER_NAME,USER_GUID,MEMO FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBillingDetHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDetHist: insertBillingDetHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBillingDetHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDetHist: updateBillingDetHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBillingDetHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDetHist: deleteBillingDetHist: ");

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
