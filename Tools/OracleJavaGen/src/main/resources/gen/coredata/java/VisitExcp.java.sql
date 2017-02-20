CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "VisitExcp" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VisitExcp {

	private static String TABLE_NAME = "VISIT_EXCP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "VISIT_EXCP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 9;

	private static String INSERT_STATEMENT = "INSERT INTO VISIT_EXCP(VISIT_EXCP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,VISIT_SK,EXCP_ID,EXCP_CLRD_MANUAL_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getVisitExcp(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitExcp: getVisitExcp: ");

			try {

					String selectPattern = "SELECT VISIT_EXCP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,VISIT_SK,EXCP_ID,EXCP_CLRD_MANUAL_IND FROM VISIT_EXCP %s";

					String whereClause = "WHERE VISIT_SK=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getVisitExcp(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitExcp: getVisitExcp: ");

			try {

					String sql = String.format("SELECT VISIT_EXCP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,VISIT_SK,EXCP_ID,EXCP_CLRD_MANUAL_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertVisitExcp(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitExcp: insertVisitExcp: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateVisitExcp(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitExcp: updateVisitExcp: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteVisitExcp(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("VisitExcp: deleteVisitExcp: ");

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
