CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "ContrExcpLst" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ContrExcpLst {

	private static String TABLE_NAME = "CONTR_EXCP_LST";

	private static String SEQUENCE_KEY_COLUMN_NAME = "CONTR_EXCP_LST_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 5;

	private static int CURR_REC_IND_INDEX = 9;

	private static int TABLE_ID_COLUMN_INDEX = -1;

	private static int CHANGE_VERSION_ID_INDEX = 10;

	private static String INSERT_STATEMENT = "INSERT INTO CONTR_EXCP_LST(CONTR_EXCP_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PAYER_ID,CONTR_ID,APP_MOD_NAME,EXCP_ID,EXCP_NON_FIXABLE_IND,EXCP_ACK_IND) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getContrExcpLst(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("ContrExcpLst: getContrExcpLst: ");

			try {

					String selectPattern = "SELECT CONTR_EXCP_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PAYER_ID,CONTR_ID,APP_MOD_NAME,EXCP_ID,EXCP_NON_FIXABLE_IND,EXCP_ACK_IND FROM CONTR_EXCP_LST %s";

					String whereClause = "WHERE PAYER_ID=? AND CONTR_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getContrExcpLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ContrExcpLst: getContrExcpLst: ");

			try {

					String sql = String.format("SELECT CONTR_EXCP_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,PAYER_ID,CONTR_ID,APP_MOD_NAME,EXCP_ID,EXCP_NON_FIXABLE_IND,EXCP_ACK_IND FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertContrExcpLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ContrExcpLst: insertContrExcpLst: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateContrExcpLst(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("ContrExcpLst: updateContrExcpLst: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteContrExcpLst(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("ContrExcpLst: deleteContrExcpLst: ");

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
