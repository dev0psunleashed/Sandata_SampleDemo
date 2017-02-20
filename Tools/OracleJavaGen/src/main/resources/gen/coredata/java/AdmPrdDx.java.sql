CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AdmPrdDx" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdmPrdDx {

	private static String TABLE_NAME = "ADM_PRD_DX";

	private static String SEQUENCE_KEY_COLUMN_NAME = "ADM_PRD_DX_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO ADM_PRD_DX(ADM_PRD_DX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ADM_PRD_SK,ICD_DX_RANK,ICD_DX_FIRST_ONSET_DATE,ICD_DX_CODE,ICD_DX_CODE_REVISION_QLFR) VALUES (?,?,?,?,?,?,?,?,?)";

	public static ResultSet getAdmPrdDx(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrdDx: getAdmPrdDx: ");

			try {

					String selectPattern = "SELECT ADM_PRD_DX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ADM_PRD_SK,ICD_DX_RANK,ICD_DX_FIRST_ONSET_DATE,ICD_DX_CODE,ICD_DX_CODE_REVISION_QLFR FROM ADM_PRD_DX %s";

					String whereClause = "WHERE ADM_PRD_SK=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getAdmPrdDx(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrdDx: getAdmPrdDx: ");

			try {

					String sql = String.format("SELECT ADM_PRD_DX_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,ADM_PRD_SK,ICD_DX_RANK,ICD_DX_FIRST_ONSET_DATE,ICD_DX_CODE,ICD_DX_CODE_REVISION_QLFR FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAdmPrdDx(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrdDx: insertAdmPrdDx: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAdmPrdDx(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrdDx: updateAdmPrdDx: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAdmPrdDx(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AdmPrdDx: deleteAdmPrdDx: ");

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
