CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PocTaskLst" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PocTaskLst {

	private static String TABLE_NAME = "POC_TASK_LST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "POC_TASK_LST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO POC_TASK_LST(POC_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,POC_SK,BE_ID,BE_TASK_ID,POC_START_TIME_DAY_1,POC_END_TIME_DAY_1,POC_START_TIME_DAY_2,POC_END_TIME_DAY_2,POC_START_TIME_DAY_3,POC_END_TIME_DAY_3,POC_START_TIME_DAY_4,POC_END_TIME_DAY_4,POC_START_TIME_DAY_5,POC_END_TIME_DAY_5,POC_START_TIME_DAY_6,POC_END_TIME_DAY_6,POC_START_TIME_DAY_7,POC_END_TIME_DAY_7) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPocTaskLst(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocTaskLst: getPocTaskLst: ");

			try {

					String sql = String.format("SELECT POC_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,POC_SK,BE_ID,BE_TASK_ID,POC_START_TIME_DAY_1,POC_END_TIME_DAY_1,POC_START_TIME_DAY_2,POC_END_TIME_DAY_2,POC_START_TIME_DAY_3,POC_END_TIME_DAY_3,POC_START_TIME_DAY_4,POC_END_TIME_DAY_4,POC_START_TIME_DAY_5,POC_END_TIME_DAY_5,POC_START_TIME_DAY_6,POC_END_TIME_DAY_6,POC_START_TIME_DAY_7,POC_END_TIME_DAY_7 FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPocTaskLst(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocTaskLst: getPocTaskLst: ");

			try {

					String selectPattern = "SELECT POC_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,POC_SK,BE_ID,BE_TASK_ID,POC_START_TIME_DAY_1,POC_END_TIME_DAY_1,POC_START_TIME_DAY_2,POC_END_TIME_DAY_2,POC_START_TIME_DAY_3,POC_END_TIME_DAY_3,POC_START_TIME_DAY_4,POC_END_TIME_DAY_4,POC_START_TIME_DAY_5,POC_END_TIME_DAY_5,POC_START_TIME_DAY_6,POC_END_TIME_DAY_6,POC_START_TIME_DAY_7,POC_END_TIME_DAY_7 FROM POC_TASK_LST %s";

					String whereClause = "WHERE POC_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPocTaskLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocTaskLst: getPocTaskLst: ");

			try {

					String sql = String.format("SELECT POC_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,POC_SK,BE_ID,BE_TASK_ID,POC_START_TIME_DAY_1,POC_END_TIME_DAY_1,POC_START_TIME_DAY_2,POC_END_TIME_DAY_2,POC_START_TIME_DAY_3,POC_END_TIME_DAY_3,POC_START_TIME_DAY_4,POC_END_TIME_DAY_4,POC_START_TIME_DAY_5,POC_END_TIME_DAY_5,POC_START_TIME_DAY_6,POC_END_TIME_DAY_6,POC_START_TIME_DAY_7,POC_END_TIME_DAY_7 FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPocTaskLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocTaskLst: insertPocTaskLst: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePocTaskLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocTaskLst: updatePocTaskLst: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePocTaskLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocTaskLst: deletePocTaskLst: ");

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
