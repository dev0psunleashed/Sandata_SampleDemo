CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Rfrl" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Rfrl {

	private static String TABLE_NAME = "RFRL";

	private static String SEQUENCE_KEY_COLUMN_NAME = "RFRL_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO RFRL(RFRL_SK,RFRL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,RFRL_TYP_NAME,HCP_NPI,RFRL_NAME,RFRL_RECEIVAL_MTHD,RFRL_PHONE,RFRL_EMAIL,RFRL_START_DATE,RFRL_END_DATE,RFRL_SRC_NAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getRfrl(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Rfrl: getRfrl: ");

			try {

					String selectPattern = "SELECT RFRL_SK,RFRL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,RFRL_TYP_NAME,HCP_NPI,RFRL_NAME,RFRL_RECEIVAL_MTHD,RFRL_PHONE,RFRL_EMAIL,RFRL_START_DATE,RFRL_END_DATE,RFRL_SRC_NAME FROM RFRL %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getRfrl(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Rfrl: getRfrl: ");

			try {

					String sql = String.format("SELECT RFRL_SK,RFRL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,RFRL_TYP_NAME,HCP_NPI,RFRL_NAME,RFRL_RECEIVAL_MTHD,RFRL_PHONE,RFRL_EMAIL,RFRL_START_DATE,RFRL_END_DATE,RFRL_SRC_NAME FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertRfrl(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Rfrl: insertRfrl: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateRfrl(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Rfrl: updateRfrl: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteRfrl(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Rfrl: deleteRfrl: ");

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
