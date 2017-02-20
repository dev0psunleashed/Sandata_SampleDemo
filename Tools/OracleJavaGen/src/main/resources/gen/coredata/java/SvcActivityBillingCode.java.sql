CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SvcActivityBillingCode" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SvcActivityBillingCode {

	private static String TABLE_NAME = "SVC_ACTIVITY_BILLING_CODE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SVC_ACTIVITY_BILLING_CODE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SVC_ACTIVITY_BILLING_CODE(SVC_ACTIVITY_BILLING_CODE_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME,ACTIVITY_SK,BILLING_CODE,BILLING_RATE,BILLING_RATE_UNIT) VALUES (?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getSvcActivityBillingCode(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcActivityBillingCode: getSvcActivityBillingCode: ");

			try {

					String sql = String.format("SELECT SVC_ACTIVITY_BILLING_CODE_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME,ACTIVITY_SK,BILLING_CODE,BILLING_RATE,BILLING_RATE_UNIT FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSvcActivityBillingCode(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcActivityBillingCode: getSvcActivityBillingCode: ");

			try {

					String selectPattern = "SELECT SVC_ACTIVITY_BILLING_CODE_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME,ACTIVITY_SK,BILLING_CODE,BILLING_RATE,BILLING_RATE_UNIT FROM SVC_ACTIVITY_BILLING_CODE %s";

					String whereClause = "WHERE ACTIVITY_SK=? AND SVC_NAME=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSvcActivityBillingCode(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcActivityBillingCode: getSvcActivityBillingCode: ");

			try {

					String sql = String.format("SELECT SVC_ACTIVITY_BILLING_CODE_SK,REC_UPDATE_TMSTP,REC_CREATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME,ACTIVITY_SK,BILLING_CODE,BILLING_RATE,BILLING_RATE_UNIT FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSvcActivityBillingCode(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcActivityBillingCode: insertSvcActivityBillingCode: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSvcActivityBillingCode(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcActivityBillingCode: updateSvcActivityBillingCode: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSvcActivityBillingCode(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SvcActivityBillingCode: deleteSvcActivityBillingCode: ");

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
