CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeCalendarLkup" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeCalendarLkup {

	private static String TABLE_NAME = "BE_CALENDAR_LKUP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_CALENDAR_LKUP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_CALENDAR_LKUP(BE_CALENDAR_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,CALENDAR_DATE,DAY_OF_WEEK,DAY_IN_YEAR,WEEKDAY_IND,WEEKEND_IND,NATIONAL_HOLIDAY_IND,STATE_HOLIDAY_IND,LOC_HOLIDAY_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeCalendarLkup() throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCalendarLkup: getBeCalendarLkup: ");

			try {

					String sql = String.format("SELECT BE_CALENDAR_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,CALENDAR_DATE,DAY_OF_WEEK,DAY_IN_YEAR,WEEKDAY_IND,WEEKEND_IND,NATIONAL_HOLIDAY_IND,STATE_HOLIDAY_IND,LOC_HOLIDAY_IND FROM %s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

					return new OracleQueryHandler().execute(sql);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCalendarLkup(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCalendarLkup: getBeCalendarLkup: ");

			try {

					String selectPattern = "SELECT BE_CALENDAR_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,CALENDAR_DATE,DAY_OF_WEEK,DAY_IN_YEAR,WEEKDAY_IND,WEEKEND_IND,NATIONAL_HOLIDAY_IND,STATE_HOLIDAY_IND,LOC_HOLIDAY_IND FROM BE_CALENDAR_LKUP %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCalendarLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCalendarLkup: getBeCalendarLkup: ");

			try {

					String sql = String.format("SELECT BE_CALENDAR_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,CALENDAR_DATE,DAY_OF_WEEK,DAY_IN_YEAR,WEEKDAY_IND,WEEKEND_IND,NATIONAL_HOLIDAY_IND,STATE_HOLIDAY_IND,LOC_HOLIDAY_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeCalendarLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCalendarLkup: insertBeCalendarLkup: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeCalendarLkup(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCalendarLkup: updateBeCalendarLkup: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeCalendarLkup(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCalendarLkup: deleteBeCalendarLkup: ");

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
