CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffTrngLoc" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffTrngLoc {

	private static String TABLE_NAME = "STAFF_TRNG_LOC";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_TRNG_LOC_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_TRNG_LOC(STAFF_TRNG_LOC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_TRNG_LOC_NAME,STAFF_TRNG_ADDR1,STAFF_TRNG_ADDR2,STAFF_TRNG_CITY,STAFF_TRNG_STATE,STAFF_TRNG_PSTL_CODE,STAFF_TRNG_ZIP4) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffTrngLoc(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrngLoc: getStaffTrngLoc: ");

			try {

					String selectPattern = "SELECT STAFF_TRNG_LOC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_TRNG_LOC_NAME,STAFF_TRNG_ADDR1,STAFF_TRNG_ADDR2,STAFF_TRNG_CITY,STAFF_TRNG_STATE,STAFF_TRNG_PSTL_CODE,STAFF_TRNG_ZIP4 FROM STAFF_TRNG_LOC %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffTrngLoc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrngLoc: getStaffTrngLoc: ");

			try {

					String sql = String.format("SELECT STAFF_TRNG_LOC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_TRNG_LOC_NAME,STAFF_TRNG_ADDR1,STAFF_TRNG_ADDR2,STAFF_TRNG_CITY,STAFF_TRNG_STATE,STAFF_TRNG_PSTL_CODE,STAFF_TRNG_ZIP4 FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffTrngLoc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrngLoc: insertStaffTrngLoc: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffTrngLoc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrngLoc: updateStaffTrngLoc: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffTrngLoc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrngLoc: deleteStaffTrngLoc: ");

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
