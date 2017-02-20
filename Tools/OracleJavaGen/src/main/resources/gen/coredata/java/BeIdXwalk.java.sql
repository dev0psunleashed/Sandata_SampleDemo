CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "BeIdXwalk" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BeIdXwalk {

	private static String TABLE_NAME = "BE_ID_XWALK";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_ID_XWALK_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 8;

	private static String INSERT_STATEMENT = "INSERT INTO BE_ID_XWALK(BE_ID_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BE_ID_TYP,BE_ID_QLFR,BE_ID_NUM,BE_ID_CREATING_ORG) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBeIdXwalk(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeIdXwalk: getBeIdXwalk: ");

			try {

					String sql = String.format("SELECT BE_ID_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BE_ID_TYP,BE_ID_QLFR,BE_ID_NUM,BE_ID_CREATING_ORG FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeIdXwalk(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeIdXwalk: getBeIdXwalk: ");

			try {

					String selectPattern = "SELECT BE_ID_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BE_ID_TYP,BE_ID_QLFR,BE_ID_NUM,BE_ID_CREATING_ORG FROM BE_ID_XWALK %s";

					String whereClause = "WHERE BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBeIdXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeIdXwalk: getBeIdXwalk: ");

			try {

					String sql = String.format("SELECT BE_ID_XWALK_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_VERSION_ID,BE_ID,BE_ID_TYP,BE_ID_QLFR,BE_ID_NUM,BE_ID_CREATING_ORG FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBeIdXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeIdXwalk: insertBeIdXwalk: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBeIdXwalk(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeIdXwalk: updateBeIdXwalk: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBeIdXwalk(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("BeIdXwalk: deleteBeIdXwalk: ");

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
