CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtSftyMeasure" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtSftyMeasure {

	private static String TABLE_NAME = "PT_SFTY_MEASURE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_SFTY_MEASURE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO PT_SFTY_MEASURE(PT_SFTY_MEASURE_SK,PT_SFTY_MEASURE_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PT_ID,BE_ID,SFTY_MEASURE_NAME,SFTY_MEASURE_DESC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtSftyMeasure(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtSftyMeasure: getPtSftyMeasure: ");

			try {

					String selectPattern = "SELECT PT_SFTY_MEASURE_SK,PT_SFTY_MEASURE_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PT_ID,BE_ID,SFTY_MEASURE_NAME,SFTY_MEASURE_DESC FROM PT_SFTY_MEASURE %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtSftyMeasure(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtSftyMeasure: getPtSftyMeasure: ");

			try {

					String sql = String.format("SELECT PT_SFTY_MEASURE_SK,PT_SFTY_MEASURE_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PT_ID,BE_ID,SFTY_MEASURE_NAME,SFTY_MEASURE_DESC FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtSftyMeasure(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtSftyMeasure: insertPtSftyMeasure: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtSftyMeasure(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtSftyMeasure: updatePtSftyMeasure: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtSftyMeasure(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtSftyMeasure: deletePtSftyMeasure: ");

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
