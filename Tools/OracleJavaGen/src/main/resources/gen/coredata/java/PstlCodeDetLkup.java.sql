CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PstlCodeDetLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PstlCodeDetLkup {

	private static String TABLE_NAME = "PSTL_CODE_DET_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PSTL_CODE_DET_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PSTL_CODE_DET_LKUP(PSTL_CODE_DET_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PSTL_CODE,PSTL_CODE_SRC_QLFR,PSTL_CODE_ZIP4,PSTL_CODE_ZIP4_RANGE,PSTL_CODE_CITY_NAME,PSTL_CODE_REC_TYP_CODE,PSTL_CODE_CARRIER_ROUTE_ID,PSTL_CODE_STREET_PREDIR_CODE,PSTL_CODE_STREET_NAME,PSTL_CODE_STREET_SUFFIX,PSTL_CODE_STREET_POSTDIR_CODE,PSTL_CODE_PRMY_LOW_NUM,PSTL_CODE_PRMY_HIGH_NUM,PSTL_CODE_PRMY_ODD_EVEN_CODE,PSTL_CODE_ENT_NAME,PSTL_CODE_SCNDRY_ABBRV,PSTL_CODE_SCNDRY_LOW_NUM,PSTL_CODE_SCNDRY_HIGH_NUM,PSTL_CODE_SCNDRY_ODD_EVEN_CODE,PSTL_CODE_ADD_ON_LOW_SECTOR,PSTL_CODE_ADD_ON_LOW_SEGMENT,PSTL_CODE_ADD_ON_HIGH_SECTOR,PSTL_CODE_ADD_ON_HIGH_SEGMENT,PSTL_CODE_BASE_ALTERNATE_CODE,LACS_STATUS_IND,GOV_BLDG_CODE,PSTL_CODE_FIN_NUM,PSTL_CODE_STATE_CODE,COUNTY_FIPS_CODE,CONGRNL_DISTR_NUM,MUNI_CITY_STATE_KEY,URBANIZATION_CITY_STATE_KEY,PREFD_LAST_LINE_CITY_STATE_KEY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPstlCodeDetLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeDetLkup: getPstlCodeDetLkup: ");

			try {

					String sql = String.format("SELECT PSTL_CODE_DET_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PSTL_CODE,PSTL_CODE_SRC_QLFR,PSTL_CODE_ZIP4,PSTL_CODE_ZIP4_RANGE,PSTL_CODE_CITY_NAME,PSTL_CODE_REC_TYP_CODE,PSTL_CODE_CARRIER_ROUTE_ID,PSTL_CODE_STREET_PREDIR_CODE,PSTL_CODE_STREET_NAME,PSTL_CODE_STREET_SUFFIX,PSTL_CODE_STREET_POSTDIR_CODE,PSTL_CODE_PRMY_LOW_NUM,PSTL_CODE_PRMY_HIGH_NUM,PSTL_CODE_PRMY_ODD_EVEN_CODE,PSTL_CODE_ENT_NAME,PSTL_CODE_SCNDRY_ABBRV,PSTL_CODE_SCNDRY_LOW_NUM,PSTL_CODE_SCNDRY_HIGH_NUM,PSTL_CODE_SCNDRY_ODD_EVEN_CODE,PSTL_CODE_ADD_ON_LOW_SECTOR,PSTL_CODE_ADD_ON_LOW_SEGMENT,PSTL_CODE_ADD_ON_HIGH_SECTOR,PSTL_CODE_ADD_ON_HIGH_SEGMENT,PSTL_CODE_BASE_ALTERNATE_CODE,LACS_STATUS_IND,GOV_BLDG_CODE,PSTL_CODE_FIN_NUM,PSTL_CODE_STATE_CODE,COUNTY_FIPS_CODE,CONGRNL_DISTR_NUM,MUNI_CITY_STATE_KEY,URBANIZATION_CITY_STATE_KEY,PREFD_LAST_LINE_CITY_STATE_KEY FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPstlCodeDetLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeDetLkup: getPstlCodeDetLkup: ");

			try {

					String sql = String.format("SELECT PSTL_CODE_DET_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PSTL_CODE,PSTL_CODE_SRC_QLFR,PSTL_CODE_ZIP4,PSTL_CODE_ZIP4_RANGE,PSTL_CODE_CITY_NAME,PSTL_CODE_REC_TYP_CODE,PSTL_CODE_CARRIER_ROUTE_ID,PSTL_CODE_STREET_PREDIR_CODE,PSTL_CODE_STREET_NAME,PSTL_CODE_STREET_SUFFIX,PSTL_CODE_STREET_POSTDIR_CODE,PSTL_CODE_PRMY_LOW_NUM,PSTL_CODE_PRMY_HIGH_NUM,PSTL_CODE_PRMY_ODD_EVEN_CODE,PSTL_CODE_ENT_NAME,PSTL_CODE_SCNDRY_ABBRV,PSTL_CODE_SCNDRY_LOW_NUM,PSTL_CODE_SCNDRY_HIGH_NUM,PSTL_CODE_SCNDRY_ODD_EVEN_CODE,PSTL_CODE_ADD_ON_LOW_SECTOR,PSTL_CODE_ADD_ON_LOW_SEGMENT,PSTL_CODE_ADD_ON_HIGH_SECTOR,PSTL_CODE_ADD_ON_HIGH_SEGMENT,PSTL_CODE_BASE_ALTERNATE_CODE,LACS_STATUS_IND,GOV_BLDG_CODE,PSTL_CODE_FIN_NUM,PSTL_CODE_STATE_CODE,COUNTY_FIPS_CODE,CONGRNL_DISTR_NUM,MUNI_CITY_STATE_KEY,URBANIZATION_CITY_STATE_KEY,PREFD_LAST_LINE_CITY_STATE_KEY FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPstlCodeDetLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeDetLkup: insertPstlCodeDetLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePstlCodeDetLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeDetLkup: updatePstlCodeDetLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePstlCodeDetLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PstlCodeDetLkup: deletePstlCodeDetLkup: ");

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
