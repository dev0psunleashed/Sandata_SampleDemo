CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "PtPayer" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PtPayer {

	private static String TABLE_NAME = "PT_PAYER";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_PAYER_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO PT_PAYER(PT_PAYER_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PAYER_RANK_NAME,SVC_NAME,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE,PT_SELF_PAY_IND,PT_GAURANTOR_IND,PT_PAYER_REL_TYP_NAME,PT_PAYER_FREQ_TYP_NAME,PT_PAYER_PMT_RESP_VAL_QLFR,PT_PAYER_PMT_RESP_VAL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPtPayer(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPayer: getPtPayer: ");

			try {

					String selectPattern = "SELECT PT_PAYER_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PAYER_RANK_NAME,SVC_NAME,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE,PT_SELF_PAY_IND,PT_GAURANTOR_IND,PT_PAYER_REL_TYP_NAME,PT_PAYER_FREQ_TYP_NAME,PT_PAYER_PMT_RESP_VAL_QLFR,PT_PAYER_PMT_RESP_VAL FROM PT_PAYER %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND PAYER_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPtPayer(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPayer: getPtPayer: ");

			try {

					String sql = String.format("SELECT PT_PAYER_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PT_ID,PAYER_ID,CONTR_ID,PAYER_RANK_NAME,SVC_NAME,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE,PT_SELF_PAY_IND,PT_GAURANTOR_IND,PT_PAYER_REL_TYP_NAME,PT_PAYER_FREQ_TYP_NAME,PT_PAYER_PMT_RESP_VAL_QLFR,PT_PAYER_PMT_RESP_VAL FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPtPayer(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPayer: insertPtPayer: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePtPayer(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPayer: updatePtPayer: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePtPayer(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("PtPayer: deletePtPayer: ");

			try {

					return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}



}
;
/
