CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Ord" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Ord {

	private static String TABLE_NAME = "ORD";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ORD_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO ORD(ORD_SK,ORD_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,ORD_ISSUED_DATE,ORD_START_TMSTP,ORD_END_TMSTP,ORD_COMMENT,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_LMT,ORD_LMT_TOTAL,ORD_LMT_DAY_1,ORD_LMT_START_TIME_DAY_1,ORD_LMT_END_TIME_DAY_1,ORD_LMT_DAY_2,ORD_LMT_START_TIME_DAY_2,ORD_LMT_END_TIME_DAY_2,ORD_LMT_DAY_3,ORD_LMT_START_TIME_DAY_3,ORD_LMT_END_TIME_DAY_3,ORD_LMT_DAY_4,ORD_LMT_START_TIME_DAY_4,ORD_LMT_END_TIME_DAY_4,ORD_LMT_DAY_5,ORD_LMT_START_TIME_DAY_5,ORD_LMT_END_TIME_DAY_5,ORD_LMT_DAY_6,ORD_LMT_START_TIME_DAY_6,ORD_LMT_END_TIME_DAY_6,ORD_LMT_DAY_7,ORD_LMT_START_TIME_DAY_7,ORD_LMT_END_TIME_DAY_7,CONTR_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getOrd(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Ord: getOrd: ");

			try {

					String selectPattern = "SELECT ORD_SK,ORD_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,ORD_ISSUED_DATE,ORD_START_TMSTP,ORD_END_TMSTP,ORD_COMMENT,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_LMT,ORD_LMT_TOTAL,ORD_LMT_DAY_1,ORD_LMT_START_TIME_DAY_1,ORD_LMT_END_TIME_DAY_1,ORD_LMT_DAY_2,ORD_LMT_START_TIME_DAY_2,ORD_LMT_END_TIME_DAY_2,ORD_LMT_DAY_3,ORD_LMT_START_TIME_DAY_3,ORD_LMT_END_TIME_DAY_3,ORD_LMT_DAY_4,ORD_LMT_START_TIME_DAY_4,ORD_LMT_END_TIME_DAY_4,ORD_LMT_DAY_5,ORD_LMT_START_TIME_DAY_5,ORD_LMT_END_TIME_DAY_5,ORD_LMT_DAY_6,ORD_LMT_START_TIME_DAY_6,ORD_LMT_END_TIME_DAY_6,ORD_LMT_DAY_7,ORD_LMT_START_TIME_DAY_7,ORD_LMT_END_TIME_DAY_7,CONTR_ID FROM ORD %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getOrd(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Ord: getOrd: ");

			try {

					String sql = String.format("SELECT ORD_SK,ORD_PAR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,ORD_ISSUED_DATE,ORD_START_TMSTP,ORD_END_TMSTP,ORD_COMMENT,ORD_LMT_TYP_NAME,ORD_SVC_UNIT_NAME,ORD_LMT,ORD_LMT_TOTAL,ORD_LMT_DAY_1,ORD_LMT_START_TIME_DAY_1,ORD_LMT_END_TIME_DAY_1,ORD_LMT_DAY_2,ORD_LMT_START_TIME_DAY_2,ORD_LMT_END_TIME_DAY_2,ORD_LMT_DAY_3,ORD_LMT_START_TIME_DAY_3,ORD_LMT_END_TIME_DAY_3,ORD_LMT_DAY_4,ORD_LMT_START_TIME_DAY_4,ORD_LMT_END_TIME_DAY_4,ORD_LMT_DAY_5,ORD_LMT_START_TIME_DAY_5,ORD_LMT_END_TIME_DAY_5,ORD_LMT_DAY_6,ORD_LMT_START_TIME_DAY_6,ORD_LMT_END_TIME_DAY_6,ORD_LMT_DAY_7,ORD_LMT_START_TIME_DAY_7,ORD_LMT_END_TIME_DAY_7,CONTR_ID FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertOrd(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Ord: insertOrd: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateOrd(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Ord: updateOrd: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteOrd(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Ord: deleteOrd: ");

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
