CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "ArTxnBatchDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ArTxnBatchDet {

	private static String TABLE_NAME = "AR_TXN_BATCH_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "AR_TXN_BATCH_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO AR_TXN_BATCH_DET(AR_TXN_BATCH_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,AR_TXN_BATCH_ID,PAYER_ID,INV_NUM,CHECK_DATE,CHECK_DEPOSIT_DATE,CHECK_RCVD_DATE,PMT_TYP_QLFR,PMT_TYP_NUM,PMT_AMT,AR_NOTE_TYP_CODE,AR_TXN_NOTE,AR_TXN_BATCH_POST_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getArTxnBatchDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArTxnBatchDet: getArTxnBatchDet: ");

			try {

					String sql = String.format("SELECT AR_TXN_BATCH_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,AR_TXN_BATCH_ID,PAYER_ID,INV_NUM,CHECK_DATE,CHECK_DEPOSIT_DATE,CHECK_RCVD_DATE,PMT_TYP_QLFR,PMT_TYP_NUM,PMT_AMT,AR_NOTE_TYP_CODE,AR_TXN_NOTE,AR_TXN_BATCH_POST_IND FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getArTxnBatchDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArTxnBatchDet: getArTxnBatchDet: ");

			try {

					String selectPattern = "SELECT AR_TXN_BATCH_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,AR_TXN_BATCH_ID,PAYER_ID,INV_NUM,CHECK_DATE,CHECK_DEPOSIT_DATE,CHECK_RCVD_DATE,PMT_TYP_QLFR,PMT_TYP_NUM,PMT_AMT,AR_NOTE_TYP_CODE,AR_TXN_NOTE,AR_TXN_BATCH_POST_IND FROM AR_TXN_BATCH_DET %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getArTxnBatchDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArTxnBatchDet: getArTxnBatchDet: ");

			try {

					String sql = String.format("SELECT AR_TXN_BATCH_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,AR_TXN_BATCH_ID,PAYER_ID,INV_NUM,CHECK_DATE,CHECK_DEPOSIT_DATE,CHECK_RCVD_DATE,PMT_TYP_QLFR,PMT_TYP_NUM,PMT_AMT,AR_NOTE_TYP_CODE,AR_TXN_NOTE,AR_TXN_BATCH_POST_IND FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertArTxnBatchDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArTxnBatchDet: insertArTxnBatchDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateArTxnBatchDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArTxnBatchDet: updateArTxnBatchDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteArTxnBatchDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArTxnBatchDet: deleteArTxnBatchDet: ");

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
