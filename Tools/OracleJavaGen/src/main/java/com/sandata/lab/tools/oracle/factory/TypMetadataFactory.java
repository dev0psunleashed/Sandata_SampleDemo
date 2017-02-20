package com.sandata.lab.tools.oracle.factory;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.manager.model.OraclePackage;
import com.sandata.lab.tools.oracle.model.Package;
import com.sandata.lab.tools.oracle.model.TypMappingMetadata;

/**
 * Date: 5/5/16
 * Time: 11:54 PM
 */

public class TypMetadataFactory {

    public static TypMappingMetadata CreateTypMapping(Package packageObj, OraclePackage oraclePackage, Table table) {

        TypMappingMetadata metadata = new TypMappingMetadata();
        metadata.setTypePackageName(oraclePackage.getJpubPackage());
        metadata.setTable(table);
        metadata.setPackageName(packageObj.getName());

        return metadata;
    }
}
