CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "InvDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InvDet {

	private static String TABLE_NAME = "INV_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "INV_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 14;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO INV_DET(INV_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,INV_NUM,INV_DET_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,INV_DET_SVC_DATE,INV_DET_STATUS_CODE,INV_DET_SUBM_STATUS_NAME,PAYER_ID,REV_CODE,BILLING_CODE,MDFR_4_CODE,MDFR_3_CODE,MDFR_2_CODE,MDFR_1_CODE,RENDER_PROFNL_NPI,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,INV_DET_RATE_AMT,INV_DET_TOTAL_AMT,INV_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,CRN,INV_DET_SVC_LINE_ITEM_NUM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getInvDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("InvDet: getInvDet: ");

			try {

					String selectPattern = "SELECT INV_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,INV_NUM,INV_DET_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,INV_DET_SVC_DATE,INV_DET_STATUS_CODE,INV_DET_SUBM_STATUS_NAME,PAYER_ID,REV_CODE,BILLING_CODE,MDFR_4_CODE,MDFR_3_CODE,MDFR_2_CODE,MDFR_1_CODE,RENDER_PROFNL_NPI,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,INV_DET_RATE_AMT,INV_DET_TOTAL_AMT,INV_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,CRN,INV_DET_SVC_LINE_ITEM_NUM FROM INV_DET %s";

					String whereClause = "WHERE INV_SK=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getInvDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("InvDet: getInvDet: ");

			try {

					String sql = String.format("SELECT INV_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,INV_NUM,INV_DET_ID,TIMESHEET_DET_SK,TIMESHEET_SUMMARY_SK,VENDOR_NAME,INV_DET_SVC_DATE,INV_DET_STATUS_CODE,INV_DET_SUBM_STATUS_NAME,PAYER_ID,REV_CODE,BILLING_CODE,MDFR_4_CODE,MDFR_3_CODE,MDFR_2_CODE,MDFR_1_CODE,RENDER_PROFNL_NPI,SVC_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,INV_DET_RATE_AMT,INV_DET_TOTAL_AMT,INV_DET_TOTAL_UNIT,SPLIT_BILLING_ALWD_IND,USER_NAME,USER_GUID,MEMO,CRN,INV_DET_SVC_LINE_ITEM_NUM FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertInvDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("InvDet: insertInvDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateInvDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("InvDet: updateInvDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteInvDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("InvDet: deleteInvDet: ");

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
