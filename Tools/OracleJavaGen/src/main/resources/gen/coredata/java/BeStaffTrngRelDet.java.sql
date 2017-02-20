CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeStaffTrngRelDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeStaffTrngRelDet {

	private static String TABLE_NAME = "BE_STAFF_TRNG_REL_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_STAFF_TRNG_REL_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO BE_STAFF_TRNG_REL_DET(BE_STAFF_TRNG_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_STAFF_TRNG_REL_SK,STAFF_TRNG_RESULT_VAL,STAFF_TRNG_COMPLIANT_IND,STAFF_TRNG_SCHED_PERM_QLFR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeStaffTrngRelDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeStaffTrngRelDet: getBeStaffTrngRelDet: ");

			try {

					String sql = String.format("SELECT BE_STAFF_TRNG_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_STAFF_TRNG_REL_SK,STAFF_TRNG_RESULT_VAL,STAFF_TRNG_COMPLIANT_IND,STAFF_TRNG_SCHED_PERM_QLFR FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeStaffTrngRelDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeStaffTrngRelDet: getBeStaffTrngRelDet: ");

			try {

					String selectPattern = "SELECT BE_STAFF_TRNG_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_STAFF_TRNG_REL_SK,STAFF_TRNG_RESULT_VAL,STAFF_TRNG_COMPLIANT_IND,STAFF_TRNG_SCHED_PERM_QLFR FROM BE_STAFF_TRNG_REL_DET %s";

					String whereClause = "WHERE BE_STAFF_TRNG_REL_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeStaffTrngRelDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeStaffTrngRelDet: getBeStaffTrngRelDet: ");

			try {

					String sql = String.format("SELECT BE_STAFF_TRNG_REL_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,BE_ID,BE_STAFF_TRNG_REL_SK,STAFF_TRNG_RESULT_VAL,STAFF_TRNG_COMPLIANT_IND,STAFF_TRNG_SCHED_PERM_QLFR FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeStaffTrngRelDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeStaffTrngRelDet: insertBeStaffTrngRelDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeStaffTrngRelDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeStaffTrngRelDet: updateBeStaffTrngRelDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeStaffTrngRelDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeStaffTrngRelDet: deleteBeStaffTrngRelDet: ");

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
