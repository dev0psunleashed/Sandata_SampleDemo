CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PdContDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PdContDet {

	private static String TABLE_NAME = "PD_CONT_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PD_CONT_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PD_CONT_DET(PD_CONT_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,PT_ID,BE_ID,PD_CONT_DET_TYP_NAME,CONT_REL,PD_FIRST_NAME,PD_MIDDLE_NAME,PD_LAST_NAME,PD_SUFFIX_NAME,PD_ADDR1,PD_ADDR2,PD_CITY,PD_STATE,PD_PSTL_CODE,PD_HOME_PHONE,PD_WORK_PHONE,PD_MOBILE_PHONE,PD_PHONE_OTHER,PD_PHONE_OTHER_1,PD_FAX,PD_EMAIL,PD_EMAIL_OTHER_1,PD_EMAIL_OTHER_2,PD_EMAIL_OTHER_3,PD_ZIP4) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPdContDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PdContDet: getPdContDet: ");

			try {

					String selectPattern = "SELECT PD_CONT_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,PT_ID,BE_ID,PD_CONT_DET_TYP_NAME,CONT_REL,PD_FIRST_NAME,PD_MIDDLE_NAME,PD_LAST_NAME,PD_SUFFIX_NAME,PD_ADDR1,PD_ADDR2,PD_CITY,PD_STATE,PD_PSTL_CODE,PD_HOME_PHONE,PD_WORK_PHONE,PD_MOBILE_PHONE,PD_PHONE_OTHER,PD_PHONE_OTHER_1,PD_FAX,PD_EMAIL,PD_EMAIL_OTHER_1,PD_EMAIL_OTHER_2,PD_EMAIL_OTHER_3,PD_ZIP4 FROM PD_CONT_DET %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPdContDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PdContDet: getPdContDet: ");

			try {

					String sql = String.format("SELECT PD_CONT_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,PT_ID,BE_ID,PD_CONT_DET_TYP_NAME,CONT_REL,PD_FIRST_NAME,PD_MIDDLE_NAME,PD_LAST_NAME,PD_SUFFIX_NAME,PD_ADDR1,PD_ADDR2,PD_CITY,PD_STATE,PD_PSTL_CODE,PD_HOME_PHONE,PD_WORK_PHONE,PD_MOBILE_PHONE,PD_PHONE_OTHER,PD_PHONE_OTHER_1,PD_FAX,PD_EMAIL,PD_EMAIL_OTHER_1,PD_EMAIL_OTHER_2,PD_EMAIL_OTHER_3,PD_ZIP4 FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPdContDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PdContDet: insertPdContDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePdContDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PdContDet: updatePdContDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePdContDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PdContDet: deletePdContDet: ");

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
