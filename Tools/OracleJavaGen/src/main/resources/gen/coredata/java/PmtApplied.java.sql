CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PmtApplied" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PmtApplied {

	private static String TABLE_NAME = "PMT_APPLIED";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PMT_APPLIED_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 11;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PMT_APPLIED(PMT_APPLIED_SK,PMT_APPLIED_ID,REC_CREATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,PMT_SK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPmtApplied(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PmtApplied: getPmtApplied: ");

			try {

					String selectPattern = "SELECT PMT_APPLIED_SK,PMT_APPLIED_ID,REC_CREATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,PMT_SK FROM PMT_APPLIED %s";

					String whereClause = "WHERE PMT_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPmtApplied(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PmtApplied: getPmtApplied: ");

			try {

					String sql = String.format("SELECT PMT_APPLIED_SK,PMT_APPLIED_ID,REC_CREATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,PMT_SK FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPmtApplied(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PmtApplied: insertPmtApplied: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePmtApplied(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PmtApplied: updatePmtApplied: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePmtApplied(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PmtApplied: deletePmtApplied: ");

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
