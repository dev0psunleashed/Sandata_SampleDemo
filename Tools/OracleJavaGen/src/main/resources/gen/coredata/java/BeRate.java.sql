CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeRate" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeRate {

	private static String TABLE_NAME = "BE_RATE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_RATE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_RATE(BE_RATE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,BE_RATE_EFF_DATE,BE_RATE_TERM_DATE,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BE_RATE_AMT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeRate(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRate: getBeRate: ");

			try {

					String selectPattern = "SELECT BE_RATE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,BE_RATE_EFF_DATE,BE_RATE_TERM_DATE,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BE_RATE_AMT FROM BE_RATE %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeRate(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRate: getBeRate: ");

			try {

					String sql = String.format("SELECT BE_RATE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_LOB_ID,BE_LOC_ID,BE_RATE_EFF_DATE,BE_RATE_TERM_DATE,SVC_NAME,RATE_SUB_TYP_NAME,RATE_TYP_NAME,RATE_QLFR_CODE,SVC_UNIT_NAME,BE_RATE_AMT FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeRate(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRate: insertBeRate: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeRate(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRate: updateBeRate: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeRate(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeRate: deleteBeRate: ");

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
