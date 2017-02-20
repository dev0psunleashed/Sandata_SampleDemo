CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PrInputTaxDet" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrInputTaxDet {

	private static String TABLE_NAME = "PR_INPUT_TAX_DET";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PR_INPUT_TAX_DET_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = -1;

	private static int CURR_REC_IND_INDEX = -1;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 4;

	private static String INSERT_STATEMENT = "INSERT INTO PR_INPUT_TAX_DET(PR_INPUT_TAX_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,TAX_CODE,TAX_AMT) VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet getPrInputTaxDet(String bsnEntId) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputTaxDet: getPrInputTaxDet: ");

			try {

					String sql = String.format("SELECT PR_INPUT_TAX_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,TAX_CODE,TAX_AMT FROM %s WHERE BE_ID = ?", TABLE_NAME);

					return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrInputTaxDet(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputTaxDet: getPrInputTaxDet: ");

			try {

					String selectPattern = "SELECT PR_INPUT_TAX_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,TAX_CODE,TAX_AMT FROM PR_INPUT_TAX_DET %s";

					String whereClause = "WHERE PR_INPUT_SK=? AND BE_ID=?";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPrInputTaxDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputTaxDet: getPrInputTaxDet: ");

			try {

					String sql = String.format("SELECT PR_INPUT_TAX_DET_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,PR_INPUT_SK,BE_ID,TAX_CODE,TAX_AMT FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPrInputTaxDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputTaxDet: insertPrInputTaxDet: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePrInputTaxDet(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputTaxDet: updatePrInputTaxDet: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePrInputTaxDet(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PrInputTaxDet: deletePrInputTaxDet: ");

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
