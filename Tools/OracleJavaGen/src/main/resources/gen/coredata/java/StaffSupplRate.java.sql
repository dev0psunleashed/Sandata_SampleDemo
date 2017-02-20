CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffSupplRate" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffSupplRate {

	private static String TABLE_NAME = "STAFF_SUPPL_RATE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_SUPPL_RATE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_SUPPL_RATE(STAFF_SUPPL_RATE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_SUPPL_RATE_EFF_DATE,STAFF_SUPPL_RATE_TERM_DATE,STAFF_SUPPL_RATE_CHANGE_QLFR,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,STAFF_SUPPL_RATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffSupplRate(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffSupplRate: getStaffSupplRate: ");

			try {

					String selectPattern = "SELECT STAFF_SUPPL_RATE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_SUPPL_RATE_EFF_DATE,STAFF_SUPPL_RATE_TERM_DATE,STAFF_SUPPL_RATE_CHANGE_QLFR,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,STAFF_SUPPL_RATE FROM STAFF_SUPPL_RATE %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffSupplRate(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffSupplRate: getStaffSupplRate: ");

			try {

					String sql = String.format("SELECT STAFF_SUPPL_RATE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,STAFF_SUPPL_RATE_EFF_DATE,STAFF_SUPPL_RATE_TERM_DATE,STAFF_SUPPL_RATE_CHANGE_QLFR,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,STAFF_SUPPL_RATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffSupplRate(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffSupplRate: insertStaffSupplRate: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffSupplRate(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffSupplRate: updateStaffSupplRate: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffSupplRate(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffSupplRate: deleteStaffSupplRate: ");

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
