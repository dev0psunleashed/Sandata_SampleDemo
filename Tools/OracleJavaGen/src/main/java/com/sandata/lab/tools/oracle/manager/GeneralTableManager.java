package com.sandata.lab.tools.oracle.manager;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.main.OracleTools;
import com.sandata.lab.tools.oracle.model.JavaCode;

/**
 * Date: 5/4/16
 * Time: 10:27 PM
 */

public class GeneralTableManager extends OracleTools {

    public GeneralTableManager() throws Exception {
        super();
    }

    public GeneralTableManager(PackageManager packageManager) throws Exception {
        super(packageManager);
    }

    @Override
    protected int abbreviationType() {
        return AbbreviationUtil.COREDATA_TYPE;
    }

    @Override
    public void generateCode() throws Exception {

    }

    @Override
    protected JavaCode createJavaClass(Table table) throws Exception {
        return null;
    }

    @Override
    protected void setup() throws Exception {

    }

    @Override
    protected void initProperties() throws Exception {

    }

    @Override
    protected String tablePropertyResourceName() {
        return "general.tables.cfg";
    }

    public String[] getPrimaryKeyColumnsProps(final String tableName) {

        String key = "primary.keys." + tableName;

        String primaryKeyColumnsString = tableProperties.getProperty(key);
        if (primaryKeyColumnsString != null) {

            String[] primaryKeyColumns = primaryKeyColumnsString.split("\\|");
            return primaryKeyColumns;
        }

        return null;
    }

    @Override
    protected void initSandataOracleConnection() throws Exception {
    }
}
