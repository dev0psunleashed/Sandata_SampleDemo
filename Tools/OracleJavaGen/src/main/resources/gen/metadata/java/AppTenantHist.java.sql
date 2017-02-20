CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AppTenantHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppTenantHist {

	private static String TABLE_NAME = "APP_TENANT_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "APP_TENANT_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO APP_TENANT_HIST(APP_TENANT_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,APP_TENANT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,TENANT_NAME,TENANT_TYP_NAME) VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet getAppTenantHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantHist: getAppTenantHist: ");

			try {

					String sql = String.format("SELECT APP_TENANT_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,APP_TENANT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,TENANT_NAME,TENANT_TYP_NAME FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAppTenantHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantHist: insertAppTenantHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAppTenantHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantHist: updateAppTenantHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAppTenantHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantHist: deleteAppTenantHist: ");

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
