CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PayerRateMdfr" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PayerRateMdfr {

	private static String TABLE_NAME = "PAYER_RATE_MDFR";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PAYER_RATE_MDFR_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO PAYER_RATE_MDFR(PAYER_RATE_MDFR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PAYER_ID,CONTR_ID,PAYER_RATE_MDFR_CODE,PAYER_RATE_MDFR_DESC) VALUES (?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPayerRateMdfr(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerRateMdfr: getPayerRateMdfr: ");

			try {

					String sql = String.format("SELECT PAYER_RATE_MDFR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PAYER_ID,CONTR_ID,PAYER_RATE_MDFR_CODE,PAYER_RATE_MDFR_DESC FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPayerRateMdfr(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerRateMdfr: getPayerRateMdfr: ");

			try {

					String selectPattern = "SELECT PAYER_RATE_MDFR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PAYER_ID,CONTR_ID,PAYER_RATE_MDFR_CODE,PAYER_RATE_MDFR_DESC FROM PAYER_RATE_MDFR %s";

					String whereClause = "WHERE PAYER_ID=? AND CONTR_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPayerRateMdfr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerRateMdfr: getPayerRateMdfr: ");

			try {

					String sql = String.format("SELECT PAYER_RATE_MDFR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PAYER_ID,CONTR_ID,PAYER_RATE_MDFR_CODE,PAYER_RATE_MDFR_DESC FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPayerRateMdfr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerRateMdfr: insertPayerRateMdfr: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePayerRateMdfr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerRateMdfr: updatePayerRateMdfr: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePayerRateMdfr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PayerRateMdfr: deletePayerRateMdfr: ");

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
