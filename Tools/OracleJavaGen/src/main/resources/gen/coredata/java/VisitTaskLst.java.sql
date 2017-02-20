CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "VisitTaskLst" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VisitTaskLst {

	private static String TABLE_NAME = "VISIT_TASK_LST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "VISIT_TASK_LST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO VISIT_TASK_LST(VISIT_TASK_LST_SK,VISIT_TASK_LST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,VISIT_SK,BE_TASK_ID,CRITICAL_TASK_IND,VISIT_TASK_SCHEDULED_IND,VISIT_TASK_PERF_IND,VISIT_TASK_NOT_PERF_REASON,VISIT_TASK_COMMENT,TASK_RESULTS_RDNG_TYP,TASK_RESULTS_RDNG_VAL,VISIT_TASK_CNFRM_QLFR, VISIT_TASK_TYP_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getVisitTaskLst(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitTaskLst: getVisitTaskLst: ");

			try {

					String sql = String.format("SELECT VISIT_TASK_LST_SK,VISIT_TASK_LST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,VISIT_SK,BE_TASK_ID,CRITICAL_TASK_IND,VISIT_TASK_SCHEDULED_IND,VISIT_TASK_PERF_IND,VISIT_TASK_NOT_PERF_REASON,VISIT_TASK_COMMENT,TASK_RESULTS_RDNG_TYP,TASK_RESULTS_RDNG_VAL,VISIT_TASK_CNFRM_QLFR, VISIT_TASK_TYP_CODE FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitTaskLst(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitTaskLst: getVisitTaskLst: ");

			try {

					String selectPattern = "SELECT VISIT_TASK_LST_SK,VISIT_TASK_LST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,VISIT_SK,BE_TASK_ID,CRITICAL_TASK_IND,VISIT_TASK_SCHEDULED_IND,VISIT_TASK_PERF_IND,VISIT_TASK_NOT_PERF_REASON,VISIT_TASK_COMMENT,TASK_RESULTS_RDNG_TYP,TASK_RESULTS_RDNG_VAL,VISIT_TASK_CNFRM_QLFR, VISIT_TASK_TYP_CODE FROM VISIT_TASK_LST %s";

					String whereClause = "WHERE VISIT_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitTaskLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitTaskLst: getVisitTaskLst: ");

			try {

					String sql = String.format("SELECT VISIT_TASK_LST_SK,VISIT_TASK_LST_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,VISIT_SK,BE_TASK_ID,CRITICAL_TASK_IND,VISIT_TASK_SCHEDULED_IND,VISIT_TASK_PERF_IND,VISIT_TASK_NOT_PERF_REASON,VISIT_TASK_COMMENT,TASK_RESULTS_RDNG_TYP,TASK_RESULTS_RDNG_VAL,VISIT_TASK_CNFRM_QLFR, VISIT_TASK_TYP_CODE FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertVisitTaskLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitTaskLst: insertVisitTaskLst: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateVisitTaskLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitTaskLst: updateVisitTaskLst: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteVisitTaskLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitTaskLst: deleteVisitTaskLst: ");

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
