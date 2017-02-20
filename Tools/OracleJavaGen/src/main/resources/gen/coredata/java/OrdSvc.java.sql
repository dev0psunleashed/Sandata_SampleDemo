CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "OrdSvc" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrdSvc {

	private static String TABLE_NAME = "ORD_SVC";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ORD_SVC_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO ORD_SVC(ORD_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ORD_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,ORD_SVC_RATE_AMT,ORD_SVC_START_DATE,ORD_SVC_END_DATE,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_SVC_LMT,ORD_SVC_LMT_DAY_1,ORD_SVC_LMT_START_TIME_DAY_1,ORD_SVC_LMT_END_TIME_DAY_1,ORD_SVC_LMT_DAY_2,ORD_SVC_LMT_START_TIME_DAY_2,ORD_SVC_LMT_END_TIME_DAY_2,ORD_SVC_LMT_DAY_3,ORD_SVC_LMT_START_TIME_DAY_3,ORD_SVC_LMT_END_TIME_DAY_3,ORD_SVC_LMT_DAY_4,ORD_SVC_LMT_START_TIME_DAY_4,ORD_SVC_LMT_END_TIME_DAY_4,ORD_SVC_LMT_DAY_5,ORD_SVC_LMT_START_TIME_DAY_5,ORD_SVC_LMT_END_TIME_DAY_5,ORD_SVC_LMT_DAY_6,ORD_SVC_LMT_START_TIME_DAY_6,ORD_SVC_LMT_END_TIME_DAY_6,ORD_SVC_LMT_DAY_7,ORD_SVC_LMT_START_TIME_DAY_7,ORD_SVC_LMT_END_TIME_DAY_7,PAYER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getOrdSvc(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("OrdSvc: getOrdSvc: ");

			try {

					String sql = String.format("SELECT ORD_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ORD_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,ORD_SVC_RATE_AMT,ORD_SVC_START_DATE,ORD_SVC_END_DATE,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_SVC_LMT,ORD_SVC_LMT_DAY_1,ORD_SVC_LMT_START_TIME_DAY_1,ORD_SVC_LMT_END_TIME_DAY_1,ORD_SVC_LMT_DAY_2,ORD_SVC_LMT_START_TIME_DAY_2,ORD_SVC_LMT_END_TIME_DAY_2,ORD_SVC_LMT_DAY_3,ORD_SVC_LMT_START_TIME_DAY_3,ORD_SVC_LMT_END_TIME_DAY_3,ORD_SVC_LMT_DAY_4,ORD_SVC_LMT_START_TIME_DAY_4,ORD_SVC_LMT_END_TIME_DAY_4,ORD_SVC_LMT_DAY_5,ORD_SVC_LMT_START_TIME_DAY_5,ORD_SVC_LMT_END_TIME_DAY_5,ORD_SVC_LMT_DAY_6,ORD_SVC_LMT_START_TIME_DAY_6,ORD_SVC_LMT_END_TIME_DAY_6,ORD_SVC_LMT_DAY_7,ORD_SVC_LMT_START_TIME_DAY_7,ORD_SVC_LMT_END_TIME_DAY_7,PAYER_ID FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getOrdSvc(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("OrdSvc: getOrdSvc: ");

			try {

					String selectPattern = "SELECT ORD_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ORD_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,ORD_SVC_RATE_AMT,ORD_SVC_START_DATE,ORD_SVC_END_DATE,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_SVC_LMT,ORD_SVC_LMT_DAY_1,ORD_SVC_LMT_START_TIME_DAY_1,ORD_SVC_LMT_END_TIME_DAY_1,ORD_SVC_LMT_DAY_2,ORD_SVC_LMT_START_TIME_DAY_2,ORD_SVC_LMT_END_TIME_DAY_2,ORD_SVC_LMT_DAY_3,ORD_SVC_LMT_START_TIME_DAY_3,ORD_SVC_LMT_END_TIME_DAY_3,ORD_SVC_LMT_DAY_4,ORD_SVC_LMT_START_TIME_DAY_4,ORD_SVC_LMT_END_TIME_DAY_4,ORD_SVC_LMT_DAY_5,ORD_SVC_LMT_START_TIME_DAY_5,ORD_SVC_LMT_END_TIME_DAY_5,ORD_SVC_LMT_DAY_6,ORD_SVC_LMT_START_TIME_DAY_6,ORD_SVC_LMT_END_TIME_DAY_6,ORD_SVC_LMT_DAY_7,ORD_SVC_LMT_START_TIME_DAY_7,ORD_SVC_LMT_END_TIME_DAY_7,PAYER_ID FROM ORD_SVC %s";

					String whereClause = "WHERE ORD_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getOrdSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("OrdSvc: getOrdSvc: ");

			try {

					String sql = String.format("SELECT ORD_SVC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ORD_SK,BE_ID,SVC_NAME,BILLING_CODE,RATE_TYP_NAME,RATE_QLFR_CODE,ORD_SVC_RATE_AMT,ORD_SVC_START_DATE,ORD_SVC_END_DATE,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_SVC_LMT,ORD_SVC_LMT_DAY_1,ORD_SVC_LMT_START_TIME_DAY_1,ORD_SVC_LMT_END_TIME_DAY_1,ORD_SVC_LMT_DAY_2,ORD_SVC_LMT_START_TIME_DAY_2,ORD_SVC_LMT_END_TIME_DAY_2,ORD_SVC_LMT_DAY_3,ORD_SVC_LMT_START_TIME_DAY_3,ORD_SVC_LMT_END_TIME_DAY_3,ORD_SVC_LMT_DAY_4,ORD_SVC_LMT_START_TIME_DAY_4,ORD_SVC_LMT_END_TIME_DAY_4,ORD_SVC_LMT_DAY_5,ORD_SVC_LMT_START_TIME_DAY_5,ORD_SVC_LMT_END_TIME_DAY_5,ORD_SVC_LMT_DAY_6,ORD_SVC_LMT_START_TIME_DAY_6,ORD_SVC_LMT_END_TIME_DAY_6,ORD_SVC_LMT_DAY_7,ORD_SVC_LMT_START_TIME_DAY_7,ORD_SVC_LMT_END_TIME_DAY_7,PAYER_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertOrdSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("OrdSvc: insertOrdSvc: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateOrdSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("OrdSvc: updateOrdSvc: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteOrdSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("OrdSvc: deleteOrdSvc: ");

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
