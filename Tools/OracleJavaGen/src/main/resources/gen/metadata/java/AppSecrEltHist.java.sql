CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AppSecrEltHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppSecrEltHist {

	private static String TABLE_NAME = "APP_SECR_ELT_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "APP_SECR_ELT_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO APP_SECR_ELT_HIST(APP_SECR_ELT_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,APP_SECR_ELT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_TENANT_SK,APP_FUNC_ELT_SK,APP_FUN_SK,APP_MOD_SK,SECR_ELT_NAME,INCL_INHC_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAppSecrEltHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppSecrEltHist: getAppSecrEltHist: ");

			try {

					String sql = String.format("SELECT APP_SECR_ELT_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,APP_SECR_ELT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_TENANT_SK,APP_FUNC_ELT_SK,APP_FUN_SK,APP_MOD_SK,SECR_ELT_NAME,INCL_INHC_IND FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAppSecrEltHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppSecrEltHist: insertAppSecrEltHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAppSecrEltHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppSecrEltHist: updateAppSecrEltHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAppSecrEltHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppSecrEltHist: deleteAppSecrEltHist: ");

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
