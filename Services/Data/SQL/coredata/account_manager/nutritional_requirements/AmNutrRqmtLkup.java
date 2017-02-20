import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AmNutrRqmtLkup {

    private static String TABLE_NAME = "NUTR_RQMT_LKUP";

    private static String SEQUENCE_KEY_COLUMN_NAME = "NUTR_RQMT_LKUP_SK";

    private static int PRIMARY_KEY_INDEX = 1;

    private static int REC_TERM_TMSTP_INDEX = 5;

    private static int CURR_REC_IND_INDEX = 9;

    private static int TABLE_ID_COLUMN_INDEX = -2; // Doesn't have an ID column

    private static String INSERT_STATEMENT = "INSERT INTO NUTR_RQMT_LKUP(NUTR_RQMT_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,NUTR_RQMT_NAME,NUTR_RQMT_DESC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static ResultSet getNutrRqmtLkup(ARRAY params) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmNutrRqmtLkup: getNutrRqmtLkup: ");

        try {

            String selectPattern = "SELECT NUTR_RQMT_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,NUTR_RQMT_NAME,NUTR_RQMT_DESC FROM NUTR_RQMT_LKUP %s";

            String whereClause = "WHERE BSN_ENT_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            return new OracleQueryHandler().execute(selectPattern, whereClause, params);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static ResultSet getNutrRqmtLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmNutrRqmtLkup: getNutrRqmtLkup: ");

        try {

            String sql = String.format("SELECT NUTR_RQMT_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,NUTR_RQMT_NAME,NUTR_RQMT_DESC FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

            return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int insertNutrRqmtLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmNutrRqmtLkup: insertNutrRqmtLkup: ");

        try {

            return  new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int updateNutrRqmtLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmNutrRqmtLkup: updateNutrRqmtLkup: ");

        try {

            return  new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int deleteNutrRqmtLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmNutrRqmtLkup: deleteNutrRqmtLkup: ");

        try {

            return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static ResultSet getNutrRqmtLkup(String bsnEntId) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmNutrRqmtLkup: getNutrRqmtLkup: ");

        try {

            String sql = String.format("SELECT NUTR_RQMT_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,NUTR_RQMT_NAME,NUTR_RQMT_DESC FROM %s WHERE BSN_ENT_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

            return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
}