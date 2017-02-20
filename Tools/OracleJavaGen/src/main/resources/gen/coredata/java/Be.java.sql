CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Be" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Be {

	private static String TABLE_NAME = "BE";

	private static String SEQUENCE_KEY_COLUMN_NAME = "BE_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 10;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO BE(BE_SK,BE_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,TZ_NAME,BE_LOGO_POINTER,BE_NAME,BE_LEGAL_NAME,BE_FEDERALTAX_ID,BE_MEDICARE_ID,BE_MEDICAID_ID,OASIS_ID,BE_NPI,BE_API,BE_TAXONOMY_CODE,BE_TYP,BE_PRMY_CONT_NAME,BE_PRMY_CONT_TITLE,BE_PRMY_ADDR_USE_FOR_MAIL_IND,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY,BE_PRMY_STATE,BE_PRMY_COUNTY,BE_PRMY_PSTL_CODE,BE_PRMY_ZIP4,BE_PRMY_PHONE,BE_PRMY_PHONE_EXT,BE_PRMY_PHONE_QLFR,BE_PRMY_PHONE_1,BE_PRMY_PHONE_1_EXT,BE_PRMY_PHONE_1_QLFR,BE_PRMY_PHONE_2,BE_PRMY_PHONE_2_EXT,BE_PRMY_PHONE_2_QLFR,BE_FAX,BE_FAX_QLFR,BE_FAX_1,BE_FAX_1_QLFR,BE_PRMY_EMAIL,BE_PR_WEEK_END_DAY,BE_HOLD_BILLING_IND,BE_SPLIT_BILLING_ALWD_IND,BE_INCL_REMIT_ADDR_IND,BE_WEEKEND_START_DAY,BE_WEEKEND_START_TIME,BE_WEEKEND_END_DAY,BE_WEEKEND_END_TIME,BE_PT_ASMT_FREQ,BE_PT_SUPVY_VISIT_FREQ,BE_PR_FREQ_TYP_NAME,BE_BILLING_FREQ_TYP_NAME,BE_LI_EQUIV,BE_FIRST_PR_DATE,BE_PR_START_DAY,VV_RNDING_RULE_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getBe(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Be: getBe: ");

			try {

					String selectPattern = "SELECT BE_SK,BE_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,TZ_NAME,BE_LOGO_POINTER,BE_NAME,BE_LEGAL_NAME,BE_FEDERALTAX_ID,BE_MEDICARE_ID,BE_MEDICAID_ID,OASIS_ID,BE_NPI,BE_API,BE_TAXONOMY_CODE,BE_TYP,BE_PRMY_CONT_NAME,BE_PRMY_CONT_TITLE,BE_PRMY_ADDR_USE_FOR_MAIL_IND,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY,BE_PRMY_STATE,BE_PRMY_COUNTY,BE_PRMY_PSTL_CODE,BE_PRMY_ZIP4,BE_PRMY_PHONE,BE_PRMY_PHONE_EXT,BE_PRMY_PHONE_QLFR,BE_PRMY_PHONE_1,BE_PRMY_PHONE_1_EXT,BE_PRMY_PHONE_1_QLFR,BE_PRMY_PHONE_2,BE_PRMY_PHONE_2_EXT,BE_PRMY_PHONE_2_QLFR,BE_FAX,BE_FAX_QLFR,BE_FAX_1,BE_FAX_1_QLFR,BE_PRMY_EMAIL,BE_PR_WEEK_END_DAY,BE_HOLD_BILLING_IND,BE_SPLIT_BILLING_ALWD_IND,BE_INCL_REMIT_ADDR_IND,BE_WEEKEND_START_DAY,BE_WEEKEND_START_TIME,BE_WEEKEND_END_DAY,BE_WEEKEND_END_TIME,BE_PT_ASMT_FREQ,BE_PT_SUPVY_VISIT_FREQ,BE_PR_FREQ_TYP_NAME,BE_BILLING_FREQ_TYP_NAME,BE_LI_EQUIV,BE_FIRST_PR_DATE,BE_PR_START_DAY,VV_RNDING_RULE_ID FROM BE %s";

					String whereClause = "WHERE BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getBe(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Be: getBe: ");

			try {

					String sql = String.format("SELECT BE_SK,BE_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,TZ_NAME,BE_LOGO_POINTER,BE_NAME,BE_LEGAL_NAME,BE_FEDERALTAX_ID,BE_MEDICARE_ID,BE_MEDICAID_ID,OASIS_ID,BE_NPI,BE_API,BE_TAXONOMY_CODE,BE_TYP,BE_PRMY_CONT_NAME,BE_PRMY_CONT_TITLE,BE_PRMY_ADDR_USE_FOR_MAIL_IND,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY,BE_PRMY_STATE,BE_PRMY_COUNTY,BE_PRMY_PSTL_CODE,BE_PRMY_ZIP4,BE_PRMY_PHONE,BE_PRMY_PHONE_EXT,BE_PRMY_PHONE_QLFR,BE_PRMY_PHONE_1,BE_PRMY_PHONE_1_EXT,BE_PRMY_PHONE_1_QLFR,BE_PRMY_PHONE_2,BE_PRMY_PHONE_2_EXT,BE_PRMY_PHONE_2_QLFR,BE_FAX,BE_FAX_QLFR,BE_FAX_1,BE_FAX_1_QLFR,BE_PRMY_EMAIL,BE_PR_WEEK_END_DAY,BE_HOLD_BILLING_IND,BE_SPLIT_BILLING_ALWD_IND,BE_INCL_REMIT_ADDR_IND,BE_WEEKEND_START_DAY,BE_WEEKEND_START_TIME,BE_WEEKEND_END_DAY,BE_WEEKEND_END_TIME,BE_PT_ASMT_FREQ,BE_PT_SUPVY_VISIT_FREQ,BE_PR_FREQ_TYP_NAME,BE_BILLING_FREQ_TYP_NAME,BE_LI_EQUIV,BE_FIRST_PR_DATE,BE_PR_START_DAY,VV_RNDING_RULE_ID FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertBe(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Be: insertBe: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updateBe(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Be: updateBe: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deleteBe(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Be: deleteBe: ");

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
