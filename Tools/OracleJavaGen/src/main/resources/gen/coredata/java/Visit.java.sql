CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Visit" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Visit {

	private static String TABLE_NAME = "VISIT";

	private static String SEQUENCE_KEY_COLUMN_NAME = "VISIT_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO VISIT(VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO,VISIT_STATUS_NAME,VISIT_OTHER_ID,VISIT_PT_SIG_IND,VISIT_CNFRM_QLFR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getVisit(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("Visit: getVisit: ");

			try {

					String sql = String.format("SELECT VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO,VISIT_STATUS_NAME,VISIT_OTHER_ID,VISIT_PT_SIG_IND,VISIT_CNFRM_QLFR FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisit(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Visit: getVisit: ");

			try {

					String selectPattern = "SELECT VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO,VISIT_STATUS_NAME,VISIT_OTHER_ID,VISIT_PT_SIG_IND,VISIT_CNFRM_QLFR FROM VISIT %s";

					String whereClause = "WHERE STAFF_ID=? AND SCHED_EVNT_SK=? AND PT_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisit(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Visit: getVisit: ");

			try {

					String sql = String.format("SELECT VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP,VISIT_CANCELLED_IND,VISIT_CANCELLATION_REASON,VISIT_APRVD_IND,VISIT_VFYD_BY_SCHED_IND,VISIT_PAY_BY_SCHED_IND,VISIT_DO_NOT_BILL_IND,VISIT_DO_NOT_PAY_IND,VISIT_PAY_HRS,VISIT_BILL_HRS,VISIT_OT_ABS_HRS,USER_NAME,USER_GUID,RESOLUTION_CODE,MEMO,VISIT_STATUS_NAME,VISIT_OTHER_ID,VISIT_PT_SIG_IND,VISIT_CNFRM_QLFR FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertVisit(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Visit: insertVisit: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateVisit(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Visit: updateVisit: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteVisit(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Visit: deleteVisit: ");

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
