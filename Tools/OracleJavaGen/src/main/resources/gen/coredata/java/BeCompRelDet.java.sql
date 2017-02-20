CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeCompRelDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeCompRelDet {

	private static String TABLE_NAME = "BE_COMP_REL_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_COMP_REL_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO BE_COMP_REL_DET(BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeCompRelDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDet: getBeCompRelDet: ");

			try {

					String sql = String.format("SELECT BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompRelDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDet: getBeCompRelDet: ");

			try {

					String selectPattern = "SELECT BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM FROM BE_COMP_REL_DET %s";

					String whereClause = "WHERE BE_COMP_REL_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompRelDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDet: getBeCompRelDet: ");

			try {

					String sql = String.format("SELECT BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeCompRelDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDet: insertBeCompRelDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeCompRelDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDet: updateBeCompRelDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeCompRelDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDet: deleteBeCompRelDet: ");

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
