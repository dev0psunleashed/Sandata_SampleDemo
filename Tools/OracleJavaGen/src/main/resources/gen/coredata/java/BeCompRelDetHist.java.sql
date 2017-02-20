CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeCompRelDetHist" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeCompRelDetHist {

	private static String TABLE_NAME = "BE_COMP_REL_DET_HIST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_COMP_REL_DET_HIST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO BE_COMP_REL_DET_HIST(BE_COMP_REL_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeCompRelDetHist(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDetHist: getBeCompRelDetHist: ");

			try {

					String sql = String.format("SELECT BE_COMP_REL_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompRelDetHist(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDetHist: getBeCompRelDetHist: ");

			try {

					String selectPattern = "SELECT BE_COMP_REL_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM FROM BE_COMP_REL_DET_HIST %s";

					String whereClause = "WHERE BE_COMP_REL_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeCompRelDetHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDetHist: getBeCompRelDetHist: ");

			try {

					String sql = String.format("SELECT BE_COMP_REL_DET_HIST_SK,REC_CREATE_TMSTP_HIST,ACTION_CODE,BE_COMP_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_COMP_REL_SK,COMP_RESULT_RDNG_VAL,COMPLIANT_IND,COMP_MAND_COMPL_THRESHOLD,COMP_STOP_RECUR_IND,COMP_MAND_COMPL_THRESHOLD_UOM FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeCompRelDetHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDetHist: insertBeCompRelDetHist: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeCompRelDetHist(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDetHist: updateBeCompRelDetHist: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeCompRelDetHist(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeCompRelDetHist: deleteBeCompRelDetHist: ");

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
