import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;
import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AmDmeAndSupplyLkup {

    private static String TABLE_NAME = "DME_AND_SUPPLY_LKUP";

    private static String SEQUENCE_KEY_COLUMN_NAME = "DME_AND_SUPPLY_LKUP_SK";

    private static int PRIMARY_KEY_INDEX = 1;

    private static int REC_TERM_TMSTP_INDEX = 5;

    private static int CURR_REC_IND_INDEX = 9;

    private static int TABLE_ID_COLUMN_INDEX = 12;

    private static String INSERT_STATEMENT = "INSERT INTO DME_AND_SUPPLY_LKUP(DME_AND_SUPPLY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,DME_SUPPLY_ID,DME_SUPPLY_TYPE,DME_SUPPLY_NAME,DME_SUPPLY_DESC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static ResultSet getDmeAndSupplyLkup(ARRAY params) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmDmeAndSupplyLkup: getDmeAndSupplyLkup: ");

        try {

            String selectPattern = "SELECT DME_AND_SUPPLY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,DME_SUPPLY_ID,DME_SUPPLY_TYPE,DME_SUPPLY_NAME,DME_SUPPLY_DESC FROM DME_AND_SUPPLY_LKUP %s";

            String whereClause = "WHERE BSN_ENT_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            return new OracleQueryHandler().execute(selectPattern, whereClause, params);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static ResultSet getDmeAndSupplyLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmDmeAndSupplyLkup: getDmeAndSupplyLkup: ");

        try {

            String sql = String.format("SELECT DME_AND_SUPPLY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,DME_SUPPLY_ID,DME_SUPPLY_TYPE,DME_SUPPLY_NAME,DME_SUPPLY_DESC FROM %s WHERE %s=?", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);

            return new OracleQueryHandler().execute(sql, new Object[]{primaryKey});

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int insertDmeAndSupplyLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmDmeAndSupplyLkup: insertDmeAndSupplyLkup: ");

        try {

            return  new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int updateDmeAndSupplyLkup(STRUCT struct) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmDmeAndSupplyLkup: updateDmeAndSupplyLkup: ");

        try {

            return  new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, struct);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static int deleteDmeAndSupplyLkup(int primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmDmeAndSupplyLkup: deleteDmeAndSupplyLkup: ");

        try {

            return  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }

    public static ResultSet getDmeAndSupplyLkup(String bsnEntId) throws SQLException {

        StringBuilder errLog = new StringBuilder("AmDmeAndSupplyLkup: getDmeAndSupplyLkup: ");

        try {

            String sql = String.format("SELECT DME_AND_SUPPLY_LKUP_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BSN_ENT_ID,DME_SUPPLY_ID,DME_SUPPLY_TYPE,DME_SUPPLY_NAME,DME_SUPPLY_DESC FROM %s WHERE BSN_ENT_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", TABLE_NAME);

            return  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});

        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " + e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        }
    }
}
