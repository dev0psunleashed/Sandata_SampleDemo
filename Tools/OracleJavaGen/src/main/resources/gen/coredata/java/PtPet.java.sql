CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtPet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtPet {

	private static String TABLE_NAME = "PT_PET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_PET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 7;

	private static int CURR_REC_IND_INDEX = 12;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PT_PET(PT_PET_SK,PT_PETS_ID,PT_ENV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CHANGE_REASON_MEMO,CURR_REC_IND,PET_TYP,SIZE_OF_PET,AGGRESSIVE_PET_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtPet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPet: getPtPet: ");

			try {

					String selectPattern = "SELECT PT_PET_SK,PT_PETS_ID,PT_ENV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CHANGE_REASON_MEMO,CURR_REC_IND,PET_TYP,SIZE_OF_PET,AGGRESSIVE_PET_IND FROM PT_PET %s";

					String whereClause = "WHERE PT_ENV_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtPet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPet: getPtPet: ");

			try {

					String sql = String.format("SELECT PT_PET_SK,PT_PETS_ID,PT_ENV_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,CHANGE_REASON_MEMO,CURR_REC_IND,PET_TYP,SIZE_OF_PET,AGGRESSIVE_PET_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtPet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPet: insertPtPet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtPet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPet: updatePtPet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtPet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPet: deletePtPet: ");

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
