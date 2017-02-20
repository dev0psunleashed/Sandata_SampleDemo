CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeContDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeContDet {

	private static String TABLE_NAME = "BE_CONT_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_CONT_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO BE_CONT_DET(BE_CONT_DET_SK,BE_CONT_DET_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_CONT_DET_TYP,BE_OTHER_CONT_NAME,BE_OTHER_CONT_TITLE,BE_OTHER_ADDR_USE_FOR_MAIL_IND,BE_OTHER_ADDR1,BE_OTHER_ADDR2,BE_OTHER_CITY,BE_OTHER_STATE,BE_OTHER_COUNTY,BE_OTHER_PSTL_CODE,BE_OTHER_ZIP4,BE_OTHER_PHONE,BE_OTHER_PHONE_EXT,BE_OTHER_PHONE_QLFR,BE_OTHER_PHONE_1,BE_OTHER_PHONE_1_EXT,BE_OTHER_PHONE_1_QLFR,BE_OTHER_PHONE_2,BE_OTHER_PHONE_2_EXT,BE_OTHER_PHONE_2_QLFR,BE_OTHER_EMAIL,BE_OTHER_EMAIL_QLFR,BE_OTHER_EMAIL_1,BE_OTHER_EMAIL_1_QLFR,BE_OTHER_EMAIL_2,BE_OTHER_EMAIL_2_QLFR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeContDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeContDet: getBeContDet: ");

			try {

					String selectPattern = "SELECT BE_CONT_DET_SK,BE_CONT_DET_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_CONT_DET_TYP,BE_OTHER_CONT_NAME,BE_OTHER_CONT_TITLE,BE_OTHER_ADDR_USE_FOR_MAIL_IND,BE_OTHER_ADDR1,BE_OTHER_ADDR2,BE_OTHER_CITY,BE_OTHER_STATE,BE_OTHER_COUNTY,BE_OTHER_PSTL_CODE,BE_OTHER_ZIP4,BE_OTHER_PHONE,BE_OTHER_PHONE_EXT,BE_OTHER_PHONE_QLFR,BE_OTHER_PHONE_1,BE_OTHER_PHONE_1_EXT,BE_OTHER_PHONE_1_QLFR,BE_OTHER_PHONE_2,BE_OTHER_PHONE_2_EXT,BE_OTHER_PHONE_2_QLFR,BE_OTHER_EMAIL,BE_OTHER_EMAIL_QLFR,BE_OTHER_EMAIL_1,BE_OTHER_EMAIL_1_QLFR,BE_OTHER_EMAIL_2,BE_OTHER_EMAIL_2_QLFR FROM BE_CONT_DET %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeContDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeContDet: getBeContDet: ");

			try {

					String sql = String.format("SELECT BE_CONT_DET_SK,BE_CONT_DET_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,BE_CONT_DET_TYP,BE_OTHER_CONT_NAME,BE_OTHER_CONT_TITLE,BE_OTHER_ADDR_USE_FOR_MAIL_IND,BE_OTHER_ADDR1,BE_OTHER_ADDR2,BE_OTHER_CITY,BE_OTHER_STATE,BE_OTHER_COUNTY,BE_OTHER_PSTL_CODE,BE_OTHER_ZIP4,BE_OTHER_PHONE,BE_OTHER_PHONE_EXT,BE_OTHER_PHONE_QLFR,BE_OTHER_PHONE_1,BE_OTHER_PHONE_1_EXT,BE_OTHER_PHONE_1_QLFR,BE_OTHER_PHONE_2,BE_OTHER_PHONE_2_EXT,BE_OTHER_PHONE_2_QLFR,BE_OTHER_EMAIL,BE_OTHER_EMAIL_QLFR,BE_OTHER_EMAIL_1,BE_OTHER_EMAIL_1_QLFR,BE_OTHER_EMAIL_2,BE_OTHER_EMAIL_2_QLFR FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeContDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeContDet: insertBeContDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeContDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeContDet: updateBeContDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeContDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeContDet: deleteBeContDet: ");

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
