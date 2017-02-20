CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeCredential" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeCredential {

	private static String TABLE_NAME = "BE_CREDENTIAL";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_CREDENTIAL_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO BE_CREDENTIAL(BE_CREDENTIAL_SK,BE_CREDENTIAL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_CREDENTIAL_NAME,BE_CREDENTIAL_DESC,BE_CREDENTIAL_ORG,CREDENTIAL_ORG_CTF_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeCredential(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCredential: getBeCredential: ");

			try {

					String selectPattern = "SELECT BE_CREDENTIAL_SK,BE_CREDENTIAL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_CREDENTIAL_NAME,BE_CREDENTIAL_DESC,BE_CREDENTIAL_ORG,CREDENTIAL_ORG_CTF_ID FROM BE_CREDENTIAL %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCredential(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCredential: getBeCredential: ");

			try {

					String sql = String.format("SELECT BE_CREDENTIAL_SK,BE_CREDENTIAL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_CREDENTIAL_NAME,BE_CREDENTIAL_DESC,BE_CREDENTIAL_ORG,CREDENTIAL_ORG_CTF_ID FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeCredential(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCredential: insertBeCredential: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeCredential(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCredential: updateBeCredential: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeCredential(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCredential: deleteBeCredential: ");

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
