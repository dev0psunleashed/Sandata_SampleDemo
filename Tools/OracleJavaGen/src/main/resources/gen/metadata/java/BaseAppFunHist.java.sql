CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BaseAppFunHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BaseAppFunHist {

	private static String TABLE_NAME = "BASE_APP_FUN_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BASE_APP_FUN_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO BASE_APP_FUN_HIST(BASE_APP_FUN_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,BASE_APP_FUN_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_SECR_ELT_SK,APP_TENANT_SK,APP_ROLE_SK,BASE_APP_PRIV_SK) VALUES (?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBaseAppFunHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppFunHist: getBaseAppFunHist: ");

			try {

					String sql = String.format("SELECT BASE_APP_FUN_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,BASE_APP_FUN_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_SECR_ELT_SK,APP_TENANT_SK,APP_ROLE_SK,BASE_APP_PRIV_SK FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBaseAppFunHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppFunHist: insertBaseAppFunHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBaseAppFunHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppFunHist: updateBaseAppFunHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBaseAppFunHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppFunHist: deleteBaseAppFunHist: ");

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
