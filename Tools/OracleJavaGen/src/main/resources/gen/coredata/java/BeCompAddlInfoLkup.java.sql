CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeCompAddlInfoLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeCompAddlInfoLkup {

	private static String TABLE_NAME = "BE_COMP_ADDL_INFO_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_COMP_ADDL_INFO_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_COMP_ADDL_INFO_LKUP(BE_COMP_ADDL_INFO_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CODE,COMPLAINCE_ADDL_INFO_TYP_NAME,COMP_ADDL_INFO_NAME,COMP_ADDL_INFO_DESC,COMP_ADDL_INFO_NOTE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeCompAddlInfoLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompAddlInfoLkup: getBeCompAddlInfoLkup: ");

			try {

					String sql = String.format("SELECT BE_COMP_ADDL_INFO_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CODE,COMPLAINCE_ADDL_INFO_TYP_NAME,COMP_ADDL_INFO_NAME,COMP_ADDL_INFO_DESC,COMP_ADDL_INFO_NOTE FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompAddlInfoLkup(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompAddlInfoLkup: getBeCompAddlInfoLkup: ");

			try {

					String selectPattern = "SELECT BE_COMP_ADDL_INFO_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CODE,COMPLAINCE_ADDL_INFO_TYP_NAME,COMP_ADDL_INFO_NAME,COMP_ADDL_INFO_DESC,COMP_ADDL_INFO_NOTE FROM BE_COMP_ADDL_INFO_LKUP %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompAddlInfoLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompAddlInfoLkup: getBeCompAddlInfoLkup: ");

			try {

					String sql = String.format("SELECT BE_COMP_ADDL_INFO_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,COMP_CODE,COMPLAINCE_ADDL_INFO_TYP_NAME,COMP_ADDL_INFO_NAME,COMP_ADDL_INFO_DESC,COMP_ADDL_INFO_NOTE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeCompAddlInfoLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompAddlInfoLkup: insertBeCompAddlInfoLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeCompAddlInfoLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompAddlInfoLkup: updateBeCompAddlInfoLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeCompAddlInfoLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompAddlInfoLkup: deleteBeCompAddlInfoLkup: ");

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
