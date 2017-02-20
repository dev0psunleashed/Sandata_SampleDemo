CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Auth" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Auth {

	private static String TABLE_NAME = "AUTH";

	private static String SEQUENCE_KEY_COLUMN_NAME = "AUTH_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 7;

	private static int CURR_REC_IND_INDEX = 11;

	private static int TABLE_ID_COLUMN_INDEX = 3;

	private static int CHANGE_VERSION_ID_INDEX = 12;

	private static String INSERT_STATEMENT = "INSERT INTO AUTH(AUTH_SK,AUTH_PAR_SK,AUTH_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,ORD_SK,AUTH_ISSUED_DATE,AUTH_START_TMSTP,AUTH_END_TMSTP,AUTH_COMMENT,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_LMT,AUTH_LMT_TOTAL,AUTH_LMT_DAY_1,AUTH_LMT_START_TIME_DAY_1,AUTH_LMT_END_TIME_DAY_1,AUTH_LMT_DAY_2,AUTH_LMT_START_TIME_DAY_2,AUTH_LMT_END_TIME_DAY_2,AUTH_LMT_DAY_3,AUTH_LMT_START_TIME_DAY_3,AUTH_LMT_END_TIME_DAY_3,AUTH_LMT_DAY_4,AUTH_LMT_START_TIME_DAY_4,AUTH_LMT_END_TIME_DAY_4,AUTH_LMT_DAY_5,AUTH_LMT_START_TIME_DAY_5,AUTH_LMT_END_TIME_DAY_5,AUTH_LMT_DAY_6,AUTH_LMT_START_TIME_DAY_6,AUTH_LMT_END_TIME_DAY_6,AUTH_LMT_DAY_7,AUTH_LMT_START_TIME_DAY_7,AUTH_LMT_END_TIME_DAY_7,CONTR_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAuth(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Auth: getAuth: ");

			try {

					String selectPattern = "SELECT AUTH_SK,AUTH_PAR_SK,AUTH_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,ORD_SK,AUTH_ISSUED_DATE,AUTH_START_TMSTP,AUTH_END_TMSTP,AUTH_COMMENT,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_LMT,AUTH_LMT_TOTAL,AUTH_LMT_DAY_1,AUTH_LMT_START_TIME_DAY_1,AUTH_LMT_END_TIME_DAY_1,AUTH_LMT_DAY_2,AUTH_LMT_START_TIME_DAY_2,AUTH_LMT_END_TIME_DAY_2,AUTH_LMT_DAY_3,AUTH_LMT_START_TIME_DAY_3,AUTH_LMT_END_TIME_DAY_3,AUTH_LMT_DAY_4,AUTH_LMT_START_TIME_DAY_4,AUTH_LMT_END_TIME_DAY_4,AUTH_LMT_DAY_5,AUTH_LMT_START_TIME_DAY_5,AUTH_LMT_END_TIME_DAY_5,AUTH_LMT_DAY_6,AUTH_LMT_START_TIME_DAY_6,AUTH_LMT_END_TIME_DAY_6,AUTH_LMT_DAY_7,AUTH_LMT_START_TIME_DAY_7,AUTH_LMT_END_TIME_DAY_7,CONTR_ID FROM AUTH %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAuth(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Auth: getAuth: ");

			try {

					String sql = String.format("SELECT AUTH_SK,AUTH_PAR_SK,AUTH_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,ORD_SK,AUTH_ISSUED_DATE,AUTH_START_TMSTP,AUTH_END_TMSTP,AUTH_COMMENT,AUTH_LMT_TYP_NAME,AUTH_SVC_UNIT_NAME,AUTH_LMT,AUTH_LMT_TOTAL,AUTH_LMT_DAY_1,AUTH_LMT_START_TIME_DAY_1,AUTH_LMT_END_TIME_DAY_1,AUTH_LMT_DAY_2,AUTH_LMT_START_TIME_DAY_2,AUTH_LMT_END_TIME_DAY_2,AUTH_LMT_DAY_3,AUTH_LMT_START_TIME_DAY_3,AUTH_LMT_END_TIME_DAY_3,AUTH_LMT_DAY_4,AUTH_LMT_START_TIME_DAY_4,AUTH_LMT_END_TIME_DAY_4,AUTH_LMT_DAY_5,AUTH_LMT_START_TIME_DAY_5,AUTH_LMT_END_TIME_DAY_5,AUTH_LMT_DAY_6,AUTH_LMT_START_TIME_DAY_6,AUTH_LMT_END_TIME_DAY_6,AUTH_LMT_DAY_7,AUTH_LMT_START_TIME_DAY_7,AUTH_LMT_END_TIME_DAY_7,CONTR_ID FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAuth(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Auth: insertAuth: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAuth(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Auth: updateAuth: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAuth(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Auth: deleteAuth: ");

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
