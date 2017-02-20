CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "VisitHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VisitHist {

	private static String TABLE_NAME = "VISIT_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "VISIT_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO VISIT_HIST(VISIT_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getVisitHist(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitHist: getVisitHist: ");

			try {

					String sql = String.format("SELECT VISIT_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitHist(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitHist: getVisitHist: ");

			try {

					String selectPattern = "SELECT VISIT_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO FROM VISIT_HIST %s";

					String whereClause = "WHERE VISIT_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitHist: getVisitHist: ");

			try {

					String sql = String.format("SELECT VISIT_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertVisitHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitHist: insertVisitHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateVisitHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitHist: updateVisitHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteVisitHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitHist: deleteVisitHist: ");

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
