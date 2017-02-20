CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Provider" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Provider {

	private static String TABLE_NAME = "PROVIDER";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PROVIDER_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO PROVIDER(PROVIDER_SK,PROVIDER_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PROVIDER_FIRST_NAME,PROVIDER_MIDDLE_NAME,PROVIDER_LAST_NAME,PROVIDER_ORG_NAME,PROVIDER_UPIN,PROVIDER_NPI,PROVIDER_LICENSE_NUM,PROVIDER_LICENSE_STATE,PROVIDER_LICENSE_EXPR,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ZIP4,PROVIDER_PHONE,PROVIDER_FAX,PROVIDER_CONT_NAME,PROVIDER_SIG_IND,PROVIDER_TAXONOMY_CODE,MEDICARE_ASSIGNMENT_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getProvider(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Provider: getProvider: ");

			try {

					String selectPattern = "SELECT PROVIDER_SK,PROVIDER_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PROVIDER_FIRST_NAME,PROVIDER_MIDDLE_NAME,PROVIDER_LAST_NAME,PROVIDER_ORG_NAME,PROVIDER_UPIN,PROVIDER_NPI,PROVIDER_LICENSE_NUM,PROVIDER_LICENSE_STATE,PROVIDER_LICENSE_EXPR,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ZIP4,PROVIDER_PHONE,PROVIDER_FAX,PROVIDER_CONT_NAME,PROVIDER_SIG_IND,PROVIDER_TAXONOMY_CODE,MEDICARE_ASSIGNMENT_CODE FROM PROVIDER %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getProvider(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Provider: getProvider: ");

			try {

					String sql = String.format("SELECT PROVIDER_SK,PROVIDER_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PROVIDER_FIRST_NAME,PROVIDER_MIDDLE_NAME,PROVIDER_LAST_NAME,PROVIDER_ORG_NAME,PROVIDER_UPIN,PROVIDER_NPI,PROVIDER_LICENSE_NUM,PROVIDER_LICENSE_STATE,PROVIDER_LICENSE_EXPR,PROVIDER_ADDR1,PROVIDER_ADDR2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_PSTL_CODE,PROVIDER_ZIP4,PROVIDER_PHONE,PROVIDER_FAX,PROVIDER_CONT_NAME,PROVIDER_SIG_IND,PROVIDER_TAXONOMY_CODE,MEDICARE_ASSIGNMENT_CODE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertProvider(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Provider: insertProvider: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateProvider(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Provider: updateProvider: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteProvider(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Provider: deleteProvider: ");

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
