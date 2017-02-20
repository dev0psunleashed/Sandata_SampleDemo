CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BillingDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BillingDet {

	private static String TABLE_NAME = "BILLING_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BILLING_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 9;

	private static String INSERT_STATEMENT = "INSERT INTO BILLING_DET(BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,CONTR_ID,PAYER_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS_CODE,BILLING_DET_SUBM_STATUS_NAME,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,VISIT_SK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBillingDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDet: getBillingDet: ");

			try {

					String sql = String.format("SELECT BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,CONTR_ID,PAYER_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS_CODE,BILLING_DET_SUBM_STATUS_NAME,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,VISIT_SK FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBillingDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDet: getBillingDet: ");

			try {

					String selectPattern = "SELECT BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,CONTR_ID,PAYER_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS_CODE,BILLING_DET_SUBM_STATUS_NAME,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,VISIT_SK FROM BILLING_DET %s";

					String whereClause = "WHERE PT_ID=? AND PAYER_ID=? AND CONTR_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBillingDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDet: getBillingDet: ");

			try {

					String sql = String.format("SELECT BILLING_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,CONTR_ID,PAYER_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,BILLING_DET_DATE,BILLING_DET_STATUS_CODE,BILLING_DET_SUBM_STATUS_NAME,SVC_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE,RENDER_HCP_NPI,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BILLING_DET_RATE_AMT,BILLING_DET_TOTAL_AMT,BILLING_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,VISIT_SK FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBillingDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDet: insertBillingDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBillingDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDet: updateBillingDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBillingDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingDet: deleteBillingDet: ");

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
