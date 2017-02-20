CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AdmPrd" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdmPrd {

	private static String TABLE_NAME = "ADM_PRD";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ADM_PRD_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO ADM_PRD(ADM_PRD_SK,ADM_PRD_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PT_INTAKE_SK,ADM_PRD_EFF_DATE,ADM_PRD_TERM_DATE) VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet getAdmPrd(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrd: getAdmPrd: ");

			try {

					String selectPattern = "SELECT ADM_PRD_SK,ADM_PRD_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PT_INTAKE_SK,ADM_PRD_EFF_DATE,ADM_PRD_TERM_DATE FROM ADM_PRD %s";

					String whereClause = "WHERE PT_INTAKE_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAdmPrd(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrd: getAdmPrd: ");

			try {

					String sql = String.format("SELECT ADM_PRD_SK,ADM_PRD_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PT_INTAKE_SK,ADM_PRD_EFF_DATE,ADM_PRD_TERM_DATE FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAdmPrd(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrd: insertAdmPrd: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAdmPrd(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrd: updateAdmPrd: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAdmPrd(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrd: deleteAdmPrd: ");

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
