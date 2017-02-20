CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeLvl" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeLvl {

	private static String TABLE_NAME = "BE_LVL";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_LVL_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 7;

	private static String INSERT_STATEMENT = "INSERT INTO BE_LVL(BE_LVL_SK,BE_LVL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,BE_LVL_NAME) VALUES (?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeLvl(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeLvl: getBeLvl: ");

			try {

					String sql = String.format("SELECT BE_LVL_SK,BE_LVL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,BE_LVL_NAME FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeLvl(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeLvl: getBeLvl: ");

			try {

					String selectPattern = "SELECT BE_LVL_SK,BE_LVL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,BE_LVL_NAME FROM BE_LVL %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeLvl(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeLvl: getBeLvl: ");

			try {

					String sql = String.format("SELECT BE_LVL_SK,BE_LVL_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,CHANGE_VERSION_ID,BE_ID,BE_LVL_NAME FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeLvl(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeLvl: insertBeLvl: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeLvl(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeLvl: updateBeLvl: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeLvl(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeLvl: deleteBeLvl: ");

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
