CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PocSvc" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PocSvc {

	private static String TABLE_NAME = "POC_SVC";

	private static String SEQUENCE_KEY_COLUMN_NAME = "POC_SVC_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO POC_SVC(POC_SVC_SK,POC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME) VALUES (?,?,?,?,?,?,?)";

	public static ResultSet getPocSvc(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocSvc: getPocSvc: ");

			try {

					String sql = String.format("SELECT POC_SVC_SK,POC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPocSvc(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocSvc: getPocSvc: ");

			try {

					String selectPattern = "SELECT POC_SVC_SK,POC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME FROM POC_SVC %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPocSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocSvc: getPocSvc: ");

			try {

					String sql = String.format("SELECT POC_SVC_SK,POC_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BE_ID,SVC_NAME FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPocSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocSvc: insertPocSvc: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePocSvc(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocSvc: updatePocSvc: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePocSvc(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PocSvc: deletePocSvc: ");

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
