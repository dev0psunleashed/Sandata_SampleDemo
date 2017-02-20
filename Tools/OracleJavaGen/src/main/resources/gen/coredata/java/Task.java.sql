CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Task" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Task {

	private static String TABLE_NAME = "TASK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "TASK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = 12;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO TASK(TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,TASK_ID,BE_TASK_ID,BE_TASK_NAME,TASK_DESC,TASK_RDNG_IND,TASK_RDNG_VALID_RULE,CRITICAL_TASK_IND,TASK_EFF_DATE,TASK_TERM_DATE,TASK_SOURCE_QLFR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getTask(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Task: getTask: ");

			try {

					String selectPattern = "SELECT TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,TASK_ID,BE_TASK_ID,BE_TASK_NAME,TASK_DESC,TASK_RDNG_IND,TASK_RDNG_VALID_RULE,CRITICAL_TASK_IND,TASK_EFF_DATE,TASK_TERM_DATE,TASK_SOURCE_QLFR FROM TASK %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getTask(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Task: getTask: ");

			try {

					String sql = String.format("SELECT TASK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,TASK_ID,BE_TASK_ID,BE_TASK_NAME,TASK_DESC,TASK_RDNG_IND,TASK_RDNG_VALID_RULE,CRITICAL_TASK_IND,TASK_EFF_DATE,TASK_TERM_DATE,TASK_SOURCE_QLFR FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertTask(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Task: insertTask: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateTask(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Task: updateTask: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteTask(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Task: deleteTask: ");

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
