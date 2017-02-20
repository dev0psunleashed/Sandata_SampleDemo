CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtStructBrrDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtStructBrrDet {

	private static String TABLE_NAME = "PT_STRUCT_BRR_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_STRUCT_BRR_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 7;

	private static int CURR_REC_IND_INDEX = 11;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 12;

	private static String INSERT_STATEMENT = "INSERT INTO PT_STRUCT_BRR_DET(PT_STRUCT_BRR_DET_SK,PT_STRUCT_BRR_DET_ID,PT_ENV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,STRUCT_BRR_NAME,STRUCT_BRR_DESC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtStructBrrDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtStructBrrDet: getPtStructBrrDet: ");

			try {

					String selectPattern = "SELECT PT_STRUCT_BRR_DET_SK,PT_STRUCT_BRR_DET_ID,PT_ENV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,STRUCT_BRR_NAME,STRUCT_BRR_DESC FROM PT_STRUCT_BRR_DET %s";

					String whereClause = "WHERE PT_ENV_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtStructBrrDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtStructBrrDet: getPtStructBrrDet: ");

			try {

					String sql = String.format("SELECT PT_STRUCT_BRR_DET_SK,PT_STRUCT_BRR_DET_ID,PT_ENV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,STRUCT_BRR_NAME,STRUCT_BRR_DESC FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtStructBrrDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtStructBrrDet: insertPtStructBrrDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtStructBrrDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtStructBrrDet: updatePtStructBrrDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtStructBrrDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtStructBrrDet: deletePtStructBrrDet: ");

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
