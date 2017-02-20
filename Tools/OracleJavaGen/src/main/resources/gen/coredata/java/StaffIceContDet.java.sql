CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffIceContDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffIceContDet {

	private static String TABLE_NAME = "STAFF_ICE_CONT_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_ICE_CONT_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_ICE_CONT_DET(STAFF_ICE_CONT_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,STAFF_ID,CONT_REL,STAFF_ICE_CONT_FIRST_NAME,STAFF_ICE_CONT_MIDDLE_NAME,STAFF_ICE_CONT_LAST_NAME,STAFF_ICE_CONT_SUFFIX_NAME,STAFF_ICE_CONT_ADDR1,STAFF_ICE_CONT_ADDR2,STAFF_ICE_CONT_CITY,STAFF_ICE_CONT_STATE,STAFF_ICE_CONT_PSTL_CODE,STAFF_ICE_CONT_ZIP4,STAFF_ICE_CONT_HOME_PHONE,STAFF_ICE_CONT_WORK_PHONE,STAFF_ICE_CONT_MOBILE_PHONE,STAFF_ICE_CONT_EMAIL,STAFF_ICE_CONT_EMAIL_OTHER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffIceContDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffIceContDet: getStaffIceContDet: ");

			try {

					String selectPattern = "SELECT STAFF_ICE_CONT_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,STAFF_ID,CONT_REL,STAFF_ICE_CONT_FIRST_NAME,STAFF_ICE_CONT_MIDDLE_NAME,STAFF_ICE_CONT_LAST_NAME,STAFF_ICE_CONT_SUFFIX_NAME,STAFF_ICE_CONT_ADDR1,STAFF_ICE_CONT_ADDR2,STAFF_ICE_CONT_CITY,STAFF_ICE_CONT_STATE,STAFF_ICE_CONT_PSTL_CODE,STAFF_ICE_CONT_ZIP4,STAFF_ICE_CONT_HOME_PHONE,STAFF_ICE_CONT_WORK_PHONE,STAFF_ICE_CONT_MOBILE_PHONE,STAFF_ICE_CONT_EMAIL,STAFF_ICE_CONT_EMAIL_OTHER FROM STAFF_ICE_CONT_DET %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffIceContDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffIceContDet: getStaffIceContDet: ");

			try {

					String sql = String.format("SELECT STAFF_ICE_CONT_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,STAFF_ID,CONT_REL,STAFF_ICE_CONT_FIRST_NAME,STAFF_ICE_CONT_MIDDLE_NAME,STAFF_ICE_CONT_LAST_NAME,STAFF_ICE_CONT_SUFFIX_NAME,STAFF_ICE_CONT_ADDR1,STAFF_ICE_CONT_ADDR2,STAFF_ICE_CONT_CITY,STAFF_ICE_CONT_STATE,STAFF_ICE_CONT_PSTL_CODE,STAFF_ICE_CONT_ZIP4,STAFF_ICE_CONT_HOME_PHONE,STAFF_ICE_CONT_WORK_PHONE,STAFF_ICE_CONT_MOBILE_PHONE,STAFF_ICE_CONT_EMAIL,STAFF_ICE_CONT_EMAIL_OTHER FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffIceContDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffIceContDet: insertStaffIceContDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffIceContDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffIceContDet: updateStaffIceContDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffIceContDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffIceContDet: deleteStaffIceContDet: ");

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
