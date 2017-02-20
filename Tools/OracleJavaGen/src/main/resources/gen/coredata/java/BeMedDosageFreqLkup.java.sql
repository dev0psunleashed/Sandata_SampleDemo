CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeMedDosageFreqLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeMedDosageFreqLkup {

	private static String TABLE_NAME = "BE_MED_DOSAGE_FREQ_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_MED_DOSAGE_FREQ_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_MED_DOSAGE_FREQ_LKUP(BE_MED_DOSAGE_FREQ_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,MED_DOSAGE_FREQ_NAME,MED_DOSAGE_FREQ_DESC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeMedDosageFreqLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("BeMedDosageFreqLkup: getBeMedDosageFreqLkup: ");

			try {

					String sql = String.format("SELECT BE_MED_DOSAGE_FREQ_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,MED_DOSAGE_FREQ_NAME,MED_DOSAGE_FREQ_DESC FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeMedDosageFreqLkup(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeMedDosageFreqLkup: getBeMedDosageFreqLkup: ");

			try {

					String selectPattern = "SELECT BE_MED_DOSAGE_FREQ_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,MED_DOSAGE_FREQ_NAME,MED_DOSAGE_FREQ_DESC FROM BE_MED_DOSAGE_FREQ_LKUP %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeMedDosageFreqLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeMedDosageFreqLkup: getBeMedDosageFreqLkup: ");

			try {

					String sql = String.format("SELECT BE_MED_DOSAGE_FREQ_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,MED_DOSAGE_FREQ_NAME,MED_DOSAGE_FREQ_DESC FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeMedDosageFreqLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeMedDosageFreqLkup: insertBeMedDosageFreqLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeMedDosageFreqLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeMedDosageFreqLkup: updateBeMedDosageFreqLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeMedDosageFreqLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeMedDosageFreqLkup: deleteBeMedDosageFreqLkup: ");

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
