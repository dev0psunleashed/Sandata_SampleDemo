CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffCredential" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffCredential {

	private static String TABLE_NAME = "STAFF_CREDENTIAL";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_CREDENTIAL_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_CREDENTIAL(STAFF_CREDENTIAL_SK,STAFF_CREDENTIAL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,SVC_NAME,STAFF_LICENSE_NUM,STAFF_LICENSE_EXPR_DATE,STAFF_LICENSE_RCVD_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffCredential(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffCredential: getStaffCredential: ");

			try {

					String selectPattern = "SELECT STAFF_CREDENTIAL_SK,STAFF_CREDENTIAL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,SVC_NAME,STAFF_LICENSE_NUM,STAFF_LICENSE_EXPR_DATE,STAFF_LICENSE_RCVD_DATE FROM STAFF_CREDENTIAL %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffCredential(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffCredential: getStaffCredential: ");

			try {

					String sql = String.format("SELECT STAFF_CREDENTIAL_SK,STAFF_CREDENTIAL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,SVC_NAME,STAFF_LICENSE_NUM,STAFF_LICENSE_EXPR_DATE,STAFF_LICENSE_RCVD_DATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffCredential(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffCredential: insertStaffCredential: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffCredential(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffCredential: updateStaffCredential: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffCredential(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffCredential: deleteStaffCredential: ");

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
