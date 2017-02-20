package com.sandata.lab.tools.oracle.main;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.factory.PackageFunctionFactory;
import com.sandata.lab.tools.oracle.factory.TypMetadataFactory;
import com.sandata.lab.tools.oracle.manager.MetadataPackageManager;
import com.sandata.lab.tools.oracle.manager.model.OraclePackage;
import com.sandata.lab.tools.oracle.model.Function;
import com.sandata.lab.tools.oracle.model.JavaCode;
import com.sandata.lab.tools.oracle.model.Package;
import com.sandata.lab.tools.oracle.model.TypMappingMetadata;
import com.sandata.lab.tools.oracle.util.PhysicalModelUtil;
import com.sandata.lab.tools.oracle.util.TableUtil;
import com.sandata.lab.util.process.MetadataJPubProcess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Generate Oracle Java Classes for Metadata Schema.
 * <p/>
 *
 * @author David Rutgos
 */
public class MetadataOracleJavaGen {

    public static void main(String[] args) throws Exception {

        Connection connection = null;

        try {
            AbbreviationUtil abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.METADATA_TYPE);

            MetadataPackageManager packageManager = MetadataPackageManager.getInstance();

            connection = packageManager.getConnection();
            connection.setAutoCommit(true);

            packageManager.setConnection(connection);

            List<OraclePackage> oraclePackages = packageManager.getOraclePackages();
            for (OraclePackage oraclePackage : oraclePackages) {

                System.out.println(String.format("Processing: Package [%s]...", oraclePackage.getPackageName()));

                for (Table table : oraclePackage.getTables()) {

                    System.out.println(String.format("\tProcessing: Table [%s]...", table.getLongName()));

                    // Drop Type if they exist
                    TableUtil.DropType(connection, String.format("%s_TYP", table.getShortName()));

                    // Creates Types SQL files under resources/gen/types folder
                    String typeStringSQL = TableUtil.CreateOrReplaceTyp(table, packageManager.getTypePath());
                    packageManager.executeSql(typeStringSQL, connection);

                    Package packageObj = packageManager.createPackageForTable(table.getLongName());

                    String logicalClassName = PhysicalModelUtil.LogicalClass(abbreviationUtil, table.getLongName());
                    TypMappingMetadata metadata = TypMetadataFactory.CreateTypMapping(packageObj, oraclePackage, table);
                    metadata.setModelClass(logicalClassName);
                    packageManager.addTypMappingMetadata(table.getLongName(), metadata);
                    JavaCode javaCode = packageManager.createOracleJavaSrc(table, metadata);

                    //JavaUtil.DropJava(connection, javaCode.getClassName());

                    String executeJava = javaCode.oracleCreate();

                    packageManager.executeSql(executeJava, connection);

                    // Create Packages

                    // GET
                    Function getPackageFunction = PackageFunctionFactory.CreateGet(table, javaCode.getClassName());
                    packageObj.addFunction(getPackageFunction);

                    // GET for PK
                    Function getPkPackageFunction = PackageFunctionFactory.CreateGetForPK(table, javaCode.getClassName());
                    packageObj.addFunction(getPkPackageFunction);

                    // GET for PK Array
                    Function getPkArrayPackageFunction = PackageFunctionFactory.CreateGetForPKArray(table, javaCode.getClassName());
                    packageObj.addFunction(getPkArrayPackageFunction);

                    // INSERT
                    Function insertPackageFunction = PackageFunctionFactory.CreateInsert(table, javaCode.getClassName());
                    packageObj.addFunction(insertPackageFunction);

                    // UPDATE
                    Function updatePackageFunction = PackageFunctionFactory.CreateUpdate(table, javaCode.getClassName());
                    packageObj.addFunction(updatePackageFunction);

                    // DELETE
                    Function deletePackageFunction = PackageFunctionFactory.CreateDelete(table, javaCode.getClassName());
                    packageObj.addFunction(deletePackageFunction);

                    // JPUB: Generate Java classes from created types...
                    // JPUB: Create Batch File / Since executable is only on Windows
                    MetadataJPubProcess jPubProcess = MetadataJPubProcess.getInstance();
                    jPubProcess.createTyp(packageManager.schemaOwner() + "." + metadata.getTypName(), metadata.getTypeClassName(), "com.sandata.lab.data.model.jpub.model");
                    //--restServicesCreator.AddExecProcess(jPubProcess.toString());
                    //dmr--jPubProcess.execute();
                }
            }

            // Create Oracle Packages and Methods
            packageManager.execute();

            // Generate Oracle Metadata Annotations
            // TODO: Need to make this into a seamless process
            //dmr--Comment this out until the latest model has been generated and JPub's exist
            //dmr--MetadataMapperExt.getInstance(packageManager.getMetadataMap()).process();

            System.out.println("MetadataOracleJavaGen: Complete!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
