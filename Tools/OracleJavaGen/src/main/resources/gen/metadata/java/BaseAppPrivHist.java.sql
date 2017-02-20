CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BaseAppPrivHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BaseAppPrivHist {

	private static String TABLE_NAME = "BASE_APP_PRIV_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BASE_APP_PRIV_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO BASE_APP_PRIV_HIST(BASE_APP_PRIV_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,BASE_APP_PRIV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,INCL_INHC_IND,PRIV_ACCESS_IND,PRIV_NO_ACCESS_IND,PRIV_CREATE_IND,PRIV_NO_CREATE_IND,PRIV_UPDATE_IND,PRIV_NO_UPDATE_IND,PRIV_DELETE_IND,PRIV_NO_DELETE_IND,EXCL_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBaseAppPrivHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppPrivHist: getBaseAppPrivHist: ");

			try {

					String sql = String.format("SELECT BASE_APP_PRIV_HIST_SK,REC_CHANGE_TMSTP,REC_CHANGE_CODE,BASE_APP_PRIV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,INCL_INHC_IND,PRIV_ACCESS_IND,PRIV_NO_ACCESS_IND,PRIV_CREATE_IND,PRIV_NO_CREATE_IND,PRIV_UPDATE_IND,PRIV_NO_UPDATE_IND,PRIV_DELETE_IND,PRIV_NO_DELETE_IND,EXCL_IND FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBaseAppPrivHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppPrivHist: insertBaseAppPrivHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBaseAppPrivHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppPrivHist: updateBaseAppPrivHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBaseAppPrivHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BaseAppPrivHist: deleteBaseAppPrivHist: ");

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
