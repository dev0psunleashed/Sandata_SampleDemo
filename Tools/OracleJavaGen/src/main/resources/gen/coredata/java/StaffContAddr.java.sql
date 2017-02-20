CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffContAddr" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffContAddr {

	private static String TABLE_NAME = "STAFF_CONT_ADDR";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_CONT_ADDR_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_CONT_ADDR(STAFF_CONT_ADDR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,STAFF_ID,ADDR_PRIO_NAME,STAFF_ADDR_TYP_NAME,STAFF_ADDR_USE_FOR_MAIL_IND,STAFF_ADDR1,STAFF_ADDR2,STAFF_APT_NUM,STAFF_CITY,STAFF_STATE,STAFF_PSTL_CODE,STAFF_ZIP4,STAFF_COUNTY,STAFF_REGION,STAFF_BOROUGH,STAFF_COORD_LATITUDE,STAFF_COORD_LONGITUDE,STAFF_COORD_ALTITUDE,STAFF_COORD_TMSTP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffContAddr(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffContAddr: getStaffContAddr: ");

			try {

					String selectPattern = "SELECT STAFF_CONT_ADDR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,STAFF_ID,ADDR_PRIO_NAME,STAFF_ADDR_TYP_NAME,STAFF_ADDR_USE_FOR_MAIL_IND,STAFF_ADDR1,STAFF_ADDR2,STAFF_APT_NUM,STAFF_CITY,STAFF_STATE,STAFF_PSTL_CODE,STAFF_ZIP4,STAFF_COUNTY,STAFF_REGION,STAFF_BOROUGH,STAFF_COORD_LATITUDE,STAFF_COORD_LONGITUDE,STAFF_COORD_ALTITUDE,STAFF_COORD_TMSTP FROM STAFF_CONT_ADDR %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffContAddr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffContAddr: getStaffContAddr: ");

			try {

					String sql = String.format("SELECT STAFF_CONT_ADDR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,STAFF_ID,ADDR_PRIO_NAME,STAFF_ADDR_TYP_NAME,STAFF_ADDR_USE_FOR_MAIL_IND,STAFF_ADDR1,STAFF_ADDR2,STAFF_APT_NUM,STAFF_CITY,STAFF_STATE,STAFF_PSTL_CODE,STAFF_ZIP4,STAFF_COUNTY,STAFF_REGION,STAFF_BOROUGH,STAFF_COORD_LATITUDE,STAFF_COORD_LONGITUDE,STAFF_COORD_ALTITUDE,STAFF_COORD_TMSTP FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffContAddr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffContAddr: insertStaffContAddr: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffContAddr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffContAddr: updateStaffContAddr: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffContAddr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffContAddr: deleteStaffContAddr: ");

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
