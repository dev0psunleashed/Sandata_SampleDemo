CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AppTenantBeXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppTenantBeXwalk {

	private static String TABLE_NAME = "APP_TENANT_BE_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "APP_TENANT_BE_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO APP_TENANT_BE_XWALK(APP_TENANT_BE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_TENANT_SK,BE_ID) VALUES (?,?,?,?,?)";

	public static ResultSet getAppTenantBeXwalk(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantBeXwalk: getAppTenantBeXwalk: ");

			try {

					String sql = String.format("SELECT APP_TENANT_BE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_TENANT_SK,BE_ID FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAppTenantBeXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantBeXwalk: getAppTenantBeXwalk: ");

			try {

					String selectPattern = "SELECT APP_TENANT_BE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_TENANT_SK,BE_ID FROM APP_TENANT_BE_XWALK %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAppTenantBeXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantBeXwalk: getAppTenantBeXwalk: ");

			try {

					String sql = String.format("SELECT APP_TENANT_BE_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_TENANT_SK,BE_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAppTenantBeXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantBeXwalk: insertAppTenantBeXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAppTenantBeXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantBeXwalk: updateAppTenantBeXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAppTenantBeXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppTenantBeXwalk: deleteAppTenantBeXwalk: ");

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
