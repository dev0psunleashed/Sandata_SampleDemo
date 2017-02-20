CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Pmt" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Pmt {

	private static String TABLE_NAME = "PMT";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PMT_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PMT(PMT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PAYER_SK,CLAIM_PMT_ADVICE_SK,PMT_835_HIST_SK,BILLING_DET_SK,CLAIM_SK,BE_ID,PT_ID,BILLING_NUM,SANDATA_CLAIM_ID,PMT_SRC,PMT_AMT,FULL_PMT_IND,PARTIAL_PMT_IND,MTHD_OF_PMT,CHECK_NUM,BANK_ROUTING_NUM,BANK_ACCOUNT_NUM,BANK_ACCOUNT_TYP,CHECK_ISSUE_DATE,BOUNCED_CHECK_IND,CHECK_STATUS,CHECK_REASON_FOR_HOLD,CREDIT_CARD_TYP,CREDIT_CARD_NUM,CREDIT_CARD_HOLDER_NAME,CREDIT_CARD_HOLDER_ADDR1,CREDIT_CARD_HOLDER_ADDR2,CREDIT_CARD_HOLDER_APT_NUM,CREDIT_CARD_HOLDER_CITY,CREDIT_CARD_HOLDER_STATE,CREDIT_CARD_HOLDER_PSTL_CODE,CREDIT_CARD_EXPR_DATE,CREDIT_CARD_CVC_CODE,CREDIT_CARD_AUTH_CODE,CREDIT_CARD_AUTH_DATE,POSTED_DATE,PMT_PROCESSING_MTHD_CODE,BATCH_ID,PMT_POSTED_IND,PMT_POSTED_DATE,PMT_POST_STATUS_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPmt(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt: getPmt: ");

			try {

					String selectPattern = "SELECT PMT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PAYER_SK,CLAIM_PMT_ADVICE_SK,PMT_835_HIST_SK,BILLING_DET_SK,CLAIM_SK,BE_ID,PT_ID,BILLING_NUM,SANDATA_CLAIM_ID,PMT_SRC,PMT_AMT,FULL_PMT_IND,PARTIAL_PMT_IND,MTHD_OF_PMT,CHECK_NUM,BANK_ROUTING_NUM,BANK_ACCOUNT_NUM,BANK_ACCOUNT_TYP,CHECK_ISSUE_DATE,BOUNCED_CHECK_IND,CHECK_STATUS,CHECK_REASON_FOR_HOLD,CREDIT_CARD_TYP,CREDIT_CARD_NUM,CREDIT_CARD_HOLDER_NAME,CREDIT_CARD_HOLDER_ADDR1,CREDIT_CARD_HOLDER_ADDR2,CREDIT_CARD_HOLDER_APT_NUM,CREDIT_CARD_HOLDER_CITY,CREDIT_CARD_HOLDER_STATE,CREDIT_CARD_HOLDER_PSTL_CODE,CREDIT_CARD_EXPR_DATE,CREDIT_CARD_CVC_CODE,CREDIT_CARD_AUTH_CODE,CREDIT_CARD_AUTH_DATE,POSTED_DATE,PMT_PROCESSING_MTHD_CODE,BATCH_ID,PMT_POSTED_IND,PMT_POSTED_DATE,PMT_POST_STATUS_CODE FROM PMT %s";

					String whereClause = "WHERE PAYER_SK=? AND INV_DET_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPmt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt: getPmt: ");

			try {

					String sql = String.format("SELECT PMT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PAYER_SK,CLAIM_PMT_ADVICE_SK,PMT_835_HIST_SK,BILLING_DET_SK,CLAIM_SK,BE_ID,PT_ID,BILLING_NUM,SANDATA_CLAIM_ID,PMT_SRC,PMT_AMT,FULL_PMT_IND,PARTIAL_PMT_IND,MTHD_OF_PMT,CHECK_NUM,BANK_ROUTING_NUM,BANK_ACCOUNT_NUM,BANK_ACCOUNT_TYP,CHECK_ISSUE_DATE,BOUNCED_CHECK_IND,CHECK_STATUS,CHECK_REASON_FOR_HOLD,CREDIT_CARD_TYP,CREDIT_CARD_NUM,CREDIT_CARD_HOLDER_NAME,CREDIT_CARD_HOLDER_ADDR1,CREDIT_CARD_HOLDER_ADDR2,CREDIT_CARD_HOLDER_APT_NUM,CREDIT_CARD_HOLDER_CITY,CREDIT_CARD_HOLDER_STATE,CREDIT_CARD_HOLDER_PSTL_CODE,CREDIT_CARD_EXPR_DATE,CREDIT_CARD_CVC_CODE,CREDIT_CARD_AUTH_CODE,CREDIT_CARD_AUTH_DATE,POSTED_DATE,PMT_PROCESSING_MTHD_CODE,BATCH_ID,PMT_POSTED_IND,PMT_POSTED_DATE,PMT_POST_STATUS_CODE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPmt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt: insertPmt: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePmt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt: updatePmt: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePmt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt: deletePmt: ");

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
