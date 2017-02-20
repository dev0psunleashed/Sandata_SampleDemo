CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AdminStaff" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminStaff {

	private static String TABLE_NAME = "ADMIN_STAFF";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ADMIN_STAFF_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = 12;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO ADMIN_STAFF(ADMIN_STAFF_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_MIDDLE_NAME,ADMIN_STAFF_LAST_NAME,ADMIN_STAFF_DOB,ADMIN_STAFF_HOME_PHONE,ADMIN_STAFF_MOBILE_PHONE,ADMIN_STAFF_ADDR1,ADMIN_STAFF_ADDR2,ADMIN_STAFF_CITY,ADMIN_STAFF_STATE,ADMIN_STAFF_PSTL_CODE,ADMIN_STAFF_ZIP4,ADMIN_STAFF_EMAIL_ADDR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAdminStaff(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaff: getAdminStaff: ");

			try {

					String selectPattern = "SELECT ADMIN_STAFF_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_MIDDLE_NAME,ADMIN_STAFF_LAST_NAME,ADMIN_STAFF_DOB,ADMIN_STAFF_HOME_PHONE,ADMIN_STAFF_MOBILE_PHONE,ADMIN_STAFF_ADDR1,ADMIN_STAFF_ADDR2,ADMIN_STAFF_CITY,ADMIN_STAFF_STATE,ADMIN_STAFF_PSTL_CODE,ADMIN_STAFF_ZIP4,ADMIN_STAFF_EMAIL_ADDR FROM ADMIN_STAFF %s";

					String whereClause = "WHERE ADMIN_STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAdminStaff(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaff: getAdminStaff: ");

			try {

					String sql = String.format("SELECT ADMIN_STAFF_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_MIDDLE_NAME,ADMIN_STAFF_LAST_NAME,ADMIN_STAFF_DOB,ADMIN_STAFF_HOME_PHONE,ADMIN_STAFF_MOBILE_PHONE,ADMIN_STAFF_ADDR1,ADMIN_STAFF_ADDR2,ADMIN_STAFF_CITY,ADMIN_STAFF_STATE,ADMIN_STAFF_PSTL_CODE,ADMIN_STAFF_ZIP4,ADMIN_STAFF_EMAIL_ADDR FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAdminStaff(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaff: insertAdminStaff: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAdminStaff(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaff: updateAdminStaff: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAdminStaff(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdminStaff: deleteAdminStaff: ");

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
