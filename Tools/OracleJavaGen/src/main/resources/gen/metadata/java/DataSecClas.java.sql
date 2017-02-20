CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "DataSecClas" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataSecClas {

	private static String TABLE_NAME = "DATA_SEC_CLAS";

	private static String SEQUENCE_KEY_COLUMN_NAME = "DATA_SEC_CLAS_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 5;

	private static int CHANGE_VERSION_ID_INDEX = -1;

	private static String INSERT_STATEMENT = "INSERT INTO DATA_SEC_CLAS(DATA_SEC_CLAS_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,DATA_SEC_COMP_TYP_NAME,DATA_SEC_CLAS_ID,DATA_SEC_CLAS_DESC) VALUES (?,?,?,?,?,?)";

	public static ResultSet getDataSecClas(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("DataSecClas: getDataSecClas: ");

			try {

					String sql = String.format("SELECT DATA_SEC_CLAS_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,DATA_SEC_COMP_TYP_NAME,DATA_SEC_CLAS_ID,DATA_SEC_CLAS_DESC FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertDataSecClas(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("DataSecClas: insertDataSecClas: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateDataSecClas(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("DataSecClas: updateDataSecClas: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteDataSecClas(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("DataSecClas: deleteDataSecClas: ");

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
