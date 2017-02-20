package com.sandata.lab.db.oracle.tools;

/**
 * Date: 8/19/15
 * Time: 5:53 AM
 */

public class GenerateCRUD {

    public static void main(String[] args) throws Exception {

        // LOOKUP
        //OracleTools lookupTables = new GenerateLookupTables();
        //lookupTables.generateCode();
        //lookupTables.close();

        // GENERAL
        OracleTools generateTables = new GenerateTables();
        generateTables.generateCode();
        generateTables.close();
    }
}
