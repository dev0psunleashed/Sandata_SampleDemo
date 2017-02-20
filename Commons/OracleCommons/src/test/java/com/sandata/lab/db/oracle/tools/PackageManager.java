package com.sandata.lab.db.oracle.tools;

import com.sandata.lab.db.oracle.model.Column;
import com.sandata.lab.db.oracle.model.Function;
import com.sandata.lab.db.oracle.model.PLSQLScript;
import com.sandata.lab.db.oracle.model.Package;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Date: 8/19/15
 * Time: 10:25 PM
 */

public class PackageManager {

    private HashMap<String, Package> packageHashMap;

    private final Connection connection;

    private PackageManager(Connection connection) {
        this.connection = connection;
    }

    public static PackageManager getInstance(final Connection connection) {

        return new PackageManager(connection);
    }

    public void execute() throws SQLException {

        if (packageHashMap == null) {
            System.out.println("WARNING: PackageManager: packageHashMap == null!");
            return;
        }

        Iterator itr = packageHashMap.entrySet().iterator();
        while (itr.hasNext()) {

            Map.Entry pair = (Map.Entry)itr.next();
            Package packageObj = (Package)pair.getValue();

            String toPackage = packageObj.toPackage();
            Statement toPackageStatement = connection.createStatement();
            toPackageStatement.setEscapeProcessing(false);
            toPackageStatement.execute(toPackage);
            toPackageStatement.close();

            String toPackageBody = packageObj.toPackageBody();
            Statement toPackageBodyStatement = connection.createStatement();
            toPackageBodyStatement.setEscapeProcessing(false);
            toPackageBodyStatement.execute(toPackageBody);
            toPackageBodyStatement.close();

            System.out.println("---- RUN TESTS ----");

            for (Function function : packageObj.getFunctions()) {

                if (function.getTestSql() == null) {
                    System.out.println(String.format("*** TODO: [%s]: Generate an integration PLSQL Test! ***",
                            function.getMethodName()));

                    continue;
                }

                Statement statement = connection.createStatement();
                statement.setEscapeProcessing(false);

                System.out.println(String.format("TEST: [%s]: \n\nSQL: %s",
                        function.getMethodName(), function.getTestSql().getSource()));

                PLSQLScript plsqlScript = function.getTestSql();

                System.out.print(String.format("*** EXECUTING: START: [%s.%s] ***\n\n",
                        plsqlScript.getPackageName(), plsqlScript.getMethodName()));

                try {
                    ResultSet resultSet = statement.executeQuery(plsqlScript.getSource());

                    System.out.print(String.format("*** EXECUTING: FINISHED: [%s.%s] ***\n\n",
                            plsqlScript.getPackageName(), plsqlScript.getMethodName()));

                    Assert.assertNotNull(resultSet);
                    Assert.assertTrue(resultSet.next());

                    Object storedProcDataObj = resultSet.getObject(1);

                    Assert.assertNotNull(storedProcDataObj);
                    Assert.assertTrue(storedProcDataObj instanceof ResultSet);

                    ResultSet storedProcResultSet = (ResultSet) storedProcDataObj;

                    while (storedProcResultSet.next()) {

                        for (Column column : function.getTable().getColumns()) {

                            System.out.println(String.format("Column: %s: Value: %s",
                                    column.getName(),
                                    storedProcResultSet.getObject(column.getName())));
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println(String.format("*** TEST FAILED ***: %s: %s",
                            e.getClass().getName(), e.getMessage()));
                }

                statement.close();
            }

            System.out.println("---- TESTS: COMPLETE! ----");
        }
    }

    public Package createPackage(final String packageName) {

        if (packageHashMap == null) {
            packageHashMap = new HashMap<String, Package>();
        }

        Package packageObj = packageHashMap.get(packageName);

        if (packageObj == null) {

            packageObj = new Package(packageName);
            packageHashMap.put(packageName, packageObj);
        }

        return packageObj;
    }
}
