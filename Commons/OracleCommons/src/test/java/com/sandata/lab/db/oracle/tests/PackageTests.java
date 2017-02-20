package com.sandata.lab.db.oracle.tests;

import com.sandata.lab.db.oracle.model.Function;
import com.sandata.lab.db.oracle.model.Package;
import com.sandata.lab.db.oracle.util.FunctionUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 8/19/15
 * Time: 4:25 PM
 */

public class PackageTests {

    @Test
    public void should_create_package_plsql_body_statement_from_package_model() throws Exception {

        Package packageObj = new Package("PKG_STAFF");
        Function function = FunctionUtil.GetMethodForPK(packageObj.getName(), "STAFF_AVAIL");
        function.setClassName("StaffAvail");

        packageObj.addFunction(function);

        String toPackageBody = packageObj.toPackageBody();

        Assert.assertNotNull(toPackageBody);

        System.out.println(toPackageBody);
    }

    @Test
    public void should_create_package_plsql_statement_from_package_model() throws Exception {

        Package packageObj = new Package("PKG_STAFF");
        packageObj.addFunction(FunctionUtil.GetMethodForPK(packageObj.getName(), "STAFF_AVAIL"));

        String packagePlsql = packageObj.toPackage();

        Assert.assertNotNull(packagePlsql);

        System.out.println(packagePlsql);
    }
}
