CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffPtXref" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffPtXref {

	private static String TABLE_NAME = "STAFF_PT_XREF";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_PT_XREF_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 3;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_PT_XREF(STAFF_PT_XREF_SK,REC_CREATE_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,STAFF_PT_XREF_EFF_DATE,STAFF_PT_XREF_TERM_DATE,SVC_NAME) VALUES (?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffPtXref(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtXref: getStaffPtXref: ");

			try {

					String sql = String.format("SELECT STAFF_PT_XREF_SK,REC_CREATE_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,STAFF_PT_XREF_EFF_DATE,STAFF_PT_XREF_TERM_DATE,SVC_NAME FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffPtXref(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtXref: getStaffPtXref: ");

			try {

					String selectPattern = "SELECT STAFF_PT_XREF_SK,REC_CREATE_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,STAFF_PT_XREF_EFF_DATE,STAFF_PT_XREF_TERM_DATE,SVC_NAME FROM STAFF_PT_XREF %s";

					String whereClause = "WHERE STAFF_ID=? AND PT_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffPtXref(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtXref: getStaffPtXref: ");

			try {

					String sql = String.format("SELECT STAFF_PT_XREF_SK,REC_CREATE_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,STAFF_PT_XREF_EFF_DATE,STAFF_PT_XREF_TERM_DATE,SVC_NAME FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffPtXref(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtXref: insertStaffPtXref: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffPtXref(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtXref: updateStaffPtXref: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffPtXref(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtXref: deleteStaffPtXref: ");

			try {

					return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, false);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}



}
;
/
