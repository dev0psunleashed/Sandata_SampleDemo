CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PstlCodeLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PstlCodeLkup {

	private static String TABLE_NAME = "PSTL_CODE_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PSTL_CODE_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PSTL_CODE_LKUP(PSTL_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PSTL_CODE,PSTL_CODE_SRC_QLFR,PSTL_CODE_USPS_TYP_CODE,PSTL_CODE_CITY_NAME,PSTL_CODE_USPS_CITY_TYP_CODE,PSTL_CODE_COUNTY_NAME,COUNTY_FIPS_CODE,PSTL_CODE_STATE_NAME,PSTL_CODE_STATE_CODE,STATE_FIPS_CODE,PSTL_CODE_MSA_CODE,PSTL_CODE_AREA_CODE,TZ_NAME,UTC_OFFSET,PSTL_CODE_DST_IND,PSTL_CODE_COORD_LATITUDE,PSTL_CODE_COORD_LONGITIDE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPstlCodeLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeLkup: getPstlCodeLkup: ");

			try {

					String sql = String.format("SELECT PSTL_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PSTL_CODE,PSTL_CODE_SRC_QLFR,PSTL_CODE_USPS_TYP_CODE,PSTL_CODE_CITY_NAME,PSTL_CODE_USPS_CITY_TYP_CODE,PSTL_CODE_COUNTY_NAME,COUNTY_FIPS_CODE,PSTL_CODE_STATE_NAME,PSTL_CODE_STATE_CODE,STATE_FIPS_CODE,PSTL_CODE_MSA_CODE,PSTL_CODE_AREA_CODE,TZ_NAME,UTC_OFFSET,PSTL_CODE_DST_IND,PSTL_CODE_COORD_LATITUDE,PSTL_CODE_COORD_LONGITIDE FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPstlCodeLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeLkup: getPstlCodeLkup: ");

			try {

					String sql = String.format("SELECT PSTL_CODE_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PSTL_CODE,PSTL_CODE_SRC_QLFR,PSTL_CODE_USPS_TYP_CODE,PSTL_CODE_CITY_NAME,PSTL_CODE_USPS_CITY_TYP_CODE,PSTL_CODE_COUNTY_NAME,COUNTY_FIPS_CODE,PSTL_CODE_STATE_NAME,PSTL_CODE_STATE_CODE,STATE_FIPS_CODE,PSTL_CODE_MSA_CODE,PSTL_CODE_AREA_CODE,TZ_NAME,UTC_OFFSET,PSTL_CODE_DST_IND,PSTL_CODE_COORD_LATITUDE,PSTL_CODE_COORD_LONGITIDE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPstlCodeLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeLkup: insertPstlCodeLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePstlCodeLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeLkup: updatePstlCodeLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePstlCodeLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeLkup: deletePstlCodeLkup: ");

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
