CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AppModHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppModHist {

	private static String TABLE_NAME = "APP_MOD_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "APP_MOD_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO APP_MOD_HIST(APP_MOD_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,APP_MOD_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_MOD_PAR_SK,MOD_NAME,MOD_TYP_NAME) VALUES (?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAppModHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppModHist: getAppModHist: ");

			try {

					String sql = String.format("SELECT APP_MOD_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,APP_MOD_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,APP_MOD_PAR_SK,MOD_NAME,MOD_TYP_NAME FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAppModHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppModHist: insertAppModHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAppModHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppModHist: updateAppModHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAppModHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppModHist: deleteAppModHist: ");

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
