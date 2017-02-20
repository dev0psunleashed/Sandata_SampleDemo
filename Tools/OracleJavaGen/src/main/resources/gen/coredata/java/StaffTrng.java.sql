CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffTrng" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffTrng {

	private static String TABLE_NAME = "STAFF_TRNG";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_TRNG_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_TRNG(STAFF_TRNG_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_TRNG_START_DTIME,STAFF_TRNG_ENROLLED_DATE,STAFF_TRNG_RESULT_CODE,STAFF_TRNG_LOC_NAME,STAFF_TRNG_CODE,STAFF_TRNG_QLFR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffTrng(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrng: getStaffTrng: ");

			try {

					String selectPattern = "SELECT STAFF_TRNG_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_TRNG_START_DTIME,STAFF_TRNG_ENROLLED_DATE,STAFF_TRNG_RESULT_CODE,STAFF_TRNG_LOC_NAME,STAFF_TRNG_CODE,STAFF_TRNG_QLFR FROM STAFF_TRNG %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffTrng(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrng: getStaffTrng: ");

			try {

					String sql = String.format("SELECT STAFF_TRNG_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_TRNG_START_DTIME,STAFF_TRNG_ENROLLED_DATE,STAFF_TRNG_RESULT_CODE,STAFF_TRNG_LOC_NAME,STAFF_TRNG_CODE,STAFF_TRNG_QLFR FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffTrng(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrng: insertStaffTrng: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffTrng(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrng: updateStaffTrng: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffTrng(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffTrng: deleteStaffTrng: ");

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
