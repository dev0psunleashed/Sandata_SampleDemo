CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffAvail" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffAvail {

	private static String TABLE_NAME = "STAFF_AVAIL";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_AVAIL_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_AVAIL(STAFF_AVAIL_SK,STAFF_AVAIL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_IS_AVAILABLE_IND,AVAIL_DAY,AVAIL_DATE,AVAIL_START_HOUR,AVAIL_END_HOUR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffAvail(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffAvail: getStaffAvail: ");

			try {

					String sql = String.format("SELECT STAFF_AVAIL_SK,STAFF_AVAIL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_IS_AVAILABLE_IND,AVAIL_DAY,AVAIL_DATE,AVAIL_START_HOUR,AVAIL_END_HOUR FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffAvail(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffAvail: getStaffAvail: ");

			try {

					String selectPattern = "SELECT STAFF_AVAIL_SK,STAFF_AVAIL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_IS_AVAILABLE_IND,AVAIL_DAY,AVAIL_DATE,AVAIL_START_HOUR,AVAIL_END_HOUR FROM STAFF_AVAIL %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffAvail(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffAvail: getStaffAvail: ");

			try {

					String sql = String.format("SELECT STAFF_AVAIL_SK,STAFF_AVAIL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_IS_AVAILABLE_IND,AVAIL_DAY,AVAIL_DATE,AVAIL_START_HOUR,AVAIL_END_HOUR FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffAvail(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffAvail: insertStaffAvail: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffAvail(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffAvail: updateStaffAvail: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffAvail(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffAvail: deleteStaffAvail: ");

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
