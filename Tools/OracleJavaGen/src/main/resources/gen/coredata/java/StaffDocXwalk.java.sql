CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffDocXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffDocXwalk {

	private static String TABLE_NAME = "STAFF_DOC_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_DOC_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 9;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_DOC_XWALK(STAFF_DOC_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,DOC_ID,DOC_CLAS_NAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffDocXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDocXwalk: getStaffDocXwalk: ");

			try {

					String selectPattern = "SELECT STAFF_DOC_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,DOC_ID,DOC_CLAS_NAME FROM STAFF_DOC_XWALK %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffDocXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDocXwalk: getStaffDocXwalk: ");

			try {

					String sql = String.format("SELECT STAFF_DOC_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,DOC_ID,DOC_CLAS_NAME FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffDocXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDocXwalk: insertStaffDocXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffDocXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDocXwalk: updateStaffDocXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffDocXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffDocXwalk: deleteStaffDocXwalk: ");

			try {

					return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}



}
;
/
