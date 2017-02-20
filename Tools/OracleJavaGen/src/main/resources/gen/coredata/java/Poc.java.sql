CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Poc" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Poc {

	private static String TABLE_NAME = "POC";

	private static String SEQUENCE_KEY_COLUMN_NAME = "POC_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 7;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO POC(POC_SK,POC_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,AUTH_ID,FREQ_TYP_LKUP_SK,POC_START_DATE,POC_END_DATE,PLAN_OF_TREAT_IND,POC_DAYS_PER_WEEK,POC_DAY_1_IND,POC_DAY_2_IND,POC_DAY_3_IND,POC_DAY_4_IND,POC_DAY_5_IND,POC_DAY_6_IND,POC_DAY_7_IND,POC_HRS_PER_DAY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPoc(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Poc: getPoc: ");

			try {

					String selectPattern = "SELECT POC_SK,POC_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,AUTH_ID,FREQ_TYP_LKUP_SK,POC_START_DATE,POC_END_DATE,PLAN_OF_TREAT_IND,POC_DAYS_PER_WEEK,POC_DAY_1_IND,POC_DAY_2_IND,POC_DAY_3_IND,POC_DAY_4_IND,POC_DAY_5_IND,POC_DAY_6_IND,POC_DAY_7_IND,POC_HRS_PER_DAY FROM POC %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPoc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Poc: getPoc: ");

			try {

					String sql = String.format("SELECT POC_SK,POC_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,AUTH_ID,FREQ_TYP_LKUP_SK,POC_START_DATE,POC_END_DATE,PLAN_OF_TREAT_IND,POC_DAYS_PER_WEEK,POC_DAY_1_IND,POC_DAY_2_IND,POC_DAY_3_IND,POC_DAY_4_IND,POC_DAY_5_IND,POC_DAY_6_IND,POC_DAY_7_IND,POC_HRS_PER_DAY FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPoc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Poc: insertPoc: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePoc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Poc: updatePoc: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePoc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Poc: deletePoc: ");

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
