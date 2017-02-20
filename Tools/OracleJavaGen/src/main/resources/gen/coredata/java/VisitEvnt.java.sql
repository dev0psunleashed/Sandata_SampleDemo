CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "VisitEvnt" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VisitEvnt {

	private static String TABLE_NAME = "VISIT_EVNT";

	private static String SEQUENCE_KEY_COLUMN_NAME = "VISIT_EVNT_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO VISIT_EVNT(VISIT_EVNT_SK,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,VISIT_EVNT_TYP_CODE,VISIT_EVNT_DTIME,TZ_NAME,ANI,INFO_DIGITS,DNIS,EQUIPMENT_ID,STAFF_ID,PT_ID,COORD_LATITUDE,COORD_LONGITIDE,COORD_ACCURACY,COORD_ALTITUDE,COORD_ALTITUDE_ACCURACY,COORD_HEADING,COORD_SPEED,COORD_TMSTP,IMEI,CALL_IN_IND,CALL_OUT_IND,VISIT_EVNT_PT_CNFRM_QLFR,CHANGE_REASON_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getVisitEvnt(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitEvnt: getVisitEvnt: ");

			try {

					String selectPattern = "SELECT VISIT_EVNT_SK,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,VISIT_EVNT_TYP_CODE,VISIT_EVNT_DTIME,TZ_NAME,ANI,INFO_DIGITS,DNIS,EQUIPMENT_ID,STAFF_ID,PT_ID,COORD_LATITUDE,COORD_LONGITIDE,COORD_ACCURACY,COORD_ALTITUDE,COORD_ALTITUDE_ACCURACY,COORD_HEADING,COORD_SPEED,COORD_TMSTP,IMEI,CALL_IN_IND,CALL_OUT_IND,VISIT_EVNT_PT_CNFRM_QLFR,CHANGE_REASON_CODE FROM VISIT_EVNT %s";

					String whereClause = "WHERE VISIT_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitEvnt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitEvnt: getVisitEvnt: ");

			try {

					String sql = String.format("SELECT VISIT_EVNT_SK,VISIT_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,VISIT_EVNT_TYP_CODE,VISIT_EVNT_DTIME,TZ_NAME,ANI,INFO_DIGITS,DNIS,EQUIPMENT_ID,STAFF_ID,PT_ID,COORD_LATITUDE,COORD_LONGITIDE,COORD_ACCURACY,COORD_ALTITUDE,COORD_ALTITUDE_ACCURACY,COORD_HEADING,COORD_SPEED,COORD_TMSTP,IMEI,CALL_IN_IND,CALL_OUT_IND,VISIT_EVNT_PT_CNFRM_QLFR,CHANGE_REASON_CODE FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertVisitEvnt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitEvnt: insertVisitEvnt: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateVisitEvnt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitEvnt: updateVisitEvnt: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteVisitEvnt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitEvnt: deleteVisitEvnt: ");

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
