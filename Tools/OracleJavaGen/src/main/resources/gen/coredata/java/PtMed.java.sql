CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtMed" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtMed {

	private static String TABLE_NAME = "PT_MED";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_MED_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PT_MED(PT_MED_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PT_ID,BE_ID,MED_GENERIC_NAME,MED_DOSAGE_STRG_NAME,MED_DOSAGE_FORM_NAME,MED_DOSAGE_FREQ_NAME,FREQ_TYP_LKUP_SK,MED_ROUTE_NAME,MED_CLAS_NAME,MED_START_DATE,MED_END_DATE,MED_RANK,AUTHREIMBURSE,MED_PRN_IND,MED_PRN_REASON,MED_RXCUI,MED_CONTRAINDICATION,MED_COMMENT,MED_INFO_SHEET) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtMed(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtMed: getPtMed: ");

			try {

					String selectPattern = "SELECT PT_MED_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PT_ID,BE_ID,MED_GENERIC_NAME,MED_DOSAGE_STRG_NAME,MED_DOSAGE_FORM_NAME,MED_DOSAGE_FREQ_NAME,FREQ_TYP_LKUP_SK,MED_ROUTE_NAME,MED_CLAS_NAME,MED_START_DATE,MED_END_DATE,MED_RANK,AUTHREIMBURSE,MED_PRN_IND,MED_PRN_REASON,MED_RXCUI,MED_CONTRAINDICATION,MED_COMMENT,MED_INFO_SHEET FROM PT_MED %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtMed(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtMed: getPtMed: ");

			try {

					String sql = String.format("SELECT PT_MED_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PT_ID,BE_ID,MED_GENERIC_NAME,MED_DOSAGE_STRG_NAME,MED_DOSAGE_FORM_NAME,MED_DOSAGE_FREQ_NAME,FREQ_TYP_LKUP_SK,MED_ROUTE_NAME,MED_CLAS_NAME,MED_START_DATE,MED_END_DATE,MED_RANK,AUTHREIMBURSE,MED_PRN_IND,MED_PRN_REASON,MED_RXCUI,MED_CONTRAINDICATION,MED_COMMENT,MED_INFO_SHEET FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtMed(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtMed: insertPtMed: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtMed(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtMed: updatePtMed: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtMed(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtMed: deletePtMed: ");

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
