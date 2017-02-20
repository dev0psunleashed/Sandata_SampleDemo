CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtContAddr" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtContAddr {

	private static String TABLE_NAME = "PT_CONT_ADDR";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_CONT_ADDR_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 8;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PT_CONT_ADDR(PT_CONT_ADDR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,PT_ID,BE_ID,ADDR_PRIO_NAME,PT_ADDR_TYP_NAME,PT_ADDR_USE_FOR_MAIL_IND,PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE,PT_PSTL_CODE,PT_ZIP4,PT_COUNTY,PT_REGION,PT_BOROUGH,PT_COORD_LATITUDE,PT_COORD_LONGITUDE,PT_COORD_ALTITUDE,PT_COORD_TMSTP,PT_ADDR_POSITION_TRK_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtContAddr(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtContAddr: getPtContAddr: ");

			try {

					String selectPattern = "SELECT PT_CONT_ADDR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,PT_ID,BE_ID,ADDR_PRIO_NAME,PT_ADDR_TYP_NAME,PT_ADDR_USE_FOR_MAIL_IND,PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE,PT_PSTL_CODE,PT_ZIP4,PT_COUNTY,PT_REGION,PT_BOROUGH,PT_COORD_LATITUDE,PT_COORD_LONGITUDE,PT_COORD_ALTITUDE,PT_COORD_TMSTP,PT_ADDR_POSITION_TRK_IND FROM PT_CONT_ADDR %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtContAddr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtContAddr: getPtContAddr: ");

			try {

					String sql = String.format("SELECT PT_CONT_ADDR_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CURR_REC_IND,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,PT_ID,BE_ID,ADDR_PRIO_NAME,PT_ADDR_TYP_NAME,PT_ADDR_USE_FOR_MAIL_IND,PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE,PT_PSTL_CODE,PT_ZIP4,PT_COUNTY,PT_REGION,PT_BOROUGH,PT_COORD_LATITUDE,PT_COORD_LONGITUDE,PT_COORD_ALTITUDE,PT_COORD_TMSTP,PT_ADDR_POSITION_TRK_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtContAddr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtContAddr: insertPtContAddr: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtContAddr(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtContAddr: updatePtContAddr: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtContAddr(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtContAddr: deletePtContAddr: ");

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
