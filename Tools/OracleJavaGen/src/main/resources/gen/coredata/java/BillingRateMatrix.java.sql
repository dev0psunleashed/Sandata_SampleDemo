CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BillingRateMatrix" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BillingRateMatrix {

	private static String TABLE_NAME = "BILLING_RATE_MATRIX";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BILLING_RATE_MATRIX_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BILLING_RATE_MATRIX(BILLING_RATE_MATRIX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,BILLING_RATE_MATRIX_EFF_DATE,BILLING_RATE_MATRIX_TERM_DATE,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,RATE_AMT,SVC_UNIT_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBillingRateMatrix(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingRateMatrix: getBillingRateMatrix: ");

			try {

					String selectPattern = "SELECT BILLING_RATE_MATRIX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,BILLING_RATE_MATRIX_EFF_DATE,BILLING_RATE_MATRIX_TERM_DATE,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,RATE_AMT,SVC_UNIT_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE FROM BILLING_RATE_MATRIX %s";

					String whereClause = "WHERE PAYER_ID=? AND CONTR_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBillingRateMatrix(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingRateMatrix: getBillingRateMatrix: ");

			try {

					String sql = String.format("SELECT BILLING_RATE_MATRIX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,BILLING_RATE_MATRIX_EFF_DATE,BILLING_RATE_MATRIX_TERM_DATE,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,RATE_AMT,SVC_UNIT_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBillingRateMatrix(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingRateMatrix: insertBillingRateMatrix: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBillingRateMatrix(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingRateMatrix: updateBillingRateMatrix: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBillingRateMatrix(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BillingRateMatrix: deleteBillingRateMatrix: ");

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
