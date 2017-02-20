CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PrRateMatrix" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrRateMatrix {

	private static String TABLE_NAME = "PR_RATE_MATRIX";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PR_RATE_MATRIX_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PR_RATE_MATRIX(PR_RATE_MATRIX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_LOC_ID,BE_ID,PAYER_ID,CONTR_ID,PR_RATE_MATRIX_EFF_DATE,PR_RATE_MATRIX_TERM_DATE,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,RATE_SUB_TYP_NAME,PR_CODE,RATE_AMT,SVC_UNIT_NAME,PR_RATE_MATRIX_NOTE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPrRateMatrix(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrRateMatrix: getPrRateMatrix: ");

			try {

					String selectPattern = "SELECT PR_RATE_MATRIX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_LOC_ID,BE_ID,PAYER_ID,CONTR_ID,PR_RATE_MATRIX_EFF_DATE,PR_RATE_MATRIX_TERM_DATE,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,RATE_SUB_TYP_NAME,PR_CODE,RATE_AMT,SVC_UNIT_NAME,PR_RATE_MATRIX_NOTE FROM PR_RATE_MATRIX %s";

					String whereClause = "WHERE PAYER_ID=? AND CONTR_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrRateMatrix(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrRateMatrix: getPrRateMatrix: ");

			try {

					String sql = String.format("SELECT PR_RATE_MATRIX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_LOC_ID,BE_ID,PAYER_ID,CONTR_ID,PR_RATE_MATRIX_EFF_DATE,PR_RATE_MATRIX_TERM_DATE,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,RATE_SUB_TYP_NAME,PR_CODE,RATE_AMT,SVC_UNIT_NAME,PR_RATE_MATRIX_NOTE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPrRateMatrix(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrRateMatrix: insertPrRateMatrix: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePrRateMatrix(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrRateMatrix: updatePrRateMatrix: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePrRateMatrix(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrRateMatrix: deletePrRateMatrix: ");

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
