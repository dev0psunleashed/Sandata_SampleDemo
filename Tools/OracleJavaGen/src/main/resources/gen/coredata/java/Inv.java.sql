CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Inv" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Inv {

	private static String TABLE_NAME = "INV";

	private static String SEQUENCE_KEY_COLUMN_NAME = "INV_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO INV(INV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOC_ID,BE_LOB_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,INV_NUM,INV_STATUS_CODE,INV_SUBM_TYP_CODE,INV_START_DATE,INV_END_DATE,INV_DATE,INV_TYP_QLFR,INV_FMT_TYP_NAME,ICD_DX_CODE_REVISION_QLFR,ICD_DX_CODE_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_3,ICD_DX_CODE_4,ICD_DX_CODE_5,ICD_DX_CODE_6,ICD_DX_CODE_7,ICD_DX_CODE_8,ICD_DX_CODE_9,ICD_DX_CODE_10,ICD_DX_CODE_11,ICD_DX_CODE_12,RENDER_HCP_NPI,REFING_PROFNL_NPI,INV_TOTAL_AMT,INV_TOTAL_UNIT,INV_BILL_TYP_CODE,INV_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CRN,CLAIM_FILING_IND_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getInv(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Inv: getInv: ");

			try {

					String selectPattern = "SELECT INV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOC_ID,BE_LOB_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,INV_NUM,INV_STATUS_CODE,INV_SUBM_TYP_CODE,INV_START_DATE,INV_END_DATE,INV_DATE,INV_TYP_QLFR,INV_FMT_TYP_NAME,ICD_DX_CODE_REVISION_QLFR,ICD_DX_CODE_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_3,ICD_DX_CODE_4,ICD_DX_CODE_5,ICD_DX_CODE_6,ICD_DX_CODE_7,ICD_DX_CODE_8,ICD_DX_CODE_9,ICD_DX_CODE_10,ICD_DX_CODE_11,ICD_DX_CODE_12,RENDER_HCP_NPI,REFING_PROFNL_NPI,INV_TOTAL_AMT,INV_TOTAL_UNIT,INV_BILL_TYP_CODE,INV_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CRN,CLAIM_FILING_IND_CODE FROM INV %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getInv(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Inv: getInv: ");

			try {

					String sql = String.format("SELECT INV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOC_ID,BE_LOB_ID,PAYER_ID,CONTR_ID,AUTH_ID,PT_ID,PT_INS_ID_NUM,INV_NUM,INV_STATUS_CODE,INV_SUBM_TYP_CODE,INV_START_DATE,INV_END_DATE,INV_DATE,INV_TYP_QLFR,INV_FMT_TYP_NAME,ICD_DX_CODE_REVISION_QLFR,ICD_DX_CODE_PRMY,ICD_DX_CODE_2,ICD_DX_CODE_3,ICD_DX_CODE_4,ICD_DX_CODE_5,ICD_DX_CODE_6,ICD_DX_CODE_7,ICD_DX_CODE_8,ICD_DX_CODE_9,ICD_DX_CODE_10,ICD_DX_CODE_11,ICD_DX_CODE_12,RENDER_HCP_NPI,REFING_PROFNL_NPI,INV_TOTAL_AMT,INV_TOTAL_UNIT,INV_BILL_TYP_CODE,INV_MANUAL_OVERRIDE_IND,USER_NAME,USER_GUID,MEMO,CRN,CLAIM_FILING_IND_CODE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertInv(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Inv: insertInv: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateInv(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Inv: updateInv: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteInv(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Inv: deleteInv: ");

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
