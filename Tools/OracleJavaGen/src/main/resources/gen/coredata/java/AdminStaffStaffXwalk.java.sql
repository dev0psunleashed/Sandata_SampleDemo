CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AdminStaffStaffXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminStaffStaffXwalk {

	private static String TABLE_NAME = "ADMIN_STAFF_STAFF_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ADMIN_STAFF_STAFF_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO ADMIN_STAFF_STAFF_XWALK(ADMIN_STAFF_STAFF_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAdminStaffStaffXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffStaffXwalk: getAdminStaffStaffXwalk: ");

			try {

					String selectPattern = "SELECT ADMIN_STAFF_STAFF_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE FROM ADMIN_STAFF_STAFF_XWALK %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAdminStaffStaffXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffStaffXwalk: getAdminStaffStaffXwalk: ");

			try {

					String sql = String.format("SELECT ADMIN_STAFF_STAFF_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAdminStaffStaffXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffStaffXwalk: insertAdminStaffStaffXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAdminStaffStaffXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffStaffXwalk: updateAdminStaffStaffXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAdminStaffStaffXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffStaffXwalk: deleteAdminStaffStaffXwalk: ");

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
