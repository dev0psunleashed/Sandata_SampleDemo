CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED "Pt" AS 
import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Pt {

	private static String TABLE_NAME = "PT";

	private static String SEQUENCE_KEY_COLUMN_NAME = "PT_SK";

	private static int PRIMARY_KEY_INDEX = 1;

	private static int REC_TERM_TMSTP_INDEX = 6;

	private static int CURR_REC_IND_INDEX = 12;

	private static int TABLE_ID_COLUMN_INDEX = 2;

	private static int CHANGE_VERSION_ID_INDEX = 11;

	private static String INSERT_STATEMENT = "INSERT INTO PT(PT_SK,PT_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,BE_ID,TZ_NAME,PT_STATUS_NAME,PT_STATUS_CHANGE_DATE,PT_TIN_QLFR,PT_TIN,PT_MRN,PT_BE_ADM_ID,PT_MEDICARE_ID,PT_MEDICAID_ID,PT_OTHER_ID,PT_OTHER_ID_QLFR,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MRTL_STATUS_NAME,PT_DOB,PT_GENDER_TYP_NAME,PT_PREFD_CONT_MTHD,PT_PREFD_LANG_CODE,PT_PREFD_NICKNAME,NON_ENGLISH_SPEAKING_IND,PT_WRITTEN_LANG,PT_RACE_ETHNICITY_CODE,PT_RELIGION_CODE,PT_CURR_RESIDENCE_TYP,PERSONS_SHARING_LIVING_SPACE,PT_SUPPORT_NETWORK_IND,PT_EVAC_ZONE,PT_SUPPORT_ASST,PT_TAL,PT_TAL_DATE,PT_PRIO_LVL_CODE,PT_DNR_IND,PT_AD_IND,PT_DNR_DATE,PT_DOD,PT_DEATH_CTF_NUM,PT_WEIGHT,PT_WEIGHT_UOM,HOMEBOUND_IND,PT_DISCHARGE_DATE,PT_DISCHARGE_STATUS_CODE,PT_ADM_SRC_CODE,PT_ADM_TYP_CODE,PT_VV_ID,PT_VV_ID_QLFR) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static ResultSet getPt(ARRAY params) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pt: getPt: ");

			try {

					String selectPattern = "SELECT PT_SK,PT_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,BE_ID,TZ_NAME,PT_STATUS_NAME,PT_STATUS_CHANGE_DATE,PT_TIN_QLFR,PT_TIN,PT_MRN,PT_BE_ADM_ID,PT_MEDICARE_ID,PT_MEDICAID_ID,PT_OTHER_ID,PT_OTHER_ID_QLFR,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MRTL_STATUS_NAME,PT_DOB,PT_GENDER_TYP_NAME,PT_PREFD_CONT_MTHD,PT_PREFD_LANG_CODE,PT_PREFD_NICKNAME,NON_ENGLISH_SPEAKING_IND,PT_WRITTEN_LANG,PT_RACE_ETHNICITY_CODE,PT_RELIGION_CODE,PT_CURR_RESIDENCE_TYP,PERSONS_SHARING_LIVING_SPACE,PT_SUPPORT_NETWORK_IND,PT_EVAC_ZONE,PT_SUPPORT_ASST,PT_TAL,PT_TAL_DATE,PT_PRIO_LVL_CODE,PT_DNR_IND,PT_AD_IND,PT_DNR_DATE,PT_DOD,PT_DEATH_CTF_NUM,PT_WEIGHT,PT_WEIGHT_UOM,HOMEBOUND_IND,PT_DISCHARGE_DATE,PT_DISCHARGE_STATUS_CODE,PT_ADM_SRC_CODE,PT_ADM_TYP_CODE,PT_VV_ID,PT_VV_ID_QLFR FROM PT %s";

					String whereClause = "WHERE PT_ID=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

					return new OracleQueryHandler().execute(selectPattern, whereClause, params);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static ResultSet getPt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pt: getPt: ");

			try {

					String sql = String.format("SELECT PT_SK,PT_ID,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_CODE,CHANGE_REASON_MEMO,CHANGE_VERSION_ID,CURR_REC_IND,BE_ID,TZ_NAME,PT_STATUS_NAME,PT_STATUS_CHANGE_DATE,PT_TIN_QLFR,PT_TIN,PT_MRN,PT_BE_ADM_ID,PT_MEDICARE_ID,PT_MEDICAID_ID,PT_OTHER_ID,PT_OTHER_ID_QLFR,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MRTL_STATUS_NAME,PT_DOB,PT_GENDER_TYP_NAME,PT_PREFD_CONT_MTHD,PT_PREFD_LANG_CODE,PT_PREFD_NICKNAME,NON_ENGLISH_SPEAKING_IND,PT_WRITTEN_LANG,PT_RACE_ETHNICITY_CODE,PT_RELIGION_CODE,PT_CURR_RESIDENCE_TYP,PERSONS_SHARING_LIVING_SPACE,PT_SUPPORT_NETWORK_IND,PT_EVAC_ZONE,PT_SUPPORT_ASST,PT_TAL,PT_TAL_DATE,PT_PRIO_LVL_CODE,PT_DNR_IND,PT_AD_IND,PT_DNR_DATE,PT_DOD,PT_DEATH_CTF_NUM,PT_WEIGHT,PT_WEIGHT_UOM,HOMEBOUND_IND,PT_DISCHARGE_DATE,PT_DISCHARGE_STATUS_CODE,PT_ADM_SRC_CODE,PT_ADM_TYP_CODE,PT_VV_ID,PT_VV_ID_QLFR FROM %s WHERE %s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

					return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long insertPt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pt: insertPt: ");

			try {

					return new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long updatePt(STRUCT struct) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pt: updatePt: ");

			try {

					return new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);

			} catch (Exception e) {
					errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
					throw new SQLException(errLog.toString());

			}
	}

	public static long deletePt(long primaryKey) throws SQLException {

			StringBuilder errLog = new StringBuilder("Pt: deletePt: ");

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
