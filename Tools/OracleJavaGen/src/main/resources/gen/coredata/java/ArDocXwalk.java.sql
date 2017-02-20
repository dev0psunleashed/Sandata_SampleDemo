CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "ArDocXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ArDocXwalk {

	private static String TABLE_NAME = "AR_DOC_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "AR_DOC_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 9;

	private static String INSERT_STATEMENT = "INSERT INTO AR_DOC_XWALK(AR_DOC_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,DOC_ID,AR_SK,DOC_CLAS_NAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getArDocXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArDocXwalk: getArDocXwalk: ");

			try {

					String selectPattern = "SELECT AR_DOC_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,DOC_ID,AR_SK,DOC_CLAS_NAME FROM AR_DOC_XWALK %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getArDocXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArDocXwalk: getArDocXwalk: ");

			try {

					String sql = String.format("SELECT AR_DOC_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,DOC_ID,AR_SK,DOC_CLAS_NAME FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertArDocXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArDocXwalk: insertArDocXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateArDocXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArDocXwalk: updateArDocXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteArDocXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ArDocXwalk: deleteArDocXwalk: ");

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
