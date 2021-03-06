CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Billing" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Billing {

	private static String TABLE_NAME = "BILLING";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BILLING_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO BILLING(BILLING_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,BILLING_STATUS_NAME,BILLING_SUBM_TYP_NAME,BILLING_START_DATE,BILLING_END_DATE,BILLING_DATE,BILLING_TYP_QLFR,BILLING_FMT_TYP_NAME,ICD_DX_CODE_PRMY,ICD_DX_CODE_REVISION_QLFR_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_REVISION_QLFR_2,ICD_DX_CODE_3,ICD_DX_CODE_REVISION_QLFR_3,ICD_DX_CODE_4,ICD_DX_CODE_REVISION_QLFR_4,ICD_DX_CODE_5,ICD_DX_CODE_REVISION_QLFR_5,ICD_DX_CODE_6,ICD_DX_CODE_REVISION_QLFR_6,ICD_DX_CODE_7,ICD_DX_CODE_REVISION_QLFR_7,ICD_DX_CODE_8,ICD_DX_CODE_REVISION_QLFR_8,ICD_DX_CODE_9,ICD_DX_CODE_REVISION_QLFR_9,ICD_DX_CODE_10,ICD_DX_CODE_REVISION_QLFR_10,ICD_DX_CODE_11,ICD_DX_CODE_REVISION_QLFR_11,ICD_DX_CODE_12,ICD_DX_CODE_REVISION_QLFR_12,RENDER_HCP_NPI,REFING_HCP_NPI,BILLING_TOTAL_AMT,BILLING_TOTAL_UNIT,BILLING_BILL_TYP_CODE,BILLING_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CLAIM_FILING_IND_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBilling(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("Billing: getBilling: ");

			try {

					String sql = String.format("SELECT BILLING_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,BILLING_STATUS_NAME,BILLING_SUBM_TYP_NAME,BILLING_START_DATE,BILLING_END_DATE,BILLING_DATE,BILLING_TYP_QLFR,BILLING_FMT_TYP_NAME,ICD_DX_CODE_PRMY,ICD_DX_CODE_REVISION_QLFR_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_REVISION_QLFR_2,ICD_DX_CODE_3,ICD_DX_CODE_REVISION_QLFR_3,ICD_DX_CODE_4,ICD_DX_CODE_REVISION_QLFR_4,ICD_DX_CODE_5,ICD_DX_CODE_REVISION_QLFR_5,ICD_DX_CODE_6,ICD_DX_CODE_REVISION_QLFR_6,ICD_DX_CODE_7,ICD_DX_CODE_REVISION_QLFR_7,ICD_DX_CODE_8,ICD_DX_CODE_REVISION_QLFR_8,ICD_DX_CODE_9,ICD_DX_CODE_REVISION_QLFR_9,ICD_DX_CODE_10,ICD_DX_CODE_REVISION_QLFR_10,ICD_DX_CODE_11,ICD_DX_CODE_REVISION_QLFR_11,ICD_DX_CODE_12,ICD_DX_CODE_REVISION_QLFR_12,RENDER_HCP_NPI,REFING_HCP_NPI,BILLING_TOTAL_AMT,BILLING_TOTAL_UNIT,BILLING_BILL_TYP_CODE,BILLING_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CLAIM_FILING_IND_CODE FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBilling(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Billing: getBilling: ");

			try {

					String selectPattern = "SELECT BILLING_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,BILLING_STATUS_NAME,BILLING_SUBM_TYP_NAME,BILLING_START_DATE,BILLING_END_DATE,BILLING_DATE,BILLING_TYP_QLFR,BILLING_FMT_TYP_NAME,ICD_DX_CODE_PRMY,ICD_DX_CODE_REVISION_QLFR_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_REVISION_QLFR_2,ICD_DX_CODE_3,ICD_DX_CODE_REVISION_QLFR_3,ICD_DX_CODE_4,ICD_DX_CODE_REVISION_QLFR_4,ICD_DX_CODE_5,ICD_DX_CODE_REVISION_QLFR_5,ICD_DX_CODE_6,ICD_DX_CODE_REVISION_QLFR_6,ICD_DX_CODE_7,ICD_DX_CODE_REVISION_QLFR_7,ICD_DX_CODE_8,ICD_DX_CODE_REVISION_QLFR_8,ICD_DX_CODE_9,ICD_DX_CODE_REVISION_QLFR_9,ICD_DX_CODE_10,ICD_DX_CODE_REVISION_QLFR_10,ICD_DX_CODE_11,ICD_DX_CODE_REVISION_QLFR_11,ICD_DX_CODE_12,ICD_DX_CODE_REVISION_QLFR_12,RENDER_HCP_NPI,REFING_HCP_NPI,BILLING_TOTAL_AMT,BILLING_TOTAL_UNIT,BILLING_BILL_TYP_CODE,BILLING_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CLAIM_FILING_IND_CODE FROM BILLING %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBilling(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Billing: getBilling: ");

			try {

					String sql = String.format("SELECT BILLING_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,BILLING_STATUS_NAME,BILLING_SUBM_TYP_NAME,BILLING_START_DATE,BILLING_END_DATE,BILLING_DATE,BILLING_TYP_QLFR,BILLING_FMT_TYP_NAME,ICD_DX_CODE_PRMY,ICD_DX_CODE_REVISION_QLFR_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_REVISION_QLFR_2,ICD_DX_CODE_3,ICD_DX_CODE_REVISION_QLFR_3,ICD_DX_CODE_4,ICD_DX_CODE_REVISION_QLFR_4,ICD_DX_CODE_5,ICD_DX_CODE_REVISION_QLFR_5,ICD_DX_CODE_6,ICD_DX_CODE_REVISION_QLFR_6,ICD_DX_CODE_7,ICD_DX_CODE_REVISION_QLFR_7,ICD_DX_CODE_8,ICD_DX_CODE_REVISION_QLFR_8,ICD_DX_CODE_9,ICD_DX_CODE_REVISION_QLFR_9,ICD_DX_CODE_10,ICD_DX_CODE_REVISION_QLFR_10,ICD_DX_CODE_11,ICD_DX_CODE_REVISION_QLFR_11,ICD_DX_CODE_12,ICD_DX_CODE_REVISION_QLFR_12,RENDER_HCP_NPI,REFING_HCP_NPI,BILLING_TOTAL_AMT,BILLING_TOTAL_UNIT,BILLING_BILL_TYP_CODE,BILLING_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CLAIM_FILING_IND_CODE FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBilling(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Billing: insertBilling: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBilling(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Billing: updateBilling: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBilling(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Billing: deleteBilling: ");

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
