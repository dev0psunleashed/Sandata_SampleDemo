CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeCompLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeCompLkup {

	private static String TABLE_NAME = "BE_COMP_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_COMP_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_COMP_LKUP(BE_COMP_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CTGY_CODE,COMP_EFF_DATE,COMP_TERM_DATE,COMP_CODE,COMP_NAME,COMP_DESC,COMP_RECUR_IND,COMP_RQD_BY_DATE,COMP_RECUR_FREQ,COMP_RECUR_FREQ_UOM,COMP_RQD_FROM_DATE_QLFR,NON_COMP_ALERT_IND,NON_COMP_ALERT_THRESHOLD,COMP_NOTE,COMP_ADDL_INFO_QLFR,COMP_ADDL_INFO_RQD_IND,COMP_ADDL_INFO_NAME,COMP_REQUISITE_ITEM_IND,COMP_SCHED_PERM_QLFR,COMP_RQD_FROM_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeCompLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompLkup: getBeCompLkup: ");

			try {

					String sql = String.format("SELECT BE_COMP_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CTGY_CODE,COMP_EFF_DATE,COMP_TERM_DATE,COMP_CODE,COMP_NAME,COMP_DESC,COMP_RECUR_IND,COMP_RQD_BY_DATE,COMP_RECUR_FREQ,COMP_RECUR_FREQ_UOM,COMP_RQD_FROM_DATE_QLFR,NON_COMP_ALERT_IND,NON_COMP_ALERT_THRESHOLD,COMP_NOTE,COMP_ADDL_INFO_QLFR,COMP_ADDL_INFO_RQD_IND,COMP_ADDL_INFO_NAME,COMP_REQUISITE_ITEM_IND,COMP_SCHED_PERM_QLFR,COMP_RQD_FROM_DATE FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompLkup(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompLkup: getBeCompLkup: ");

			try {

					String selectPattern = "SELECT BE_COMP_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CTGY_CODE,COMP_EFF_DATE,COMP_TERM_DATE,COMP_CODE,COMP_NAME,COMP_DESC,COMP_RECUR_IND,COMP_RQD_BY_DATE,COMP_RECUR_FREQ,COMP_RECUR_FREQ_UOM,COMP_RQD_FROM_DATE_QLFR,NON_COMP_ALERT_IND,NON_COMP_ALERT_THRESHOLD,COMP_NOTE,COMP_ADDL_INFO_QLFR,COMP_ADDL_INFO_RQD_IND,COMP_ADDL_INFO_NAME,COMP_REQUISITE_ITEM_IND,COMP_SCHED_PERM_QLFR,COMP_RQD_FROM_DATE FROM BE_COMP_LKUP %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompLkup: getBeCompLkup: ");

			try {

					String sql = String.format("SELECT BE_COMP_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CTGY_CODE,COMP_EFF_DATE,COMP_TERM_DATE,COMP_CODE,COMP_NAME,COMP_DESC,COMP_RECUR_IND,COMP_RQD_BY_DATE,COMP_RECUR_FREQ,COMP_RECUR_FREQ_UOM,COMP_RQD_FROM_DATE_QLFR,NON_COMP_ALERT_IND,NON_COMP_ALERT_THRESHOLD,COMP_NOTE,COMP_ADDL_INFO_QLFR,COMP_ADDL_INFO_RQD_IND,COMP_ADDL_INFO_NAME,COMP_REQUISITE_ITEM_IND,COMP_SCHED_PERM_QLFR,COMP_RQD_FROM_DATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeCompLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompLkup: insertBeCompLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeCompLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompLkup: updateBeCompLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeCompLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompLkup: deleteBeCompLkup: ");

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
