CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeRateTypPrCodeXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeRateTypPrCodeXwalk {

	private static String TABLE_NAME = "BE_RATE_TYP_PR_CODE_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_RATE_TYP_PR_CODE_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_RATE_TYP_PR_CODE_XWALK(BE_RATE_TYP_PR_CODE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,RATE_TYP_NAME,RATE_QLFR_CODE,PR_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeRateTypPrCodeXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRateTypPrCodeXwalk: getBeRateTypPrCodeXwalk: ");

			try {

					String selectPattern = "SELECT BE_RATE_TYP_PR_CODE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,RATE_TYP_NAME,RATE_QLFR_CODE,PR_CODE FROM BE_RATE_TYP_PR_CODE_XWALK %s";

					String whereClause = "WHERE RATE_TYP_NAME=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeRateTypPrCodeXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRateTypPrCodeXwalk: getBeRateTypPrCodeXwalk: ");

			try {

					String sql = String.format("SELECT BE_RATE_TYP_PR_CODE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,RATE_TYP_NAME,RATE_QLFR_CODE,PR_CODE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeRateTypPrCodeXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRateTypPrCodeXwalk: insertBeRateTypPrCodeXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeRateTypPrCodeXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRateTypPrCodeXwalk: updateBeRateTypPrCodeXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeRateTypPrCodeXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRateTypPrCodeXwalk: deleteBeRateTypPrCodeXwalk: ");

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
