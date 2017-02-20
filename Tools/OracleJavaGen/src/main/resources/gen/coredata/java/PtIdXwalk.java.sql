CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtIdXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtIdXwalk {

	private static String TABLE_NAME = "PT_ID_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_ID_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO PT_ID_XWALK(PT_ID_XWALK_SK,PT_ID_XWALK_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,PT_ID_TYP,PT_ID_QLFR,PT_ID_NUM,PT_ID_CREATING_ORG) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtIdXwalk(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtIdXwalk: getPtIdXwalk: ");

			try {

					String sql = String.format("SELECT PT_ID_XWALK_SK,PT_ID_XWALK_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,PT_ID_TYP,PT_ID_QLFR,PT_ID_NUM,PT_ID_CREATING_ORG FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtIdXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtIdXwalk: getPtIdXwalk: ");

			try {

					String selectPattern = "SELECT PT_ID_XWALK_SK,PT_ID_XWALK_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,PT_ID_TYP,PT_ID_QLFR,PT_ID_NUM,PT_ID_CREATING_ORG FROM PT_ID_XWALK %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtIdXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtIdXwalk: getPtIdXwalk: ");

			try {

					String sql = String.format("SELECT PT_ID_XWALK_SK,PT_ID_XWALK_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,PT_ID,PT_ID_TYP,PT_ID_QLFR,PT_ID_NUM,PT_ID_CREATING_ORG FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtIdXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtIdXwalk: insertPtIdXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtIdXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtIdXwalk: updatePtIdXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtIdXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtIdXwalk: deletePtIdXwalk: ");

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
