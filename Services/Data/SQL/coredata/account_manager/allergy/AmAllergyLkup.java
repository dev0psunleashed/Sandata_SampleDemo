import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AmAllergyLkup {

    private static String TABLE_NAME = "ALLERGY_LKUP";

    private static String SEQUENCE_KEY_COLUMN_NAME = "ALLERGY_LKUP_SK";

    private static int PRIMARY_KEY_INDEX = 1;

    private static int REC_TERM_TMSTP_INDEX = -1;

    private static int CURR_REC_IND_INDEX = -1;

    private static int TABLE_ID_COLUMN_INDEX = 6;

    private static String INSERT_STATEMENT = "INSERT INTO ALLERGY_LKUP(ALLERGY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BSN_ENT_ID,ALLERGY_ID,ALLERGY_NAME,ALLERGY_DESC) VALUES (?,?,?,?,?,?,?,?)";

    public static ResultSet getAllergyLkup(ARRAY params) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmAllergyLkup: getAuthService: ");

        try {

            String selectPattern = "SELECT ALLERGY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BSN_ENT_ID,ALLERGY_ID,ALLERGY_NAME,ALLERGY_DESC FROM ALLERGY_LKUP %s";

            String whereClause = "WHERE BSN_ENT_ID=?";

            return new OracleQueryHandler().execute(selectPattern, whereClause, params);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static ResultSet getAllergyLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmAllergyLkup: getAllergyLkup: ");

        try {

            String sql = String.format("SELECT ALLERGY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BSN_ENT_ID,ALLERGY_ID,ALLERGY_NAME,ALLERGY_DESC FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

            return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int insertAllergyLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmAllergyLkup: insertAllergyLkup: ");

        try {

            return  new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int updateAllergyLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmAllergyLkup: updateAllergyLkup: ");

        try {

            return  new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int deleteAllergyLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmAllergyLkup: deleteAllergyLkup: ");

        try {

            return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, false);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static ResultSet getAllergyLkup(String bsnEntId) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmAllergyLkup: getAllergyLkup: ");

        try {

            String sql = String.format("SELECT ALLERGY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,CHANGE_VERSION_ID,BSN_ENT_ID,ALLERGY_ID,ALLERGY_NAME,ALLERGY_DESC FROM %s WHERE BSN_ENT_ID = ?", TABLE_NAME);

            return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
}
