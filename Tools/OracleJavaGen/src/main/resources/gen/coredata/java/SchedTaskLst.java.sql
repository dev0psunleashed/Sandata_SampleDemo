CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "SchedTaskLst" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SchedTaskLst {

	private static String TABLE_NAME = "SCHED_TASK_LST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_TASK_LST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED_TASK_LST(SCHED_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,BE_ID,BE_TASK_ID) VALUES (?,?,?,?,?,?,?)";

	public static ResultSet getSchedTaskLst(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedTaskLst: getSchedTaskLst: ");

			try {

					String sql = String.format("SELECT SCHED_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,BE_ID,BE_TASK_ID FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedTaskLst(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedTaskLst: getSchedTaskLst: ");

			try {

					String selectPattern = "SELECT SCHED_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,BE_ID,BE_TASK_ID FROM SCHED_TASK_LST %s";

					String whereClause = "WHERE SCHED_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSchedTaskLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedTaskLst: getSchedTaskLst: ");

			try {

					String sql = String.format("SELECT SCHED_TASK_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,SCHED_SK,BE_ID,BE_TASK_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSchedTaskLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedTaskLst: insertSchedTaskLst: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSchedTaskLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedTaskLst: updateSchedTaskLst: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSchedTaskLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("SchedTaskLst: deleteSchedTaskLst: ");

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
