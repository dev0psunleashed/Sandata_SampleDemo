CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeChangeReasonLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeChangeReasonLkup {

	private static String TABLE_NAME = "BE_CHANGE_REASON_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_CHANGE_REASON_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO BE_CHANGE_REASON_LKUP(BE_CHANGE_REASON_LKUP_SK,BE_CHANGE_REASON_LKUP_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,APP_MOD_NAME,CHANGE_REASON_CODE,CHANGE_REASON_NAME,CHANGE_REASON_DESC,CHANGE_REASON_EFF_DATE,CHANGE_REASON_TERM_DATE,CHANGE_REASON_MEMO_RQD_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeChangeReasonLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("BeChangeReasonLkup: getBeChangeReasonLkup: ");

			try {

					String sql = String.format("SELECT BE_CHANGE_REASON_LKUP_SK,BE_CHANGE_REASON_LKUP_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,APP_MOD_NAME,CHANGE_REASON_CODE,CHANGE_REASON_NAME,CHANGE_REASON_DESC,CHANGE_REASON_EFF_DATE,CHANGE_REASON_TERM_DATE,CHANGE_REASON_MEMO_RQD_IND FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeChangeReasonLkup(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeChangeReasonLkup: getBeChangeReasonLkup: ");

			try {

					String selectPattern = "SELECT BE_CHANGE_REASON_LKUP_SK,BE_CHANGE_REASON_LKUP_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,APP_MOD_NAME,CHANGE_REASON_CODE,CHANGE_REASON_NAME,CHANGE_REASON_DESC,CHANGE_REASON_EFF_DATE,CHANGE_REASON_TERM_DATE,CHANGE_REASON_MEMO_RQD_IND FROM BE_CHANGE_REASON_LKUP %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeChangeReasonLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeChangeReasonLkup: getBeChangeReasonLkup: ");

			try {

					String sql = String.format("SELECT BE_CHANGE_REASON_LKUP_SK,BE_CHANGE_REASON_LKUP_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,APP_MOD_NAME,CHANGE_REASON_CODE,CHANGE_REASON_NAME,CHANGE_REASON_DESC,CHANGE_REASON_EFF_DATE,CHANGE_REASON_TERM_DATE,CHANGE_REASON_MEMO_RQD_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeChangeReasonLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeChangeReasonLkup: insertBeChangeReasonLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeChangeReasonLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeChangeReasonLkup: updateBeChangeReasonLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeChangeReasonLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeChangeReasonLkup: deleteBeChangeReasonLkup: ");

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
