CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Staff" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Staff {

	private static String TABLE_NAME = "STAFF";

	private static String SEQUENCE_KEY_COLUMN_NAME = "STAFF_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 11;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 12;

	private static String INSERT_STATEMENT = "INSERT INTO STAFF(STAFF_SK,STAFF_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,TZ_NAME,STAFF_EMPLT_STATUS_TYP_NAME,STAFF_EMPLT_STATUS_CHANGE_DATE,STAFF_TIN_QLFR,STAFF_TIN,STAFF_VV_ID,STAFF_VV_ID_QLFR,STAFF_NPI,STAFF_API,STAFF_EMPLT_CLS_NAME,STAFF_UNION_MEMBER_IND,STAFF_FIRST_NAME,STAFF_MIDDLE_NAME,STAFF_MAIDEN_NAME,STAFF_LAST_NAME,STAFF_NICKNAME,STAFF_EMAIL,STAFF_EMAIL_OTHER,STAFF_LOC,STAFF_PREFD_CONT_MTHD,STAFF_TERM_DATE,STAFF_HIRE_DATE,STAFF_LAST_RAISE_DATE,STAFF_MRTL_STATUS_NAME,STAFF_DOB,STAFF_GENDER_TYP_NAME,STAFF_RACE_ETHNICITY_CODE,STAFF_EDUCATION,STAFF_RELIGION_CODE,STAFF_TRANSPORT_MTHD,STAFF_TRAVEL_RADIUS,STAFF_ON_OK_IND,STAFF_LI_OK_IND,STAFF_TEAM,STAFF_MILITARY_REC,STAFF_RFRL_TYP,STAFF_RFRL_NAME,STAFF_RFRL_INSTITUTION,STAFF_RFRL_CMNT,STAFF_POSITION_NAME,STAFF_RFRL_TYP_NAME,STAFF_LAST_HIRE_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getStaff(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Staff: getStaff: ");

			try {

					String selectPattern = "SELECT STAFF_SK,STAFF_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,TZ_NAME,STAFF_EMPLT_STATUS_TYP_NAME,STAFF_EMPLT_STATUS_CHANGE_DATE,STAFF_TIN_QLFR,STAFF_TIN,STAFF_VV_ID,STAFF_VV_ID_QLFR,STAFF_NPI,STAFF_API,STAFF_EMPLT_CLS_NAME,STAFF_UNION_MEMBER_IND,STAFF_FIRST_NAME,STAFF_MIDDLE_NAME,STAFF_MAIDEN_NAME,STAFF_LAST_NAME,STAFF_NICKNAME,STAFF_EMAIL,STAFF_EMAIL_OTHER,STAFF_LOC,STAFF_PREFD_CONT_MTHD,STAFF_TERM_DATE,STAFF_HIRE_DATE,STAFF_LAST_RAISE_DATE,STAFF_MRTL_STATUS_NAME,STAFF_DOB,STAFF_GENDER_TYP_NAME,STAFF_RACE_ETHNICITY_CODE,STAFF_EDUCATION,STAFF_RELIGION_CODE,STAFF_TRANSPORT_MTHD,STAFF_TRAVEL_RADIUS,STAFF_ON_OK_IND,STAFF_LI_OK_IND,STAFF_TEAM,STAFF_MILITARY_REC,STAFF_RFRL_TYP,STAFF_RFRL_NAME,STAFF_RFRL_INSTITUTION,STAFF_RFRL_CMNT,STAFF_POSITION_NAME,STAFF_RFRL_TYP_NAME,STAFF_LAST_HIRE_DATE FROM STAFF %s";

					String whereClause = "WHERE STAFF_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getStaff(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Staff: getStaff: ");

			try {

					String sql = String.format("SELECT STAFF_SK,STAFF_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,TZ_NAME,STAFF_EMPLT_STATUS_TYP_NAME,STAFF_EMPLT_STATUS_CHANGE_DATE,STAFF_TIN_QLFR,STAFF_TIN,STAFF_VV_ID,STAFF_VV_ID_QLFR,STAFF_NPI,STAFF_API,STAFF_EMPLT_CLS_NAME,STAFF_UNION_MEMBER_IND,STAFF_FIRST_NAME,STAFF_MIDDLE_NAME,STAFF_MAIDEN_NAME,STAFF_LAST_NAME,STAFF_NICKNAME,STAFF_EMAIL,STAFF_EMAIL_OTHER,STAFF_LOC,STAFF_PREFD_CONT_MTHD,STAFF_TERM_DATE,STAFF_HIRE_DATE,STAFF_LAST_RAISE_DATE,STAFF_MRTL_STATUS_NAME,STAFF_DOB,STAFF_GENDER_TYP_NAME,STAFF_RACE_ETHNICITY_CODE,STAFF_EDUCATION,STAFF_RELIGION_CODE,STAFF_TRANSPORT_MTHD,STAFF_TRAVEL_RADIUS,STAFF_ON_OK_IND,STAFF_LI_OK_IND,STAFF_TEAM,STAFF_MILITARY_REC,STAFF_RFRL_TYP,STAFF_RFRL_NAME,STAFF_RFRL_INSTITUTION,STAFF_RFRL_CMNT,STAFF_POSITION_NAME,STAFF_RFRL_TYP_NAME,STAFF_LAST_HIRE_DATE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertStaff(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Staff: insertStaff: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateStaff(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Staff: updateStaff: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteStaff(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Staff: deleteStaff: ");

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
