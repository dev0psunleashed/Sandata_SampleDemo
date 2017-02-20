CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PrInputEarnDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrInputEarnDet {

	private static String TABLE_NAME = "PR_INPUT_EARN_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PR_INPUT_EARN_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO PR_INPUT_EARN_DET(PR_INPUT_EARN_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,EARN_CODE,EARN_AMT,DATE_OF_SVC,RATE_TYP_NAME,EARN_RATE_AMT,EARN_HRS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPrInputEarnDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputEarnDet: getPrInputEarnDet: ");

			try {

					String sql = String.format("SELECT PR_INPUT_EARN_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,EARN_CODE,EARN_AMT,DATE_OF_SVC,RATE_TYP_NAME,EARN_RATE_AMT,EARN_HRS FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrInputEarnDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputEarnDet: getPrInputEarnDet: ");

			try {

					String selectPattern = "SELECT PR_INPUT_EARN_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,EARN_CODE,EARN_AMT,DATE_OF_SVC,RATE_TYP_NAME,EARN_RATE_AMT,EARN_HRS FROM PR_INPUT_EARN_DET %s";

					String whereClause = "WHERE PR_INPUT_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrInputEarnDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputEarnDet: getPrInputEarnDet: ");

			try {

					String sql = String.format("SELECT PR_INPUT_EARN_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,EARN_CODE,EARN_AMT,DATE_OF_SVC,RATE_TYP_NAME,EARN_RATE_AMT,EARN_HRS FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPrInputEarnDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputEarnDet: insertPrInputEarnDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePrInputEarnDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputEarnDet: updatePrInputEarnDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePrInputEarnDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputEarnDet: deletePrInputEarnDet: ");

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
