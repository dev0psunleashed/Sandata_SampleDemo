CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AuthSvc" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthSvc {

	private static String TABLE_NAME = "AUTH_SVC";

	private static String SEQUENCE_KEY_COLUMN_NAME = "AUTH_SVC_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO AUTH_SVC(AUTH_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,AUTH_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,AUTH_SVC_RATE_AMT,AUTH_SVC_START_DATE,AUTH_SVC_END_DATE,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_SVC_LMT,AUTH_SVC_LMT_DAY_1,AUTH_SVC_LMT_START_TIME_DAY_1,AUTH_SVC_LMT_END_TIME_DAY_1,AUTH_SVC_LMT_DAY_2,AUTH_SVC_LMT_START_TIME_DAY_2,AUTH_SVC_LMT_END_TIME_DAY_2,AUTH_SVC_LMT_DAY_3,AUTH_SVC_LMT_START_TIME_DAY_3,AUTH_SVC_LMT_END_TIME_DAY_3,AUTH_SVC_LMT_DAY_4,AUTH_SVC_LMT_START_TIME_DAY_4,AUTH_SVC_LMT_END_TIME_DAY_4,AUTH_SVC_LMT_DAY_5,AUTH_SVC_LMT_START_TIME_DAY_5,AUTH_SVC_LMT_END_TIME_DAY_5,AUTH_SVC_LMT_DAY_6,AUTH_SVC_LMT_START_TIME_DAY_6,AUTH_SVC_LMT_END_TIME_DAY_6,AUTH_SVC_LMT_DAY_7,AUTH_SVC_LMT_START_TIME_DAY_7,AUTH_SVC_LMT_END_TIME_DAY_7,PAYER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAuthSvc(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("AuthSvc: getAuthSvc: ");

			try {

					String sql = String.format("SELECT AUTH_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,AUTH_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,AUTH_SVC_RATE_AMT,AUTH_SVC_START_DATE,AUTH_SVC_END_DATE,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_SVC_LMT,AUTH_SVC_LMT_DAY_1,AUTH_SVC_LMT_START_TIME_DAY_1,AUTH_SVC_LMT_END_TIME_DAY_1,AUTH_SVC_LMT_DAY_2,AUTH_SVC_LMT_START_TIME_DAY_2,AUTH_SVC_LMT_END_TIME_DAY_2,AUTH_SVC_LMT_DAY_3,AUTH_SVC_LMT_START_TIME_DAY_3,AUTH_SVC_LMT_END_TIME_DAY_3,AUTH_SVC_LMT_DAY_4,AUTH_SVC_LMT_START_TIME_DAY_4,AUTH_SVC_LMT_END_TIME_DAY_4,AUTH_SVC_LMT_DAY_5,AUTH_SVC_LMT_START_TIME_DAY_5,AUTH_SVC_LMT_END_TIME_DAY_5,AUTH_SVC_LMT_DAY_6,AUTH_SVC_LMT_START_TIME_DAY_6,AUTH_SVC_LMT_END_TIME_DAY_6,AUTH_SVC_LMT_DAY_7,AUTH_SVC_LMT_START_TIME_DAY_7,AUTH_SVC_LMT_END_TIME_DAY_7,PAYER_ID FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAuthSvc(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AuthSvc: getAuthSvc: ");

			try {

					String selectPattern = "SELECT AUTH_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,AUTH_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,AUTH_SVC_RATE_AMT,AUTH_SVC_START_DATE,AUTH_SVC_END_DATE,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_SVC_LMT,AUTH_SVC_LMT_DAY_1,AUTH_SVC_LMT_START_TIME_DAY_1,AUTH_SVC_LMT_END_TIME_DAY_1,AUTH_SVC_LMT_DAY_2,AUTH_SVC_LMT_START_TIME_DAY_2,AUTH_SVC_LMT_END_TIME_DAY_2,AUTH_SVC_LMT_DAY_3,AUTH_SVC_LMT_START_TIME_DAY_3,AUTH_SVC_LMT_END_TIME_DAY_3,AUTH_SVC_LMT_DAY_4,AUTH_SVC_LMT_START_TIME_DAY_4,AUTH_SVC_LMT_END_TIME_DAY_4,AUTH_SVC_LMT_DAY_5,AUTH_SVC_LMT_START_TIME_DAY_5,AUTH_SVC_LMT_END_TIME_DAY_5,AUTH_SVC_LMT_DAY_6,AUTH_SVC_LMT_START_TIME_DAY_6,AUTH_SVC_LMT_END_TIME_DAY_6,AUTH_SVC_LMT_DAY_7,AUTH_SVC_LMT_START_TIME_DAY_7,AUTH_SVC_LMT_END_TIME_DAY_7,PAYER_ID FROM AUTH_SVC %s";

					String whereClause = "WHERE AUTH_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAuthSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AuthSvc: getAuthSvc: ");

			try {

					String sql = String.format("SELECT AUTH_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,AUTH_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,AUTH_SVC_RATE_AMT,AUTH_SVC_START_DATE,AUTH_SVC_END_DATE,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_SVC_LMT,AUTH_SVC_LMT_DAY_1,AUTH_SVC_LMT_START_TIME_DAY_1,AUTH_SVC_LMT_END_TIME_DAY_1,AUTH_SVC_LMT_DAY_2,AUTH_SVC_LMT_START_TIME_DAY_2,AUTH_SVC_LMT_END_TIME_DAY_2,AUTH_SVC_LMT_DAY_3,AUTH_SVC_LMT_START_TIME_DAY_3,AUTH_SVC_LMT_END_TIME_DAY_3,AUTH_SVC_LMT_DAY_4,AUTH_SVC_LMT_START_TIME_DAY_4,AUTH_SVC_LMT_END_TIME_DAY_4,AUTH_SVC_LMT_DAY_5,AUTH_SVC_LMT_START_TIME_DAY_5,AUTH_SVC_LMT_END_TIME_DAY_5,AUTH_SVC_LMT_DAY_6,AUTH_SVC_LMT_START_TIME_DAY_6,AUTH_SVC_LMT_END_TIME_DAY_6,AUTH_SVC_LMT_DAY_7,AUTH_SVC_LMT_START_TIME_DAY_7,AUTH_SVC_LMT_END_TIME_DAY_7,PAYER_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAuthSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AuthSvc: insertAuthSvc: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAuthSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AuthSvc: updateAuthSvc: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAuthSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AuthSvc: deleteAuthSvc: ");

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
