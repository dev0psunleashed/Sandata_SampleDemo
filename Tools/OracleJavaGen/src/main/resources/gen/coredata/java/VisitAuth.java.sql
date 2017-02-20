CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "VisitAuth" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VisitAuth {

	private static String TABLE_NAME = "VISIT_AUTH";

	private static String SEQUENCE_KEY_COLUMN_NAME = "VISIT_AUTH_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO VISIT_AUTH(VISIT_AUTH_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,VISIT_SK,AUTH_SK,AUTH_QLFR) VALUES (?,?,?,?,?,?,?)";

	public static ResultSet getVisitAuth(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitAuth: getVisitAuth: ");

			try {

					String selectPattern = "SELECT VISIT_AUTH_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,VISIT_SK,AUTH_SK,AUTH_QLFR FROM VISIT_AUTH %s";

					String whereClause = "WHERE VISIT_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitAuth(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitAuth: getVisitAuth: ");

			try {

					String sql = String.format("SELECT VISIT_AUTH_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,VISIT_SK,AUTH_SK,AUTH_QLFR FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertVisitAuth(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitAuth: insertVisitAuth: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateVisitAuth(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitAuth: updateVisitAuth: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteVisitAuth(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitAuth: deleteVisitAuth: ");

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
