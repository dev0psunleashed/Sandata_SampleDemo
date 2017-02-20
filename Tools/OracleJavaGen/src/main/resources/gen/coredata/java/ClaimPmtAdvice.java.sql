CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "ClaimPmtAdvice" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClaimPmtAdvice {

	private static String TABLE_NAME = "CLAIM_PMT_ADVICE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "CLAIM_PMT_ADVICE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 6;

	private static String INSERT_STATEMENT = "INSERT INTO CLAIM_PMT_ADVICE(CLAIM_PMT_ADVICE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BILLING_DET_SK,EDI_SUBMITTER_ID,EDI_RECEIVER_ID,CLAIM_PMT_ADVICE_CONTROL_NUM,PAYER_ID,PAYER_NAME,CHECK_NUM,CHECK_IND,CHECK_DATE,EFT_TXN_ID,PMT_AMT,PROVIDER_ID,PROVIDER_ID_QLFR,PROVIDER_NAME,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ID_OTHER,PROVIDER_ID_OTHER_QLFR,INV_NUM,PAYER_CLAIM_STATUS_CODE,CLAIM_CHARGE_TOTAL_AMT,CLAIM_PMT_TOTAL_AMT,CLAIM_PT_RESP_AMT,CRN,CLAIM_FACILITY_TYP_CODE,INV_SUBM_TYP_CODE,CLAIM_ENT_NAME_QLFR,CLAIM_ENT_FIRST_NAME,CLAIM_ENT_LAST_NAME,CLAIM_ENT_ID_TYP_QLFR,CLAIM_ENT_ID_NUM,CLAIM_START_DATE,CLAIM_START_DATE_QLFR,CLAIM_END_DATE,CLAIM_END_DATE_QLFR,CLAIM_AMT_QLFR,CLAIM_AMT,PCP_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getClaimPmtAdvice(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdvice: getClaimPmtAdvice: ");

			try {

					String sql = String.format("SELECT CLAIM_PMT_ADVICE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BILLING_DET_SK,EDI_SUBMITTER_ID,EDI_RECEIVER_ID,CLAIM_PMT_ADVICE_CONTROL_NUM,PAYER_ID,PAYER_NAME,CHECK_NUM,CHECK_IND,CHECK_DATE,EFT_TXN_ID,PMT_AMT,PROVIDER_ID,PROVIDER_ID_QLFR,PROVIDER_NAME,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ID_OTHER,PROVIDER_ID_OTHER_QLFR,INV_NUM,PAYER_CLAIM_STATUS_CODE,CLAIM_CHARGE_TOTAL_AMT,CLAIM_PMT_TOTAL_AMT,CLAIM_PT_RESP_AMT,CRN,CLAIM_FACILITY_TYP_CODE,INV_SUBM_TYP_CODE,CLAIM_ENT_NAME_QLFR,CLAIM_ENT_FIRST_NAME,CLAIM_ENT_LAST_NAME,CLAIM_ENT_ID_TYP_QLFR,CLAIM_ENT_ID_NUM,CLAIM_START_DATE,CLAIM_START_DATE_QLFR,CLAIM_END_DATE,CLAIM_END_DATE_QLFR,CLAIM_AMT_QLFR,CLAIM_AMT,PCP_CODE FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getClaimPmtAdvice(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdvice: getClaimPmtAdvice: ");

			try {

					String selectPattern = "SELECT CLAIM_PMT_ADVICE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BILLING_DET_SK,EDI_SUBMITTER_ID,EDI_RECEIVER_ID,CLAIM_PMT_ADVICE_CONTROL_NUM,PAYER_ID,PAYER_NAME,CHECK_NUM,CHECK_IND,CHECK_DATE,EFT_TXN_ID,PMT_AMT,PROVIDER_ID,PROVIDER_ID_QLFR,PROVIDER_NAME,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ID_OTHER,PROVIDER_ID_OTHER_QLFR,INV_NUM,PAYER_CLAIM_STATUS_CODE,CLAIM_CHARGE_TOTAL_AMT,CLAIM_PMT_TOTAL_AMT,CLAIM_PT_RESP_AMT,CRN,CLAIM_FACILITY_TYP_CODE,INV_SUBM_TYP_CODE,CLAIM_ENT_NAME_QLFR,CLAIM_ENT_FIRST_NAME,CLAIM_ENT_LAST_NAME,CLAIM_ENT_ID_TYP_QLFR,CLAIM_ENT_ID_NUM,CLAIM_START_DATE,CLAIM_START_DATE_QLFR,CLAIM_END_DATE,CLAIM_END_DATE_QLFR,CLAIM_AMT_QLFR,CLAIM_AMT,PCP_CODE FROM CLAIM_PMT_ADVICE %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getClaimPmtAdvice(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdvice: getClaimPmtAdvice: ");

			try {

					String sql = String.format("SELECT CLAIM_PMT_ADVICE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BILLING_DET_SK,EDI_SUBMITTER_ID,EDI_RECEIVER_ID,CLAIM_PMT_ADVICE_CONTROL_NUM,PAYER_ID,PAYER_NAME,CHECK_NUM,CHECK_IND,CHECK_DATE,EFT_TXN_ID,PMT_AMT,PROVIDER_ID,PROVIDER_ID_QLFR,PROVIDER_NAME,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ID_OTHER,PROVIDER_ID_OTHER_QLFR,INV_NUM,PAYER_CLAIM_STATUS_CODE,CLAIM_CHARGE_TOTAL_AMT,CLAIM_PMT_TOTAL_AMT,CLAIM_PT_RESP_AMT,CRN,CLAIM_FACILITY_TYP_CODE,INV_SUBM_TYP_CODE,CLAIM_ENT_NAME_QLFR,CLAIM_ENT_FIRST_NAME,CLAIM_ENT_LAST_NAME,CLAIM_ENT_ID_TYP_QLFR,CLAIM_ENT_ID_NUM,CLAIM_START_DATE,CLAIM_START_DATE_QLFR,CLAIM_END_DATE,CLAIM_END_DATE_QLFR,CLAIM_AMT_QLFR,CLAIM_AMT,PCP_CODE FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertClaimPmtAdvice(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdvice: insertClaimPmtAdvice: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateClaimPmtAdvice(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdvice: updateClaimPmtAdvice: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteClaimPmtAdvice(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ClaimPmtAdvice: deleteClaimPmtAdvice: ");

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
