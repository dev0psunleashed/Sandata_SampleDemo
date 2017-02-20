CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PrInputDedDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrInputDedDet {

	private static String TABLE_NAME = "PR_INPUT_DED_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PR_INPUT_DED_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO PR_INPUT_DED_DET(PR_INPUT_DED_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PR_INPUT_SK,DED_CODE,DED_AMT) VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet getPrInputDedDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputDedDet: getPrInputDedDet: ");

			try {

					String sql = String.format("SELECT PR_INPUT_DED_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PR_INPUT_SK,DED_CODE,DED_AMT FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrInputDedDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputDedDet: getPrInputDedDet: ");

			try {

					String selectPattern = "SELECT PR_INPUT_DED_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PR_INPUT_SK,DED_CODE,DED_AMT FROM PR_INPUT_DED_DET %s";

					String whereClause = "WHERE PR_INPUT_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrInputDedDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputDedDet: getPrInputDedDet: ");

			try {

					String sql = String.format("SELECT PR_INPUT_DED_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PR_INPUT_SK,DED_CODE,DED_AMT FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPrInputDedDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputDedDet: insertPrInputDedDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePrInputDedDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputDedDet: updatePrInputDedDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePrInputDedDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputDedDet: deletePrInputDedDet: ");

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
