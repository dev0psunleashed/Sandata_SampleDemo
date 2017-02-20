CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "ClaimPmtAdviceLine" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClaimPmtAdviceLine {

	private static String TABLE_NAME = "CLAIM_PMT_ADVICE_LINE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "CLAIM_PMT_ADVICE_LINE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 6;

	private static String INSERT_STATEMENT = "INSERT INTO CLAIM_PMT_ADVICE_LINE(CLAIM_PMT_ADVICE_LINE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CLAIM_PMT_ADVICE_SK,BE_ID,BILLING_CODE_TYP_QLFR,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,INV_DET_TOTAL_AMT,INV_DET_PROVIDER_PMT_AMT,INV_DET_PROVIDER_TOTAL_UNIT,CLAIM_LINE_PMT_DATE_QLFR,CLAIM_LINE_PMT_DATE,CLAIM_LINE_PMT_ID_QLFR,CLAIM_LINE_PMT_ID,CLAIM_LINE_AMT_QLFR,CLAIM_LINE_AMT,CLAIM_LINE_CRN,CLAIM_LINE_SVC_LINE_ITEM_NUM,PAYER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getClaimPmtAdviceLine(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdviceLine: getClaimPmtAdviceLine: ");

			try {

					String sql = String.format("SELECT CLAIM_PMT_ADVICE_LINE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CLAIM_PMT_ADVICE_SK,BE_ID,BILLING_CODE_TYP_QLFR,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,INV_DET_TOTAL_AMT,INV_DET_PROVIDER_PMT_AMT,INV_DET_PROVIDER_TOTAL_UNIT,CLAIM_LINE_PMT_DATE_QLFR,CLAIM_LINE_PMT_DATE,CLAIM_LINE_PMT_ID_QLFR,CLAIM_LINE_PMT_ID,CLAIM_LINE_AMT_QLFR,CLAIM_LINE_AMT,CLAIM_LINE_CRN,CLAIM_LINE_SVC_LINE_ITEM_NUM,PAYER_ID FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getClaimPmtAdviceLine(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdviceLine: getClaimPmtAdviceLine: ");

			try {

					String selectPattern = "SELECT CLAIM_PMT_ADVICE_LINE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CLAIM_PMT_ADVICE_SK,BE_ID,BILLING_CODE_TYP_QLFR,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,INV_DET_TOTAL_AMT,INV_DET_PROVIDER_PMT_AMT,INV_DET_PROVIDER_TOTAL_UNIT,CLAIM_LINE_PMT_DATE_QLFR,CLAIM_LINE_PMT_DATE,CLAIM_LINE_PMT_ID_QLFR,CLAIM_LINE_PMT_ID,CLAIM_LINE_AMT_QLFR,CLAIM_LINE_AMT,CLAIM_LINE_CRN,CLAIM_LINE_SVC_LINE_ITEM_NUM,PAYER_ID FROM CLAIM_PMT_ADVICE_LINE %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getClaimPmtAdviceLine(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdviceLine: getClaimPmtAdviceLine: ");

			try {

					String sql = String.format("SELECT CLAIM_PMT_ADVICE_LINE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CLAIM_PMT_ADVICE_SK,BE_ID,BILLING_CODE_TYP_QLFR,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,INV_DET_TOTAL_AMT,INV_DET_PROVIDER_PMT_AMT,INV_DET_PROVIDER_TOTAL_UNIT,CLAIM_LINE_PMT_DATE_QLFR,CLAIM_LINE_PMT_DATE,CLAIM_LINE_PMT_ID_QLFR,CLAIM_LINE_PMT_ID,CLAIM_LINE_AMT_QLFR,CLAIM_LINE_AMT,CLAIM_LINE_CRN,CLAIM_LINE_SVC_LINE_ITEM_NUM,PAYER_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertClaimPmtAdviceLine(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdviceLine: insertClaimPmtAdviceLine: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateClaimPmtAdviceLine(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdviceLine: updateClaimPmtAdviceLine: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteClaimPmtAdviceLine(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdviceLine: deleteClaimPmtAdviceLine: ");

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
