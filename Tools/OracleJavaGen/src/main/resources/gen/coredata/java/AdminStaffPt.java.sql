CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AdminStaffPt" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminStaffPt {

	private static String TABLE_NAME = "ADMIN_STAFF_PT";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ADMIN_STAFF_PT_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO ADMIN_STAFF_PT(ADMIN_STAFF_PT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,PT_ID,ADMIN_STAFF_PT_EFF_DATE,ADMIN_STAFF_PT_TERM_DATE,ADMIN_STAFF_ROLE_NAME,ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAdminStaffPt(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffPt: getAdminStaffPt: ");

			try {

					String selectPattern = "SELECT ADMIN_STAFF_PT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,PT_ID,ADMIN_STAFF_PT_EFF_DATE,ADMIN_STAFF_PT_TERM_DATE,ADMIN_STAFF_ROLE_NAME,ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE FROM ADMIN_STAFF_PT %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAdminStaffPt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffPt: getAdminStaffPt: ");

			try {

					String sql = String.format("SELECT ADMIN_STAFF_PT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,PT_ID,ADMIN_STAFF_PT_EFF_DATE,ADMIN_STAFF_PT_TERM_DATE,ADMIN_STAFF_ROLE_NAME,ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAdminStaffPt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffPt: insertAdminStaffPt: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAdminStaffPt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffPt: updateAdminStaffPt: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAdminStaffPt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaffPt: deleteAdminStaffPt: ");

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
