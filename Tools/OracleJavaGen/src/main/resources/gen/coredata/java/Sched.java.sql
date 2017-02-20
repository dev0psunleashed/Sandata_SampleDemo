CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Sched" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Sched {

	private static String TABLE_NAME = "SCHED";

	private static String SEQUENCE_KEY_COLUMN_NAME = "SCHED_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO SCHED(SCHED_SK,SCHED_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PAYER_SK,RFRL_SK,BE_ID,PT_ID,STAFF_ID,TZ_NAME,SCHED_START_DATE,SCHED_END_DATE,NUM_OF_OCCURENCES,SCHED_PAY_RATE,SCHED_BILL_RATE,SCHED_UNIT,SCHED_FREQ_ID,SCHED_RESTRICTIONS,SCHED_CMNT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getSched(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("Sched: getSched: ");

			try {

					String sql = String.format("SELECT SCHED_SK,SCHED_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PAYER_SK,RFRL_SK,BE_ID,PT_ID,STAFF_ID,TZ_NAME,SCHED_START_DATE,SCHED_END_DATE,NUM_OF_OCCURENCES,SCHED_PAY_RATE,SCHED_BILL_RATE,SCHED_UNIT,SCHED_FREQ_ID,SCHED_RESTRICTIONS,SCHED_CMNT FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSched(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Sched: getSched: ");

			try {

					String selectPattern = "SELECT SCHED_SK,SCHED_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PAYER_SK,RFRL_SK,BE_ID,PT_ID,STAFF_ID,TZ_NAME,SCHED_START_DATE,SCHED_END_DATE,NUM_OF_OCCURENCES,SCHED_PAY_RATE,SCHED_BILL_RATE,SCHED_UNIT,SCHED_FREQ_ID,SCHED_RESTRICTIONS,SCHED_CMNT FROM SCHED %s";

					String whereClause = "WHERE PT_ID=? AND STAFF_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getSched(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Sched: getSched: ");

			try {

					String sql = String.format("SELECT SCHED_SK,SCHED_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PAYER_SK,RFRL_SK,BE_ID,PT_ID,STAFF_ID,TZ_NAME,SCHED_START_DATE,SCHED_END_DATE,NUM_OF_OCCURENCES,SCHED_PAY_RATE,SCHED_BILL_RATE,SCHED_UNIT,SCHED_FREQ_ID,SCHED_RESTRICTIONS,SCHED_CMNT FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertSched(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Sched: insertSched: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateSched(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Sched: updateSched: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteSched(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Sched: deleteSched: ");

			try {

					return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, false);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}



}
;
/
