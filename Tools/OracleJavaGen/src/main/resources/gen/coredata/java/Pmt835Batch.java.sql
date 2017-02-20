CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Pmt835Batch" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Pmt835Batch {

	private static String TABLE_NAME = "PMT_835_BATCH";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PMT_835_BATCH_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 5;

	private static String INSERT_STATEMENT = "INSERT INTO PMT_835_BATCH(PMT_835_BATCH_SK,PMT_835_BATCH_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID) VALUES (?,?,?,?,?)";

	public static ResultSet getPmt835Batch(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt835Batch: getPmt835Batch: ");

			try {

					String selectPattern = "SELECT PMT_835_BATCH_SK,PMT_835_BATCH_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID FROM PMT_835_BATCH %s";

					String whereClause = "WHERE PMT_835_BATCH_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPmt835Batch(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt835Batch: getPmt835Batch: ");

			try {

					String sql = String.format("SELECT PMT_835_BATCH_SK,PMT_835_BATCH_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPmt835Batch(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt835Batch: insertPmt835Batch: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePmt835Batch(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt835Batch: updatePmt835Batch: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePmt835Batch(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pmt835Batch: deletePmt835Batch: ");

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
