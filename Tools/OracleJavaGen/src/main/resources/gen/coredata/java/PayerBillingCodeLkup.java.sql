CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PayerBillingCodeLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PayerBillingCodeLkup {

	private static String TABLE_NAME = "PAYER_BILLING_CODE_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PAYER_BILLING_CODE_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PAYER_BILLING_CODE_LKUP(PAYER_BILLING_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PAYER_ID,BILLING_CODE,BILLING_CODE_SHORT_DESC,BILLING_CODE_LONG_DESC,BILLING_CODE_EFF_DATE,BILLING_CODE_TERM_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPayerBillingCodeLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerBillingCodeLkup: getPayerBillingCodeLkup: ");

			try {

					String sql = String.format("SELECT PAYER_BILLING_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PAYER_ID,BILLING_CODE,BILLING_CODE_SHORT_DESC,BILLING_CODE_LONG_DESC,BILLING_CODE_EFF_DATE,BILLING_CODE_TERM_DATE FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPayerBillingCodeLkup(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerBillingCodeLkup: getPayerBillingCodeLkup: ");

			try {

					String selectPattern = "SELECT PAYER_BILLING_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PAYER_ID,BILLING_CODE,BILLING_CODE_SHORT_DESC,BILLING_CODE_LONG_DESC,BILLING_CODE_EFF_DATE,BILLING_CODE_TERM_DATE FROM PAYER_BILLING_CODE_LKUP %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPayerBillingCodeLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerBillingCodeLkup: getPayerBillingCodeLkup: ");

			try {

					String sql = String.format("SELECT PAYER_BILLING_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PAYER_ID,BILLING_CODE,BILLING_CODE_SHORT_DESC,BILLING_CODE_LONG_DESC,BILLING_CODE_EFF_DATE,BILLING_CODE_TERM_DATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPayerBillingCodeLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerBillingCodeLkup: insertPayerBillingCodeLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePayerBillingCodeLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerBillingCodeLkup: updatePayerBillingCodeLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePayerBillingCodeLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerBillingCodeLkup: deletePayerBillingCodeLkup: ");

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
