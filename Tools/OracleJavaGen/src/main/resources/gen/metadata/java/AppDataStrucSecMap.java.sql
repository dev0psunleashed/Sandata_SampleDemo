CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "AppDataStrucSecMap" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppDataStrucSecMap {

	private static String TABLE_NAME = "APP_DATA_STRUC_SEC_MAP";

	private static String SEQUENCE_KEY_COLUMN_NAME = "APP_DATA_STRUC_SEC_MAP_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO APP_DATA_STRUC_SEC_MAP(APP_DATA_STRUC_SEC_MAP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,DATA_SEC_COMP_TYP_NAME,DATA_SEC_CLAS_ID,APP_DATA_STRUC_SK,DATA_STRUC_READ_IND,DATA_STRUC_WRITE_IND) VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet getAppDataStrucSecMap(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppDataStrucSecMap: getAppDataStrucSecMap: ");

			try {

					String sql = String.format("SELECT APP_DATA_STRUC_SEC_MAP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,DATA_SEC_COMP_TYP_NAME,DATA_SEC_CLAS_ID,APP_DATA_STRUC_SK,DATA_STRUC_READ_IND,DATA_STRUC_WRITE_IND FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertAppDataStrucSecMap(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppDataStrucSecMap: insertAppDataStrucSecMap: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateAppDataStrucSecMap(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppDataStrucSecMap: updateAppDataStrucSecMap: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteAppDataStrucSecMap(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("AppDataStrucSecMap: deleteAppDataStrucSecMap: ");

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
