CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffDed" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffDed {

	private static String TABLE_NAME = "STAFF_DED";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_DED_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 6;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_DED(STAFF_DED_SK,STAFF_DED_ID,STAFF_PR_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,STAFF_DED_NAME,DED_PERCENTAGE,DED_AMT,DED_PRE_TAX_OR_POST_TAX_IND) VALUES (?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffDed(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDed: getStaffDed: ");

			try {

					String selectPattern = "SELECT STAFF_DED_SK,STAFF_DED_ID,STAFF_PR_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,STAFF_DED_NAME,DED_PERCENTAGE,DED_AMT,DED_PRE_TAX_OR_POST_TAX_IND FROM STAFF_DED %s";

					String whereClause = "WHERE STAFF_PR_DET_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffDed(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDed: getStaffDed: ");

			try {

					String sql = String.format("SELECT STAFF_DED_SK,STAFF_DED_ID,STAFF_PR_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,STAFF_DED_NAME,DED_PERCENTAGE,DED_AMT,DED_PRE_TAX_OR_POST_TAX_IND FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffDed(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDed: insertStaffDed: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffDed(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDed: updateStaffDed: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffDed(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDed: deleteStaffDed: ");

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
