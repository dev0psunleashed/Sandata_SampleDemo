CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "StaffPtHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffPtHist {

	private static String TABLE_NAME = "STAFF_PT_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_PT_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF_PT_HIST(STAFF_PT_HIST_SK,STAFF_PT_HIST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,PT_ID,STAFF_PT_HIST_START_DATE,STAFF_PT_HIST_END_DATE,STAFF_PT_REL_TYP,STAFF_PT_CURR_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaffPtHist(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtHist: getStaffPtHist: ");

			try {

					String selectPattern = "SELECT STAFF_PT_HIST_SK,STAFF_PT_HIST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,PT_ID,STAFF_PT_HIST_START_DATE,STAFF_PT_HIST_END_DATE,STAFF_PT_REL_TYP,STAFF_PT_CURR_STATUS FROM STAFF_PT_HIST %s";

					String whereClause = "WHERE STAFF_ID=? AND PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaffPtHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtHist: getStaffPtHist: ");

			try {

					String sql = String.format("SELECT STAFF_PT_HIST_SK,STAFF_PT_HIST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,STAFF_ID,PT_ID,STAFF_PT_HIST_START_DATE,STAFF_PT_HIST_END_DATE,STAFF_PT_REL_TYP,STAFF_PT_CURR_STATUS FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaffPtHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtHist: insertStaffPtHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaffPtHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtHist: updateStaffPtHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaffPtHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("StaffPtHist: deleteStaffPtHist: ");

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
